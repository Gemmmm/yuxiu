package com.tc.yuxiu.model;

import javax.persistence.Id;

public class TyOrderAction {
    @Id
    private Integer actionId;

    private Integer orderId;

    private Integer actionUser;

    private Short orderStatus;

    private Short shippingStatus;

    private Short payStatus;

    private String actionNote;

    private Integer logTime;

    private String statusDesc;

    private Short userType;

    private Integer storeId;

    public Integer getActionId() {
        return actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getActionUser() {
        return actionUser;
    }

    public void setActionUser(Integer actionUser) {
        this.actionUser = actionUser;
    }

    public Short getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Short orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Short getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(Short shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public Short getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Short payStatus) {
        this.payStatus = payStatus;
    }

    public String getActionNote() {
        return actionNote;
    }

    public void setActionNote(String actionNote) {
        this.actionNote = actionNote == null ? null : actionNote.trim();
    }

    public Integer getLogTime() {
        return logTime;
    }

    public void setLogTime(Integer logTime) {
        this.logTime = logTime;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc == null ? null : statusDesc.trim();
    }

    public Short getUserType() {
        return userType;
    }

    public void setUserType(Short userType) {
        this.userType = userType;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }
}