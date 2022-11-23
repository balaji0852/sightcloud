package com.sight.sightcloud.model;


import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "projectStore")
public class ProjectStore {

    //need to get the userStoreID in the body

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ProjectStoreID;

    private boolean deactivateProject;

    @ManyToOne
    @JoinColumn(name = "ServiceID")
    private ServicePlanStore servicePlanStore;

    private String projectName;

    private String projectDescription;

    public ServicePlanStore getServicePlanStore() {
        return servicePlanStore;
    }

    public void setServicePlanStore(ServicePlanStore servicePlanStore) {
        this.servicePlanStore = servicePlanStore;
    }
//    @OneToMany(mappedBy = "projectStore",cascade = CascadeType.ALL)
//    private Collection<ClassMaster> classMasterSet;
//

//    @OneToMany(mappedBy="projectStore" , cascade = CascadeType.ALL)
//    private Collection<UserManagementStore> UserManagementStoreSet;

    public int getProjectStoreID() {
        return ProjectStoreID;
    }

    public void setProjectStoreID(int projectStoreID) {
        ProjectStoreID = projectStoreID;
    }

    public boolean isDeactivateProject() {
        return deactivateProject;
    }

    public void setDeactivateProject(boolean deactivateProject) {
        this.deactivateProject = deactivateProject;
    }



    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

//    public Collection<ClassMaster> getClassMasterSet() {
//        return classMasterSet;
//    }
//
//    public void setClassMasterSet(Collection<ClassMaster> classMasterSet) {
//        this.classMasterSet = classMasterSet;
//    }
////
//    public Collection<UserManagementStore> getUserManagementStoreSet() {
//        return UserManagementStoreSet;
//    }
//
//    public void setUserManagementStoreSet(Collection<UserManagementStore> userManagementStoreSet) {
//        UserManagementStoreSet = userManagementStoreSet;
//    }
}
