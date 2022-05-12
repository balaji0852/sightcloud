package com.sight.sightcloud.model;

import javax.persistence.*;

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
}
