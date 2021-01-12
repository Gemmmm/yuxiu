package com.tc.yuxiu.model;

import javax.persistence.Id;
import java.math.BigDecimal;

public class TyOrderStatis {
    @Id
    private Integer id;

    private Integer startDate;

    private Integer endDate;

    private BigDecimal orderTotals;

    private BigDecimal shippingTotals;

    private BigDecimal returnTotals;

    private Integer returnIntegral;

    private BigDecimal commisTotals;

    private BigDecimal giveIntegral;

    private BigDecimal resultTotals;

    private Integer createDate;

    private Integer storeId;

    private BigDecimal orderPromAmount;

    private BigDecimal couponPrice;

    private BigDecimal distribut;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStartDate() {
        return startDate;
    }

    public void setStartDate(Integer startDate) {
        this.startDate = startDate;
    }

    public Integer getEndDate() {
        return endDate;
    }

    public void setEndDate(Integer endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getOrderTotals() {
        return orderTotals;
    }

    public void setOrderTotals(BigDecimal orderTotals) {
        this.orderTotals = orderTotals;
    }

    public BigDecimal getShippingTotals() {
        return shippingTotals;
    }

    public void setShippingTotals(BigDecimal shippingTotals) {
        this.shippingTotals = shippingTotals;
    }

    public BigDecimal getReturnTotals() {
        return returnTotals;
    }

    public void setReturnTotals(BigDecimal returnTotals) {
        this.returnTotals = returnTotals;
    }

    public Integer getReturnIntegral() {
        return returnIntegral;
    }

    public void setReturnIntegral(Integer returnIntegral) {
        this.returnIntegral = returnIntegral;
    }

    public BigDecimal getCommisTotals() {
        return commisTotals;
    }

    public void setCommisTotals(BigDecimal commisTotals) {
        this.commisTotals = commisTotals;
    }

    public BigDecimal getGiveIntegral() {
        return giveIntegral;
    }

    public void setGiveIntegral(BigDecimal giveIntegral) {
        this.giveIntegral = giveIntegral;
    }

    public BigDecimal getResultTotals() {
        return resultTotals;
    }

    public void setResultTotals(BigDecimal resultTotals) {
        this.resultTotals = resultTotals;
    }

    public Integer getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Integer createDate) {
        this.createDate = createDate;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public BigDecimal getOrderPromAmount() {
        return orderPromAmount;
    }

    public void setOrderPromAmount(BigDecimal orderPromAmount) {
        this.orderPromAmount = orderPromAmount;
    }

    public BigDecimal getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(BigDecimal couponPrice) {
        this.couponPrice = couponPrice;
    }

    public BigDecimal getDistribut() {
        return distribut;
    }

    public void setDistribut(BigDecimal distribut) {
        this.distribut = distribut;
    }
}