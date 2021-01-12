package com.tc.yuxiu.model;

import javax.persistence.Id;
import java.math.BigDecimal;

public class TyOrder {
    @Id
    private Integer orderId;

    private String orderSn;

    private String masterOrderSn;

    private String outTradeNo;

    private String transactionId;

    private Integer userId;

    private Integer userShareId;

    private Integer productId;

    private Integer orderStatus;

    private Integer shippingStatus;

    private Integer payStatus;

    private String consignee;

    private Integer country;

    private Integer province;

    private Integer city;

    private Integer district;

    private Integer twon;

    private String address;

    private String zipcode;

    private String mobile;

    private String email;

    private String shippingCode;

    private String shippingName;

    private String payCode;

    private String payName;

    private String invoiceTitle;

    private String invoiceTax;

    private String invoiceAddress;

    private String invoiceMobile;

    private String invoiceBank;

    private Integer invoiceCardid;

    private BigDecimal goodsPrice;

    private BigDecimal shippingPrice;

    private BigDecimal userMoney;

    private BigDecimal couponPrice;

    private Integer integral;

    private BigDecimal integralMoney;

    private BigDecimal orderAmount;

    private BigDecimal totalAmount;

    private BigDecimal payPrice;

    private Integer addTime;

    private Integer confirmTime;

    private Integer payTime;

    private Integer shippingTime;

    private Short orderPromId;

    private BigDecimal orderPromAmount;

    private BigDecimal discount;

    private String userNote;

    private String adminNote;

    private String parentSn;

    private Integer storeId;

    private Integer isComment;

    private Integer deleted;

    private Integer isCheckout;

    private Integer type;

    private Integer shareUserId;

    private BigDecimal shareCouponPrice;

    private BigDecimal shareOriginalPrice;

    private BigDecimal shareDiscount;

    private Integer suppliersId;

    private Integer promId;

    private BigDecimal giveSuppliers;

    private BigDecimal giveAgent;

    private Integer orderGoodsStatus;

    private BigDecimal discountFee;

    private BigDecimal rebateFee;

    private Integer isCheck;

    private Integer isPickUp;

    private String checkCode;

    private Integer pickUpTime;

    private String pickUpDay;

    private Integer doorId;

    private String orderGoodsMsg;

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

    public String getMasterOrderSn() {
        return masterOrderSn;
    }

    public void setMasterOrderSn(String masterOrderSn) {
        this.masterOrderSn = masterOrderSn == null ? null : masterOrderSn.trim();
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo == null ? null : outTradeNo.trim();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId == null ? null : transactionId.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserShareId() {
        return userShareId;
    }

    public void setUserShareId(Integer userShareId) {
        this.userShareId = userShareId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(Integer shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee == null ? null : consignee.trim();
    }

    public Integer getCountry() {
        return country;
    }

    public void setCountry(Integer country) {
        this.country = country;
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getDistrict() {
        return district;
    }

    public void setDistrict(Integer district) {
        this.district = district;
    }

    public Integer getTwon() {
        return twon;
    }

    public void setTwon(Integer twon) {
        this.twon = twon;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode == null ? null : zipcode.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getShippingCode() {
        return shippingCode;
    }

    public void setShippingCode(String shippingCode) {
        this.shippingCode = shippingCode == null ? null : shippingCode.trim();
    }

    public String getShippingName() {
        return shippingName;
    }

    public void setShippingName(String shippingName) {
        this.shippingName = shippingName == null ? null : shippingName.trim();
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode == null ? null : payCode.trim();
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName == null ? null : payName.trim();
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle == null ? null : invoiceTitle.trim();
    }

    public String getInvoiceTax() {
        return invoiceTax;
    }

    public void setInvoiceTax(String invoiceTax) {
        this.invoiceTax = invoiceTax == null ? null : invoiceTax.trim();
    }

    public String getInvoiceAddress() {
        return invoiceAddress;
    }

    public void setInvoiceAddress(String invoiceAddress) {
        this.invoiceAddress = invoiceAddress == null ? null : invoiceAddress.trim();
    }

    public String getInvoiceMobile() {
        return invoiceMobile;
    }

    public void setInvoiceMobile(String invoiceMobile) {
        this.invoiceMobile = invoiceMobile == null ? null : invoiceMobile.trim();
    }

    public String getInvoiceBank() {
        return invoiceBank;
    }

    public void setInvoiceBank(String invoiceBank) {
        this.invoiceBank = invoiceBank == null ? null : invoiceBank.trim();
    }

    public Integer getInvoiceCardid() {
        return invoiceCardid;
    }

    public void setInvoiceCardid(Integer invoiceCardid) {
        this.invoiceCardid = invoiceCardid;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public BigDecimal getShippingPrice() {
        return shippingPrice;
    }

    public void setShippingPrice(BigDecimal shippingPrice) {
        this.shippingPrice = shippingPrice;
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

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(BigDecimal payPrice) {
        this.payPrice = payPrice;
    }

    public Integer getAddTime() {
        return addTime;
    }

    public void setAddTime(Integer addTime) {
        this.addTime = addTime;
    }

    public Integer getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Integer confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Integer getPayTime() {
        return payTime;
    }

    public void setPayTime(Integer payTime) {
        this.payTime = payTime;
    }

    public Integer getShippingTime() {
        return shippingTime;
    }

    public void setShippingTime(Integer shippingTime) {
        this.shippingTime = shippingTime;
    }

    public Short getOrderPromId() {
        return orderPromId;
    }

    public void setOrderPromId(Short orderPromId) {
        this.orderPromId = orderPromId;
    }

    public BigDecimal getOrderPromAmount() {
        return orderPromAmount;
    }

    public void setOrderPromAmount(BigDecimal orderPromAmount) {
        this.orderPromAmount = orderPromAmount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getUserNote() {
        return userNote;
    }

    public void setUserNote(String userNote) {
        this.userNote = userNote == null ? null : userNote.trim();
    }

    public String getAdminNote() {
        return adminNote;
    }

    public void setAdminNote(String adminNote) {
        this.adminNote = adminNote == null ? null : adminNote.trim();
    }

    public String getParentSn() {
        return parentSn;
    }

    public void setParentSn(String parentSn) {
        this.parentSn = parentSn == null ? null : parentSn.trim();
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getIsComment() {
        return isComment;
    }

    public void setIsComment(Integer isComment) {
        this.isComment = isComment;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Integer getIsCheckout() {
        return isCheckout;
    }

    public void setIsCheckout(Integer isCheckout) {
        this.isCheckout = isCheckout;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getShareUserId() {
        return shareUserId;
    }

    public void setShareUserId(Integer shareUserId) {
        this.shareUserId = shareUserId;
    }

    public BigDecimal getShareCouponPrice() {
        return shareCouponPrice;
    }

    public void setShareCouponPrice(BigDecimal shareCouponPrice) {
        this.shareCouponPrice = shareCouponPrice;
    }

    public BigDecimal getShareOriginalPrice() {
        return shareOriginalPrice;
    }

    public void setShareOriginalPrice(BigDecimal shareOriginalPrice) {
        this.shareOriginalPrice = shareOriginalPrice;
    }

    public BigDecimal getShareDiscount() {
        return shareDiscount;
    }

    public void setShareDiscount(BigDecimal shareDiscount) {
        this.shareDiscount = shareDiscount;
    }

    public Integer getSuppliersId() {
        return suppliersId;
    }

    public void setSuppliersId(Integer suppliersId) {
        this.suppliersId = suppliersId;
    }

    public Integer getPromId() {
        return promId;
    }

    public void setPromId(Integer promId) {
        this.promId = promId;
    }

    public BigDecimal getGiveSuppliers() {
        return giveSuppliers;
    }

    public void setGiveSuppliers(BigDecimal giveSuppliers) {
        this.giveSuppliers = giveSuppliers;
    }

    public BigDecimal getGiveAgent() {
        return giveAgent;
    }

    public void setGiveAgent(BigDecimal giveAgent) {
        this.giveAgent = giveAgent;
    }

    public Integer getOrderGoodsStatus() {
        return orderGoodsStatus;
    }

    public void setOrderGoodsStatus(Integer orderGoodsStatus) {
        this.orderGoodsStatus = orderGoodsStatus;
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

    public Integer getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(Integer isCheck) {
        this.isCheck = isCheck;
    }

    public Integer getIsPickUp() {
        return isPickUp;
    }

    public void setIsPickUp(Integer isPickUp) {
        this.isPickUp = isPickUp;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode == null ? null : checkCode.trim();
    }

    public Integer getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(Integer pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public String getPickUpDay() {
        return pickUpDay;
    }

    public void setPickUpDay(String pickUpDay) {
        this.pickUpDay = pickUpDay == null ? null : pickUpDay.trim();
    }

    public Integer getDoorId() {
        return doorId;
    }

    public void setDoorId(Integer doorId) {
        this.doorId = doorId;
    }

    public String getOrderGoodsMsg() {
        return orderGoodsMsg;
    }

    public void setOrderGoodsMsg(String orderGoodsMsg) {
        this.orderGoodsMsg = orderGoodsMsg == null ? null : orderGoodsMsg.trim();
    }
}