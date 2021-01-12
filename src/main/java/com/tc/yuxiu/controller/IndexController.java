package com.tc.yuxiu.controller;

import com.tc.yuxiu.model.*;
import com.tc.yuxiu.service.*;
import com.tc.yuxiu.util.IntegerUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author DELL
 * @date 2020/9/16 14:52
 */
@Api(tags = "首页数据")
@RestController
@RequestMapping(value = "/index")
public class IndexController {

    @Autowired
    private TySellerService sellerService;
    @Autowired
    private TyStoreService storeService;
    @Autowired
    private TyOrderService orderService;
    @Autowired
    private TyGoodsService goodsService;
    @Autowired
    private TyDeliveryDocService ddService;
    @Autowired
    private TyBusinessReturnRecordsService brrService;

    @ApiOperation("头部数据")
    @GetMapping("/headData")
    public Result indexHead(@ApiParam @RequestParam String token) {
        Result result = new Result();
        Map<String, Object> map = new HashMap<>();
        TySeller seller = sellerService.getByToken( token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        Integer storeId = seller.getStoreId();

        List<TyOrder> orderList = orderService.getByStoreId(storeId);
        if (orderList != null) {
            map.put("orderNum", orderList.size());
        } else {
            map.put("orderNum", "0");
        }
        List<TyGoods> goodsList = goodsService.getByStoreId(storeId);
        if (goodsList != null) {
            map.put("goodsNum", goodsList.size());
        } else {
            map.put("goodsNum", "0");
        }
        // TODO: 2020/9/22  文章数量 今日销售总额
        map.put("articleNum", "0");
        map.put("saleNum", "0");
        result.setData(map);
        if (!map.isEmpty()) {
            result.setSuccess("首页数据查询");
        } else {
            result.setFail("首页数据查询");
        }
        return result;
    }

    @ApiOperation("中间数据")
    @GetMapping("/bodyData")
    public Result indexBody(@ApiParam @RequestParam String token) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String, Object> map = new HashMap<>();
        map.put("mobile", seller.getMobile());
        map.put("sellerName", seller.getSellerName());
        Integer lastLoginTime = seller.getLastLoginTime();
        if(lastLoginTime!=null&&lastLoginTime!=0){
            map.put("lastLoginTime", sdf.format(IntegerUtil.timeToLong(lastLoginTime)));
        }else{
            map.put("lastLoginTime","");
        }

        Byte isAdmin = seller.getIsAdmin();
        if (isAdmin == 1) {
            map.put("isAdmin", "管理员");
        } else {
            map.put("isAdmin", "普通用户");
        }
        Integer storeId = seller.getStoreId();
        TyStore store = storeService.getById(storeId);
        String storeLogo = store.getStoreLogo();
        if(storeLogo!=null){
            map.put("storeLogo",storeLogo);
        }else{
            map.put("storeLogo","");
        }

        map.put("storeId", store.getStoreId());
        Integer gradeId = store.getGradeId();
        if (gradeId == 0) {
            map.put("grade", "默认等级");
        } else {
            map.put("grade", "其他等级");
        }
        map.put("storeName", store.getStoreName());
        Integer storeEndTime = store.getStoreEndTime();
        if (storeEndTime != null && storeEndTime != 0) {
            map.put("storeEndTime", sdf.format(IntegerUtil.timeToLong(storeEndTime)));

        } else {
            map.put("storeEndTime", "无限期");
        }
        result.setData(map);
        if (!map.isEmpty()) {
            result.setSuccess("信息查询");
        } else {
            result.setNull("信息");
        }
        return result;
    }

    @ApiOperation("尾部数据")
    @RequestMapping(value = "/footData", method = RequestMethod.GET)
    public Result indeFoot(@ApiParam @RequestParam String token) {
        Result result = new Result();
        Map<String, Object> resultMap = new HashMap<>();

        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        Map<String, Object> map = new HashMap<>();
        // TODO: 2020/9/22 待审核商品，未处理佣金，待回复咨询，新增商品评论
        //待审核商品
        Integer toAuditNum=0;
        //未处理佣金
        Integer toHandleNum=0;
        //待回复咨询
        Integer toReplyNum=0;
        //新增商品评论
        Integer newCommentNum=0;

        map.put("toAuditNum",toAuditNum);
        map.put("toHandleNum",toHandleNum);
        map.put("toReplyNum",toReplyNum);
        map.put("newCommentNum",newCommentNum);
        resultMap.put("totalData", map);

        map = new HashMap<>();
        Integer storeId = seller.getStoreId();
        //审核通过商品
        Integer passNum = 0;
        //库存警告商品数
        Integer warningNum = 0;
        //新品推荐数
        Integer newRecNum = 0;
        //促销商品数
        Integer promNum = 0;
        //已下架商品数
        Integer offSaleNum = 0;
        //审核未通过商品数
        Integer notPassNum = 0;
        Integer totalGoodsNum = 0;
        List<TyGoods> goods = goodsService.getByStoreId(storeId);
        if (goods != null) {
            totalGoodsNum = goods.size();
            for (TyGoods good : goods) {
                if (good.getGoodsState() == 1) {
                    passNum++;
                }
                if (good.getGoodsState() == 2) {
                    notPassNum++;
                }
                if (good.getStoreCount() < 100) {
                    warningNum++;
                }
                if (good.getIsNew() == 1 || good.getIsRecommend() == 1) {
                    newRecNum++;
                }
                if (good.getPromType() == 3) {
                    promNum++;
                }
                if (good.getIsOnSale() == 1) {
                    offSaleNum++;
                }

            }
        }
        map.put("passNum", passNum);
        map.put("warningNum", warningNum);
        map.put("newRecNum", newRecNum);
        map.put("promNum", promNum);
        map.put("offSaleNum", offSaleNum);
        map.put("notPassNum", notPassNum);
        map.put("totalGoodsNum", totalGoodsNum);
        resultMap.put("goodsData", map);

        //代发货订单数
        Integer toDeliveryNum = 0;
        //待支付订单
        Integer toPayNum = 0;
        //待确认订单
        Integer toConfirmNum = 0;
        //退款申请
        Integer refundNum = 0;
        //部分发货订单
        Integer partShipNum = 0;
        //退货申请
        Integer returnNum = 0;
        //订单数
        Integer totalOrderNum=0;

        map = new HashMap<>();
        List<TyDeliveryDoc> deliveryDocList = ddService.getByStoreId(storeId);
        if (deliveryDocList != null) {
            toDeliveryNum=deliveryDocList.size();

        }
        // TODO: 2020/9/22  待确认订单，部分发货订单
        List<TyOrder> orderList = orderService.getByStoreId(storeId);
        if (orderList != null) {
            totalOrderNum=orderList.size();
            for (TyOrder order : orderList) {
                if (order.getOrderStatus() == 0) {
                    toPayNum++;
                }
            }
        }
        List<TyBusinessReturnRecords> returnRecords = brrService.getByStoreId(storeId);
        if (returnRecords != null) {
            for (TyBusinessReturnRecords returnRecord : returnRecords) {
                if (returnRecord.getType() == 1) {
                    refundNum++;
                } else if (returnRecord.getType() == 2) {
                    returnNum++;
                }
            }
        }
        map.put("toPayNum",toPayNum);
        map.put("toDeliveryNum", toDeliveryNum);
        map.put("refundNum",refundNum);
        map.put("returnNum",returnNum);
        map.put("totalOrderNum",totalOrderNum);
        map.put("partShipNum",partShipNum);
        map.put("toConfirmNum",toConfirmNum);
        resultMap.put("orderData", map);
        result.setData(resultMap);
        if (!resultMap.isEmpty()) {
            result.setSuccess("数据查询");
        } else {
            result.setNull("数据");
        }
        return result;
    }
}
