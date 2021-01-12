package com.tc.yuxiu.model;

import javax.persistence.Id;

public class TyCountry {
    @Id
    private Integer id;

    private String nameCn;

    private String nameEn;

    private String showCn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn == null ? null : nameCn.trim();
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn == null ? null : nameEn.trim();
    }

    public String getShowCn() {
        return showCn;
    }

    public void setShowCn(String showCn) {
        this.showCn = showCn == null ? null : showCn.trim();
    }
}