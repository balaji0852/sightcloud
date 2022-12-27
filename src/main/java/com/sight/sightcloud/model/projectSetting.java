package com.sight.sightcloud.model;

import javax.persistence.*;


@Entity
@Table(name = "projectSetting")
public class projectSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int projectSettingID;


    @ManyToOne
    @JoinColumn(name = "project_storeid")
    private ProjectStore projectStore;



    private boolean carryForwardMyWork;

    public int getProjectSettingID() {
        return projectSettingID;
    }

    public void setProjectSettingID(int projectSettingID) {
        this.projectSettingID = projectSettingID;
    }

    public ProjectStore getProjectStore() {
        return projectStore;
    }

    public void setProjectStore(ProjectStore projectStore) {
        this.projectStore = projectStore;
    }

    public boolean isCarryForwardMyWork() {
        return carryForwardMyWork;
    }

    public void setCarryForwardMyWork(boolean carryForwardMyWork) {
        this.carryForwardMyWork = carryForwardMyWork;
    }
}
