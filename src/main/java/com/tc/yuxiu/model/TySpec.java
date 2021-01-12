package com.tc.yuxiu.model;

import javax.persistence.Id;

public class TySpec {
    @Override
    public String toString() {
        return "TySpec{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sort=" + sort +
                ", searchIndex=" + searchIndex +
                ", catId1=" + catId1 +
                ", catId2=" + catId2 +
                ", catId3=" + catId3 +
                ", aliasName='" + aliasName + '\'' +
                ", storeId=" + storeId +
                '}';
    }

    @Id
    private Integer id;

    private String name;

    private Integer sort;

    private Boolean searchIndex;

    private Integer catId1;

    private Integer catId2;

    private Integer catId3;

    private String aliasName;

    private Integer storeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getSearchIndex() {
        return searchIndex;
    }

    public void setSearchIndex(Boolean searchIndex) {
        this.searchIndex = searchIndex;
    }

    public Integer getCatId1() {
        return catId1;
    }

    public void setCatId1(Integer catId1) {
        this.catId1 = catId1;
    }

    public Integer getCatId2() {
        return catId2;
    }

    public void setCatId2(Integer catId2) {
        this.catId2 = catId2;
    }

    public Integer getCatId3() {
        return catId3;
    }

    public void setCatId3(Integer catId3) {
        this.catId3 = catId3;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName == null ? null : aliasName.trim();
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }
}