package org.mycompany;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;
import org.apache.camel.dataformat.zipfile.ZipSplitter;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.mycompany.dataformat.CustomizedMultipartDataFormat;
import org.mycompany.model.InputDataModel;
import org.mycompany.model.RHIdentity;
import org.mycompany.model.cloudforms.CloudFormAnalysis;
import org.mycompany.model.notification.FilePersistedNotification;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * A Camel Java8 DSL Router
 */
@Component
public class MainRouteBuilder extends RouteBuilder {

    public void configure() {
        getContext().setTracing(false);

        restConfiguration()
                .component("servlet")
                .contextPath("/")
                .port(8090);

        rest()
                .post("/upload/{customerid}")
                    .id("uploadAction")
                    .consumes("multipart/form-data")
                    .to("direct:upload")
                .get("/health")
                    .to("direct:health");

        from("direct:health").transform().simple("${date:now:yyyy/MM/dd-HH:mm:ss.SSS} - health=OK");

        from("direct:upload")
                .unmarshal(new CustomizedMultipartDataFormat())
                .split()
                    .attachments()
                    .process(processMultipart())
                    .choice()
                        .when(isZippedFile())
                            .split(new ZipSplitter())
                            .streaming()
                            .to("direct:store")
                        .endChoice()
                        .otherwise()
                            .to("direct:store");
        
        from("direct:store")
                .convertBodyTo(String.class)
                .to("file:./upload")
                .to("direct:insights");
        
        from("direct:insights")
                .process(exchange -> {
                    MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
                    multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
                    multipartEntityBuilder.setContentType(ContentType.MULTIPART_FORM_DATA);
                    String filename = exchange.getIn().getHeader(Exchange.FILE_NAME, String.class);
                    exchange.getIn().setHeader(Exchange.FILE_NAME, filename);

                    String file = exchange.getIn().getBody(String.class);
                    multipartEntityBuilder.addPart("upload", new ByteArrayBody(file.getBytes(), ContentType.create(getMimeTypeInsightsUploadService()), filename));
                    exchange.getIn().setBody(multipartEntityBuilder.build());
                })
                .setHeader(Exchange.HTTP_METHOD, constant(org.apache.camel.component.http4.HttpMethods.POST))
                .setHeader("x-rh-identity", method(MainRouteBuilder.class, "getRHIdentity(${header.customerid}, ${header.CamelFileName})"))
                .setHeader("x-rh-insights-request-id", constant(getRHInsightsRequestId()))
                .removeHeaders("Camel*")
                .to("http4://{{env:ma-insightsuploadhost}}/api/ingress/v1/upload?bridgeEndpoint=true")
                .end();

        from("kafka:{{env:ma-kafkahost}}?topic=platform.upload.testareno&brokers={{env:ma-kafkahost}}&autoCommitEnable=true")
                .process(exchange -> {
                    String messageKey = "";
                    if (exchange.getIn() != null) {
                        Message message = exchange.getIn();
                        Integer partitionId = (Integer) message.getHeader(KafkaConstants.PARTITION);
                        String topicName = (String) message.getHeader(KafkaConstants.TOPIC);
                        if (message.getHeader(KafkaConstants.KEY) != null)
                            messageKey = (String) message.getHeader(KafkaConstants.KEY);
                        Object data = message.getBody();

                        System.out.println("topicName :: " + topicName +
                                " partitionId :: " + partitionId +
                                " messageKey :: " + messageKey +
                                " message :: "+ data + "\n");
                    }
                })
                .unmarshal().json(JsonLibrary.Jackson, FilePersistedNotification.class)
                .to("direct:download-from-S3");


        from("direct:download-from-S3")
                .setHeader("remote_url", simple("http4://${body.url.replaceAll('http://', '')}"))
                .process( exchange -> {
                    FilePersistedNotification notif_body = exchange.getIn().getBody(FilePersistedNotification.class);
                    String identity_json = new String(Base64.getDecoder().decode(notif_body.getB64_identity()));
                    RHIdentity rhIdentity = new ObjectMapper().reader().forType(RHIdentity.class).withRootName("identity").readValue(identity_json);
                    exchange.getIn().setHeader("customerid", rhIdentity.getInternal().get("customerid"));
                    exchange.getIn().setHeader("filename", rhIdentity.getInternal().get("filename"));
                    exchange.getIn().setHeader("remote_url", exchange.getIn().getHeader("remote_url"));
                    exchange.getIn().setHeader("origin", exchange.getIn().getHeader("origin"));
                })
                .filter().method(MainRouteBuilder.class, "filterMessages")
                .setBody(constant(""))
                .recipientList(simple("${header.remote_url}"))
                .convertBodyTo(String.class)
                .to("direct:parse");

        from("direct:parse")
                .unmarshal().json(JsonLibrary.Jackson, CloudFormAnalysis.class)
                .process(exchange -> {
                    int numberofhosts = exchange.getIn().getBody(CloudFormAnalysis.class).getDatacenters()
                            .stream()
                            .flatMap(e -> e.getEmsClusters().stream())
                            .mapToInt(t -> t.getHosts().size())
                            .sum();
                    long totalspace = exchange.getIn().getBody(CloudFormAnalysis.class).getDatacenters()
                            .stream()
                            .flatMap(e-> e.getDatastores().stream())
                            .mapToLong(t -> t.getTotalSpace())
                            .sum();
                    exchange.getIn().setHeader("numberofhosts",String.valueOf(numberofhosts));
                    exchange.getIn().setHeader("totaldiskspace", String.valueOf(totalspace));
                })
                .process(exchange -> exchange.getIn().setBody(InputDataModel.builder()
                        .customerId(exchange.getIn().getHeader("customerid").toString())
                        .filename( exchange.getIn().getHeader("filename").toString())
                        .numberOfHosts(Long.parseLong(exchange.getMessage().getHeader("numberofhosts").toString()))
                        .totalDiskSpace(Long.parseLong(exchange.getMessage().getHeader("totaldiskspace").toString()))
                        .build()))
                .marshal().json()
                .log("Message to send to AMQ : ${body}")
                .to("mock:amq")
                .end();
    }
    
    public boolean filterMessages(Exchange exchange) {
        String originHeader = exchange.getIn().getHeader("origin", String.class); 
        return (originHeader != null && originHeader.equalsIgnoreCase(System.getenv("ma-origin")));
    }

    private String getMimeTypeInsightsUploadService() {
        return "application/" + System.getenv("ma-uploadmime");
    }

    private String getRHInsightsRequestId() {
        // 52df9f748eabcfea
        return UUID.randomUUID().toString();
    }

    public String getRHIdentity(String customerid, String filename) {
        // '{"identity": {"account_number": "12345", "internal": {"org_id": "54321"}}}'
        Map<String,String> internal = new HashMap<>();
        internal.put("customerid", customerid);
        internal.put("filename", filename);
        internal.put("origin", System.getenv("ma-origin"));
        String rhIdentity_json = "";
        try {
            rhIdentity_json = new ObjectMapper().writer().withRootName("identity").writeValueAsString(RHIdentity.builder()
                    .account_number(System.getenv("ma-account-number"))
                    .internal(internal)
                    .build());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println("---------- RHIdentity : " + rhIdentity_json);
        return Base64.getEncoder().encodeToString(rhIdentity_json.getBytes());
    }

    private Predicate isZippedFile() {
        return exchange -> "application/zip".equalsIgnoreCase(exchange.getMessage().getHeader("part_contenttype").toString());
    }

    private Processor processMultipart() {
        return exchange -> {
            DataHandler dataHandler = exchange.getIn().getBody(Attachment.class).getDataHandler();
            exchange.getIn().setHeader(Exchange.FILE_NAME, dataHandler.getName());
            exchange.getIn().setHeader("part_contenttype", dataHandler.getContentType());
            exchange.getIn().setHeader("part_name", dataHandler.getName());
            exchange.getIn().setBody(dataHandler.getInputStream());
        };
    }


}