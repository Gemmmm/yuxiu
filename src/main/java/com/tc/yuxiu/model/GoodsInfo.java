package com.tc.yuxiu.model;


import java.util.List;
import java.util.Map;

public class GoodsInfo {
    private String token;

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

    private String marketPrice;

    private String shopPrice;

    private String costPrice;

    private Integer exchangeIntegral;

    private String keywords;

    private String goodsRemark;

    private String img;

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

    private String distribut;

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

    private String partnerPrice;

    private Integer styleId;

    private Short tips;

    private Short activityType;

    private Integer activityId;

    private Integer seriesItemId;

    private String unit;

    private Integer goodtypeotherid;

    private String goodsImgUrls;

    private List<Map<String,Object>> specList;

    private List<Map<String,Object>>  goodTypeContacts;

    public void setImg(String img) {
        this.img = img;
    }

    public List<Map<String, Object>> getSpecList() {
        return specList;
    }

    public void setSpecList(List<Map<String, Object>> specList) {
        this.specList = specList;
    }

    public List<Map<String, Object>> getGoodTypeContacts() {
        return goodTypeContacts;
    }

    public void setGoodTypeContacts(List<Map<String, Object>> goodTypeContacts) {
        this.goodTypeContacts = goodTypeContacts;
    }

    public String getGoodsImgUrls() {
        return goodsImgUrls;
    }

    public void setGoodsImgUrls(String goodsImgUrls) {
        this.goodsImgUrls = goodsImgUrls;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

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

    public String getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(String shopPrice) {
        this.shopPrice = shopPrice;
    }

    public String getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(String costPrice) {
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

    public String getImg() {
        return img;
    }

    public void setOriginalImg(String img) {
        this.img = img == null ? null : img.trim();
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

    public String getDistribut() {
        return distribut;
    }

    public void setDistribut(String distribut) {
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

    public String getPartnerPrice() {
        return partnerPrice;
    }

    public void setPartnerPrice(String partnerPrice) {
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

    private String content;

    private String xcxContent;

    public String getGoodsContent() {
        return content;
    }

    public void setGoodsContent(String content) {
        this.content = content == null ? null : content.trim();
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