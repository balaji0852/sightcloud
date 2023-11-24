package com.sight.sightcloud.model;


import org.apache.catalina.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "DataInstanceMaster")
public class DataInstanceMaster {


    @Id
    //@SequenceGenerator(name = "my_entity_gen", sequenceName = "my_entity_id_seq", allocationSize = 1)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_entity_gen")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int dataInstanceID;

    //@(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "item_masterid")
    private ClassMaster classMaster;

    private String dataInstances;

    private long instanceTime;

    private int instancesStatus;


    @ManyToOne
    @JoinColumn(name = "user_storeid")
    private UserStore userStore;


    public UserStore getUserStore() {
        return userStore;
    }

    public void setUserStore(UserStore userStore) {
        this.userStore = userStore;
    }

    public int getDataInstanceID() {
        return dataInstanceID;
    }

    public void setDataInstanceID(int dataInstanceID) {
        this.dataInstanceID = dataInstanceID;
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

    public long getInstanceTime() {
        return instanceTime;
    }

    public void setInstanceTime(long instanceTime) {
        this.instanceTime = instanceTime;
    }

    public int getInstancesStatus() {
        return instancesStatus;
    }

    public void setInstancesStatus(int instancesStatus) {
        this.instancesStatus = instancesStatus;
    }
}
