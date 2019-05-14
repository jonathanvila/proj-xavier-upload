
package org.mycompany.model.cloudforms;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "power_state",
    "vmm_product",
    "vmm_version",
    "vmm_buildnumber",
    "num_cpu",
    "cpu_total_cores",
    "cpu_cores_per_socket",
    "hyperthreading",
    "ram_size",
    "v_total_vms",
    "v_total_miq_templates"
})
public class Host {

    @JsonProperty("name")
    private String name;
    @JsonProperty("power_state")
    private String powerState;
    @JsonProperty("vmm_product")
    private String vmmProduct;
    @JsonProperty("vmm_version")
    private String vmmVersion;
    @JsonProperty("vmm_buildnumber")
    private String vmmBuildnumber;
    @JsonProperty("num_cpu")
    private Integer numCpu;
    @JsonProperty("cpu_total_cores")
    private Integer cpuTotalCores;
    @JsonProperty("cpu_cores_per_socket")
    private Integer cpuCoresPerSocket;
    @JsonProperty("hyperthreading")
    private Boolean hyperthreading;
    @JsonProperty("ram_size")
    private Integer ramSize;
    @JsonProperty("v_total_vms")
    private Integer vTotalVms;
    @JsonProperty("v_total_miq_templates")
    private Integer vTotalMiqTemplates;
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

    @JsonProperty("power_state")
    public String getPowerState() {
        return powerState;
    }

    @JsonProperty("power_state")
    public void setPowerState(String powerState) {
        this.powerState = powerState;
    }

    @JsonProperty("vmm_product")
    public String getVmmProduct() {
        return vmmProduct;
    }

    @JsonProperty("vmm_product")
    public void setVmmProduct(String vmmProduct) {
        this.vmmProduct = vmmProduct;
    }

    @JsonProperty("vmm_version")
    public String getVmmVersion() {
        return vmmVersion;
    }

    @JsonProperty("vmm_version")
    public void setVmmVersion(String vmmVersion) {
        this.vmmVersion = vmmVersion;
    }

    @JsonProperty("vmm_buildnumber")
    public String getVmmBuildnumber() {
        return vmmBuildnumber;
    }

    @JsonProperty("vmm_buildnumber")
    public void setVmmBuildnumber(String vmmBuildnumber) {
        this.vmmBuildnumber = vmmBuildnumber;
    }

    @JsonProperty("num_cpu")
    public Integer getNumCpu() {
        return numCpu;
    }

    @JsonProperty("num_cpu")
    public void setNumCpu(Integer numCpu) {
        this.numCpu = numCpu;
    }

    @JsonProperty("cpu_total_cores")
    public Integer getCpuTotalCores() {
        return cpuTotalCores;
    }

    @JsonProperty("cpu_total_cores")
    public void setCpuTotalCores(Integer cpuTotalCores) {
        this.cpuTotalCores = cpuTotalCores;
    }

    @JsonProperty("cpu_cores_per_socket")
    public Integer getCpuCoresPerSocket() {
        return cpuCoresPerSocket;
    }

    @JsonProperty("cpu_cores_per_socket")
    public void setCpuCoresPerSocket(Integer cpuCoresPerSocket) {
        this.cpuCoresPerSocket = cpuCoresPerSocket;
    }

    @JsonProperty("hyperthreading")
    public Boolean getHyperthreading() {
        return hyperthreading;
    }

    @JsonProperty("hyperthreading")
    public void setHyperthreading(Boolean hyperthreading) {
        this.hyperthreading = hyperthreading;
    }

    @JsonProperty("ram_size")
    public Integer getRamSize() {
        return ramSize;
    }

    @JsonProperty("ram_size")
    public void setRamSize(Integer ramSize) {
        this.ramSize = ramSize;
    }

    @JsonProperty("v_total_vms")
    public Integer getVTotalVms() {
        return vTotalVms;
    }

    @JsonProperty("v_total_vms")
    public void setVTotalVms(Integer vTotalVms) {
        this.vTotalVms = vTotalVms;
    }

    @JsonProperty("v_total_miq_templates")
    public Integer getVTotalMiqTemplates() {
        return vTotalMiqTemplates;
    }

    @JsonProperty("v_total_miq_templates")
    public void setVTotalMiqTemplates(Integer vTotalMiqTemplates) {
        this.vTotalMiqTemplates = vTotalMiqTemplates;
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
