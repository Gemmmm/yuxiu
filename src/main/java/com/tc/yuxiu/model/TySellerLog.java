package com.tc.yuxiu.model;

import javax.persistence.Id;

public class TySellerLog {
    @Id
    private Integer logId;

    private String logContent;

    private Integer logTime;

    private Integer logSellerId;

    private String logSellerName;

    private Integer logStoreId;

    private String logSellerIp;

    private String logUrl;

    private Byte logState;

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent == null ? null : logContent.trim();
    }

    public Integer getLogTime() {
        return logTime;
    }

    public void setLogTime(Integer logTime) {
        this.logTime = logTime;
    }

    public Integer getLogSellerId() {
        return logSellerId;
    }

    public void setLogSellerId(Integer logSellerId) {
        this.logSellerId = logSellerId;
    }

    public String getLogSellerName() {
        return logSellerName;
    }

    public void setLogSellerName(String logSellerName) {
        this.logSellerName = logSellerName == null ? null : logSellerName.trim();
    }

    public Integer getLogStoreId() {
        return logStoreId;
    }

    public void setLogStoreId(Integer logStoreId) {
        this.logStoreId = logStoreId;
    }

    public String getLogSellerIp() {
        return logSellerIp;
    }

    public void setLogSellerIp(String logSellerIp) {
        this.logSellerIp = logSellerIp == null ? null : logSellerIp.trim();
    }

    public String getLogUrl() {
        return logUrl;
    }

    public void setLogUrl(String logUrl) {
        this.logUrl = logUrl == null ? null : logUrl.trim();
    }

    public Byte getLogState() {
        return logState;
    }

    public void setLogState(Byte logState) {
        this.logState = logState;
    }
}