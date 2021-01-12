package com.tc.yuxiu.model;

import javax.persistence.Id;

public class TyGoodsCategory {
    @Id
    private Short id;

    private String name;

    private String mobileName;

    private Short parentId;

    private String parentIdPath;

    private Short level;

    private Short sortOrder;

    private Short isShow;

    private String image;

    private String logo;

    private Short isHot;

    private Short catGroup;

    private Byte commission;

    private Short commissionRate;

    private Integer typeId;

    private String icon;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMobileName() {
        return mobileName;
    }

    public void setMobileName(String mobileName) {
        this.mobileName = mobileName == null ? null : mobileName.trim();
    }

    public Short getParentId() {
        return parentId;
    }

    public void setParentId(Short parentId) {
        this.parentId = parentId;
    }

    public String getParentIdPath() {
        return parentIdPath;
    }

    public void setParentIdPath(String parentIdPath) {
        this.parentIdPath = parentIdPath == null ? null : parentIdPath.trim();
    }

    public Short getLevel() {
        return level;
    }

    public void setLevel(Short level) {
        this.level = level;
    }

    public Short getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Short sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Short getIsShow() {
        return isShow;
    }

    public void setIsShow(Short isShow) {
        this.isShow = isShow;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public Short getIsHot() {
        return isHot;
    }

    public void setIsHot(Short isHot) {
        this.isHot = isHot;
    }

    public Short getCatGroup() {
        return catGroup;
    }

    public void setCatGroup(Short catGroup) {
        this.catGroup = catGroup;
    }

    public Byte getCommission() {
        return commission;
    }

    public void setCommission(Byte commission) {
        this.commission = commission;
    }

    public Short getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(Short commissionRate) {
        this.commissionRate = commissionRate;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }
}