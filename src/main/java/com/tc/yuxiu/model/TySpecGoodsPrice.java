package com.tc.yuxiu.model;

import javax.persistence.Id;
import java.math.BigDecimal;

public class TySpecGoodsPrice {
    @Override
    public String toString() {
        return "TySpecGoodsPrice{" +
                "sgpId=" + sgpId +
                ", goodsId=" + goodsId +
                ", keyValue='" + keyValue + '\'' +
                ", keyName='" + keyName + '\'' +
                ", price=" + price +
                ", storeCount=" + storeCount +
                '}';
    }

    @Id
    private Integer sgpId;

    private Integer goodsId;

    private String keyValue;

    private String keyName;

    private BigDecimal price;

    private BigDecimal partnerPrice;

    private Integer storeCount;

    private String barCode;

    private String sku;

    private Integer storeId;

    private BigDecimal costPrice;

    public Integer getSgpId() {
        return sgpId;
    }

    public void setSgpId(Integer sgpId) {
        this.sgpId = sgpId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue == null ? null : keyValue.trim();
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName == null ? null : keyName.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPartnerPrice() {
        return partnerPrice;
    }

    public void setPartnerPrice(BigDecimal partnerPrice) {
        this.partnerPrice = partnerPrice;
    }

    public Integer getStoreCount() {
        return storeCount;
    }

    public void setStoreCount(Integer storeCount) {
        this.storeCount = storeCount;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode == null ? null : barCode.trim();
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku == null ? null : sku.trim();
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }
}