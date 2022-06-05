package com.sight.sightcloud.model;

public class dataInstanceMasterVO {
    private ClassMaster classMaster;

    private String dataInstances;

    private String instanceTime;

    public ClassMaster getClassMaster() {
        return classMaster;
    }

    public void setClassMaster(ClassMaster classMaster) {
        this.classMaster = classMaster;
    }

    public String getDataInstances() {
        return dataInstances;
    }

    public void setDataInstances(String dataInstances) {
        this.dataInstances = dataInstances;
    }

    public String getInstanceTime() {
        return instanceTime;
    }

    public void setInstanceTime(String instanceTime) {
        this.instanceTime = instanceTime;
    }
}
