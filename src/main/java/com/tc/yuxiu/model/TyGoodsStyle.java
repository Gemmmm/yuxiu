package com.tc.yuxiu.model;

import javax.persistence.Id;

public class TyGoodsStyle {
    @Id
    private Integer id;

    private String styleName;

    private Integer navigateSort;

    private Integer sort;

    private Boolean isShow;

    private String image;

    private Integer addTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName == null ? null : styleName.trim();
    }

    public Integer getNavigateSort() {
        return navigateSort;
    }

    public void setNavigateSort(Integer navigateSort) {
        this.navigateSort = navigateSort;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getIsShow() {
        return isShow;
    }

    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Integer getAddTime() {
        return addTime;
    }

    public void setAddTime(Integer addTime) {
        this.addTime = addTime;
    }
}