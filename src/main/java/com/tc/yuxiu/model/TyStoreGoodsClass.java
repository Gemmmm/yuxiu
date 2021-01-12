package com.tc.yuxiu.model;

import javax.persistence.Id;

public class TyStoreGoodsClass {
    @Id
    private Integer catId;

    private String catName;

    private Integer parentId;

    private Integer storeId;

    private Integer catSort;

    private Short isShow;

    private Short isNavShow;

    private Short isRecommend;

    private Short showNum;

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName == null ? null : catName.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getCatSort() {
        return catSort;
    }

    public void setCatSort(Integer catSort) {
        this.catSort = catSort;
    }

    public Short getIsShow() {
        return isShow;
    }

    public void setIsShow(Short isShow) {
        this.isShow = isShow;
    }

    public Short getIsNavShow() {
        return isNavShow;
    }

    public void setIsNavShow(Short isNavShow) {
        this.isNavShow = isNavShow;
    }

    public Short getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Short isRecommend) {
        this.isRecommend = isRecommend;
    }

    public Short getShowNum() {
        return showNum;
    }

    public void setShowNum(Short showNum) {
        this.showNum = showNum;
    }
}