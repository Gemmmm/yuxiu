package com.tc.yuxiu.model;

import javax.persistence.Id;
import java.math.BigDecimal;

public class TyStore {
    @Id
    private Integer storeId;

    private Integer styleId;

    private String storeName;

    private Integer gradeId;

    private Integer userId;

    private String userName;

    private String realName;

    private String sellerName;

    private Integer scId;

    private String companyName;

    private Integer provinceId;

    private Integer cityId;

    private Integer district;

    private String storeAddress;

    private String storeZip;

    private Boolean storeState;

    private String storeCloseInfo;

    private Integer storeSort;

    private String storeRebatePaytime;

    private Integer storeTime;

    private Integer storeEndTime;

    private String storeLogo;

    private String storeBanner;

    private String storeAvatar;

    private String seoKeywords;

    private String seoDescription;

    private String storeAliwangwang;

    private String storeQq;

    private String storePhone;

    private String storeDomain;

    private Boolean storeRecommend;

    private String storeTheme;

    private Integer storeCredit;

    private BigDecimal storeDesccredit;

    private BigDecimal storeServicecredit;

    private BigDecimal storeDeliverycredit;

    private Integer storeCollect;

    private String storePrintdesc;

    private Integer storeSales;

    private String storeWorkingtime;

    private BigDecimal storeFreePrice;

    private Integer storeDecorationSwitch;

    private Boolean storeDecorationOnly;

    private Byte isOwnShop;

    private Boolean bindAllGc;

    private Boolean qitian;

    private Boolean certified;

    private Boolean returned;

    private String storeFreeTime;

    private String deliverRegion;

    private Boolean cod;

    private Boolean twoHour;

    private Boolean ensure;

    private BigDecimal deposit;

    private Boolean depositIcon;

    private BigDecimal storeMoney;

    private BigDecimal pendingMoney;

    private Boolean goodsExamine;

    private Boolean storeOrderPromotionType;

    private Boolean isCheck;

    private Integer viewNum;

    private Integer batchQuantity;

    private Integer classId;

    private String webService;

    private String webGoodsService;

    private String webOrderService;

    private String appService;

    private String appGoodsService;

    private String appOrderService;

    private Boolean deleted;

    private String storeSlideUrl;

    private String storePresales;

    private String storeAftersales;

    private String mbSlide;

    private String mbSlideUrl;

    private String storeDescription;

    public Boolean getCheck() {
        return isCheck;
    }

    public void setCheck(Boolean check) {
        isCheck = check;
    }

    public String getStoreSlideUrl() {
        return storeSlideUrl;
    }

    public void setStoreSlideUrl(String storeSlideUrl) {
        this.storeSlideUrl = storeSlideUrl;
    }

    public String getStorePresales() {
        return storePresales;
    }

    public void setStorePresales(String storePresales) {
        this.storePresales = storePresales;
    }

    public String getStoreAftersales() {
        return storeAftersales;
    }

    public void setStoreAftersales(String storeAftersales) {
        this.storeAftersales = storeAftersales;
    }

    public String getMbSlide() {
        return mbSlide;
    }

    public void setMbSlide(String mbSlide) {
        this.mbSlide = mbSlide;
    }

    public String getMbSlideUrl() {
        return mbSlideUrl;
    }

    public void setMbSlideUrl(String mbSlideUrl) {
        this.mbSlideUrl = mbSlideUrl;
    }

    public String getStoreDescription() {
        return storeDescription;
    }

    public void setStoreDescription(String storeDescription) {
        this.storeDescription = storeDescription;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getStyleId() {
        return styleId;
    }

    public void setStyleId(Integer styleId) {
        this.styleId = styleId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName == null ? null : storeName.trim();
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName == null ? null : sellerName.trim();
    }

    public Integer getScId() {
        return scId;
    }

    public void setScId(Integer scId) {
        this.scId = scId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getDistrict() {
        return district;
    }

    public void setDistrict(Integer district) {
        this.district = district;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress == null ? null : storeAddress.trim();
    }

    public String getStoreZip() {
        return storeZip;
    }

    public void setStoreZip(String storeZip) {
        this.storeZip = storeZip == null ? null : storeZip.trim();
    }

    public Boolean getStoreState() {
        return storeState;
    }

    public void setStoreState(Boolean storeState) {
        this.storeState = storeState;
    }

    public String getStoreCloseInfo() {
        return storeCloseInfo;
    }

    public void setStoreCloseInfo(String storeCloseInfo) {
        this.storeCloseInfo = storeCloseInfo == null ? null : storeCloseInfo.trim();
    }

    public Integer getStoreSort() {
        return storeSort;
    }

    public void setStoreSort(Integer storeSort) {
        this.storeSort = storeSort;
    }

    public String getStoreRebatePaytime() {
        return storeRebatePaytime;
    }

    public void setStoreRebatePaytime(String storeRebatePaytime) {
        this.storeRebatePaytime = storeRebatePaytime == null ? null : storeRebatePaytime.trim();
    }

    public Integer getStoreTime() {
        return storeTime;
    }

    public void setStoreTime(Integer storeTime) {
        this.storeTime = storeTime;
    }

    public Integer getStoreEndTime() {
        return storeEndTime;
    }

    public void setStoreEndTime(Integer storeEndTime) {
        this.storeEndTime = storeEndTime;
    }

    public String getStoreLogo() {
        return storeLogo;
    }

    public void setStoreLogo(String storeLogo) {
        this.storeLogo = storeLogo == null ? null : storeLogo.trim();
    }

    public String getStoreBanner() {
        return storeBanner;
    }

    public void setStoreBanner(String storeBanner) {
        this.storeBanner = storeBanner == null ? null : storeBanner.trim();
    }

    public String getStoreAvatar() {
        return storeAvatar;
    }

    public void setStoreAvatar(String storeAvatar) {
        this.storeAvatar = storeAvatar == null ? null : storeAvatar.trim();
    }

    public String getSeoKeywords() {
        return seoKeywords;
    }

    public void setSeoKeywords(String seoKeywords) {
        this.seoKeywords = seoKeywords == null ? null : seoKeywords.trim();
    }

    public String getSeoDescription() {
        return seoDescription;
    }

    public void setSeoDescription(String seoDescription) {
        this.seoDescription = seoDescription == null ? null : seoDescription.trim();
    }

    public String getStoreAliwangwang() {
        return storeAliwangwang;
    }

    public void setStoreAliwangwang(String storeAliwangwang) {
        this.storeAliwangwang = storeAliwangwang == null ? null : storeAliwangwang.trim();
    }

    public String getStoreQq() {
        return storeQq;
    }

    public void setStoreQq(String storeQq) {
        this.storeQq = storeQq == null ? null : storeQq.trim();
    }

    public String getStorePhone() {
        return storePhone;
    }

    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone == null ? null : storePhone.trim();
    }

    public String getStoreDomain() {
        return storeDomain;
    }

    public void setStoreDomain(String storeDomain) {
        this.storeDomain = storeDomain == null ? null : storeDomain.trim();
    }

    public Boolean getStoreRecommend() {
        return storeRecommend;
    }

    public void setStoreRecommend(Boolean storeRecommend) {
        this.storeRecommend = storeRecommend;
    }

    public String getStoreTheme() {
        return storeTheme;
    }

    public void setStoreTheme(String storeTheme) {
        this.storeTheme = storeTheme == null ? null : storeTheme.trim();
    }

    public Integer getStoreCredit() {
        return storeCredit;
    }

    public void setStoreCredit(Integer storeCredit) {
        this.storeCredit = storeCredit;
    }

    public BigDecimal getStoreDesccredit() {
        return storeDesccredit;
    }

    public void setStoreDesccredit(BigDecimal storeDesccredit) {
        this.storeDesccredit = storeDesccredit;
    }

    public BigDecimal getStoreServicecredit() {
        return storeServicecredit;
    }

    public void setStoreServicecredit(BigDecimal storeServicecredit) {
        this.storeServicecredit = storeServicecredit;
    }

    public BigDecimal getStoreDeliverycredit() {
        return storeDeliverycredit;
    }

    public void setStoreDeliverycredit(BigDecimal storeDeliverycredit) {
        this.storeDeliverycredit = storeDeliverycredit;
    }

    public Integer getStoreCollect() {
        return storeCollect;
    }

    public void setStoreCollect(Integer storeCollect) {
        this.storeCollect = storeCollect;
    }

    public String getStorePrintdesc() {
        return storePrintdesc;
    }

    public void setStorePrintdesc(String storePrintdesc) {
        this.storePrintdesc = storePrintdesc == null ? null : storePrintdesc.trim();
    }

    public Integer getStoreSales() {
        return storeSales;
    }

    public void setStoreSales(Integer storeSales) {
        this.storeSales = storeSales;
    }

    public String getStoreWorkingtime() {
        return storeWorkingtime;
    }

    public void setStoreWorkingtime(String storeWorkingtime) {
        this.storeWorkingtime = storeWorkingtime == null ? null : storeWorkingtime.trim();
    }

    public BigDecimal getStoreFreePrice() {
        return storeFreePrice;
    }

    public void setStoreFreePrice(BigDecimal storeFreePrice) {
        this.storeFreePrice = storeFreePrice;
    }

    public Integer getStoreDecorationSwitch() {
        return storeDecorationSwitch;
    }

    public void setStoreDecorationSwitch(Integer storeDecorationSwitch) {
        this.storeDecorationSwitch = storeDecorationSwitch;
    }

    public Boolean getStoreDecorationOnly() {
        return storeDecorationOnly;
    }

    public void setStoreDecorationOnly(Boolean storeDecorationOnly) {
        this.storeDecorationOnly = storeDecorationOnly;
    }

    public Byte getIsOwnShop() {
        return isOwnShop;
    }

    public void setIsOwnShop(Byte isOwnShop) {
        this.isOwnShop = isOwnShop;
    }

    public Boolean getBindAllGc() {
        return bindAllGc;
    }

    public void setBindAllGc(Boolean bindAllGc) {
        this.bindAllGc = bindAllGc;
    }

    public Boolean getQitian() {
        return qitian;
    }

    public void setQitian(Boolean qitian) {
        this.qitian = qitian;
    }

    public Boolean getCertified() {
        return certified;
    }

    public void setCertified(Boolean certified) {
        this.certified = certified;
    }

    public Boolean getReturned() {
        return returned;
    }

    public void setReturned(Boolean returned) {
        this.returned = returned;
    }

    public String getStoreFreeTime() {
        return storeFreeTime;
    }

    public void setStoreFreeTime(String storeFreeTime) {
        this.storeFreeTime = storeFreeTime == null ? null : storeFreeTime.trim();
    }

    public String getDeliverRegion() {
        return deliverRegion;
    }

    public void setDeliverRegion(String deliverRegion) {
        this.deliverRegion = deliverRegion == null ? null : deliverRegion.trim();
    }

    public Boolean getCod() {
        return cod;
    }

    public void setCod(Boolean cod) {
        this.cod = cod;
    }

    public Boolean getTwoHour() {
        return twoHour;
    }

    public void setTwoHour(Boolean twoHour) {
        this.twoHour = twoHour;
    }

    public Boolean getEnsure() {
        return ensure;
    }

    public void setEnsure(Boolean ensure) {
        this.ensure = ensure;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public Boolean getDepositIcon() {
        return depositIcon;
    }

    public void setDepositIcon(Boolean depositIcon) {
        this.depositIcon = depositIcon;
    }

    public BigDecimal getStoreMoney() {
        return storeMoney;
    }

    public void setStoreMoney(BigDecimal storeMoney) {
        this.storeMoney = storeMoney;
    }

    public BigDecimal getPendingMoney() {
        return pendingMoney;
    }

    public void setPendingMoney(BigDecimal pendingMoney) {
        this.pendingMoney = pendingMoney;
    }

    public Boolean getGoodsExamine() {
        return goodsExamine;
    }

    public void setGoodsExamine(Boolean goodsExamine) {
        this.goodsExamine = goodsExamine;
    }

    public Boolean getStoreOrderPromotionType() {
        return storeOrderPromotionType;
    }

    public void setStoreOrderPromotionType(Boolean storeOrderPromotionType) {
        this.storeOrderPromotionType = storeOrderPromotionType;
    }

    public Boolean getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(Boolean isCheck) {
        this.isCheck = isCheck;
    }

    public Integer getViewNum() {
        return viewNum;
    }

    public void setViewNum(Integer viewNum) {
        this.viewNum = viewNum;
    }

    public Integer getBatchQuantity() {
        return batchQuantity;
    }

    public void setBatchQuantity(Integer batchQuantity) {
        this.batchQuantity = batchQuantity;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getWebService() {
        return webService;
    }

    public void setWebService(String webService) {
        this.webService = webService == null ? null : webService.trim();
    }

    public String getWebGoodsService() {
        return webGoodsService;
    }

    public void setWebGoodsService(String webGoodsService) {
        this.webGoodsService = webGoodsService == null ? null : webGoodsService.trim();
    }

    public String getWebOrderService() {
        return webOrderService;
    }

    public void setWebOrderService(String webOrderService) {
        this.webOrderService = webOrderService == null ? null : webOrderService.trim();
    }

    public String getAppService() {
        return appService;
    }

    public void setAppService(String appService) {
        this.appService = appService == null ? null : appService.trim();
    }

    public String getAppGoodsService() {
        return appGoodsService;
    }

    public void setAppGoodsService(String appGoodsService) {
        this.appGoodsService = appGoodsService == null ? null : appGoodsService.trim();
    }

    public String getAppOrderService() {
        return appOrderService;
    }

    public void setAppOrderService(String appOrderService) {
        this.appOrderService = appOrderService == null ? null : appOrderService.trim();
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}