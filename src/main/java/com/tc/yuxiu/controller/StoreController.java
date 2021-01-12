package com.tc.yuxiu.controller;

import com.tc.yuxiu.model.Result;
import com.tc.yuxiu.model.TySeller;
import com.tc.yuxiu.model.TyStore;
import com.tc.yuxiu.model.TyStoreGoodsClass;
import com.tc.yuxiu.service.TySellerService;
import com.tc.yuxiu.service.TyStoreGoodsClassService;
import com.tc.yuxiu.service.TyStoreService;
import com.tc.yuxiu.util.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author DELL
 * @date 2020/9/16 14:45
 */
@Api(tags = "店铺管理")
@RestController
@RequestMapping(value = "/store")
public class StoreController {

    @Autowired
    private TySellerService sellerService;
    @Autowired
    private TyStoreService storeService;
    @Autowired
    private TyStoreGoodsClassService sgcService;

    @ApiOperation("店铺设置-详情")
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public Result detailStore(@ApiParam @RequestParam String token) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        //店铺编号
        Integer storeId = seller.getStoreId();
        TyStore store = storeService.getById(storeId);
        if (store == null) {
            result.setFail("店铺信息查询");
            return result;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("storeId", store.getStoreId().toString());
        map.put("storeName", store.getStoreName());
        map.put("storeLogo", store.getStoreLogo());
        map.put("storeAvatar", store.getStoreAvatar());
        map.put("storePhone", store.getStorePhone());
        map.put("storeQQ", store.getStoreQq());
        map.put("realName", store.getRealName());
        map.put("provinceId", store.getProvinceId().toString());
        map.put("cityId", store.getCityId().toString());
        map.put("district", store.getDistrict().toString());
        map.put("storeAddress", store.getStoreAddress());
        map.put("storeDescription", store.getStoreDescription());
        result.setData(map);
        if (!map.isEmpty()) {
            result.setSuccess("店铺信息查询");
        } else {
            result.setFail("店铺信息查询");
        }
        return result;
    }

    @ApiOperation("店铺设置-保存")
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public Result modifyStore(
            @ApiParam @RequestParam String token,
            @ApiParam("店铺名") @RequestParam(required = false) String storeName,
            @ApiParam("寄件人") @RequestParam(required = false) String realName,
            @ApiParam("店铺电话") @RequestParam(required = false) String storePhone,
            @ApiParam("qq") @RequestParam(required = false) String storeQQ,
            @ApiParam("省") @RequestParam(required = false) Integer provinceId,
            @ApiParam("市") @RequestParam(required = false) Integer cityId,
            @ApiParam("区") @RequestParam(required = false) Integer district,
            @ApiParam("地址") @RequestParam(required = false) String storeAddress,
            @ApiParam("描述") @RequestParam(required = false) String storeDescription,
            @ApiParam(value = "店铺logo") @RequestParam(required = false) String storeLogo,
            @ApiParam(value = "品牌头像") @RequestParam(required = false) String storeAvatar
    ) {
        Result result = new Result();
        Map<String, Object> resultMap = new HashMap<>();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        TyStore store = storeService.getById(seller.getStoreId());
        if (storeLogo != null && !"".equals(storeLogo)) {
            store.setStoreLogo(storeLogo);
        }
        if (storeAvatar != null && !"".equals(storeAvatar)) {
            store.setStoreAvatar(storeAvatar);
        }
        if (storeName != null && !"".equals(storeName.trim())) {
            store.setStoreName(storeName);
        }
        if (realName != null && !"".equals(realName.trim())) {
            store.setRealName(realName);
        }
        if (storePhone != null && !"".equals(storePhone.trim())) {
            store.setStorePhone(storePhone);
        }
        if (storeQQ != null && !"".equals(storeQQ.trim())) {
            store.setStoreQq(storeQQ);
        }
        if (provinceId != null && provinceId != 0) {
            store.setProvinceId(provinceId);
        }
        if (cityId != null && cityId != 0) {
            store.setCityId(cityId);
        }
        if (district != null && district != 0) {
            store.setDistrict(district);
        }
        if (storeAddress != null && !"".equals(storeAddress.trim())) {
            store.setStoreAddress(storeAddress);
        }

        if (storeDescription != null && !"".equals(storeDescription.trim())) {
            store.setStoreDescription(storeDescription);
        }
        Integer flag = storeService.modify(store);
        if (flag > 0) {
            result.setSuccess("店铺信息修改");
        } else {
            result.setFail("店铺信息修改");
        }
        return result;
    }


    @ApiOperation("店铺分类-所有")
    @RequestMapping(value = "/goodsClasses", method = RequestMethod.GET)
    public Result allStoreGoodsClass(@ApiParam @RequestParam String token) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        Integer storeId = seller.getStoreId();

        List<Map<String, Object>> maps = new ArrayList<>();
        Map<String, Object> map = null;
        List<TyStoreGoodsClass> storeGoodsClasses = sgcService.getByStoreId(storeId);
        if (storeGoodsClasses != null) {
            for (TyStoreGoodsClass goodsClass : storeGoodsClasses) {
                map = new HashMap<>();
                map.put("catId", goodsClass.getCatId());
                map.put("catName", goodsClass.getCatName());
                Short isShow = goodsClass.getIsShow();
                if (isShow == 1) {
                    map.put("isShow", "是");
                } else {
                    map.put("isShow", "否");
                }
                Short isNavShow = goodsClass.getIsNavShow();
                if (isNavShow == 1) {
                    map.put("isNavShow", "是");
                } else {
                    map.put("isNavShow", "否");
                }
                Short isRecommend = goodsClass.getIsRecommend();
                if (isRecommend == 1) {
                    map.put("isRecommend", "是");
                } else {
                    map.put("isRecommend", "否");
                }
                map.put("catSort", goodsClass.getCatSort());
                maps.add(map);
            }
        }
        result.setData(maps);
        if (maps.size() > 0) {
            result.setSuccess("店铺商品分类查询");
        } else {
            result.setNull("店铺商品分类列表");
        }
        return result;
    }

    @ApiOperation("店铺分类-删除")
    @RequestMapping(value = "/goodsClass/del", method = RequestMethod.DELETE)
    public Result deleteStoreGoodsClass(
            @ApiParam @RequestParam String token,
            @ApiParam("分类id") @RequestParam Integer id) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        Integer flag = sgcService.deleteById(id);
        if (flag > 0) {
            result.setSuccess("分类删除");
        } else {
            result.setFail("分类删除");
        }
        return result;
    }

    @ApiOperation("店铺分类-添加")
    @RequestMapping(value = "/goodsClass/add", method = RequestMethod.POST)
    public Result addStoreGoodsClass(
            @ApiParam @RequestParam String token,
            @ApiParam("分类名") @RequestParam(required = false) String catName,
            @ApiParam("父id") @RequestParam(required = false) Integer parentId,
            @ApiParam("商品分类排序") @RequestParam(required = false) Integer catSort,
            @ApiParam("分类显示状态 0-不显示 1-显示") @RequestParam(required = false) Short isShow,
            @ApiParam("是否显示在导航栏 0-不显示 1-显示") @RequestParam(required = false) Short isNavShow,
            @ApiParam("是否首页推荐 0-不显示 1-显示") @RequestParam(required = false) Short isRecommend,
            @ApiParam("首页此类商品显示数量") @RequestParam(required = false) Short showNum,
            @ApiParam("首页此类商品显示数量") @RequestParam(required = false) Short id)

    {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        TyStoreGoodsClass goodsClass = new TyStoreGoodsClass();
        goodsClass.setStoreId(seller.getStoreId());
        if (catName != null && !"".equals(catName.trim())) {
            goodsClass.setCatName(catName);
        }
        if (parentId != null && !"".equals(catName.trim())) {
            goodsClass.setParentId(parentId);
        }
        if (catSort != null && !"".equals(catSort)) {
            goodsClass.setCatSort(catSort);
        }
        if (isShow != null && !"".equals(isShow)) {
            goodsClass.setIsShow(isShow);
        }
        if (isNavShow != null && !"".equals(isNavShow)) {
            goodsClass.setIsNavShow(isNavShow);
        }
        if (isRecommend != null && !"".equals(isRecommend)) {
            goodsClass.setIsRecommend(isRecommend);
        }
        if (showNum != null && !"".equals(showNum)) {
            goodsClass.setShowNum(showNum);
        }
        Integer flag = sgcService.insert(goodsClass);
        if (flag > 0) {
            result.setSuccess("店铺商品分类添加");
        } else {
            result.setFail("店铺商品分类添加");
        }
        return result;
    }

    @ApiOperation("店铺分类-详情")
    @RequestMapping(value = "/goodsClass", method = RequestMethod.GET)
    public Result detailStoreGoodsClass(
            @ApiParam @RequestParam String token,
            @ApiParam("分类id") @RequestParam Integer id) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        TyStoreGoodsClass goodsClass = sgcService.getById(id);
        if (goodsClass == null) {
            result.setFail("分类详情查询");
            return result;
        }
        Map<String, Object> map = new HashMap<>();
        //编号
        map.put("id", goodsClass.getCatId());
        //分类名
        map.put("catName", goodsClass.getCatName());
        //上级分类
        map.put("parentId", goodsClass.getParentId());
        //商品展示数量
        map.put("showNum", goodsClass.getShowNum());
        //是否展示
        map.put("isShow", goodsClass.getIsShow());
        //导航是否显示
        map.put("isNavShow", goodsClass.getIsNavShow());
        //首页是否推荐
        map.put("isRecommend", goodsClass.getIsRecommend());
        //排序
        map.put("catSort", goodsClass.getCatSort());
        if (!map.isEmpty()) {
            result.setSuccess("分类详情查询");
        } else {
            result.setFail("分类详情查询");
        }
        return result;
    }


}
