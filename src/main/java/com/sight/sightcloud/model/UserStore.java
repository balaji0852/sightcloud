package com.sight.sightcloud.model;


import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;


@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "UserStore")
@Entity
public class UserStore {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userStoreID;

    private int dateViewPreference;

    private int timeViewPreference;

    private String linkedEmail;

    private String linkedPhone;

    private int themeID;

    private String userName;

//    @OneToMany(mappedBy="userStore" , cascade = CascadeType.ALL)
//    private Collection<UserManagementStore> UserManagementStoreSet;

    public int getUserStoreID() {
        return userStoreID;
    }

    public void setUserStoreID(int userStoreID) {
        this.userStoreID = userStoreID;
    }

    public int getDateViewPreference() {
        return dateViewPreference;
    }

    public void setDateViewPreference(int dateViewPreference) {
        this.dateViewPreference = dateViewPreference;
    }

    public int getTimeViewPreference() {
        return timeViewPreference;
    }

    public void setTimeViewPreference(int timeViewPreference) {
        this.timeViewPreference = timeViewPreference;
    }

    public String getLinkedEmail() {
        return linkedEmail;
    }

    public void setLinkedEmail(String linkedEmail) {
        this.linkedEmail = linkedEmail;
    }

    public String getLinkedPhone() {
        return linkedPhone;
    }

    public void setLinkedPhone(String linkedPhone) {
        this.linkedPhone = linkedPhone;
    }

    public int getThemeID() {
        return themeID;
    }

    public void setThemeID(int themeID) {
        this.themeID = themeID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

//    public Collection<UserManagementStore> getUserManagementStoreSet() {
//        return UserManagementStoreSet;
//    }
//
//    public void setUserManagementStoreSet(Collection<UserManagementStore> userManagementStoreSet) {
//        UserManagementStoreSet = userManagementStoreSet;
//    }
}
