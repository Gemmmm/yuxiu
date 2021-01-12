package com.tc.yuxiu.model;

import javax.persistence.Id;
import java.math.BigDecimal;

public class TyGoods {
    @Override
    public String toString() {
        return "TyGoods{" +
                "goodsId=" + goodsId +
                ", catId1=" + catId1 +
                ", catId2=" + catId2 +
                ", catId3=" + catId3 +
                ", storeCatId1=" + storeCatId1 +
                ", storeCatId2=" + storeCatId2 +
                ", goodsSn='" + goodsSn + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", clickCount=" + clickCount +
                ", brandId=" + brandId +
                ", storeCount=" + storeCount +
                ", collectSum=" + collectSum +
                ", commentCount=" + commentCount +
                ", weight=" + weight +
                ", marketPrice=" + marketPrice +
                ", shopPrice=" + shopPrice +
                ", costPrice=" + costPrice +
                ", exchangeIntegral=" + exchangeIntegral +
                ", keywords='" + keywords + '\'' +
                ", goodsRemark='" + goodsRemark + '\'' +
                ", originalImg='" + originalImg + '\'' +
                ", isReal=" + isReal +
                ", isOnSale=" + isOnSale +
                ", isFreeShipping=" + isFreeShipping +
                ", onTime=" + onTime +
                ", sort=" + sort +
                ", isRecommend=" + isRecommend +
                ", isNew=" + isNew +
                ", isHot=" + isHot +
                ", lastUpdate=" + lastUpdate +
                ", goodsType=" + goodsType +
                ", giveIntegral=" + giveIntegral +
                ", salesSum=" + salesSum +
                ", promType=" + promType +
                ", promId=" + promId +
                ", distribut=" + distribut +
                ", storeId=" + storeId +
                ", spu='" + spu + '\'' +
                ", sku='" + sku + '\'' +
                ", goodsState=" + goodsState +
                ", suppliersId=" + suppliersId +
                ", shippingAreaIds='" + shippingAreaIds + '\'' +
                ", businessContacts='" + businessContacts + '\'' +
                ", isDel=" + isDel +
                ", existType=" + existType +
                ", otherSort=" + otherSort +
                ", recommendSort=" + recommendSort +
                ", batchQuantity=" + batchQuantity +
                ", orderGoodsSpecId=" + orderGoodsSpecId +
                ", storeIsRecommend=" + storeIsRecommend +
                ", storeRecommendSort=" + storeRecommendSort +
                ", storeNewSort=" + storeNewSort +
                ", storeHotSort=" + storeHotSort +
                ", viewCount=" + viewCount +
                ", maxValue=" + maxValue +
                ", offTime=" + offTime +
                ", setOnSale=" + setOnSale +
                ", setOffSale=" + setOffSale +
                ", addTime=" + addTime +
                ", partnerPrice=" + partnerPrice +
                ", styleId=" + styleId +
                ", tips=" + tips +
                ", activityType=" + activityType +
                ", activityId=" + activityId +
                ", seriesItemId=" + seriesItemId +
                ", unit='" + unit + '\'' +
                ", goodtypeotherid=" + goodtypeotherid +
                ", goodsContent='" + goodsContent + '\'' +
                ", xcxContent='" + xcxContent + '\'' +
                '}';
    }

    @Id
    private Integer goodsId;

    private Integer catId1;

    private Integer catId2;

    private Integer catId3;

    private Integer storeCatId1;

    private Integer storeCatId2;

    private String goodsSn;

    private String goodsName;

    private Integer clickCount;

    private Short brandId;

    private Short storeCount;

    private Integer collectSum;

    private Short commentCount;

    private Integer weight;

    private BigDecimal marketPrice;

    private BigDecimal shopPrice;

    private BigDecimal costPrice;

    private Integer exchangeIntegral;

    private String keywords;

    private String goodsRemark;

    private String originalImg;

    private Byte isReal;

    private Short isOnSale;

    private Short isFreeShipping;

    private Integer onTime;

    private Short sort;

    private Short isRecommend;

    private Short isNew;

    private Short isHot;

    private Integer lastUpdate;

    private Integer goodsType;

    private Integer giveIntegral;

    private Integer salesSum;

    private Short promType;

    private Integer promId;

    private BigDecimal distribut;

    private Integer storeId;

    private String spu;

    private String sku;

    private Short goodsState;

    private Short suppliersId;

    private String shippingAreaIds;

    private String businessContacts;

    private Short isDel;

    private Short existType;

    private Integer otherSort;

    private Integer recommendSort;

    private Integer batchQuantity;

    private Integer orderGoodsSpecId;

    private Short storeIsRecommend;

    private Integer storeRecommendSort;

    private Integer storeNewSort;

    private Integer storeHotSort;

    private Integer viewCount;

    private Integer maxValue;

    private Integer offTime;

    private Short setOnSale;

    private Short setOffSale;

    private Integer addTime;

    private BigDecimal partnerPrice;

    private Integer styleId;

    private Short tips;

    private Short activityType;

    private Integer activityId;

    private Integer seriesItemId;

    private String unit;

    private Integer goodtypeotherid;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getCatId1() {
        return catId1;
    }

    public void setCatId1(Integer catId1) {
        this.catId1 = catId1;
    }

    public Integer getCatId2() {
        return catId2;
    }

    public void setCatId2(Integer catId2) {
        this.catId2 = catId2;
    }

    public Integer getCatId3() {
        return catId3;
    }

    public void setCatId3(Integer catId3) {
        this.catId3 = catId3;
    }

    public Integer getStoreCatId1() {
        return storeCatId1;
    }

    public void setStoreCatId1(Integer storeCatId1) {
        this.storeCatId1 = storeCatId1;
    }

    public Integer getStoreCatId2() {
        return storeCatId2;
    }

    public void setStoreCatId2(Integer storeCatId2) {
        this.storeCatId2 = storeCatId2;
    }

    public String getGoodsSn() {
        return goodsSn;
    }

    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn == null ? null : goodsSn.trim();
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public Integer getClickCount() {
        return clickCount;
    }

    public void setClickCount(Integer clickCount) {
        this.clickCount = clickCount;
    }

    public Short getBrandId() {
        return brandId;
    }

    public void setBrandId(Short brandId) {
        this.brandId = brandId;
    }

    public Short getStoreCount() {
        return storeCount;
    }

    public void setStoreCount(Short storeCount) {
        this.storeCount = storeCount;
    }

    public Integer getCollectSum() {
        return collectSum;
    }

    public void setCollectSum(Integer collectSum) {
        this.collectSum = collectSum;
    }

    public Short getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Short commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public BigDecimal getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(BigDecimal shopPrice) {
        this.shopPrice = shopPrice;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public Integer getExchangeIntegral() {
        return exchangeIntegral;
    }

    public void setExchangeIntegral(Integer exchangeIntegral) {
        this.exchangeIntegral = exchangeIntegral;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    public String getGoodsRemark() {
        return goodsRemark;
    }

    public void setGoodsRemark(String goodsRemark) {
        this.goodsRemark = goodsRemark == null ? null : goodsRemark.trim();
    }

    public String getOriginalImg() {
        return originalImg;
    }

    public void setOriginalImg(String originalImg) {
        this.originalImg = originalImg == null ? null : originalImg.trim();
    }

    public Byte getIsReal() {
        return isReal;
    }

    public void setIsReal(Byte isReal) {
        this.isReal = isReal;
    }

    public Short getIsOnSale() {
        return isOnSale;
    }

    public void setIsOnSale(Short isOnSale) {
        this.isOnSale = isOnSale;
    }

    public Short getIsFreeShipping() {
        return isFreeShipping;
    }

    public void setIsFreeShipping(Short isFreeShipping) {
        this.isFreeShipping = isFreeShipping;
    }

    public Integer getOnTime() {
        return onTime;
    }

    public void setOnTime(Integer onTime) {
        this.onTime = onTime;
    }

    public Short getSort() {
        return sort;
    }

    public void setSort(Short sort) {
        this.sort = sort;
    }

    public Short getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Short isRecommend) {
        this.isRecommend = isRecommend;
    }

    public Short getIsNew() {
        return isNew;
    }

    public void setIsNew(Short isNew) {
        this.isNew = isNew;
    }

    public Short getIsHot() {
        return isHot;
    }

    public void setIsHot(Short isHot) {
        this.isHot = isHot;
    }

    public Integer getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Integer lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Integer getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

    public Integer getGiveIntegral() {
        return giveIntegral;
    }

    public void setGiveIntegral(Integer giveIntegral) {
        this.giveIntegral = giveIntegral;
    }

    public Integer getSalesSum() {
        return salesSum;
    }

    public void setSalesSum(Integer salesSum) {
        this.salesSum = salesSum;
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

    public BigDecimal getDistribut() {
        return distribut;
    }

    public void setDistribut(BigDecimal distribut) {
        this.distribut = distribut;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getSpu() {
        return spu;
    }

    public void setSpu(String spu) {
        this.spu = spu == null ? null : spu.trim();
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku == null ? null : sku.trim();
    }

    public Short getGoodsState() {
        return goodsState;
    }

    public void setGoodsState(Short goodsState) {
        this.goodsState = goodsState;
    }

    public Short getSuppliersId() {
        return suppliersId;
    }

    public void setSuppliersId(Short suppliersId) {
        this.suppliersId = suppliersId;
    }

    public String getShippingAreaIds() {
        return shippingAreaIds;
    }

    public void setShippingAreaIds(String shippingAreaIds) {
        this.shippingAreaIds = shippingAreaIds == null ? null : shippingAreaIds.trim();
    }

    public String getBusinessContacts() {
        return businessContacts;
    }

    public void setBusinessContacts(String businessContacts) {
        this.businessContacts = businessContacts == null ? null : businessContacts.trim();
    }

    public Short getIsDel() {
        return isDel;
    }

    public void setIsDel(Short isDel) {
        this.isDel = isDel;
    }

    public Short getExistType() {
        return existType;
    }

    public void setExistType(Short existType) {
        this.existType = existType;
    }

    public Integer getOtherSort() {
        return otherSort;
    }

    public void setOtherSort(Integer otherSort) {
        this.otherSort = otherSort;
    }

    public Integer getRecommendSort() {
        return recommendSort;
    }

    public void setRecommendSort(Integer recommendSort) {
        this.recommendSort = recommendSort;
    }

    public Integer getBatchQuantity() {
        return batchQuantity;
    }

    public void setBatchQuantity(Integer batchQuantity) {
        this.batchQuantity = batchQuantity;
    }

    public Integer getOrderGoodsSpecId() {
        return orderGoodsSpecId;
    }

    public void setOrderGoodsSpecId(Integer orderGoodsSpecId) {
        this.orderGoodsSpecId = orderGoodsSpecId;
    }

    public Short getStoreIsRecommend() {
        return storeIsRecommend;
    }

    public void setStoreIsRecommend(Short storeIsRecommend) {
        this.storeIsRecommend = storeIsRecommend;
    }

    public Integer getStoreRecommendSort() {
        return storeRecommendSort;
    }

    public void setStoreRecommendSort(Integer storeRecommendSort) {
        this.storeRecommendSort = storeRecommendSort;
    }

    public Integer getStoreNewSort() {
        return storeNewSort;
    }

    public void setStoreNewSort(Integer storeNewSort) {
        this.storeNewSort = storeNewSort;
    }

    public Integer getStoreHotSort() {
        return storeHotSort;
    }

    public void setStoreHotSort(Integer storeHotSort) {
        this.storeHotSort = storeHotSort;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Integer maxValue) {
        this.maxValue = maxValue;
    }

    public Integer getOffTime() {
        return offTime;
    }

    public void setOffTime(Integer offTime) {
        this.offTime = offTime;
    }

    public Short getSetOnSale() {
        return setOnSale;
    }

    public void setSetOnSale(Short setOnSale) {
        this.setOnSale = setOnSale;
    }

    public Short getSetOffSale() {
        return setOffSale;
    }

    public void setSetOffSale(Short setOffSale) {
        this.setOffSale = setOffSale;
    }

    public Integer getAddTime() {
        return addTime;
    }

    public void setAddTime(Integer addTime) {
        this.addTime = addTime;
    }

    public BigDecimal getPartnerPrice() {
        return partnerPrice;
    }

    public void setPartnerPrice(BigDecimal partnerPrice) {
        this.partnerPrice = partnerPrice;
    }

    public Integer getStyleId() {
        return styleId;
    }

    public void setStyleId(Integer styleId) {
        this.styleId = styleId;
    }

    public Short getTips() {
        return tips;
    }

    public void setTips(Short tips) {
        this.tips = tips;
    }

    public Short getActivityType() {
        return activityType;
    }

    private String goodsContent;

    private String xcxContent;

    public String getGoodsContent() {
        return goodsContent;
    }

    public void setGoodsContent(String goodsContent) {
        this.goodsContent = goodsContent == null ? null : goodsContent.trim();
    }

    public String getXcxContent() {
        return xcxContent;
    }

    public void setXcxContent(String xcxContent) {
        this.xcxContent = xcxContent == null ? null : xcxContent.trim();
    }

    public void setActivityType(Short activityType) {
        this.activityType = activityType;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getSeriesItemId() {
        return seriesItemId;
    }

    public void setSeriesItemId(Integer seriesItemId) {
        this.seriesItemId = seriesItemId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public Integer getGoodtypeotherid() {
        return goodtypeotherid;
    }

    public void setGoodtypeotherid(Integer goodtypeotherid) {
        this.goodtypeotherid = goodtypeotherid;
    }
}