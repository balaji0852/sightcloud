package com.sight.sightcloud.model;


import javax.persistence.*;


@Entity
@Table(name = "UserManagementStore")
public class UserManagementStore {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int UserManagementStoreID;

    private boolean isAdmin;

    //balaji : 2/25/2023 user management installation adding state and isInvited;
    private int state;

    private boolean isInvited;


    public int getUserManagementStoreID() {
        return UserManagementStoreID;
    }

    public void setUserManagementStoreID(int userManagementStoreID) {
        UserManagementStoreID = userManagementStoreID;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @ManyToOne
    @JoinColumn(name = "userStoreID")
    private UserStore userStore;


    @ManyToOne
    @JoinColumn(name = "projectStoreID")
    private ProjectStore projectStore;

    public ProjectStore getProjectStore() {
        return projectStore;
    }

    public void setProjectStore(ProjectStore projectStore) {
        this.projectStore = projectStore;
    }

    public UserStore getUserStore() {
        return userStore;
    }

    public void setUserStore(UserStore userStore) {
        this.userStore = userStore;
    }


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public boolean isInvited() {
        return isInvited;
    }

    public void setInvited(boolean invited) {
        isInvited = invited;
    }
}
