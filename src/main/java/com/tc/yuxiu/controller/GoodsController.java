package com.tc.yuxiu.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xerces.internal.xs.StringList;
import com.tc.yuxiu.model.*;
import com.tc.yuxiu.service.*;
import com.tc.yuxiu.util.DescartesUtil;
import com.tc.yuxiu.util.FileUtil;
import com.tc.yuxiu.util.IntegerUtil;
import com.tc.yuxiu.util.LetterUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author DELL
 * @date 2020/9/16 15:36
 */
@Api(tags = "商品管理")
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private TySellerService sellerService;
    @Autowired
    private TyGoodsStyleService gsService;
    @Autowired
    private TyGoodCategoryService gcService;
    @Autowired
    private TyGoodsService goodsService;
    @Autowired
    private TyGoodsImagesService giService;
    @Autowired
    private TyGoodsTypeService gtService;
    @Autowired
    private TyGoodsTypeItemService gtiService;
    @Autowired
    private TyGoodsTypeContactService gtcService;
    @Autowired
    private TySpecService specService;
    @Autowired
    private TySpecItemService siService;
    @Autowired
    private TySpecGoodsPriceService sgpService;


    @ApiOperation("商品发布-商品风格")
    @RequestMapping(value = "/goodsStyles", method = RequestMethod.GET)
    public Result allGoodStyle(
            @ApiParam @RequestParam String token
    ) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        List<Map<String, Object>> maps = new ArrayList<>();
        Map<String, Object> map = null;
        List<TyGoodsStyle> goodsStyles = gsService.getAll();
        map = new HashMap<>();
        map.put("id", "0");
        map.put("name", "请选择");
        maps.add(map);
        if (goodsStyles != null) {
            for (TyGoodsStyle goodsStyle : goodsStyles) {
                map = new HashMap<>();
                map.put("id", goodsStyle.getId().toString());
                map.put("name", goodsStyle.getStyleName());
                maps.add(map);
            }
        }
        result.setData(maps);
        if (!maps.isEmpty()) {
            result.setSuccess("商品风格查询");
        } else {
            result.setNull("商品风格");
        }
        return result;
    }

    @ApiOperation("商品发布-商品分类")
    @RequestMapping(value = "/goodsCategorys", method = RequestMethod.GET)
    public Result allGoodsCategorys(
            @ApiParam @RequestParam String token,
            @ApiParam(value = "等级1,2,3,") @RequestParam(required = false) Short level,
            @ApiParam(value = "父节点编号") @RequestParam(required = false) Integer parentId
    ) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        List<Map<String, Object>> maps = new ArrayList<>();
        Map<String, Object> map = null;


        List<TyGoodsCategory> goodsCategories = gcService.getByLevelAndParentId(level, parentId);
        if (level != null || parentId != null) {
            map = new HashMap<>();
            map.put("catId", "0");
            map.put("catName", "请选择");
            maps.add(map);
            if (goodsCategories != null) {
                for (TyGoodsCategory goodsCategory : goodsCategories) {
                    map = new HashMap<>();
                    map.put("catId", goodsCategory.getId().toString());
                    map.put("catName", goodsCategory.getName());
                    maps.add(map);
                }
            }
        } else {
            if (goodsCategories != null) {
                map = new HashMap<>();
                map.put("catId", "0");
                map.put("catName", "请选择");
                maps.add(map);
                Collections.sort(goodsCategories, (o1, o2) -> LetterUtil.getFirstLetter(o1.getName()) - LetterUtil.getFirstLetter(o2.getName()));

                for (TyGoodsCategory goodsCategory : goodsCategories) {
                    map = new HashMap<>();

                    map.put("catId", goodsCategory.getId().toString());
                    String catName = goodsCategory.getName();
                    Character letter = LetterUtil.getFirstLetter(catName);

                    catName = letter.toString().toUpperCase() + " " + catName;
                    Short parentId1 = goodsCategory.getParentId();
                    TyGoodsCategory category = gcService.getById((int) parentId1);
                    if (category != null) {
                        catName = catName + "(" + category.getName() + ")";
                    }
                    map.put("catName", catName);
                    maps.add(map);
                }
            }

        }


        result.setData(maps);
        if (maps.size() > 0) {
            result.setSuccess("商品分类查询");
        } else {
            result.setNull("商品分类");
        }
        return result;
    }

    @ApiOperation("商品发布-保存")
    @RequestMapping(value = "/goodsInfo/add", method = RequestMethod.POST)
    public Result addGoodsInfo(@RequestBody GoodsInfo goodsInfo) {
        System.out.println("goodsInfo" + goodsInfo);
        Result result = new Result();
        String token = goodsInfo.getToken();
        if (token == null) {
            result.setFailMessage("没有token");
            return result;
        }
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        Integer storeId = seller.getStoreId();
        TyGoods goods = new TyGoods();
        goods.setStoreId(storeId);

        Example example = new Example(TyGoods.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId", storeId);
        Long currentTime = System.currentTimeMillis();
        goods.setAddTime(IntegerUtil.timeToInteger(currentTime));
        //赠送积分
        Integer giveIntegral = goodsInfo.getGiveIntegral();
        if (giveIntegral != null) {
            goods.setGiveIntegral(giveIntegral);
            criteria.andEqualTo("giveIntegral", giveIntegral);
        }
        Integer exchangeIntegral = goodsInfo.getExchangeIntegral();
        //兑换积分
        if (exchangeIntegral != null) {
            goods.setExchangeIntegral(exchangeIntegral);
            criteria.andEqualTo("exchangeIntegral", exchangeIntegral);
        }
        //排序
        Short sort = goodsInfo.getSort();
        if (sort != null) {
            goods.setSort(sort);
            criteria.andEqualTo("sort", sort);
        }
        String goodsName = goodsInfo.getGoodsName();
        if (goodsName != null && !"".equals(goodsName.trim())) {
            goods.setGoodsName(goodsName);
            criteria.andEqualTo("goodsName", goodsName);
        }
        //货号
        String goodsSn = goodsInfo.getGoodsSn();
        if (goodsSn != null) {
            goods.setGoodsSn(goodsSn);
            criteria.andEqualTo("goodsSn", goodsSn);
        }
        String spu = goodsInfo.getSpu();
        if (spu != null && !"".equals(spu.trim())) {
            goods.setSpu(spu);
            criteria.andEqualTo("spu", spu);
        }
        String sku = goodsInfo.getSku();
        if (sku != null && !"".equals(sku.trim())) {
            goods.setSku(sku);
            criteria.andEqualTo("sku", sku);
        }
        //风格
        Integer styleId = goodsInfo.getStyleId();
        if (styleId != null && styleId != 0) {
            goods.setStyleId(styleId);
            criteria.andEqualTo("styleId", styleId);
        }
        //商品分类1
        Integer catId1 = goodsInfo.getCatId1();
        if (catId1 != null && catId1 != 0) {
            goods.setCatId1(catId1);
            criteria.andEqualTo("catId1", catId1);
        }
        //商品分类2
        Integer catId2 = goodsInfo.getCatId2();
        if (catId2 != null && catId2 != 0) {
            goods.setCatId2(catId2);
            criteria.andEqualTo("catId2", catId2);
        }
        //商品分类3
        Integer catId3 = goodsInfo.getCatId3();
        if (catId3 != null && catId3 != 0) {
            goods.setCatId3(catId3);
            criteria.andEqualTo("catId3", catId3);
        }
        //合伙人拿货价
        String partnerPrice = goodsInfo.getPartnerPrice();
        if (partnerPrice != null && !"".equals(partnerPrice.trim())) {
            goods.setPartnerPrice(new BigDecimal(partnerPrice));
            criteria.andEqualTo("partnerPrice", partnerPrice);
        }
        //成本价
        String costPrice = goodsInfo.getCostPrice();

        System.out.println("costPrice::" + costPrice);
        if (costPrice != null && !"0".equals(costPrice)) {
            System.out.println("costPrice::" + costPrice);
            goods.setCostPrice(new BigDecimal(costPrice));
        }
        //本店价
        String shopPrice = goodsInfo.getShopPrice();
        if (shopPrice != null && !"".equals(shopPrice.trim())) {
            goods.setShopPrice(new BigDecimal(shopPrice));
        }
        //佣金用于分销分成
        String distribut = goodsInfo.getDistribut();
        if (distribut != null && !"".equals(distribut.trim())) {
            goods.setDistribut(new BigDecimal(distribut));
        }


        //商品图片
        String img = goodsInfo.getImg();
        if (img != null && !"".equals(img)) {
            goods.setOriginalImg(img);
        }
        //1-新品 2-爆款 3-热卖
        Short tips = goodsInfo.getTips();
        if (tips != null && !"".equals(tips)) {
            goods.setTips(Short.valueOf(tips));
            criteria.andEqualTo("tips", tips);
        }
        //库存数量
        Short storeCount = goodsInfo.getStoreCount();
        if (storeCount != null && storeCount != 0) {
            goods.setStoreCount(storeCount);
            criteria.andEqualTo("storeCount", storeCount);
        }
        //起批量
        Integer batchQuantity = goodsInfo.getBatchQuantity();
        if (batchQuantity != null && batchQuantity != 0) {
            goods.setBatchQuantity(batchQuantity);
            criteria.andEqualTo("batchQuantity", batchQuantity);
        }
        String keywords = goodsInfo.getKeywords();
        if (keywords != null && !"".equals(keywords)) {
            goods.setKeywords(keywords);
            criteria.andEqualTo("keywords", keywords);
        }
        //商品内容
        String content = goodsInfo.getContent();
        if (content != null && !"".equals(content)) {
            goods.setGoodsContent(content);
            criteria.andEqualTo("goodsContent", content);
        }
        Integer flag = 0;
        Integer goodsId = goodsInfo.getGoodsId();

        if (goodsId != null) {
            goods.setGoodsId(goodsId);
            flag = goodsService.modify(goods);

            System.out.print("通用信息修改");
        } else {
            flag = goodsService.insert(goods);
            System.out.print("通用信息添加");
        }
        if (flag < 0) {
            result.setFail("商品信息保存");
            return result;
        }else{
            System.out.println("成功");
        }

        criteria.andEqualTo("isDel", 0);
        goods = goodsService.getByExample(example);

        if (goods != null) {
            goodsId = goods.getGoodsId();
        }
        if (goodsId == null) {
            result.setFailMessage("商品id为空");
            return result;
        }
        String goodsImgUrls = goodsInfo.getGoodsImgUrls();
        System.out.println("goodsImgUrls::" + goodsImgUrls);
        if (goodsImgUrls != null) {
            List<TyGoodsImages> tyGoodsImagesList = new ArrayList<>();
            String[] goodsImgUrlList = goodsImgUrls.split(",");
            if (goodsImgUrlList != null && goodsImgUrlList.length > 0) {
                giService.deleteByGoodsId(goodsId);
                for (String goodsImagUrl : goodsImgUrlList) {
                    TyGoodsImages goodsImage = new TyGoodsImages();
                    goodsImage.setGoodsId(goodsId);
                    goodsImage.setImageUrl(goodsImagUrl);
                    int flag1 = giService.insert(goodsImage);
                    if (flag1 < 0) {
                        result.setFailMessage("相册添加失败");
                        return result;
                    }
                }

            }

        }
        List<Map<String, Object>> specList = goodsInfo.getSpecList();
        System.out.println("specList::" + specList);
        if (specList != null && specList.size() > 0) {
            //flag = sgpService.deleteByStoreIdAndGoodsId(storeId, goodsId);
            for (Map<String, Object> specGoodPrice : specList) {
                System.out.println("specGoodPrice::" + specGoodPrice);
                String keyName = (String) specGoodPrice.get("keyName");
                String keyValue = (String) specGoodPrice.get("keyValue");
                String cost_Price = (String) specGoodPrice.get("price");
                if(cost_Price==null){
                    cost_Price="0";
                }
                Integer price = null;
                if (cost_Price != null) {
                    price = IntegerUtil.calcuShopPrice(cost_Price);
                } else {
                    price = 0;
                }
                String count = (String) specGoodPrice.get("count");

                System.out.println("keyName::" + keyName + " " + keyValue + " " + cost_Price + " " + count);
                TySpecGoodsPrice goodsPrice = sgpService.getByStoreIdGoodsIdAndKeyValue(storeId, goodsId, keyValue);
                System.out.println("goodsPrice::" + goodsPrice);
                if (goodsPrice != null) {
                    goodsPrice.setCostPrice(new BigDecimal(cost_Price));

                    goodsPrice.setPrice(new BigDecimal(price));
                    goodsPrice.setStoreCount(Integer.valueOf(count));
                    int flag2 = sgpService.modify(goodsPrice);
                    System.out.println("flag12::" + flag2);
                    if (flag2 < 0) {
                        result.setFailMessage("商品规格价格保存失败");
                        return result;
                    }
                } else {
                    goodsPrice = new TySpecGoodsPrice();
                    goodsPrice.setGoodsId(goodsId);
                    goodsPrice.setStoreId(storeId);
                    goodsPrice.setKeyName(keyName);
                    goodsPrice.setKeyValue(keyValue);

                    goodsPrice.setCostPrice(new BigDecimal(cost_Price));
                    goodsPrice.setPrice(new BigDecimal(price));
                    goodsPrice.setStoreCount(Integer.valueOf(count));
                    int flag2 = sgpService.insert(goodsPrice);
                    System.out.println("flag22::" + flag2);
                    if (flag2 < 0) {
                        result.setFailMessage("商品规格价格保存失败");
                        return result;
                    }

                }
            }
        }


        List<Map<String, Object>> goodTypeContacts = goodsInfo.getGoodTypeContacts();
        System.out.println("goodTypeContacts::" + goodTypeContacts);
        if (goodTypeContacts != null && goodTypeContacts.size() > 0) {
            for (Map<String, Object> goodTypeContact : goodTypeContacts) {
                System.out.println("goodTypeContact::" + goodTypeContact);
                String key = (String) goodTypeContact.get("key");
                String value = (String) goodTypeContact.get("value");
                Integer typeId = null;
                Integer itemId = null;
                try {
                    typeId = Integer.valueOf(key);
                    itemId = Integer.valueOf(value);
                } catch (Exception e) {
                    System.out.println("商品属性异常");
                    result.setFailMessage("商品属性异常");
                    return result;

                }

                TyGoodsTypeContact goodsTypeContact = gtcService.getByGoodsIdAndTypeIdAndItemId(goodsId, typeId, itemId);
                if (goodsTypeContact != null) {
                    goodsTypeContact.setTypeItemId(itemId);
                    int flag3 = gtcService.modify(goodsTypeContact);
                    if (flag3 < 0) {
                        result.setFailMessage("商品属性保存失败");
                        return result;
                    }
                } else {
                    goodsTypeContact = new TyGoodsTypeContact();
                    goodsTypeContact.setGoodsId(goodsId);
                    goodsTypeContact.setTypeId(typeId);
                    goodsTypeContact.setTypeItemId(itemId);
                    int flag3 = gtcService.insert(goodsTypeContact);
                    if (flag3 < 0) {
                        result.setFailMessage("商品属性保存失败");
                        return result;
                    }
                }

            }
        }

        result.setSuccess("商品保存");
        return result;
    }


    @ApiOperation("是否推荐/新品/热卖、排序")
    @PostMapping("/modify/sort")
    public Result modifyGoods(
            @ApiParam @RequestParam String token,
            @ApiParam("商品编号") @RequestParam(required = false) Integer goodsId,
            @ApiParam("店铺推荐") @RequestParam(required = false) Short storeIsRecommend,
            @ApiParam("店铺推荐排序") @RequestParam(required = false) Integer storeRecommendSort,
            @ApiParam("是否新品") @RequestParam(required = false) Short isNew,
            @ApiParam("店铺新品排序") @RequestParam(required = false) Integer storeNewSort,
            @ApiParam("是否热卖") @RequestParam(required = false) Short isHot,
            @ApiParam("热卖排序") @RequestParam(required = false) Integer storeHotSort,
            @ApiParam("是否上架") @RequestParam(required = false) Short isOnSale
    ) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        String message = "";
        TyGoods goods = goodsService.getById(goodsId);
        if (storeIsRecommend != null) {
            goods.setStoreIsRecommend(storeIsRecommend);
            message = "店铺推荐";
        }
        if (storeRecommendSort != null) {
            goods.setStoreRecommendSort(storeRecommendSort);
            message = "店铺推荐排序";
        }
        if (isNew != null) {
            goods.setIsNew(isNew);
            message = "新品";
        }
        if (storeNewSort != null) {
            goods.setStoreNewSort(storeNewSort);
            message = "新品排序";
        }
        if (isHot != null) {
            goods.setIsHot(isHot);
            message = "热卖";
        }
        if (storeHotSort != null) {
            goods.setStoreHotSort(storeHotSort);
            message = "热卖排序";
        }
        if (isOnSale != null) {
            goods.setIsOnSale(isOnSale);
            if (isOnSale == 1) {
                message = "上架";
            } else {
                message = "下架";
            }
        }
        Integer flag = goodsService.modify(goods);
        if (message != "") {
            if (flag > 0) {
                result.setSuccess(message + "修改");
            } else {
                result.setFail(message + "修改");
            }
        } else {
            result.setFailMessage("未选择修改项");
        }
        return result;
    }


    @ApiOperation("商品发布-商品相册-保存")
    //@RequestMapping(value = "/goodsImg/add", method = RequestMethod.POST)
    public Result addGoodsImg(
            @ApiParam @RequestParam String token,
            @ApiParam("商品相册URL: http://1.png,http://2.png") @RequestParam String goodsImgUrls,
            @ApiParam("商品编号") @RequestParam Integer goodsId
    ) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        if (goodsImgUrls == null || "".equals(goodsImgUrls)) {
            result.setMessage("商品相册为空");
        }
        List<TyGoodsImages> goodsImages = new ArrayList<>();
        TyGoodsImages tyGoodsImages = null;
        String[] goodsImgUrlList = goodsImgUrls.split(",");
        int flag = 0;
        for (String goodsImageUrl : goodsImgUrlList) {
            if (goodsImageUrl != null) {
                tyGoodsImages = new TyGoodsImages();
                tyGoodsImages.setGoodsId(goodsId);
                tyGoodsImages.setImageUrl(goodsImageUrl);
                flag = giService.insert(tyGoodsImages);
            }
        }

        if (flag > 0) {
            result.setSuccess("商品相册保存");
        } else {
            result.setFail("商品相册保存");
        }
        return result;
    }

    @ApiOperation("商品发布-商品规格-头部")
    @RequestMapping(value = "/goodsSpecs/head", method = RequestMethod.GET)
    public Result goodsSpecItems(
            @ApiParam @RequestParam String token,
            @ApiParam @RequestParam(required = false) Integer goodsId
    ) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        Integer storeId = seller.getStoreId();
        List<Map<String, Object>> mapListMapList = new ArrayList<>();
        Map<String, Object> mapListMap = null;
        List<Map<String, Object>> mapList = null;
        Map<String, Object> map = null;
        List<TySpec> specs = specService.getByStoreId(storeId);
        //System.out.println("specs::" + specs);
        Map<String, Object> valueMap = new HashMap<>();
        if (goodsId != null) {
            List<TySpecGoodsPrice> goodsPrices = sgpService.getByStoreIdGoodsId(storeId, goodsId);
            System.out.println("goodsPrices:::" + goodsPrices);
            if (goodsPrices != null) {
                for (TySpecGoodsPrice goodsPrice : goodsPrices) {
                    String keyValue = goodsPrice.getKeyValue();
                    String[] keyValueArr = keyValue.split("_");
                    if (keyValue != null && keyValueArr.length > 0) {
                        for (String value : keyValueArr) {
                            if (value != "" && !valueMap.containsKey(value)) {
                                valueMap.put(value, value);
                            }
                        }
                    }
                }
            }
        }
        System.out.println("valueMap::" + valueMap);
        if (specs != null) {
            for (TySpec spec : specs) {
                Integer specId = spec.getId();
                mapListMap = new HashMap<>();
                mapListMap.put("specId", specId.toString());
                mapListMap.put("specName", spec.getName());
                List<TySpecItem> specItems = siService.getBySpecId(specId);
                mapList = new ArrayList<>();
                if (specItems != null) {
                    for (TySpecItem specItem : specItems) {
                        map = new HashMap<>();
                        map.put("specId", specId.toString());
                        String specItemId = specItem.getId().toString();
                        map.put("itemId", specItemId);
                        map.put("itemName", specItem.getItem());
                        int isChecked = 0;
                        if (valueMap.containsKey(specItemId)) {
                            isChecked = 1;
                            map.put("isChecked", isChecked);
                            mapList.add(map);
                        }

                    }
                }
                mapListMap.put("items", mapList);
                mapListMapList.add(mapListMap);
            }
        }
        result.setData(mapListMapList);
        if (mapListMapList.size() > 0) {
            result.setSuccess("商品规格查询");
        } else {
            result.setNull("商品规格");
        }
        return result;
    }

    @ApiOperation("商品发布-商品规格-头部-查询")
    @RequestMapping(value = "/goodsSpecs/head/items", method = RequestMethod.GET)
    public Result goodsSpecItems(
            @ApiParam @RequestParam String token,
            @ApiParam @RequestParam(required = false) Integer specId,
            @ApiParam @RequestParam(required = false) String itemName
    ) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        Integer storeId = seller.getStoreId();
        List<Map<String, Object>> maps = new ArrayList<>();
        Map<String, Object> map = null;
        TySpecItem specItem = siService.getByStoreIdAndSpecIdAndItemName(storeId, specId, itemName);
        if (specItem != null) {
            map = new HashMap<>();
            map.put("itemId", specItem.getId().toString());
            map.put("specId", specItem.getSpecId().toString());
            map.put("itemName", specItem.getItem());
            maps.add(map);
        }
        result.setData(maps);

        if (maps.size() > 0) {
            result.setSuccess("规格选择项查询");
        } else {
            result.setNull("规格选择项查询");
        }
        return result;
    }

    @ApiOperation("商品发布-商品规格-头部-添加")
    @RequestMapping(value = "/goodsSpecs/head/items", method = RequestMethod.POST)
    public Result adddGoodsSpecItems(
            @ApiParam @RequestParam String token,
            @ApiParam @RequestParam(required = false) Integer specId,
            @ApiParam @RequestParam(required = false) String itemName
    ) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        if (itemName == null || "".equals(itemName.trim())) {
            result.setFailMessage("类型不能为空");
            return result;
        }
        Integer storeId = seller.getStoreId();
        TySpecItem specItem = siService.getByStoreIdAndSpecIdAndItemName(storeId, specId, itemName);
        if (specItem != null) {
            result.setFailMessage("该类型已经存在");
            return result;
        }
        specItem = new TySpecItem();
        specItem.setStoreId(storeId);
        specItem.setSpecId(specId);
        specItem.setItem(itemName);
        int flag = siService.insert(specItem);
        specItem = siService.getByStoreIdAndSpecIdAndItemName(storeId, specId, itemName);

        Map<String, Object> map = new HashMap<>();
        map.put("itemId", specItem.getId().toString());
        map.put("specId", specItem.getSpecId().toString());
        map.put("itemName", specItem.getItem());
        result.setData(map);
        if (flag > 0) {
            result.setSuccess("商品规格类型添加");
        } else {
            result.setFail("商品规格类型添加");
        }

        return result;
    }

    @ApiOperation("商品发布-商品规格-尾部000")
    @RequestMapping(value = "/goodsSpecs/foot000", method = RequestMethod.GET)
    public Result allGoodSpecItems1(
            HttpServletRequest request,
            @ApiParam @RequestParam(required = false) String token,
            @ApiParam @RequestParam(required = false) String specIds,
            @ApiParam @RequestParam(required = false) List<String> specItemsIdses,
            @ApiParam @RequestParam(required = false) Integer goodsId
    ) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }

        System.out.println("specItemsIdses:::" + specItemsIdses);

        Integer storeId = seller.getStoreId();
        Map<String, Object> resultMap = new HashMap<>();

        List<Map<String, Object>> titleMapList = new ArrayList<>();
        Map<String, Object> titleMap = null;
        List<Map<String, Object>> bodyMap = new ArrayList<>();
        Map<String, Object> map = null;
        List<TySpecGoodsPrice> specGoodsPrices = null;
        if (goodsId != null) {
            specGoodsPrices = sgpService.getByGoodsId(goodsId);
        }
        Map<Integer, Object> tempMap = new HashMap<>();
        List<Integer> tempList = null;
        if (specGoodsPrices != null) {
            System.out.println("specGoodsPrices：：" + specGoodsPrices);
            for (TySpecGoodsPrice specGoodsPrice : specGoodsPrices) {
                String key = specGoodsPrice.getKeyValue();
                String[] keyList = key.split("_");
                if (keyList != null && keyList.length > 0) {
                    for (int i = 0; i < keyList.length; i++) {
                        Integer specItemId = Integer.valueOf(keyList[i]);
                        TySpecItem specItem = null;
                        TySpec spec = null;
                        if (specItemId != null) {
                            specItem = siService.getById(specItemId);
                        }
                        if (specItem != null) {
                            spec = specService.getById(specItem.getSpecId());
                        }
                        if (spec != null) {
                            if (!tempMap.containsKey(spec.getId())) {
                                tempList = new ArrayList<>();
                                tempList.add(specItemId);
                                tempMap.put(spec.getId(), tempList);
                            } else {
                                tempList = (List<Integer>) tempMap.get(spec.getId());
                                if (!tempList.contains(specItemId)) {
                                    tempList.add(specItemId);
                                }
                                tempMap.put(spec.getId(), tempList);
                            }
                        }

                    }
                }
            }
        }
        System.out.println("tempMap::" + tempMap);

        if (specItemsIdses == null) {
            specItemsIdses = new ArrayList<>();
        }
        String[] specIdList = null;
        if (specIds != null) {
            specIdList = specIds.split(",");
        }
        if (specIdList != null && specIdList.length > 0) {
            int i;
            for (i = 0; i < specIdList.length; i++) {
                if (specIdList[i] != null && !"".equals(specIdList[i])) {
                    Integer ipecIdListInt = Integer.valueOf(specIdList[i]);
                    String specItemsIds = request.getParameter("specItemsIdses[" + ipecIdListInt + "]");
                    if (specItemsIds != null) {
                        String[] specItemsIdsArr = specItemsIds.split(",");
                        List<String> specItemsIdsList = Arrays.asList(specItemsIdsArr);
                        if (!tempMap.containsKey(ipecIdListInt)) {
                            tempMap.put(ipecIdListInt, specItemsIdsList);
                        } else {
                            tempList = (List<Integer>) tempMap.get(ipecIdListInt);
                            for (String spedItemsId : specItemsIdsList) {
                                if (spedItemsId != null && !"".equals(spedItemsId)) {

                                    tempList.add(Integer.valueOf(spedItemsId));
                                }
                            }
                            tempMap.put(ipecIdListInt, tempList);
                        }
                    }
                }

            }
        }
        System.out.println("tempMap::" + tempMap);
        Set<Integer> tempMapKeySet = tempMap.keySet();
        int k = -1;
        for (Integer specId : tempMapKeySet) {
            k++;
            TySpec spec = specService.getById(specId);
            if (spec != null) {
                titleMap = new HashMap<>();
                titleMap.put("title", spec.getName());
                titleMap.put("specId", spec.getId().toString());
                titleMap.put("dataIndex", "body" + k);
                titleMap.put("key", "body" + k);
                titleMap.put("isEdit", "0");
                titleMapList.add(titleMap);
            }
            StringBuffer sb = new StringBuffer();
            List<Object> object = (List<Object>) tempMap.get(specId);
            System.out.println("tempList1::" + object);
            for (int z = 0; z < object.size(); z++) {
                Object integer = object.get(z);
                sb.append(integer).append(",");
            }
            specItemsIdses.add(sb.toString());

        }
        titleMap = new HashMap<>();
        titleMap.put("title", "价格");
        titleMap.put("dataIndex", "price");
        titleMap.put("key", "price");
        titleMap.put("isEdit", "1");
        titleMapList.add(titleMap);
        titleMap = new HashMap<>();
        titleMap.put("title", "库存");
        titleMap.put("dataIndex", "count");
        titleMap.put("key", "count");
        titleMap.put("isEdit", "1");
        titleMapList.add(titleMap);


        List<String> keyList = DescartesUtil.descartesNew(specItemsIdses);
        List<String> keyList1 = DescartesUtil.descartesNew1(specItemsIdses);
        System.out.println("keyList::" + keyList);
        if (keyList != null && keyList.size() > 0) {
            for (int i = 0; i < keyList.size(); i++) {
                System.out.println("i:" + i);
                String key = keyList.get(i);
                if (key == null || "".equals(key)) {
                    continue;
                }
                map = new HashMap<>();
                System.out.println(key);
                String[] specItemsIds = key.split("_");

                String bodyName = "";
                for (int j = 0; j < specItemsIds.length; j++) {
                    Integer specItemsId = Integer.valueOf(specItemsIds[j]);
                    TySpecItem specItem = siService.getById(specItemsId);
                    TySpec spec = specService.getById(specItem.getSpecId());
                    if (specItem != null) {
                        bodyName += titleMapList.get(j).get("title") + ":" + specItem.getItem() + ",";
                        map.put("body" + j, specItem.getItem());
                        map.put("specId", spec.getId());
                    } else {
                        map.put("body" + j, "");
                        map.put("specId", "");
                    }
                }

                map.put("keyName", bodyName);
                map.put("keyValue", keyList1.get(i));
                System.out.println("storeId  " + storeId + " " + goodsId + " " + keyList1.get(i));
                TySpecGoodsPrice goodsPrice = sgpService.getByStoreIdGoodsIdAndKeyValue(storeId, goodsId, keyList1.get(i));
                System.out.println("goodsPrice：：" + goodsPrice);

                if (goodsPrice != null) {
                    map.put("price", goodsPrice.getPrice().toString());
                    map.put("count", goodsPrice.getStoreCount().toString());
                } else {
                    map.put("price", "0");
                    map.put("count", "0");
                }

                map.put("key", i);
                bodyMap.add(map);
            }
        }

        resultMap.put("title", titleMapList);
        resultMap.put("body", bodyMap);
        result.setData(resultMap);
        if (!resultMap.isEmpty()) {
            result.setSuccess("商品规格尾部查询");
        } else {
            result.setNull("商品规格尾部查询");
        }

        return result;
    }


    @ApiOperation("商品发布-商品规格-尾部111")
    @RequestMapping(value = "/goodsSpecs/foot111", method = RequestMethod.GET)
    public Result allGoodSpecItems(
            HttpServletRequest request,
            @ApiParam @RequestParam(required = false) String token,
            @ApiParam @RequestParam(required = false) Integer goodsId,
            @ApiParam @RequestParam(required = false) JSONArray specItemsIdses
    ) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }

        Integer storeId = seller.getStoreId();
        Map<String, Object> resultMap = new HashMap<>();

        List<Map<String, Object>> titleMapList = new ArrayList<>();
        Map<String, Object> titleMap = null;
        List<Map<String, Object>> bodyMap = new ArrayList<>();
        Map<String, Object> map = null;
        List<TySpecGoodsPrice> specGoodsPrices = null;
        if (goodsId != null) {
            specGoodsPrices = sgpService.getByGoodsId(goodsId);
        }
        Map<Integer, Object> tempMap = new HashMap<>();
        List<Integer> tempList = null;
        if (specGoodsPrices != null) {
            System.out.println("specGoodsPrices：：" + specGoodsPrices);
            for (TySpecGoodsPrice specGoodsPrice : specGoodsPrices) {
                String key = specGoodsPrice.getKeyValue();
                String[] keyList = key.split("_");
                if (keyList != null && keyList.length > 0) {
                    for (int i = 0; i < keyList.length; i++) {
                        Integer specItemId = Integer.valueOf(keyList[i]);
                        TySpecItem specItem = null;
                        TySpec spec = null;
                        if (specItemId != null) {
                            specItem = siService.getById(specItemId);
                        }
                        if (specItem != null) {
                            spec = specService.getById(specItem.getSpecId());
                        }
                        if (spec != null) {
                            if (!tempMap.containsKey(spec.getId())) {
                                tempList = new ArrayList<>();
                                tempList.add(specItemId);
                                tempMap.put(spec.getId(), tempList);
                            } else {
                                tempList = (List<Integer>) tempMap.get(spec.getId());
                                if (!tempList.contains(specItemId)) {
                                    tempList.add(specItemId);
                                }
                                tempMap.put(spec.getId(), tempList);
                            }
                        }

                    }
                }
            }
        }

        if (specItemsIdses == null) {
            specItemsIdses = new JSONArray();
        }
        String[] specIdList = null;
//        if (specIds != null) {
//            specIdList = specIds.split(",");
//        }
        if (specIdList != null && specIdList.length > 0) {
            int i;
            for (i = 0; i < specIdList.length; i++) {
                Integer ipecIdListInt = Integer.valueOf(specIdList[i]);
                TySpec spec = specService.getById(ipecIdListInt);
                if (spec != null) {
                    titleMap = new HashMap<>();
                    titleMap.put("title", spec.getName());
                    titleMap.put("dataIndex", "body" + i);
                    titleMap.put("key", "body" + i);
                    titleMap.put("isEdit", "0");
                    titleMapList.add(titleMap);
                }
                String specItemsIds = request.getParameter("specItemsIdses[" + ipecIdListInt + "]");
                specItemsIdses.add(specItemsIds);

            }
            titleMap = new HashMap<>();
            titleMap.put("title", "价格");
            titleMap.put("dataIndex", "price");
            titleMap.put("key", "price");
            titleMap.put("isEdit", "1");
            titleMapList.add(titleMap);
            titleMap = new HashMap<>();
            titleMap.put("title", "库存");
            titleMap.put("dataIndex", "count");
            titleMap.put("key", "count");
            titleMap.put("isEdit", "1");
            titleMapList.add(titleMap);
            resultMap.put("count", specIdList.length);
        } else {
            resultMap.put("count", 0);
        }

        List<String> keyList = DescartesUtil.descartesNew(new ArrayList<>());
        System.out.println("keyList::" + keyList);
        if (keyList != null && keyList.size() > 0) {
            for (int i = 0; i < keyList.size(); i++) {
                System.out.println("i:" + i);
                String key = keyList.get(i);
                if (key == null || "".equals(key)) {
                    continue;
                }
                map = new HashMap<>();
                System.out.println(key);
                String[] specItemsIds = key.split("_");

                String bodyName = "";
                for (int j = 0; j < specItemsIds.length; j++) {
                    Integer specItemsId = Integer.valueOf(specItemsIds[j]);
                    TySpecItem specItem = siService.getById(specItemsId);
                    if (specItem != null) {

                        bodyName += titleMapList.get(j).get("title") + ":" + specItem.getItem() + ",";
                        map.put("body" + j, specItem.getItem());
                    } else {
                        map.put("body" + j, "");
                    }
                }

                map.put("keyName", bodyName);
                map.put("keyValue", key);
                TySpecGoodsPrice goodsPrice = sgpService.getByStoreIdGoodsIdAndKeyValue(storeId, goodsId, key);
                if (goodsPrice != null) {
                    map.put("price", goodsPrice.getPrice());
                    map.put("count", goodsPrice.getStoreCount());
                } else {
                    map.put("price", 0);
                    map.put("count", 0);
                }

                map.put("key", i);
                bodyMap.add(map);
            }
        }

        resultMap.put("title", titleMapList);
        resultMap.put("body", bodyMap);
        result.setData(resultMap);
        if (!resultMap.isEmpty()) {
            result.setSuccess("商品规格尾部查询");
        } else {
            result.setNull("商品规格尾部查询");
        }

        return result;
    }

    @ApiOperation("商品发布-商品规格-尾部")
    @RequestMapping(value = "/goodsSpecs/foot", method = RequestMethod.POST)
    public Result allGoodSpecItemsFoot2(@RequestBody SpecItems specItems
    ) {
        Result result = new Result();
        if(specItems==null){
            result.setFailMessage("提交数据为空");
            return result;
        }

        String token = specItems.getToken();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }

        Integer storeId = seller.getStoreId();
        Map<String, Object> resultMap = new HashMap<>();

        List<Map<String, Object>> titleMapList = new ArrayList<>();
        Map<String, Object> titleMap = null;
        List<Map<String, Object>> bodyMap = new ArrayList<>();
        Map<String, Object> map = null;

        Map<String, Object> tempMap = new HashMap<>();
        System.out.println("specItems::" + specItems);
        Integer goodsId = specItems.getGoodsId();

        List<Integer> specIdList = new ArrayList<>();
        List<String> specItemsIdsList = new ArrayList<>();

        List<Map<String, Object>> specItemsIdses = specItems.getSpecItemsIdses();

        System.out.println("specItemsIdses::" + specItemsIdses);

        if (specItemsIdses != null && specItemsIdses.size() > 0) {
            for (Map<String, Object> specItemsIds : specItemsIdses) {
                String specId = (String) specItemsIds.get("specId");
                List<String> itemIds = (List<String>) specItemsIds.get("itemIds");
                if (!tempMap.containsKey(specId)) {
                    tempMap.put(specId, itemIds);
                }

            }
        }
        System.out.println("tempMap:::" + tempMap);


        System.out.println("specIdList::" + specIdList);
        System.out.println("specItemsIdsList::" + specItemsIdsList);


        Set<String> tempMapKeySet = tempMap.keySet();
        int k = -1;
        for (String specIdStr : tempMapKeySet) {
            Integer specId = Integer.valueOf(specIdStr);
            k++;
            TySpec spec = specService.getById(specId);
            if (spec != null) {
                titleMap = new HashMap<>();
                titleMap.put("title", spec.getName());
                titleMap.put("specId", spec.getId().toString());
                titleMap.put("dataIndex", "body" + k);
                titleMap.put("key", "body" + k);
                titleMap.put("isEdit", "0");
                titleMapList.add(titleMap);
            }
            StringBuffer sb = new StringBuffer();
            List<String> object = (List<String>) tempMap.get(specIdStr);
            System.out.println("tempList1::" + object);
            for (int z = 0; z < object.size(); z++) {
                Object integer = object.get(z);
                sb.append(integer).append(",");
            }
            specItemsIdsList.add(sb.toString());

        }
        titleMap = new HashMap<>();
        titleMap.put("title", "价格");
        titleMap.put("dataIndex", "price");
        titleMap.put("key", "price");
        titleMap.put("isEdit", "1");
        titleMapList.add(titleMap);
        titleMap = new HashMap<>();
        titleMap.put("title", "库存");
        titleMap.put("dataIndex", "count");
        titleMap.put("key", "count");
        titleMap.put("isEdit", "1");
        titleMapList.add(titleMap);


        List<String> keyList = DescartesUtil.descartesNew(specItemsIdsList);
        List<String> keyList1 = DescartesUtil.descartesNew1(specItemsIdsList);
        System.out.println("keyList::" + keyList);
        if (keyList != null && keyList.size() > 0) {
            for (int i = 0; i < keyList.size(); i++) {
                System.out.println("i:" + i);
                String key = keyList.get(i);
                if (key == null || "".equals(key)) {
                    continue;
                }
                map = new HashMap<>();
                System.out.println(key);
                String[] specItemsIds = key.split("_");

                String bodyName = "";
                for (int j = 0; j < specItemsIds.length; j++) {
                    Integer specItemsId = Integer.valueOf(specItemsIds[j]);
                    TySpecItem specItem = siService.getById(specItemsId);
                    TySpec spec = specService.getById(specItem.getSpecId());
                    if (specItem != null) {
                        bodyName += titleMapList.get(j).get("title") + ":" + specItem.getItem() + ",";
                        map.put("body" + j, specItem.getItem());
                        map.put("specId", spec.getId());
                    } else {
                        map.put("body" + j, "");
                        map.put("specId", "");
                    }
                }

                map.put("keyName", bodyName);
                map.put("keyValue", keyList1.get(i));
                System.out.println("storeId  " + storeId + " " + goodsId + " " + keyList1.get(i));
                TySpecGoodsPrice goodsPrice = sgpService.getByStoreIdGoodsIdAndKeyValue(storeId, goodsId, keyList1.get(i));
                System.out.println("goodsPrice：：" + goodsPrice);

                if (goodsPrice != null) {
                    map.put("price", goodsPrice.getPrice().toString());
                    map.put("count", goodsPrice.getStoreCount().toString());
                } else {
                    map.put("price", "0");
                    map.put("count", "0");
                }

                map.put("key", i);
                bodyMap.add(map);
            }
        }


        resultMap.put("title", titleMapList);
        resultMap.put("body", bodyMap);
        result.setData(resultMap);
        result.setSuccess("查询");

        return result;
    }

    @ApiOperation("商品发布-商品规格-保存添加")
    ////@RequestMapping(value = "/goodsSpec/add", method = RequestMethod.POST)
    public Result addGoodSpecs(
            @ApiParam @RequestParam String token,
            @ApiParam("商品规格：选中的list") @RequestParam String specList
    ) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }

        TySpecGoodsPrice goodsPrice = null;
        // TODO: 2020/9/23  商品规格的添加
        int flag = 0;
        if (goodsPrice != null) {

            flag = sgpService.insert(goodsPrice);
        }
        if (flag > 0) {
            result.setSuccess("商品规格添加");
        } else {
            result.setFail("商品规格添加");
        }
        return result;
    }


    @ApiOperation("商品发布-商品属性")
    @RequestMapping(value = "/goodsTypes", method = RequestMethod.GET)
    public Result allGoodsTypes(
            @ApiParam @RequestParam String token,
            @ApiParam @RequestParam(required = false) Short catId1,
            @ApiParam @RequestParam(required = false) Short catId2,
            @ApiParam @RequestParam(required = false) Short catId3,
            @ApiParam @RequestParam(required = false) Integer goodsId
    ) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        List<Map<String, Object>> mapListMapList = new ArrayList<>();
        Map<String, Object> mapListMap = null;
        List<Map<String, Object>> mapList = null;
        Map<String, Object> map = null;

        List<TyGoodsType> goodsTypes = null;
        if (catId1 != null || catId2 != null || catId3 != null) {
            goodsTypes = gtService.getByCatIds(catId1, catId2, catId3);
        }

        if (goodsTypes != null && goodsTypes.size() > 0) {
            for (TyGoodsType goodsType : goodsTypes) {
                mapListMap = new HashMap<>();
                Short typeId = goodsType.getId();
                mapListMap.put("typeId", typeId.toString());
                mapListMap.put("typeName", goodsType.getName());
                mapList = new ArrayList<>();
                map = new HashMap<>();
                map.put("itemId", "0");
                map.put("typeId", typeId.toString());
                map.put("itemName", "请选择");
                mapList.add(map);
                List<TyGoodsTypeItem> goodsTypeItems = gtiService.getByTypeId(typeId);
                if (goodsTypeItems != null) {
                    for (TyGoodsTypeItem goodsTypeItem : goodsTypeItems) {
                        map = new HashMap<>();
                        Integer itemId = goodsTypeItem.getId();
                        map.put("typeId", typeId.toString());
                        map.put("itemId", itemId.toString());
                        map.put("itemName", goodsTypeItem.getItem());
                        int isChecked = 0;
                        if (goodsId != null) {
                            TyGoodsTypeContact goodsTypeContact = gtcService.getByGoodsIdAndTypeIdAndItemId(goodsId, typeId.intValue(), itemId);
                            if (goodsTypeContact != null) {
                                isChecked = 1;
                            }
                        }


                        map.put("isChecked", isChecked);
                        mapList.add(map);
                    }
                }
                mapListMap.put("items", mapList);
                mapListMapList.add(mapListMap);
            }
        }
        result.setData(mapListMapList);
        if (mapListMapList.size() > 0) {
            result.setSuccess("商品属性查询");
        } else {
            result.setNull("商品属性");
        }
        return result;
    }

    @ApiOperation("商品发布-商品属性-保存")
    //@RequestMapping(value = "/goodsType/add", method = RequestMethod.POST)
    public Result addGoodType(
            @ApiParam @RequestParam String token,
            @ApiParam @RequestParam Integer goodsId,
            @ApiParam("商品属性：[{typeId=1,typeItemId=1}...]") @RequestParam String typeItems
    ) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        JSONArray jsonArray = JSONArray.parseArray(typeItems);
        List<TyGoodsTypeContact> goodsTypeContactList = new ArrayList<>();
        TyGoodsTypeContact goodsTypeContact = null;
        Integer flag = null;
        if (jsonArray != null) {
            for (Object object : jsonArray) {
                JSONObject jsonObject = (JSONObject) object;
                Integer typeId = (Integer) jsonObject.get("typeId");
                Integer typeItemId = (Integer) jsonObject.get("typeItemId");
                goodsTypeContact = new TyGoodsTypeContact();
                goodsTypeContact.setGoodsId(goodsId);
                goodsTypeContact.setTypeId(typeId);
                goodsTypeContact.setTypeItemId(typeItemId);
                goodsTypeContactList.add(goodsTypeContact);
            }
            flag = gtcService.insertList(goodsTypeContactList);
        }
        if (flag > 0) {
            result.setSuccess("商品属性添加");
        } else {
            result.setFail("商品属性添加");
        }
        return result;
    }


    @ApiOperation("出售中/仓库的商品")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Result allGoodOnSaleOrHouse(
            @ApiParam @RequestParam(required = false) String token,
            @ApiParam @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @ApiParam @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @ApiParam("商品类型，1出售中，0仓库中") @RequestParam(required = false) Short isOnSale,
            @ApiParam("分类编号") @RequestParam(required = false) Integer catId,
            @ApiParam("0全部，1新品，2推荐") @RequestParam(required = false) String type,
            @ApiParam("-1全部，0现货，1到期") @RequestParam(required = false) Short existType,
            @ApiParam("上架时间") @RequestParam(required = false) String time,
            @ApiParam("商品关键词") @RequestParam(required = false) String keywords
    ) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        Integer storeId = seller.getStoreId();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<TyGoods> goods = null;
        PageHelper.startPage(pageNum, pageSize);
        if (catId != null || type != null || existType != null || time != null || keywords != null) {
            goods = goodsService.getByParametersAndTime(storeId, catId, type, existType, keywords, time, isOnSale);
        } else if (isOnSale != null) {
            goods = goodsService.getByStoreIdAndIsOnSale(storeId, isOnSale);
        } else {
            goods = goodsService.getByStoreId(storeId);
        }
        List<Map<String, Object>> maps = new ArrayList<>();
        Map<String, Object> map = null;
        System.out.println("goods::" + goods.size());
        PageInfo<TyGoods> page = new PageInfo<>(new ArrayList<>());
        if (goods != null) {
            int i = 0;
            page = new PageInfo<>(goods);
            for (TyGoods good : goods) {
                System.out.println(i++);
                map = new HashMap<>();
                map.put("checked", false);
                //编号
                map.put("goodsId", good.getGoodsId().toString());
                //名称
                map.put("goodsName", good.getGoodsName());
                //货号
                map.put("goodsSn", good.getGoodsSn());
                //分类
                Integer catId1 = good.getCatId1();
                TyGoodsCategory goodsCategory = gcService.getById(catId1);
                if (goodsCategory != null) {
                    map.put("category", goodsCategory.getName());
                } else {
                    map.put("category", "");
                }
                map.put("existType", good.getExistType() == 1 ? "期货" : "现货");
                //价格
                map.put("shopPrice", good.getCostPrice().toString());
                map.put("costPrice", good.getCostPrice().toString());
                //店铺推荐
                map.put("storeIsRecommend", good.getStoreIsRecommend());
                //店铺推荐排序
                map.put("storeRecommendSort", good.getStoreRecommendSort());
                //是否新品
                map.put("isNew", good.getIsNew());
                //店铺新品排序
                map.put("storeNewSort", good.getStoreNewSort());
                //是否热卖
                map.put("isHot", good.getIsHot());
                //热卖排序
                map.put("storeHotSort", good.getStoreHotSort());
                map.put("isOnSale", good.getIsOnSale());
                //库存数量
                map.put("storeCount", good.getStoreCount());
                //上架时间
                Integer onTime = good.getOnTime();
                if (onTime != null && onTime != 0) {
                    map.put("onTime", sdf.format(IntegerUtil.timeToLong(onTime)));
                } else {
                    map.put("onTime", "");
                }
                Short goodsState = good.getGoodsState();
                if (goodsState == 1) {
                    map.put("goodsState", "审核通过");
                } else if (goodsState == 2) {
                    map.put("goodsState", "审核失败");
                } else if (goodsState == 3) {
                    map.put("goodsState", "违规下架");
                } else {
                    map.put("goodsState", "待审核");
                }
                maps.add(map);
            }
        }
        result.setPage(page);
        result.setData(maps);

        if (maps.size() > 0) {
            result.setSuccess("商品查询");
        } else {
            result.setNull("商品");
        }
        return result;
    }

    @ApiOperation("商品回收站")
    @RequestMapping(value = "/recycleGoods", method = RequestMethod.GET)
    public Result allGoodOnRecycle(
            @ApiParam @RequestParam String token,
            @ApiParam @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @ApiParam("页面大小") @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @ApiParam("分类编号") @RequestParam(required = false) Integer catId,
            @ApiParam("0待审核1审核通过2审核失败3违规下架") @RequestParam(required = false) Short goodsState,
            @ApiParam("商品名称") @RequestParam(required = false) String goodsName
    ) {
        Result result = new Result();
        Map<String, Object> resultMap = new HashMap<>();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Integer storeId = seller.getStoreId();
        List<TyGoods> goods = null;
        PageHelper.startPage(pageNum, pageSize);
        if (catId != null || goodsState != null || goodsName != null) {
            goods = goodsService.getByStoreIdAndCatIdAndGoodsStateAndGoodsName(storeId, catId, goodsState, goodsName);
        } else {
            Short isDel = 1;
            goods = goodsService.getByStoreIdAndIsDel(storeId, isDel);
        }
        PageInfo<TyGoods> page = new PageInfo<>(new ArrayList<>());
        List<Map<String, Object>> maps = new ArrayList<>();
        Map<String, Object> map = null;
        if (goods != null) {
            page = new PageInfo<>(goods);
            for (TyGoods good : goods) {
                map = new HashMap<>();
                map.put("checked", false);
                //编号
                map.put("goodsId", good.getGoodsId());
                //名称
                map.put("goodsName", good.getGoodsName());
                //货号
                map.put("goodsSn", good.getGoodsName());
                //分类
                Integer catId1 = good.getCatId1();
                TyGoodsCategory goodsCategory = gcService.getById(catId1);
                if (goodsCategory != null) {
                    map.put("category", goodsCategory.getName());
                } else {
                    map.put("category", "");
                }
                //价格
                map.put("shopPrice", good.getShopPrice().toString());
                //是否店铺推荐
                map.put("storeIsRecommend", good.getStoreIsRecommend());
                //店铺推荐排序
                map.put("storeRecommendSort", good.getStoreRecommendSort());
                //是否新品
                map.put("isNew", good.getIsNew());
                //新品排序
                map.put("storeNewSort", good.getStoreNewSort());
                //是否热卖
                map.put("isHot", good.getIsHot());
                //热卖排序
                map.put("storeHotSort", good.getStoreHotSort());
                //库存数量
                map.put("storeCount", good.getStoreCount());
                //上、下架
                map.put("isOnSale", good.getIsOnSale());
                if (goodsState == null) {
                    goodsState = good.getGoodsState();
                }
                if (goodsState == 0) {
                    map.put("goodsState", "待审核");
                } else if (goodsState == 1) {
                    map.put("goodsState", "审核通过");
                } else if (goodsState == 2) {
                    map.put("goodsState", "审核失败");
                } else if (goodsState == 3) {
                    map.put("goodsState", "违规下架");
                } else {
                    map.put("goodsState", "");
                }
                maps.add(map);
            }
        }
        resultMap.put("page", page);
        resultMap.put("list", maps);
        // TODO: 2020/11/3  分页
        result.setPage(page);
        //result.setData(resultMap);
        result.setData(maps);
        if (maps.size() > 0) {
            result.setSuccess("商品查询");
        } else {
            result.setNull("商品");
        }
        return result;
    }

    @ApiOperation("批量或单个删除到回收站或者还原")
    @RequestMapping(value = "/isDel", method = RequestMethod.GET)
    public Result isDelGood(
            @ApiParam @RequestParam String token,
            @ApiParam("商品编号列表：1,2,3,4") @RequestParam String ids,
            @ApiParam("0还原，1删除") @RequestParam Short isDel
    ) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        String[] idList = null;
        if (ids != null) {
            idList = ids.split(",");
        }
        Integer flag = null;
        if (idList != null) {
            for (String id : idList) {
                System.out.println("id:::" + id);
                if (id != null && !"".equals(id.trim())) {
                    TyGoods goods = goodsService.getById(Integer.valueOf(id.trim()));
                    goods.setIsDel(isDel);
                    flag = goodsService.modify(goods);
                }
            }
        }

        if (isDel == 1) {
            if (flag > 0) {
                result.setSuccess("商品删除");
            } else {
                result.setFail("商品删除");
            }
        } else {
            if (flag > 0) {
                result.setSuccess("商品还原");
            } else {
                result.setFail("商品还原");
            }
        }
        return result;
    }

    @ApiOperation("批量或者单个彻底删除商品")
    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    public Result isDelGood(
            @ApiParam @RequestParam String token,
            @ApiParam("商品编号：1,2,3,4") @RequestParam String ids
    ) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        String[] idList = null;
        if (ids != null) {
            idList = ids.split(",");
        }
        Integer flag = null;
        if (idList != null) {
            for (String id : idList) {
                if (id != null && !"".equals(id.trim())) {
                    flag = goodsService.deleteById(Integer.valueOf(id.trim()));
                }
            }
        }

        if (flag > 0) {
            result.setSuccess("商品删除");
        } else {
            result.setFail("商品删除");
        }
        return result;
    }

    @ApiOperation("商品详情")
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public Result detailGoods(
            @ApiParam @RequestParam String token,
            @ApiParam("商品编号") @RequestParam(required = false) Integer id
    ) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        Map<String, Object> resultMap = new HashMap<>();
        if (id == null) {
            result.setFailMessage("输入id");
            return result;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //通用信息
        TyGoods good = goodsService.getById(id);
        if (good != null) {
            Map<String, Object> map = new HashMap<>();
            //编号
            map.put("goodsId", good.getGoodsId());
            //名称
            map.put("goodsName", good.getGoodsName());
            map.put("spu", good.getSpu());
            map.put("sku", good.getSku());
            map.put("styleId", good.getStyleId().toString());
            map.put("catId1", good.getCatId1().toString());
            map.put("catId2", good.getCatId2().toString());
            map.put("catId3", good.getCatId3().toString());
            map.put("partnerPrice", good.getPartnerPrice().toString());
            map.put("distribut", good.getDistribut().toString());
            map.put("originalImg", good.getOriginalImg());
            map.put("batchQuantity", good.getBatchQuantity());
            map.put("tips", good.getTips());
            map.put("storeCount", good.getStoreCount());
            map.put("keywords", good.getKeywords());
            map.put("content", good.getGoodsContent());
            map.put("sort", good.getSort().toString());
            map.put("giveIntegral", good.getGiveIntegral().toString());
            map.put("exchangeIntegral", good.getExchangeIntegral().toString());
            //货号
            map.put("goodsSn", good.getGoodsSn());
            //分类
            Integer catId1 = good.getCatId1();
            TyGoodsCategory goodsCategory = gcService.getById(catId1);
            if (goodsCategory != null) {
                map.put("category", goodsCategory.getName());
            } else {
                map.put("category", "");
            }
            map.put("existType", good.getExistType());
            //价格
            map.put("shopPrice", good.getShopPrice().toString());
            map.put("costPrice", good.getCostPrice().toString());
            //店铺推荐
            map.put("storeIsRecommend", good.getStoreIsRecommend());
            //店铺推荐排序
            map.put("storeRecommendSort", good.getStoreRecommendSort());
            //是否新品
            map.put("isNew", good.getIsNew());
            //店铺新品排序
            map.put("storeNewSort", good.getStoreNewSort());
            //是否热卖
            map.put("isHot", good.getIsHot());
            //热卖排序
            map.put("storeHotSort", good.getStoreHotSort());
            //库存数量
            map.put("storeCount", good.getStoreCount());
            //上架时间
            Integer onTime = good.getOnTime();
            if (onTime != null && onTime != 0) {
                map.put("onTime", sdf.format(IntegerUtil.timeToLong(onTime)));
            } else {
                map.put("onTime", "");
            }
            Short goodsState = good.getGoodsState();
            if (goodsState == 1) {
                map.put("goodsState", "审核通过");
            } else if (goodsState == 2) {
                map.put("goodsState", "审核失败");
            } else if (goodsState == 3) {
                map.put("goodsState", "违规下架");
            } else {
                map.put("goodsState", "待审核");
            }
            resultMap.put("goods", map);
        } else {
            resultMap.put("goods", "");
        }
        //相册
        List<TyGoodsImages> goodsImages = giService.getByGoodsId(id);
        if (goodsImages != null) {

            resultMap.put("goodsImages", goodsImages);
        } else {
            resultMap.put("goodsImages", "");
        }

        List<TySpecGoodsPrice> goodsPrices = sgpService.getByGoodsId(id);
        if (goodsPrices != null) {
            resultMap.put("goodsSpecs", goodsPrices);
        } else {
            resultMap.put("goodsSpecs", "");
        }


        List<TyGoodsTypeContact> goodsTypeContacts = gtcService.getByGoodsId(id);
        if (goodsTypeContacts != null) {
            resultMap.put("goodsType", goodsTypeContacts);
        } else {
            resultMap.put("goodsType", "");
        }
        result.setData(resultMap);
        if (!resultMap.isEmpty()) {
            result.setSuccess("商品详情查询");
        } else {
            result.setFail("商品详情查询");
        }
        return result;
    }

    @ApiOperation("导出excel")
    @GetMapping("/toExcel")
    public Result toExcel(
            @ApiParam @RequestParam String token,
            @ApiParam("分类编号") @RequestParam(required = false) Integer catId,
            @ApiParam("0待审核1审核通过2审核失败3违规下架") @RequestParam(required = false) Short goodsState,
            @ApiParam("商品名称") @RequestParam(required = false) String goodsName,
            HttpServletResponse response

    ) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Integer storeId = seller.getStoreId();
        List<TyGoods> goods = null;
        if (catId != null || goodsState != null || goodsName != null) {
            goods = goodsService.getByStoreIdAndCatIdAndGoodsStateAndGoodsName(storeId, catId, goodsState, goodsName);
        } else {
            Short isDel = 1;
            goods = goodsService.getByStoreIdAndIsDel(storeId, isDel);
        }
        System.out.println("goods:::"+goods);
        try {
            // 导出excel
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode("商品列表.xls", "UTF-8"));
            response.setContentType("application/x-excel;charset=UTF-8");
            OutputStream outputStream = response.getOutputStream();
            Integer flag = goodsService.exportToExcel(goods, outputStream);
            if (flag > 0) {
                result.setSuccess("商品列表导出");
            } else {
                response.setContentType("application/json;charset=UTF-8");
                result.setFail("商品列表导出");
                return result;
            }
        } catch (IOException e) {
            result.setFail("商品列表");
        }

        return null;
    }

}
