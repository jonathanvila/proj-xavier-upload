
package org.mycompany.cloudforms;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "ems_clusters",
    "datastores"
})
public class Datacenter {

    @JsonProperty("name")
    private String name;
    @JsonProperty("ems_clusters")
    private List<EmsCluster> emsClusters = null;
    @JsonProperty("datastores")
    private List<Datastore> datastores = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("ems_clusters")
    public List<EmsCluster> getEmsClusters() {
        return emsClusters;
    }

    @JsonProperty("ems_clusters")
    public void setEmsClusters(List<EmsCluster> emsClusters) {
        this.emsClusters = emsClusters;
    }

    @JsonProperty("datastores")
    public List<Datastore> getDatastores() {
        return datastores;
    }

    @JsonProperty("datastores")
    public void setDatastores(List<Datastore> datastores) {
        this.datastores = datastores;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
