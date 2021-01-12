package com.tc.yuxiu.model;

import javax.persistence.Id;
import java.math.BigDecimal;

public class TyOrderGoods {
    @Id
    private Integer recId;

    private Integer orderId;

    private Integer goodsId;

    private String goodsName;

    private String goodsSn;

    private Short goodsNum;

    private BigDecimal marketPrice;

    private BigDecimal goodsPrice;

    private BigDecimal costPrice;

    private BigDecimal memberGoodsPrice;

    private Integer giveIntegral;

    private String specKey;

    private String specKeyName;

    private String barCode;

    private Short isComment;

    private Short promType;

    private Integer promId;

    private Short isSend;

    private Integer deliveryId;

    private String sku;

    private Integer storeId;

    private Short commission;

    private Short isCheckout;

    private Short deleted;

    private BigDecimal distribut;

    private Short type;

    private Integer suppliersId;

    private Integer activityId;

    private Integer activityType;

    private BigDecimal userMoney;

    private BigDecimal couponPrice;

    private Integer integral;

    private BigDecimal integralMoney;

    private String originalImg;

    private BigDecimal reallyPrice;

    private BigDecimal discountFee;

    private BigDecimal rebateFee;

    public Integer getRecId() {
        return recId;
    }

    public void setRecId(Integer recId) {
        this.recId = recId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getGoodsSn() {
        return goodsSn;
    }

    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn == null ? null : goodsSn.trim();
    }

    public Short getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Short goodsNum) {
        this.goodsNum = goodsNum;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public BigDecimal getMemberGoodsPrice() {
        return memberGoodsPrice;
    }

    public void setMemberGoodsPrice(BigDecimal memberGoodsPrice) {
        this.memberGoodsPrice = memberGoodsPrice;
    }

    public Integer getGiveIntegral() {
        return giveIntegral;
    }

    public void setGiveIntegral(Integer giveIntegral) {
        this.giveIntegral = giveIntegral;
    }

    public String getSpecKey() {
        return specKey;
    }

    public void setSpecKey(String specKey) {
        this.specKey = specKey == null ? null : specKey.trim();
    }

    public String getSpecKeyName() {
        return specKeyName;
    }

    public void setSpecKeyName(String specKeyName) {
        this.specKeyName = specKeyName == null ? null : specKeyName.trim();
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode == null ? null : barCode.trim();
    }

    public Short getIsComment() {
        return isComment;
    }

    public void setIsComment(Short isComment) {
        this.isComment = isComment;
    }

    public Short getPromType() {
        return promType;
    }

    public void setPromType(Short promType) {
        this.promType = promType;
    }

    public Integer getPromId() {
        return promId;
    }

    public void setPromId(Integer promId) {
        this.promId = promId;
    }

    public Short getIsSend() {
        return isSend;
    }

    public void setIsSend(Short isSend) {
        this.isSend = isSend;
    }

    public Integer getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Integer deliveryId) {
        this.deliveryId = deliveryId;
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

    public Short getCommission() {
        return commission;
    }

    public void setCommission(Short commission) {
        this.commission = commission;
    }

    public Short getIsCheckout() {
        return isCheckout;
    }

    public void setIsCheckout(Short isCheckout) {
        this.isCheckout = isCheckout;
    }

    public Short getDeleted() {
        return deleted;
    }

    public void setDeleted(Short deleted) {
        this.deleted = deleted;
    }

    public BigDecimal getDistribut() {
        return distribut;
    }

    public void setDistribut(BigDecimal distribut) {
        this.distribut = distribut;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Integer getSuppliersId() {
        return suppliersId;
    }

    public void setSuppliersId(Integer suppliersId) {
        this.suppliersId = suppliersId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getActivityType() {
        return activityType;
    }

    public void setActivityType(Integer activityType) {
        this.activityType = activityType;
    }

    public BigDecimal getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(BigDecimal userMoney) {
        this.userMoney = userMoney;
    }

    public BigDecimal getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(BigDecimal couponPrice) {
        this.couponPrice = couponPrice;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public BigDecimal getIntegralMoney() {
        return integralMoney;
    }

    public void setIntegralMoney(BigDecimal integralMoney) {
        this.integralMoney = integralMoney;
    }

    public String getOriginalImg() {
        return originalImg;
    }

    public void setOriginalImg(String originalImg) {
        this.originalImg = originalImg == null ? null : originalImg.trim();
    }

    public BigDecimal getReallyPrice() {
        return reallyPrice;
    }

    public void setReallyPrice(BigDecimal reallyPrice) {
        this.reallyPrice = reallyPrice;
    }

    public BigDecimal getDiscountFee() {
        return discountFee;
    }

    public void setDiscountFee(BigDecimal discountFee) {
        this.discountFee = discountFee;
    }

    public BigDecimal getRebateFee() {
        return rebateFee;
    }

    public void setRebateFee(BigDecimal rebateFee) {
        this.rebateFee = rebateFee;
    }
}