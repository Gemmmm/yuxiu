package com.tc.yuxiu.model;

import javax.persistence.Id;

public class TyComment {
    @Id
    private Integer commentId;

    private Integer goodsId;

    private Integer orderId;

    private Integer storeId;

    private Integer userId;

    private Integer addTime;

    private String ipAddress;

    private Short isShow;

    private String specKeyName;

    private Integer goodsRank;

    private Integer zanNum;

    private String zanUserid;

    private Integer replyNum;

    private Short isAnonymous;

    private String impression;

    private Short deleted;

    private Integer parentId;

    private Integer adminId;
    private String content;

    private String img;
//
//    private String userName;
//    private String orderSn;
//    private String goodsName;

//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getOrderSn() {
//        return orderSn;
//    }
//
//    public void setOrderSn(String orderSn) {
//        this.orderSn = orderSn;
//    }
//
//    public String getGoodsName() {
//        return goodsName;
//    }
//
//    public void setGoodsName(String goodsName) {
//        this.goodsName = goodsName;
//    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAddTime() {
        return addTime;
    }

    public void setAddTime(Integer addTime) {
        this.addTime = addTime;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress == null ? null : ipAddress.trim();
    }

    public Short getIsShow() {
        return isShow;
    }

    public void setIsShow(Short isShow) {
        this.isShow = isShow;
    }

    public String getSpecKeyName() {
        return specKeyName;
    }

    public void setSpecKeyName(String specKeyName) {
        this.specKeyName = specKeyName == null ? null : specKeyName.trim();
    }

    public Integer getGoodsRank() {
        return goodsRank;
    }

    public void setGoodsRank(Integer goodsRank) {
        this.goodsRank = goodsRank;
    }

    public Integer getZanNum() {
        return zanNum;
    }

    public void setZanNum(Integer zanNum) {
        this.zanNum = zanNum;
    }

    public String getZanUserid() {
        return zanUserid;
    }

    public void setZanUserid(String zanUserid) {
        this.zanUserid = zanUserid == null ? null : zanUserid.trim();
    }

    public Integer getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(Integer replyNum) {
        this.replyNum = replyNum;
    }

    public Short getIsAnonymous() {
        return isAnonymous;
    }

    public void setIsAnonymous(Short isAnonymous) {
        this.isAnonymous = isAnonymous;
    }

    public String getImpression() {
        return impression;
    }

    public void setImpression(String impression) {
        this.impression = impression == null ? null : impression.trim();
    }

    public Short getDeleted() {
        return deleted;
    }

    public void setDeleted(Short deleted) {
        this.deleted = deleted;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }
}