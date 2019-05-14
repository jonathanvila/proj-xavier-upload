package org.mycompany.dataformat;

import org.mycompany.model.InputDataModel;
import org.apache.camel.Exchange;
import org.apache.camel.spi.DataFormat;

import java.io.InputStream;
import java.io.OutputStream;

import static org.apache.camel.builder.SimpleBuilder.simple;

public class ReportModelDataFormat implements DataFormat {
    @Override
    public void marshal(Exchange exchange, Object graph, OutputStream stream) throws Exception {
        
    }

    @Override
    public Object unmarshal(Exchange exchange, InputStream stream) throws Exception {
        InputDataModel dataModel = InputDataModel.builder().customerId(simple("${header.customerid}").getText())
                .filename(simple("${CamelFileName}").getText())
                .numberOfHosts(Integer.parseInt(simple("${header.numberofhosts").getText()))
                .totalDiskSpace(Integer.parseInt(simple("${header.totaldiskspace").getText()))
                .build();
        
        exchange.getOut().setBody(dataModel);
        return exchange.getOut();
    }
}
