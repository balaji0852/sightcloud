package com.sight.sightcloud.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "DataInstanceMaster")
public class DataInstanceMaster {

    @Id
    @GeneratedValue
//    @Column(name = "dataInstanceID", unique = true, nullable = false)
    private int dataInstanceID;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "itemMasterID")
    private ClassMaster classMaster;

    private String dataInstances;

    private int instanceTime;

    public int getDataInstanceID() {
        return dataInstanceID;
    }

    public void setDataInstanceID(int i) {
        this.dataInstanceID = i;
    }

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

    public int getInstanceTime() {
        return instanceTime;
    }

    public void setInstanceTime(int instanceTime) {
        this.instanceTime = instanceTime;
    }
}
