package com.sight.sightcloud.model;


import javax.persistence.*;

@Entity
@Table(name = "pinnedclass")
public class pinnedClass {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int pinID;


    @ManyToOne
    @JoinColumn(name = "user_storeid")
    private UserStore userStore;


    @ManyToOne
    @JoinColumn(name = "item_masterid")
    private ClassMaster classMaster;

    private boolean isPinned;

    private int folderID;


    public int getPinID() {
        return pinID;
    }

    public void setPinID(int pinID) {
        this.pinID = pinID;
    }

    public UserStore getUserStore() {
        return userStore;
    }

    public void setUserStore(UserStore userStore) {
        this.userStore = userStore;
    }

    public ClassMaster getClassMaster() {
        return classMaster;
    }

    public void setClassMaster(ClassMaster classMaster) {
        this.classMaster = classMaster;
    }

    public boolean isPinned() {
        return isPinned;
    }

    public void setPinned(boolean pinned) {
        isPinned = pinned;
    }

    public int getFolderID() {
        return folderID;
    }

    public void setFolderID(int folderID) {
        this.folderID = folderID;
    }
}
