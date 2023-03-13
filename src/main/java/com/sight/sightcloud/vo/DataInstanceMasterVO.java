package com.sight.sightcloud.vo;

import com.sight.sightcloud.model.ClassMaster;
import com.sight.sightcloud.model.DataInstanceMaster;
import com.sight.sightcloud.model.UserStore;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class DataInstanceMasterVO implements Comparable<DataInstanceMasterVO> {
    private int dataInstanceID;


    private ClassMaster classMaster;

    private String dataInstances;

    private long instanceTime;

    private int instancesStatus;

    private boolean isPinnedForCurrentUser;

    private int directoryid;

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

    public boolean isPinnedForCurrentUser() {
        return isPinnedForCurrentUser;
    }

    public void setPinnedForCurrentUser(boolean pinnedForCurrentUser) {
        isPinnedForCurrentUser = pinnedForCurrentUser;
    }

    public int getDirectoryid() {
        return directoryid;
    }

    public void setDirectoryid(int directoryid) {
        this.directoryid = directoryid;
    }

    public void setDataInstanceMaster(DataInstanceMaster dataInstanceMaster,boolean isPinnedForCurrentUser){
        dataInstanceID = dataInstanceMaster.getDataInstanceID();
        dataInstances = dataInstanceMaster.getDataInstances();
        instanceTime = dataInstanceMaster.getInstanceTime();
        instancesStatus = dataInstanceMaster.getInstancesStatus();
        this.isPinnedForCurrentUser = isPinnedForCurrentUser;
        directoryid = 1;
        userStore = dataInstanceMaster.getUserStore();
        classMaster = dataInstanceMaster.getClassMaster();
    }

    @Override
    public int compareTo(DataInstanceMasterVO dataInstanceMasterVO) {
        return (int) (dataInstanceMasterVO.getInstanceTime()-instanceTime);
    }
}
