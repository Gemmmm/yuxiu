package com.tc.yuxiu.model;

import javax.persistence.Id;

public class TyGoodsTypeContact {
    @Id
    private Integer id;

    private Integer goodsId;

    private Integer typeId;

    private Integer typeItemId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getTypeItemId() {
        return typeItemId;
    }

    public void setTypeItemId(Integer typeItemId) {
        this.typeItemId = typeItemId;
    }
}