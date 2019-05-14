
package org.mycompany.model.cloudforms;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "aggregate_physical_cpus",
    "aggregate_cpu_total_cores",
    "aggregate_cpu_speed",
    "aggregate_memory",
    "effective_cpu",
    "effective_memory",
    "aggregate_vm_cpus",
    "aggregate_vm_memory",
    "drs_enabled",
    "drs_automation_level",
    "drs_migration_threshold",
    "ha_enabled",
    "ha_admit_control",
    "ha_max_failures",
    "total_direct_vms",
    "total_direct_miq_templates",
    "v_cpu_vr_ratio",
    "v_ram_vr_ratio",
    "hosts"
})
public class EmsCluster {

    @JsonProperty("name")
    private String name;
    @JsonProperty("aggregate_physical_cpus")
    private Integer aggregatePhysicalCpus;
    @JsonProperty("aggregate_cpu_total_cores")
    private Integer aggregateCpuTotalCores;
    @JsonProperty("aggregate_cpu_speed")
    private Integer aggregateCpuSpeed;
    @JsonProperty("aggregate_memory")
    private Integer aggregateMemory;
    @JsonProperty("effective_cpu")
    private Integer effectiveCpu;
    @JsonProperty("effective_memory")
    private Integer effectiveMemory;
    @JsonProperty("aggregate_vm_cpus")
    private Integer aggregateVmCpus;
    @JsonProperty("aggregate_vm_memory")
    private Integer aggregateVmMemory;
    @JsonProperty("drs_enabled")
    private Boolean drsEnabled;
    @JsonProperty("drs_automation_level")
    private String drsAutomationLevel;
    @JsonProperty("drs_migration_threshold")
    private Integer drsMigrationThreshold;
    @JsonProperty("ha_enabled")
    private Boolean haEnabled;
    @JsonProperty("ha_admit_control")
    private Boolean haAdmitControl;
    @JsonProperty("ha_max_failures")
    private Integer haMaxFailures;
    @JsonProperty("total_direct_vms")
    private Integer totalDirectVms;
    @JsonProperty("total_direct_miq_templates")
    private Integer totalDirectMiqTemplates;
    @JsonProperty("v_cpu_vr_ratio")
    private Double vCpuVrRatio;
    @JsonProperty("v_ram_vr_ratio")
    private Double vRamVrRatio;
    @JsonProperty("hosts")
    private List<Host> hosts = null;
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

    @JsonProperty("aggregate_physical_cpus")
    public Integer getAggregatePhysicalCpus() {
        return aggregatePhysicalCpus;
    }

    @JsonProperty("aggregate_physical_cpus")
    public void setAggregatePhysicalCpus(Integer aggregatePhysicalCpus) {
        this.aggregatePhysicalCpus = aggregatePhysicalCpus;
    }

    @JsonProperty("aggregate_cpu_total_cores")
    public Integer getAggregateCpuTotalCores() {
        return aggregateCpuTotalCores;
    }

    @JsonProperty("aggregate_cpu_total_cores")
    public void setAggregateCpuTotalCores(Integer aggregateCpuTotalCores) {
        this.aggregateCpuTotalCores = aggregateCpuTotalCores;
    }

    @JsonProperty("aggregate_cpu_speed")
    public Integer getAggregateCpuSpeed() {
        return aggregateCpuSpeed;
    }

    @JsonProperty("aggregate_cpu_speed")
    public void setAggregateCpuSpeed(Integer aggregateCpuSpeed) {
        this.aggregateCpuSpeed = aggregateCpuSpeed;
    }

    @JsonProperty("aggregate_memory")
    public Integer getAggregateMemory() {
        return aggregateMemory;
    }

    @JsonProperty("aggregate_memory")
    public void setAggregateMemory(Integer aggregateMemory) {
        this.aggregateMemory = aggregateMemory;
    }

    @JsonProperty("effective_cpu")
    public Integer getEffectiveCpu() {
        return effectiveCpu;
    }

    @JsonProperty("effective_cpu")
    public void setEffectiveCpu(Integer effectiveCpu) {
        this.effectiveCpu = effectiveCpu;
    }

    @JsonProperty("effective_memory")
    public Integer getEffectiveMemory() {
        return effectiveMemory;
    }

    @JsonProperty("effective_memory")
    public void setEffectiveMemory(Integer effectiveMemory) {
        this.effectiveMemory = effectiveMemory;
    }

    @JsonProperty("aggregate_vm_cpus")
    public Integer getAggregateVmCpus() {
        return aggregateVmCpus;
    }

    @JsonProperty("aggregate_vm_cpus")
    public void setAggregateVmCpus(Integer aggregateVmCpus) {
        this.aggregateVmCpus = aggregateVmCpus;
    }

    @JsonProperty("aggregate_vm_memory")
    public Integer getAggregateVmMemory() {
        return aggregateVmMemory;
    }

    @JsonProperty("aggregate_vm_memory")
    public void setAggregateVmMemory(Integer aggregateVmMemory) {
        this.aggregateVmMemory = aggregateVmMemory;
    }

    @JsonProperty("drs_enabled")
    public Boolean getDrsEnabled() {
        return drsEnabled;
    }

    @JsonProperty("drs_enabled")
    public void setDrsEnabled(Boolean drsEnabled) {
        this.drsEnabled = drsEnabled;
    }

    @JsonProperty("drs_automation_level")
    public String getDrsAutomationLevel() {
        return drsAutomationLevel;
    }

    @JsonProperty("drs_automation_level")
    public void setDrsAutomationLevel(String drsAutomationLevel) {
        this.drsAutomationLevel = drsAutomationLevel;
    }

    @JsonProperty("drs_migration_threshold")
    public Integer getDrsMigrationThreshold() {
        return drsMigrationThreshold;
    }

    @JsonProperty("drs_migration_threshold")
    public void setDrsMigrationThreshold(Integer drsMigrationThreshold) {
        this.drsMigrationThreshold = drsMigrationThreshold;
    }

    @JsonProperty("ha_enabled")
    public Boolean getHaEnabled() {
        return haEnabled;
    }

    @JsonProperty("ha_enabled")
    public void setHaEnabled(Boolean haEnabled) {
        this.haEnabled = haEnabled;
    }

    @JsonProperty("ha_admit_control")
    public Boolean getHaAdmitControl() {
        return haAdmitControl;
    }

    @JsonProperty("ha_admit_control")
    public void setHaAdmitControl(Boolean haAdmitControl) {
        this.haAdmitControl = haAdmitControl;
    }

    @JsonProperty("ha_max_failures")
    public Integer getHaMaxFailures() {
        return haMaxFailures;
    }

    @JsonProperty("ha_max_failures")
    public void setHaMaxFailures(Integer haMaxFailures) {
        this.haMaxFailures = haMaxFailures;
    }

    @JsonProperty("total_direct_vms")
    public Integer getTotalDirectVms() {
        return totalDirectVms;
    }

    @JsonProperty("total_direct_vms")
    public void setTotalDirectVms(Integer totalDirectVms) {
        this.totalDirectVms = totalDirectVms;
    }

    @JsonProperty("total_direct_miq_templates")
    public Integer getTotalDirectMiqTemplates() {
        return totalDirectMiqTemplates;
    }

    @JsonProperty("total_direct_miq_templates")
    public void setTotalDirectMiqTemplates(Integer totalDirectMiqTemplates) {
        this.totalDirectMiqTemplates = totalDirectMiqTemplates;
    }

    @JsonProperty("v_cpu_vr_ratio")
    public Double getVCpuVrRatio() {
        return vCpuVrRatio;
    }

    @JsonProperty("v_cpu_vr_ratio")
    public void setVCpuVrRatio(Double vCpuVrRatio) {
        this.vCpuVrRatio = vCpuVrRatio;
    }

    @JsonProperty("v_ram_vr_ratio")
    public Double getVRamVrRatio() {
        return vRamVrRatio;
    }

    @JsonProperty("v_ram_vr_ratio")
    public void setVRamVrRatio(Double vRamVrRatio) {
        this.vRamVrRatio = vRamVrRatio;
    }

    @JsonProperty("hosts")
    public List<Host> getHosts() {
        return hosts;
    }

    @JsonProperty("hosts")
    public void setHosts(List<Host> hosts) {
        this.hosts = hosts;
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
