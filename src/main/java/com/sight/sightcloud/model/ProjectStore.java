package com.sight.sightcloud.model;


import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "projectStore")
public class ProjectStore {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ProjectStoreID;

    private boolean deactivateProject;

    private int servicePlanID;

    private String projectName;

    private String projectDescription;


    @OneToMany(mappedBy = "projectStore",cascade = CascadeType.ALL)
    private Set<ClassMaster> classMasterSet;


    @OneToMany(mappedBy="projectStore" , cascade = CascadeType.ALL)
    private Collection<UserManagementStore> UserManagementStoreSet;

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

    public int getServicePlanID() {
        return servicePlanID;
    }

    public void setServicePlanID(int servicePlanID) {
        this.servicePlanID = servicePlanID;
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

    public Set<ClassMaster> getClassMasterSet() {
        return classMasterSet;
    }

    public void setClassMasterSet(Set<ClassMaster> classMasterSet) {
        this.classMasterSet = classMasterSet;
    }

    public Collection<UserManagementStore> getUserManagementStoreSet() {
        return UserManagementStoreSet;
    }

    public void setUserManagementStoreSet(Collection<UserManagementStore> userManagementStoreSet) {
        UserManagementStoreSet = userManagementStoreSet;
    }
}
