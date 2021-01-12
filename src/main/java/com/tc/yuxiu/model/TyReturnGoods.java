package com.tc.yuxiu.model;

import javax.persistence.Id;
import java.math.BigDecimal;

public class TyReturnGoods {
    @Id
    private Integer id;

    private Integer orderId;

    private String orderSn;

    private Integer goodsId;

    private Short type;

    private Short isDelivery;

    private String reason;

    private String imgs;

    private Integer addtime;

    private Short status;

    private String remark;

    private Integer userId;

    private Integer storeId;

    private String specKey;

    private String content;

    private BigDecimal returnMoney;

    private Integer goodsSum;

    private String shippingName;

    private String invoiceNo;

    private Integer returnAddressId;

    private BigDecimal agreeReturnMoney;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn == null ? null : orderSn.trim();
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Short getIsDelivery() {
        return isDelivery;
    }

    public void setIsDelivery(Short isDelivery) {
        this.isDelivery = isDelivery;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs == null ? null : imgs.trim();
    }

    public Integer getAddtime() {
        return addtime;
    }

    public void setAddtime(Integer addtime) {
        this.addtime = addtime;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getSpecKey() {
        return specKey;
    }

    public void setSpecKey(String specKey) {
        this.specKey = specKey == null ? null : specKey.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public BigDecimal getReturnMoney() {
        return returnMoney;
    }

    public void setReturnMoney(BigDecimal returnMoney) {
        this.returnMoney = returnMoney;
    }

    public Integer getGoodsSum() {
        return goodsSum;
    }

    public void setGoodsSum(Integer goodsSum) {
        this.goodsSum = goodsSum;
    }

    public String getShippingName() {
        return shippingName;
    }

    public void setShippingName(String shippingName) {
        this.shippingName = shippingName == null ? null : shippingName.trim();
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo == null ? null : invoiceNo.trim();
    }

    public Integer getReturnAddressId() {
        return returnAddressId;
    }

    public void setReturnAddressId(Integer returnAddressId) {
        this.returnAddressId = returnAddressId;
    }

    public BigDecimal getAgreeReturnMoney() {
        return agreeReturnMoney;
    }

    public void setAgreeReturnMoney(BigDecimal agreeReturnMoney) {
        this.agreeReturnMoney = agreeReturnMoney;
    }
}