package com.tc.yuxiu.model;

import javax.persistence.Id;

public class TyGoodsType {
    @Id
    private Short id;

    private String name;

    private Short catId1;

    private Short catId2;

    private Short catId3;

    private Boolean isShow;

    private Integer typeSort;

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

    public Short getCatId1() {
        return catId1;
    }

    public void setCatId1(Short catId1) {
        this.catId1 = catId1;
    }

    public Short getCatId2() {
        return catId2;
    }

    public void setCatId2(Short catId2) {
        this.catId2 = catId2;
    }

    public Short getCatId3() {
        return catId3;
    }

    public void setCatId3(Short catId3) {
        this.catId3 = catId3;
    }

    public Boolean getIsShow() {
        return isShow;
    }

    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
    }

    public Integer getTypeSort() {
        return typeSort;
    }

    public void setTypeSort(Integer typeSort) {
        this.typeSort = typeSort;
    }
}