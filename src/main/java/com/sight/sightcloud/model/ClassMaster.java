package com.sight.sightcloud.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "classmaster")
public class ClassMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int itemMasterID;

    private String itemName;

    private int categoryID;

    private int subCategoryID;

    private int itemClassColorID;

    private int itemPriority;

    private int isItemCommentable;

    private String description;

    //balaji : 21/12: adding carryForwardMyWork bool
    private boolean carryForwardMyWork;


    public boolean isCarryForwardMyWork() {
        return carryForwardMyWork;
    }

    public void setCarryForwardMyWork(boolean carryForwardMyWork) {
        this.carryForwardMyWork = carryForwardMyWork;
    }


//    @OneToMany(mappedBy = "classMaster",cascade = CascadeType.ALL)
//    private Set<DataInstanceMaster> dataInstanceMasterSet;

    @ManyToOne
    @JoinColumn(name = "project_storeid")
    private ProjectStore projectStore;

    private long createdDate;

    @ManyToOne
    @JoinColumn(name = "user_storeid")
    private UserStore userStore;

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public UserStore getUserStore() {
        return userStore;
    }

    public void setUserStore(UserStore userStore) {
        this.userStore = userStore;
    }

    public int getItemMasterID() {
        return itemMasterID;
    }

    public void setItemMasterID(int itemMasterID) {
        this.itemMasterID = itemMasterID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getSubCategoryID() {
        return subCategoryID;
    }

    public void setSubCategoryID(int subCategoryID) {
        this.subCategoryID = subCategoryID;
    }

    public int getItemClassColorID() {
        return itemClassColorID;
    }

    public void setItemClassColorID(int itemClassColorID) {
        this.itemClassColorID = itemClassColorID;
    }

    public int getItemPriority() {
        return itemPriority;
    }

    public void setItemPriority(int itemPriority) {
        this.itemPriority = itemPriority;
    }

    public int getIsItemCommentable() {
        return isItemCommentable;
    }

    public void setIsItemCommentable(int isItemCommentable) {
        this.isItemCommentable = isItemCommentable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProjectStore getProjectStore() {
        return projectStore;
    }

    public void setProjectStore(ProjectStore projectStore) {
        this.projectStore = projectStore;
    }

}
