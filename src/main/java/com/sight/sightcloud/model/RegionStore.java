package com.sight.sightcloud.model;

import javax.persistence.*;
import java.util.Set;

@Table(name = "RegionStore")
@Entity
public class RegionStore {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int regionID;

    @Column(length=25)
    private String regionName;

    @Column(length = 2000)
    private String  regionDescription;

    @Column(length = 50)
    private String server;



//    @OneToMany(mappedBy = "RegionStore", cascade = CascadeType.ALL)
//    private Set<ServicePlanStore> ServicePlanStoreSet;

    public int getRegionID() {
        return regionID;
    }

    public void setRegionID(int regionID) {
        this.regionID = regionID;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionDescription() {
        return regionDescription;
    }

    public void setRegionDescription(String regionDescription) {
        this.regionDescription = regionDescription;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }
}
