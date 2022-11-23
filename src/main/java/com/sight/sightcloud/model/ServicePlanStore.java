package com.sight.sightcloud.model;


import javax.persistence.*;

@Table(name = "ServicePlanStore")
@Entity
public class ServicePlanStore {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ServiceID;

    @Column(length = 25)
    private String ServiceName;

    @Column(length = 2000)
    private String ServiceDescription;

//    private int RegionID;
    @ManyToOne
    @JoinColumn(name = "regionID")
    private RegionStore regionStore;

    public RegionStore getRegionStore() {
        return regionStore;
    }

    public void setRegionStore(RegionStore regionStore) {
        this.regionStore = regionStore;
    }

    public int getServiceID() {
        return ServiceID;
    }

    public void setServiceID(int serviceID) {
        ServiceID = serviceID;
    }

    public String getServiceName() {
        return ServiceName;
    }

    public void setServiceName(String serviceName) {
        ServiceName = serviceName;
    }

    public String getServiceDescription() {
        return ServiceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        ServiceDescription = serviceDescription;
    }


}
