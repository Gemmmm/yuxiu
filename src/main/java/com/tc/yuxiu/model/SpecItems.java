package com.tc.yuxiu.model;

import com.alibaba.fastjson.JSONArray;

import java.util.List;
import java.util.Map;

public class SpecItems {
    @Override
    public String toString() {
        return "SpecItems{" +
                "goodsId=" + goodsId +
                ", token='" + token + '\'' +
                ", specItemsIdses=" + specItemsIdses +
                '}';
    }

    private Integer goodsId;

    private String token;

    private List<Map<String,Object>> specItemsIdses;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Map<String, Object>> getSpecItemsIdses() {
        return specItemsIdses;
    }

    public void setSpecItemsIdses(List<Map<String, Object>> specItemsIdses) {
        this.specItemsIdses = specItemsIdses;
    }
}
