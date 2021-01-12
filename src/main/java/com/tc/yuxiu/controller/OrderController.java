package com.tc.yuxiu.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tc.yuxiu.model.*;
import com.tc.yuxiu.service.*;
import com.tc.yuxiu.util.IntegerUtil;
import com.tc.yuxiu.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.apache.commons.collections.map.AbstractHashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author DELL
 * @date 2020/9/18 9:13
 */
@RestController
@RequestMapping("/order")
@Api(tags = "订单物流")
public class OrderController {

    @Autowired
    private TySellerService sellerService;
    @Autowired
    private TyOrderService orderService;
    @Autowired
    private TyDeliveryDocService ddService;
    @Autowired
    private TyShippingAreaService saService;
    @Autowired
    private TyBusinessReturnRecordsService brrService;
    @Autowired
    private TyCommentService commentService;
    @Autowired
    private TyUsersService usersService;
    @Autowired
    private TyStoreReturnAddressService sraService;
    @Autowired
    private TyRegionService regionService;
    @Autowired
    private TyGoodsService goodsService;
    @Autowired
    private TyOrderGoodsService ogService;
    @Autowired
    private TyOrderActionService oaService;
    @Autowired
    private TyReplyService replyService;
    @Autowired
    private TyStoreService storeService;
    @Autowired
    private TyUserAddressService uaService;
    @Autowired
    private TyReturnGoodsService rgService;
    @Autowired
    private TyReturnGoodsDetailService rgdService;

    @ApiOperation("订单列表")
    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public Result allOrderList(
            @ApiParam @RequestParam String token,
            @ApiParam @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @ApiParam @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @ApiParam("收货人") @RequestParam(required = false) String consignee,
            @ApiParam("订单编号") @RequestParam(required = false) String orderSn,
            @ApiParam("下单日期") @RequestParam(required = false) String time,
            @ApiParam("支付状态，1已支付，0未支付") @RequestParam(required = false) Integer payStatus,
            @ApiParam("支付方式") @RequestParam(required = false) String payName,
            @ApiParam("发货状态，0-未发货 1-已发货") @RequestParam(required = false) Integer shippingStatus,
            @ApiParam("订单状态，0-待支付 1-待发货(待收货) 2-待评价 3-已取消 4-已完成 5-退货（换货）中") @RequestParam(required = false) Integer orderStatus,
            @ApiParam("订单类型，0-商品订单  1-订货订单 2-拼团订单 3-推广员订单") @RequestParam(required = false) Integer type
    ) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        List<Map<String, Object>> maps = new ArrayList<>();
        Map<String, Object> map = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Integer storeId = seller.getStoreId();
        List<TyOrder> orderList;
        PageHelper.startPage(pageNum, pageSize);
        if (consignee != null || orderSn != null || time != null || payStatus != null || payName != null || shippingStatus != null || orderStatus != null || type != null) {
            orderList = orderService.getByParametersAndTime(storeId, consignee, orderSn, time, payStatus, payName, shippingStatus, orderStatus, type);
        } else {
            orderList = orderService.getByStoreId(storeId);
        }
        PageInfo<TyOrder> page = new PageInfo<>(new ArrayList<>());
        if (orderList != null) {
            page = new PageInfo<>(orderList);
            for (TyOrder order : orderList) {
                map = new HashMap<>();
                map.put("checked", false);
                //编号
                map.put("orderId", order.getOrderId().toString());
                //订单编号
                map.put("orderSn", order.getOrderSn());
                //收货人
                map.put("consignee", order.getConsignee());
                //订单金额
                map.put("orderAmount", order.getOrderAmount());
                //支付金额
                map.put("payPrice", order.getPayPrice());
                //订单类型
                type = order.getType();
                if (type == 0) {
                    map.put("type", "商品订单");
                } else if (type == 1) {
                    map.put("type", "订货订单");
                } else if (type == 2) {
                    map.put("type", "拼团订单");
                } else if (type == 3) {
                    map.put("type", "推广员订单");
                }
                //订单状态
                orderStatus = order.getOrderStatus();
                Integer isDel = 0;
                if (orderStatus == 0) {
                    map.put("orderStatus", "待支付");
                } else if (orderStatus == 1) {
                    map.put("orderStatus", "待发货(待收货)");
                } else if (orderStatus == 2) {
                    map.put("orderStatus", "待评价");
                } else if (orderStatus == 3) {
                    map.put("orderStatus", "已取消");
                    isDel = 1;
                } else if (orderStatus == 4) {
                    map.put("orderStatus", "已完成");
                } else if (orderStatus == 5) {
                    map.put("orderStatus", "退货（换货）中");
                }
                //是否可以删除 1是，0否
                map.put("isDel", isDel.toString());
                //支付状态
                payStatus = order.getPayStatus();
                if (payStatus == 1) {
                    map.put("payStatus", "已支付");
                } else {
                    map.put("payStatus", "未支付");
                }
                //发货状态
                shippingStatus = order.getShippingStatus();
                if (shippingStatus == 1) {
                    map.put("shippingStatus", "已发货");
                } else {
                    map.put("shippingStatus", "未发货");
                }
                //支付方式
                map.put("payName", order.getPayName());
                //配送方式
                map.put("shippingName", order.getShippingName());
                //下单时间
                Integer addTime = order.getAddTime();
                if (addTime != null && addTime != 0) {
                    map.put("addTime", sdf.format(IntegerUtil.timeToLong(addTime)));
                } else {
                    map.put("addTime", "");
                }
                //支付时间
                Integer payTime = order.getPayTime();
                if (payTime != null && payTime != 0) {
                    map.put("payTime", sdf.format(IntegerUtil.timeToLong(payTime)));
                } else {
                    map.put("payTime", "");
                }
                maps.add(map);
            }
        }
        result.setPage(page);
        result.setData(maps);

        if (maps.size() > 0) {
            result.setSuccess("订单查询");
        } else {
            result.setNull("订单");
        }
        return result;
    }


    @ApiOperation("导出Excel表格")
    @RequestMapping(value = "/orders/toExcel", method = RequestMethod.GET)
    public Result exportToExcel(
            HttpServletResponse response,
            @ApiParam @RequestParam String token,
            @ApiParam("收货人") @RequestParam(required = false) String consignee,
            @ApiParam("订单编号") @RequestParam(required = false) String orderSn,
            @ApiParam("下单日期") @RequestParam(required = false) String time,
            @ApiParam("支付状态，1已支付，0未支付") @RequestParam(required = false) Integer payStatus,
            @ApiParam("支付方式") @RequestParam(required = false) String payName,
            @ApiParam("发货状态，0-未发货 1-已发货") @RequestParam(required = false) Integer shippingStatus,
            @ApiParam("订单状态，0-待支付 1-待发货(待收货) 2-待评价 3-已取消 4-已完成 5-退货（换货）中") @RequestParam(required = false) Integer orderStatus,
            @ApiParam("订单类型，0-商品订单  1-订货订单 2-拼团订单 3-推广员订单") @RequestParam(required = false) Integer type
    ) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Integer storeId = seller.getStoreId();
        List<TyOrder> orderList = null;

        if (consignee != null || orderSn != null || time != null || payStatus != null || payName != null || shippingStatus != null || orderStatus != null || type != null) {
            orderList = orderService.getByParametersAndTime(storeId, consignee, orderSn, time, payStatus, payName, shippingStatus, orderStatus, type);
        } else {
            orderList = orderService.getByStoreId(storeId);
        }

        try {
            // 导出excel
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode("订单列表.xls", "UTF-8"));
            response.setContentType("application/x-excel;charset=UTF-8");
            OutputStream outputStream = response.getOutputStream();
            Integer flag = orderService.exportToExcel(orderList, outputStream);
            if (flag > 0) {
                result.setSuccess("订单列表导出");
            } else {
                result.setFail("订单列表导出");
            }
        } catch (IOException e) {
            result.setFail("订单列表导出");
        }
        return null;
    }

    @ApiOperation("订单列表-详情")
    @GetMapping("/order/detail")
    public Result detailOrder(
            @ApiParam @RequestParam String token,
            @ApiParam("订单编号") @RequestParam Integer id
    ) {
        Result result = new Result();
        Map<String, Object> resultMap = new HashMap<>();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Map<String, Object>> maps = null;
        Map<String, Object> map = null;
        TyOrder order = orderService.getByOrderId(id);
        Integer userId = null;
        Integer cancelButton = 0;
        Integer shippingButtion = 0;

        Integer deleteButtion = 0;
        Integer orderStatus = 0;
        Short payStatus = 0;
        //基本信息
        if (order != null) {
            map = new HashMap<>();
            //编号
            map.put("orderId", order.getOrderId().toString());
            //订单编号
            map.put("orderSn", order.getOrderSn());
            userId = order.getUserId();

            TyUsers user = usersService.getByUserId(userId);
            //会员名
            map.put("userName", user.getUserName());
            //邮箱
            map.put("email", user.getEmail());
            //手机号
            map.put("mobile", user.getMobile());
            //订单金额
            map.put("orderAmount", order.getOrderAmount().toString());
            //订单状态
            orderStatus = order.getOrderStatus();
            if (orderStatus == 0) {
                map.put("orderStatus", "待支付");
            } else if (orderStatus == 1) {
                map.put("orderStatus", "待发货(待收货)");
            } else if (orderStatus == 2) {
                map.put("orderStatus", "待评价");
            } else if (orderStatus == 3) {
                map.put("orderStatus", "已取消");
            } else if (orderStatus == 4) {
                map.put("orderStatus", "已完成");
            } else if (orderStatus == 5) {
                map.put("orderStatus", "退货（换货）中");
            }
            //下单时间
            Integer addTime = order.getAddTime();
            if (addTime != null && addTime != 0) {
                map.put("addTime", sdf.format(IntegerUtil.timeToLong(addTime)));
            } else {
                map.put("addTime", "");
            }
            //支付时间
            Integer payTime = order.getPayTime();
            if (payTime != null && payTime != 0) {
                map.put("payTime", sdf.format(IntegerUtil.timeToLong(payTime)));
            } else {
                map.put("payTime", "");
            }

            map.put("payName", order.getPayName());
            resultMap.put("basicInfo", map);
        } else {
            resultMap.put("basicInfo", "");
        }


        TyUserAddress userAddress = uaService.getByUserId(userId);
        if (userAddress == null) {
            resultMap.put("consigneeInfo", "");
        } else {
            map = new HashMap<>();
            map.put("consignee", userAddress.getConsignee());
            map.put("mobile", userAddress.getMobile());
            map.put("zipcode", userAddress.getZipcode());
            String pcdAddress = "";
            Integer province = userAddress.getProvince();
            if (province != null && province != 0) {
                map.put("province", province.toString());
                TyRegion address = regionService.getById(province);
                if (address != null) {
                    pcdAddress = pcdAddress + address.getName() + ",";
                }
            } else {
                map.put("province", "");
            }
            Integer city = userAddress.getCity();
            if (city != null && city != 0) {
                map.put("city", city.toString());
                TyRegion address = regionService.getById(city);
                if (address != null) {
                    pcdAddress = pcdAddress + address.getName() + ",";
                }
            } else {
                map.put("city", "");
            }

            Integer district = userAddress.getDistrict();
            if (district != null && district != 0) {
                map.put("district", district.toString());
                TyRegion address = regionService.getById(district);
                if (address != null) {
                    pcdAddress = pcdAddress + address.getName() + ",";
                }
            } else {
                map.put("district", "");
            }
            String address = userAddress.getAddress();
            if (address != null) {
                map.put("detailAddress", address);
                pcdAddress = pcdAddress + address;
            } else {
                map.put("detailAddress", "");
            }
            map.put("address", pcdAddress);
            if (order != null) {
                map.put("shippingName", order.getShippingName());
                map.put("payName", order.getPayName());
            } else {
                map.put("shippingName", "");
                map.put("payName", "");
            }


            resultMap.put("consigneeInfo", map);


        }


        //商品信息
        List<TyOrderGoods> orderGoods = ogService.getByOrderId(id);
        if (orderGoods == null) {
            resultMap.put("goodsInfo", "");
        } else {
            maps = new ArrayList<>();
            Double totalPrice = (double) 0;
            for (TyOrderGoods orderGood : orderGoods) {
                map = new HashMap<>();
                map.put("id", orderGood.getRecId().toString());
                map.put("goodsName", orderGood.getGoodsName());
                map.put("goodsSn", orderGood.getGoodsSn());
                map.put("goodsId", orderGood.getGoodsId().toString());
                Short goodsNum = orderGood.getGoodsNum();
                map.put("goodsNum", goodsNum.toString());
                map.put("goodsPrice", orderGood.getGoodsPrice().toString());
                BigDecimal price = orderGood.getMemberGoodsPrice();
                map.put("memberGoodsPrice", price.toString());
                Double singlePrice = goodsNum * price.doubleValue();
                totalPrice += singlePrice;
                map.put("singleGoodsPriceSum", singlePrice);
                map.put("specKeyName", orderGood.getSpecKeyName());
                map.put("specKey", orderGood.getSpecKey());
                maps.add(map);
            }
            resultMap.put("goodsTotalPrice", totalPrice);
            resultMap.put("goodsInfo", maps);
        }

        //发票信息
        if (order == null) {
            resultMap.put("invoiceInfo", "");
        } else {
            map = new HashMap<>();
            //抬头
            map.put("invoiceTitle", order.getInvoiceTitle());
            //识别号
            map.put("invoiceTax", order.getInvoiceTax());
            //注册地址
            map.put("invoiceAddress", order.getInvoiceAddress());
            //注册电话
            map.put("invoiceMobile", order.getInvoiceMobile());
            //开户银行
            map.put("invoiceBank", order.getInvoiceBank());
            //银行账户
            map.put("invoiceCardId", order.getInvoiceCardid());
            resultMap.put("invoiceInfo", map);
        }

        //费用信息
        if (order == null) {
            resultMap.put("expenseInfo", "");
        } else {
            map = new HashMap<>();
            //运费
            map.put("shippingPrice", order.getShippingPrice());
            //使用积分
            map.put("integral", order.getIntegral());
            //使用积分抵多少钱
            map.put("integralMoney", order.getIntegralMoney());
            //余额抵扣
            map.put("userMoney", order.getUserMoney());
            //优惠券抵扣
            map.put("couponPrice", order.getCouponPrice());
            //价格调整
            map.put("discount", order.getDiscount());
            //推广员优惠价格
            map.put("shareCouponPrice", order.getShareCouponPrice());
            //非推广价格
            map.put("shareOriginalPrice", order.getShareOriginalPrice());
            //合伙人减免
            map.put("discountFee", order.getDiscount());
            //应付
            map.put("orderAmount", order.getOrderAmount());
            resultMap.put("expenseInfo", map);
        }

        //操作信息
        List<TyOrderAction> orderActions = oaService.getByOrderId(id);
        if (orderActions == null) {
            resultMap.put("actionsInfo", "");
        } else {
            maps = new ArrayList<>();
            for (TyOrderAction action : orderActions) {
                map = new HashMap<>();
                map.put("actionId", action.getActionId());
                userId = action.getActionUser();
                TyUsers user = usersService.getByUserId(userId);
                if (user != null) {
                    map.put("acionUser", user.getUserName());
                } else {
                    map.put("acionUser", "");
                }
                Integer logTime = action.getLogTime();
                if (logTime != null && logTime != 0) {
                    map.put("logTime", sdf.format(IntegerUtil.timeToLong(logTime)));
                } else {
                    map.put("logTime", "");
                }
                Short actionOrderStatus = action.getOrderStatus();
                if (actionOrderStatus == 0) {
                    map.put("orderStatus", "待支付");
                } else if (actionOrderStatus == 1) {
                    map.put("orderStatus", "待发货(待收货)");
                } else if (actionOrderStatus == 2) {
                    map.put("orderStatus", "待评价");
                } else if (actionOrderStatus == 3) {
                    map.put("orderStatus", "已取消");
                } else if (actionOrderStatus == 4) {
                    map.put("orderStatus", "已完成");
                } else if (actionOrderStatus == 5) {
                    map.put("orderStatus", "退货（换货）中");
                }
                //支付状态
                payStatus = action.getPayStatus();
                if (payStatus == 1) {
                    map.put("payStatus", "已支付");
                } else {
                    map.put("payStatus", "未支付");
                }
                Short shippingStatus = action.getShippingStatus();
                if (shippingStatus == 1) {
                    map.put("shippingStatus", "已发货");
                } else {
                    map.put("shippingStatus", "未发货");
                }
                map.put("desc", action.getStatusDesc());
                map.put("note", action.getActionNote());
                maps.add(map);
            }
            resultMap.put("actionsInfo", maps);
        }
        if (orderStatus == 3) {
            deleteButtion = 1;
        } else {
            cancelButton = 1;
            if (payStatus == 1) {
                shippingButtion = 1;
            }
        }

        resultMap.put("deleteButtion", deleteButtion);
        resultMap.put("cancelButton", cancelButton);
        resultMap.put("shippingButtion", shippingButtion);


        result.setData(resultMap);
        if (!resultMap.isEmpty()) {
            result.setSuccess("订单详情查询");
        } else {
            result.setNull("订单详情");
        }

        return result;
    }

    @ApiOperation("订单列表-修改订单-保存")
    @PostMapping("/order/modify")
    public Result modifyOrder(
            @ApiParam @RequestParam String token,
            @ApiParam("订单编号") @RequestParam Integer id,
            @ApiParam("收货人") @RequestParam String consignee,
            @ApiParam("手机") @RequestParam String mobile,
            @ApiParam("省") @RequestParam Integer province,
            @ApiParam("市") @RequestParam Integer city,
            @ApiParam("区") @RequestParam Integer district,
            @ApiParam("地址") @RequestParam String address,
            @ApiParam("配送物流") @RequestParam String shippingName,
            @ApiParam("支付方式") @RequestParam String payName,
            @ApiParam("发票抬头") @RequestParam String invoiceTitle,
            @ApiParam("纳税人识别号") @RequestParam String invoiceTax,
            @ApiParam("注册地址") @RequestParam String invoiceAddress,
            @ApiParam("注册电话") @RequestParam String invoiceMobile,
            @ApiParam("开户银行") @RequestParam String invoiceBank,
            @ApiParam("银行账户") @RequestParam Integer invoiceCardId,
            @ApiParam("管理员备注") @RequestParam String adminNote
    ) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        TyOrder order = orderService.getByOrderId(id);
        if (consignee != null) {
            order.setConsignee(consignee);
        }
        if (mobile != null) {
            order.setMobile(mobile);
        }
        if (province != null) {
            order.setProvince(province);
        }
        if (city != null) {
            order.setCity(city);
        }
        if (district != null) {
            order.setDistrict(district);
        }
        if (shippingName != null) {
            order.setShippingName(shippingName);
        }
        if (address != null) {
            order.setAddress(address);
        }
        if (payName != null) {
            order.setPayName(payName);
        }
        if (invoiceTitle != null) {
            order.setInvoiceTitle(invoiceTitle);
        }
        if (adminNote != null) {
            order.setAdminNote(adminNote);
        }
        if (invoiceBank != null) {
            order.setInvoiceBank(invoiceBank);
        }
        if (invoiceTax != null) {
            order.setInvoiceTax(invoiceTax);
        }
        if (invoiceCardId != null) {
            order.setInvoiceCardid(invoiceCardId);
        }
        if (invoiceAddress != null) {
            order.setInvoiceAddress(invoiceAddress);
        }
        if (invoiceMobile != null) {
            order.setInvoiceMobile(invoiceMobile);
        }
        Integer flag = orderService.modifyByOrderId(order);
        if (flag > 0) {
            result.setSuccess("修改订单");
        } else {
            result.setFail("修改订单");
        }
        return result;
    }


    @ApiOperation("订单-打印")
    @GetMapping("/order/print")
    public Result printOrder(
            @ApiParam @RequestParam String token,
            @ApiParam("订单编号") @RequestParam Integer id
    ) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        Integer storeId = seller.getStoreId();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Map<String, Object>> maps = new ArrayList<>();
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> map = null;
        TyOrder order = orderService.getByOrderId(id);
        if (order == null) {
            result.setData(new HashMap<>());
            result.setFail("查询");
            return result;
        }
        TyStore store = storeService.getById(storeId);
        if (store != null) {
            map = new HashMap<>();
            map.put("storeName", store.getStoreName());
            String address = "";
            Integer provinceId = store.getProvinceId();
            TyRegion region = regionService.getById(provinceId);
            if (region != null) {
                address += region.getName();
            }
            Integer cityId = store.getCityId();
            region = regionService.getById(cityId);
            if (region != null) {
                address += region.getName();
            }
            Integer district = store.getDistrict();
            region = regionService.getById(district);
            if (region != null) {
                address += region.getName();
            }
            address += store.getStoreAddress();
            map.put("address", address);

            map.put("phone", store.getStorePhone());
            map.put("email", "");
            map.put("website", "");
            resultMap.put("fromInfo", map);
        }

        if (order != null) {
            map = new HashMap<>();
            map.put("orderSn", order.getOrderSn());
            Integer addTime = order.getAddTime();
            if (addTime != null && addTime != 0) {
                map.put("addTime", sdf.format(IntegerUtil.timeToLong(addTime)));
            } else {
                map.put("addTime", "");
            }
            map.put("payName", order.getPayName());
            Integer payTime = order.getPayTime();
            if (payTime != null && payTime != 0) {
                map.put("payTime", sdf.format(IntegerUtil.timeToLong(payTime)));
            } else {
                map.put("payTime", "");
            }

            map.put("shippingName", order.getShippingName());
            map.put("orderAmount", order.getOrderAmount());
            map.put("goodsPrice", order.getGoodsPrice());
            map.put("shippingPrice", order.getShippingPrice());
            map.put("couponPrice", order.getCouponPrice());
            map.put("integral", order.getIntegral());
            map.put("userMoney", order.getUserMoney());
            map.put("payPrice", order.getPayPrice());
            resultMap.put("orderDetail", map);

            map = new HashMap<>();
            map.put("consignee", order.getConsignee());
            String address = "";
            Integer provinceId = order.getProvince();
            TyRegion region = regionService.getById(provinceId);
            if (region != null) {
                address += region.getName();
            }
            Integer cityId = order.getCity();
            region = regionService.getById(cityId);
            if (region != null) {
                address += region.getName();
            }
            Integer district = order.getDistrict();
            region = regionService.getById(district);
            if (region != null) {
                address += region.getName();
            }
            address += order.getAddress();
            map.put("address", address);
            map.put("mobile", order.getMobile());
            map.put("zipcode", order.getZipcode());
            resultMap.put("consigneeInfo", map);

            //商品信息
            List<TyOrderGoods> orderGoods = ogService.getByOrderId(id);
            if (orderGoods == null) {
                resultMap.put("goodsInfo", "");
            } else {
                maps = new ArrayList<>();
                Double totalPrice = (double) 0;
                for (TyOrderGoods orderGood : orderGoods) {
                    map = new HashMap<>();
                    map.put("id", orderGood.getRecId().toString());
                    map.put("goodsName", orderGood.getGoodsName());
                    map.put("goodsSn", orderGood.getGoodsSn());
                    map.put("goodsId", orderGood.getGoodsId().toString());
                    Short goodsNum = orderGood.getGoodsNum();
                    map.put("goodsNum", goodsNum.toString());
                    map.put("goodsPrice", orderGood.getGoodsPrice().toString());
                    BigDecimal price = orderGood.getMemberGoodsPrice();
                    map.put("memberGoodsPrice", price.toString());
                    Double singlePrice = goodsNum * price.doubleValue();
                    totalPrice += singlePrice;
                    map.put("singleGoodsPriceSum", singlePrice);
                    maps.add(map);
                }
                resultMap.put("goodsInfo", maps);
            }
            //发票信息
            if (order == null) {
                resultMap.put("invoiceInfo", "");
            } else {
                map = new HashMap<>();
                //抬头
                map.put("invoiceTitle", order.getInvoiceTitle());
                //识别号
                map.put("invoiceTax", order.getInvoiceTax());
                //注册地址
                map.put("invoiceAddress", order.getInvoiceAddress());
                //注册电话
                map.put("invoiceMobile", order.getInvoiceMobile());
                //开户银行
                map.put("invoiceBank", order.getInvoiceBank());
                //银行账户
                map.put("invoiceCardId", order.getInvoiceCardid());
                resultMap.put("invoiceInfo", map);
            }
        }

        result.setData(resultMap);
        result.setSuccess("订单详情查询");
        return result;
    }


    @ApiOperation("订单列表-详情-取消订单")
    @GetMapping("/order/cancel")
    public Result cancelOrder(
            @ApiParam @RequestParam String token,
            @ApiParam @RequestParam Integer id
    ) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        TyOrder order = orderService.getByOrderId(id);
        order.setOrderStatus(3);
        int flag = orderService.modifyByOrderId(order);
        if (flag > 0) {
            result.setSuccess("订单取消");
        } else {
            result.setFail("订单取消");
        }
        return result;
    }


    @ApiOperation("订单列表-批量/单个删除")
    @RequestMapping(value = "/order/del", method = RequestMethod.DELETE)
    public Result deleteOrder(
            @ApiParam @RequestParam String token,
            @ApiParam("订单编号：1,2,3,...") @RequestParam(required = false) String ids
    ) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        String[] idList = ids.split(",");
        Integer flag = null;
        if (idList != null && idList.length > 0) {
            for (String id : idList) {
                if (id != null) {
                    Integer orderId = Integer.valueOf(id);
                    flag = orderService.deleteById(orderId);
                }
            }
        }
        if (flag > 0) {
            result.setSuccess("订单列表删除");
        } else {
            result.setFail("订单列表删除");
        }
        return result;
    }


    @ApiOperation("发货单")
    @RequestMapping(value = "/deliverys", method = RequestMethod.GET)
    public Result allDeliveryDocList(
            @ApiParam @RequestParam String token,
            @ApiParam @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @ApiParam @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @ApiParam("收货人") @RequestParam(required = false) String consignee,
            @ApiParam("订单编号") @RequestParam(required = false) String orderSn,
            @ApiParam("发货状态，0-未发货 1-已发货") @RequestParam(required = false) Integer shippingStatus,
            @ApiParam("支付时间") @RequestParam(required = false) String time
    ) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        List<Map<String, Object>> maps = new ArrayList<>();
        Map<String, Object> map = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Integer storeId = seller.getStoreId();

        PageHelper.startPage(pageNum, pageSize);
        List<TyOrder> orders = orderService.getByParametersAndTime(storeId, consignee, orderSn, time, null, null, shippingStatus, -1, null);
        PageInfo<TyOrder> page=new PageInfo<>(new ArrayList<>());
        if (orders != null) {
            page=new PageInfo<>(orders);
            for (TyOrder order : orders) {
                map = new HashMap<>();
                map.put("checked", false);
                map.put("id", order.getOrderId().toString());
                map.put("orderSn", order.getOrderSn());
                map.put("orderId", order.getOrderId().toString());
                //收货人
                map.put("consignee", order.getConsignee());
                map.put("mobile", order.getMobile());
                if (order != null) {
                    map.put("orderAmount", order.getOrderAmount());
                    map.put("payPrice", order.getPayPrice());
                    Integer addTime = order.getAddTime();
                    if (addTime != null && addTime != 0) {
                        map.put("addTime", sdf.format(IntegerUtil.timeToLong(addTime)));
                    } else {
                        map.put("addTime", "");
                    }
                    Integer payTime = order.getPayTime();
                    if (payTime != null && payTime != 0) {
                        map.put("payTime", sdf.format(IntegerUtil.timeToLong(payTime)));
                    } else {
                        map.put("payTime", "");
                    }

                } else {
                    map.put("orderAmount", "");
                    map.put("payPrice", "");
                    map.put("addTime", "");
                    map.put("payTime", "");
                }


                map.put("shippingName", order.getShippingName());
                map.put("shippingPrice", order.getShippingPrice());
                maps.add(map);

            }
        }
        result.setPage(page);
        result.setData(maps);

        if (maps.size() > 0) {
            result.setSuccess("发货单查询");
        } else {
            result.setNull("发货单");
        }
        return result;
    }

    @ApiOperation("发货单-去发货")
    @RequestMapping(value = "/delivery/toDelivery", method = RequestMethod.GET)
    public Result toDeliveryInfo(
            @ApiParam @RequestParam String token,
            @ApiParam("订单编号") @RequestParam(required = false) Integer id
    ) {
        Result result = new Result();
        Map<String, Object> resultMap = new HashMap<>();
        List<Map<String, Object>> maps = null;
        Map<String, Object> map = null;
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (id == null) {
            result.setFailMessage("id不能为空");
            return result;
        }
        TyOrder order = orderService.getByOrderId(id);
        Integer orderId = order.getOrderId();
        if (order != null) {
            map = new HashMap<>();
            map.put("id", order.getOrderId().toString());
            map.put("orderSn", order.getOrderSn());
            if (order != null) {
                Integer addTime = order.getAddTime();
                if (addTime != null && addTime != 0) {
                    map.put("addTime", sdf.format(IntegerUtil.timeToLong(addTime)));
                } else {
                    map.put("addTime", "");
                }
                //快递名称
            }
            resultMap.put("basicInfo", map);

//            map.put("shippingName", deliveryDoc.getShippingName());
//            //快递价格
//            map.put("shippingPrice", deliveryDoc.getShippingPrice().toString());
//            //物流图片
//            map.put("shippingPic", deliveryDoc.getShippingPic());
//            map.put("shippingCode", deliveryDoc.getShippingCode());
//            map.put("invoiceNo", deliveryDoc.getInvoiceNo());
//            map.put("remarks", deliveryDoc.getRemarks());

            map = new HashMap<>();
            map.put("consignee", order.getConsignee());
            map.put("email", order.getEmail());
            map.put("province", order.getProvince());
            map.put("city", order.getCity());
            map.put("district", order.getDistrict());
            map.put("address", order.getAddress());
            map.put("zipcode", order.getZipcode());
            map.put("mobile", order.getMobile());
            resultMap.put("consigneeInfo", map);
            //商品信息
            List<TyOrderGoods> orderGoods = null;
            if (orderId != null) {
                orderGoods = ogService.getByOrderId(orderId);
            }
            if (orderGoods == null) {
                resultMap.put("goodsInfo", "");
            } else {
                maps = new ArrayList<>();
                for (TyOrderGoods orderGood : orderGoods) {
                    map = new HashMap<>();
                    map.put("id", orderGood.getRecId().toString());
                    map.put("goodsName", orderGood.getGoodsName());
                    map.put("goodsSn", orderGood.getGoodsSn());
                    map.put("goodsId", orderGood.getGoodsId().toString());
                    map.put("specKeyName", orderGood.getSpecKeyName());
                    Short goodsNum = orderGood.getGoodsNum();
                    map.put("goodsNum", goodsNum.toString());
                    map.put("goodsPrice", orderGood.getGoodsPrice().toString());
                    maps.add(map);
                }
                resultMap.put("goodsInfo", maps);
            }
            //发票信息
            if (order != null) {
                map = new HashMap<>();
                //识别号
                map.put("invoiceTax", order.getInvoiceTax());
                //注册地址
                map.put("invoiceAddress", order.getInvoiceAddress());
                //注册电话
                map.put("invoiceMobile", order.getInvoiceMobile());
                //开户银行
                map.put("invoiceBank", order.getInvoiceBank());
                //银行账户
                map.put("invoiceCardId", order.getInvoiceCardid());
                //抬头
                map.put("invoiceTitle", order.getInvoiceTitle());
                resultMap.put("invoiceInfo", map);
            } else {
                resultMap.put("invoiceInfo", "");
            }

            maps = new ArrayList<>();
            List<TyDeliveryDoc> deliveryDocs = ddService.getByOrderId(orderId);
            if (deliveryDocs != null) {
                for (TyDeliveryDoc deliveryDoc : deliveryDocs) {
                    map = new HashMap<>();
                    map.put("id", deliveryDoc.getId());
                    map.put("sellerName", seller.getSellerName());
                    Integer createTime = deliveryDoc.getCreateTime();
                    if (createTime != null && createTime != 0) {
                        map.put("createTime", sdf.format(IntegerUtil.timeToLong(createTime)));
                    } else {
                        map.put("createTime", "");
                    }
                    map.put("invoiceNo", deliveryDoc.getInvoiceNo());
                    map.put("consignee", deliveryDoc.getConsignee());
                    map.put("shippingName", deliveryDoc.getShippingName());
                    map.put("remarks", deliveryDoc.getRemarks());
                    maps.add(map);
                }
            }
            //发货记录
            resultMap.put("deliveryRecord", maps);

        }
        result.setData(resultMap);
        result.setSuccess("去发货信息查询");
        return result;
    }


    @ApiOperation("发货单-去发货-确认发货")
    @RequestMapping(value = "/delivery/toDelivery", method = RequestMethod.POST)
    public Result toDelivery(
            @ApiParam @RequestParam String token,
            @ApiParam @RequestParam Integer id,
            @ApiParam("快递名称") @RequestParam(required = false) String shippingName,
            @ApiParam("快递照片") @RequestParam(required = false) String shippingPic,
            @ApiParam("物流单号") @RequestParam(required = false) String invoiceNo,
            @ApiParam("物流备注") @RequestParam(required = false) String remarks,
            @ApiParam("发货单备注") @RequestParam(required = false) String note
    ) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }

        TyDeliveryDoc deliveryDoc = new TyDeliveryDoc();
        if (shippingName != null) {
            deliveryDoc.setShippingName(shippingName);
        }
        if (shippingPic != null) {
            deliveryDoc.setShippingPic(shippingPic);
        }
        if (invoiceNo != null) {
            deliveryDoc.setInvoiceNo(invoiceNo);
        }
        if (note != null) {
            deliveryDoc.setNote(note);
        }
        if (remarks != null) {
            deliveryDoc.setRemarks(remarks);
        }
        int flag = ddService.insert(deliveryDoc);
        if (flag > 0) {
            result.setSuccess("确认发货");
        } else {
            result.setFail("确认发货");
        }
        return result;
    }

    @ApiOperation("发货单-批量/单个删除")
    @RequestMapping(value = "/delivery", method = RequestMethod.DELETE)
    public Result deleteDeliveryDoc(
            @ApiParam @RequestParam String token,
            @ApiParam("订单编号，[1,2,3,...]") @RequestParam String ids
    ) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        String[] idList = ids.split(",");
        Integer flag = null;
        if (idList != null && idList.length > 0) {
            for (String id : idList) {
                if (id != null && !"".equals(id.trim())) {
                    flag = ddService.deleteById(Integer.valueOf(id));
                }
            }
        }
        if (flag > 0) {
            result.setSuccess("发货单删除");
        } else {
            result.setFail("发货单删除");
        }
        return result;
    }


    @ApiOperation("发货设置")
    @RequestMapping(value = "/shippingAreas", method = RequestMethod.GET)
    public Result allShippingArea(
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
        Integer storeId = seller.getStoreId();
        List<TyShippingArea> shippingAreas = saService.getByStoreId(storeId);
        if (shippingAreas != null) {
            for (TyShippingArea shippingArea : shippingAreas) {
                map = new HashMap<>();
                map.put("id", shippingArea.getShippingAreaId().toString());
                map.put("shippingAreaName", shippingArea.getShippingAreaName());
                String config = shippingArea.getConfig();


                maps.add(map);
            }
        }
        result.setData(maps);
        if (maps.size() > 0) {
            result.setSuccess("发货单查询");
        } else {
            result.setNull("发货单");
        }
        return result;
    }


    @ApiOperation("发货设置-删除")
    @RequestMapping(value = "/shippingArea", method = RequestMethod.DELETE)
    public Result deleteShippingArea(
            @ApiParam @RequestParam String token,
            @ApiParam("发货设置编号") @RequestParam Integer id
    ) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        Integer flag = saService.deleteById(id);
        if (flag > 0) {
            result.setSuccess("发货设置删除");
        } else {
            result.setFail("发货设置删除");
        }
        return result;
    }

    @ApiOperation("发货设置-详情")
    @RequestMapping(value = "/shippingArea", method = RequestMethod.GET)
    public Result detailShippingArea(
            @ApiParam @RequestParam String token,
            @ApiParam("发货设置编号") @RequestParam Integer id) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        TyShippingArea shippingArea = saService.getById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("name", shippingArea.getShippingAreaName());
        map.put("config", shippingArea.getConfig());
        result.setData(map);

        return result;
    }


    @ApiOperation("退货单")
    //@RequestMapping(value = "/retRecoreds", method = RequestMethod.GET)
    @RequestMapping(value = "/retGoods", method = RequestMethod.GET)
    public Result allReturnGoods(
            @ApiParam @RequestParam String token,
            @ApiParam @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @ApiParam @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @ApiParam("订单编号") @RequestParam(required = false) String orderSn,
            @ApiParam("退换类型，1退款2退款退货3换货") @RequestParam(required = false) Short type,
            @ApiParam("退换状态，状态0:未处理1:处理中2:审核通过3拒绝审核4已发快递5已收到快递6平台介入8退款完成9撤销审核") @RequestParam(required = false) Short status,
            @ApiParam("支付时间") @RequestParam(required = false) String time
    ) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        Integer storeId = seller.getStoreId();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Map<String, Object>> maps = new ArrayList<>();
        Map<String, Object> map = null;
        List<TyReturnGoods> returnGoods = null;
        PageHelper.startPage(pageNum, pageSize);
        if (orderSn != null || type != null || status != null || time != null) {
            returnGoods = rgService.getByParametersAndTime(storeId, orderSn, type, status, time);
        } else {
            returnGoods = rgService.getByStoreId(storeId);
        }
        if (returnGoods != null) {
            for (TyReturnGoods returnGood : returnGoods) {
                map = new HashMap<>();
                map.put("id", returnGood.getId().toString());

                status = returnGood.getStatus();
                if (status == 0) {
                    map.put("status", "未处理");
                } else if (status == 1) {
                    map.put("status", "处理中");
                } else if (status == 2) {
                    map.put("status", "审核通过");
                } else if (status == 3) {
                    map.put("status", "拒绝审核");
                } else if (status == 4) {
                    map.put("status", "已发快递");
                } else if (status == 5) {
                    map.put("status", "已收快递");
                } else if (status == 6) {
                    map.put("status", "平台介入");
                } else if (status == 8) {
                    map.put("status", "退款完成");
                } else if (status == 9) {
                    map.put("status", "撤销审核");
                }
                orderSn = returnGood.getOrderSn();
                map.put("orderSn", orderSn);

                map.put("returnPrice", returnGood.getReturnMoney());

                type = returnGood.getType();
                if (type == 1) {
                    map.put("type", "退款");
                } else if (type == 2) {
                    map.put("type", "退款退货");
                } else if (type == 3) {
                    map.put("type", "换货");
                }
                //map.put("note", returnGood.getNote());
                Integer addtime = returnGood.getAddtime();
                if (addtime != null && addtime != 0) {
                    map.put("optTime", sdf.format(IntegerUtil.timeToLong(addtime)));
                } else {
                    map.put("optTime", "");
                }
                maps.add(map);
            }
        }
        result.setData(maps);
        if (maps.size() > 0) {
            result.setSuccess("退货单查询");
        } else {
            result.setNull("退货单");
        }
        return result;
    }

    @ApiOperation("退货单")
    //@RequestMapping(value = "/retRecoreds", method = RequestMethod.GET)
    @RequestMapping(value = "/retGoods/toExcel", method = RequestMethod.GET)
    public Result allReturnGoodsToExcel(
            @ApiParam @RequestParam String token,
            @ApiParam @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @ApiParam @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @ApiParam("订单编号") @RequestParam(required = false) String orderSn,
            @ApiParam("退换类型，1退款2退款退货3换货") @RequestParam(required = false) Short type,
            @ApiParam("退换状态，状态0:未处理1:处理中2:审核通过3拒绝审核4已发快递5已收到快递6平台介入8退款完成9撤销审核") @RequestParam(required = false) Short status,
            @ApiParam("支付时间") @RequestParam(required = false) String time,
            HttpServletResponse response
    ) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        Integer storeId = seller.getStoreId();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Map<String, Object>> maps = new ArrayList<>();
        Map<String, Object> map = null;
        List<TyReturnGoods> returnGoods = null;
        PageHelper.startPage(pageNum, pageSize);
        if (orderSn != null || type != null || status != null || time != null) {
            returnGoods = rgService.getByParametersAndTime(storeId, orderSn, type, status, time);
        } else {
            returnGoods = rgService.getByStoreId(storeId);
        }
        try {
            // 导出excel
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode("退货单.xls", "UTF-8"));
            response.setContentType("application/x-excel;charset=UTF-8");
            OutputStream outputStream = response.getOutputStream();
            Integer flag = rgService.exportToExcel(returnGoods, outputStream);
            if (flag > 0) {
                result.setSuccess("退货单");
            } else {
                result.setFail("退货单");
            }
        } catch (IOException e) {
            result.setFail("退货单");
        }
        return null;
    }

    @ApiOperation("退货单-删除")
    @RequestMapping(value = "/retGood", method = RequestMethod.DELETE)
    public Result deleteReturnGoodsDetail(
            @ApiParam @RequestParam String token,
            @ApiParam @RequestParam String id
    ) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }

        int flag = rgService.deleteById(id);
        if (flag > 0) {
            result.setSuccess("退货单删除");
        } else {
            result.setFail("退货单删除");
        }
        return result;

    }

    @ApiOperation("退货单-审核")
    @RequestMapping(value = "/retGood", method = RequestMethod.GET)
    public Result returnGoodsDetail(
            @ApiParam @RequestParam String token,
            @ApiParam @RequestParam String id
    ) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        Map<String, Object> map = new HashMap<>();
        TyReturnGoods returnGoods = rgService.getById(id);
        if (returnGoods == null) {
            result.setFail("退货单查询");
            return result;
        }

        String orderSn = returnGoods.getOrderSn();
        //订单编号
        map.put("orderSn", orderSn);

        TyOrder order = orderService.getByOrderSn(orderSn);
        if (order != null) {
            //支付方式
            map.put("payName", order.getPayName());
            String orderStatus = StringUtil.getOrderStatus(order.getOrderStatus());
            //订单状态
            map.put("orderStatus", orderStatus);
            //支付金额
            map.put("payPrice", order.getPayPrice());
        } else {
            map.put("payName", "");
            //订单状态
            map.put("orderStatus", "");
            //支付金额
            map.put("payPrice", "");
        }
        //用户
        Integer userId = returnGoods.getUserId();
        TyUsers user = usersService.getByUserId(userId);
        if (user != null) {
            map.put("userName", user.getUserName());
        } else {
            map.put("userName", "");
        }
        TyReturnGoodsDetail goodsDetail = rgdService.getByReturnId(returnGoods.getId());


//        Integer goodsId = returnGoods.getGoodsId();
//        TyGoods goods = goodsService.getById(goodsId);
        if (goodsDetail != null) {
            //商品名称
            map.put("goodsName", goodsDetail.getGoodsName());
            //商品数量
            map.put("goodsSum", goodsDetail.getGoodsNum());
            map.put("memberGoodsPrice", goodsDetail.getMemberGoodsPrice());
        } else {
            map.put("goodsName", "");
            map.put("goodsSum", "");
            map.put("memberGoodsPrice", "");
        }
        //退换货类型
        Short type = returnGoods.getType();
        if (type == 1) {
            map.put("type", "退款");
        } else if (type == 2) {
            map.put("type", "退款退货");
        } else {
            map.put("type", "换货");
        }
        //退货描述
        map.put("reason", returnGoods.getReason());
        //用户上传图片
        List<String> imgList = new ArrayList<>();
        String img = returnGoods.getImgs();
        if (img != null && !"".equals(img)) {
            String[] imgs = img.split(",");
            if (imgs != null && imgs.length > 0) {
                for (String image : imgs) {
                    imgList.add(image);
                }
            }
        }
        map.put("imgs", imgList);
        result.setData(map);
        if (!map.isEmpty()) {
            result.setSuccess("退货单详情查询");
        } else {
            result.setFail("退货单详情查询");
        }

        return result;
    }

    @ApiOperation("退货单-审核-保存")
    @RequestMapping(value = "/retGood/retRecord", method = RequestMethod.POST)
    public Result saveReturnRecord(
            @ApiParam @RequestParam String token,
            @ApiParam("退货单编号") @RequestParam String id,
            @ApiParam("状态0:未处理1:处理中2:审核通过3拒绝审核4已发快递5已收到快递6平台介入8退款完成9撤销审核") @RequestParam(required = false) Short status,
            @ApiParam @RequestParam(required = false) String orderSn,
            @ApiParam("1退款2退款退货3换货") @RequestParam(required = false) Short type,
            @ApiParam("处理备注") @RequestParam(required = false) String note
    ) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        Integer storeId = seller.getStoreId();
        TyReturnGoods returnGoods = rgService.getById(id);
        returnGoods.setStatus(status);
        int flag = rgService.modify(returnGoods);
        if (flag < 0) {
            result.setFail("退货单修改");
            return result;
        }
        TyBusinessReturnRecords returnRecords = new TyBusinessReturnRecords();
        returnRecords.setStoreId(storeId);

        long currentTimeMillis = System.currentTimeMillis();
        Integer time = IntegerUtil.timeToInteger(currentTimeMillis);
        returnRecords.setOperationTime(time);

        if (status != null) {
            returnRecords.setStatus(status);
        }
        if (orderSn != null) {
            returnRecords.setOrderSn(orderSn);
        }
        if (type != null) {
            returnRecords.setType(type);
        }
        if (note != null) {
            returnRecords.setNote(note);
        }
        flag = brrService.insert(returnRecords);
        if (flag > 0) {
            result.setSuccess("保存");
        } else {
            result.setFail("保存");
        }
        return result;

    }


    @ApiOperation("商品评论")
    @RequestMapping(value = "/comments", method = RequestMethod.GET)
    public Result allComments(
            @ApiParam @RequestParam String token,
            @ApiParam("用户名字") @RequestParam(required = false) String userName,
            @ApiParam("订单编号") @RequestParam(required = false) String orderSn,
            @ApiParam("商品名字") @RequestParam(required = false) String goodsName,
            @ApiParam("评论时间") @RequestParam(required = false) String time
    ) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        Integer storeId = seller.getStoreId();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String[] times = null;
        Integer startTime = null;
        Integer endTime = null;
        if (time != null) {
            times = time.split(",");
            try {
                if (times != null && times.length > 0) {
                    startTime = IntegerUtil.timeToInteger(sdf.parse(times[0].trim()).getTime());
                    endTime = IntegerUtil.timeToInteger(sdf.parse(times[1].trim()).getTime());
                }
            } catch (ParseException e) {
            }
        }
        List<Map<String, Object>> maps = new ArrayList<>();
        Map<String, Object> map = null;
        List<TyComment> comments = null;// commentService.getByParametersAndTime(null,storeId,userName,orderSn,startTime,endTime);
        comments = commentService.getByStoreId(storeId);
        if (comments != null) {
            for (TyComment comment : comments) {
                map = new HashMap<>();
                //编号
                map.put("commentId", comment.getCommentId());
                //订单编号
                Integer orderId = comment.getOrderId();
                TyOrder order = null;
                if (orderId != null) {
                    order = orderService.getByOrderId(orderId);
                }
                if (order != null) {
                    String commentOrderSn = order.getOrderSn();
                    if (orderSn != null && !"".equals(orderSn.trim()) && !orderSn.equals(commentOrderSn)) {
                        continue;
                    }
                    map.put("orderSn", commentOrderSn);
                } else {
                    if (orderSn != null && !"".equals(orderSn.trim())) {
                        continue;
                    }
                    map.put("orderSn", "");
                }
                //用户名
                Integer userId = comment.getUserId();
                TyUsers user = null;
                if (userId != null) {
                    user = usersService.getByUserId(userId);
                }
                if (user != null) {
                    String commentUserName = user.getUserName();
                    if (userName != null && !"".equals(userName.trim()) && userName.equals(commentUserName)) {
                        continue;
                    }
                    map.put("userName", commentUserName);
                } else {
                    if (userName != null && !"".equals(userName.trim())) {
                        continue;
                    }
                    map.put("userName", "");
                }
                //星级
                map.put("goodsRank", comment.getGoodsRank());
                //评论内容
                map.put("content", comment.getContent());

                //商品
                Integer goodsId = comment.getGoodsId();
                TyGoods goods = null;
                if (goodsId != null) {
                    goods = goodsService.getById(goodsId);
                }
                if (goods != null) {
                    if (goodsName != null && !"".equals(goodsName.trim()) && !goodsName.equals(goods.getGoodsName())) {
                        continue;
                    }
                    map.put("goodsName", goods.getGoodsName());
                } else {
                    if (goodsName != null && !"".equals(goodsName.trim())) {
                        continue;
                    }
                    map.put("goodsName", "");
                }
                //评论时间
                Integer addTime = comment.getAddTime();
                if (addTime != null && addTime != 0) {
                    if (startTime != null && endTime != null && (addTime > endTime || addTime < startTime)) {
                        continue;
                    }
                    map.put("addTime", sdf.format(addTime));
                } else {
                    if (startTime != null && endTime != null) {
                        continue;
                    }
                    map.put("addTime", "");
                }
                //ip地址
                map.put("idAddress", comment.getIpAddress());
                maps.add(map);
            }
        }
        result.setData(maps);
        if (maps.size() > 0) {
            result.setSuccess("商品评论列表查询");
        } else {
            result.setNull("商品评论列表");
        }
        return result;
    }

    @ApiOperation("商品评论-导出Excel（todo）")
    @RequestMapping(value = "/comments/toExcel", method = RequestMethod.GET)
    public Result allCommentsToExcel(
            @ApiParam @RequestParam String token,
            @ApiParam("用户名字") @RequestParam(required = false) String userName,
            @ApiParam("订单编号") @RequestParam(required = false) String orderSn,
            @ApiParam("商品名字") @RequestParam(required = false) String goodsName,
            @ApiParam("评论时间") @RequestParam(required = false) String time
    ) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        Integer storeId = seller.getStoreId();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String[] times = null;
        Integer startTime = null;
        Integer endTime = null;
        if (time != null) {
            times = time.split(",");
            try {
                startTime = IntegerUtil.timeToInteger(sdf.parse(times[0].trim()).getTime());
                endTime = IntegerUtil.timeToInteger(sdf.parse(times[1].trim()).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }


        }


        return result;

    }

    @ApiOperation("商品评论-删除")
    @RequestMapping(value = "/comment/del", method = RequestMethod.DELETE)
    public Result deleteComment(
            @ApiParam @RequestParam String token,
            @ApiParam @RequestParam Integer id
    ) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        Integer flag = commentService.deleteById(id);
        if (flag > 0) {
            result.setSuccess("评论删除");
        } else {
            result.setFail("评论删除");
        }
        return result;
    }

    @ApiOperation("商品评论-编辑")
    @RequestMapping(value = "/comment/detail", method = RequestMethod.GET)
    public Result detailComment(
            @ApiParam @RequestParam String token,
            @ApiParam @RequestParam Integer id
    ) {
        Result result = new Result();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        Integer storeId = seller.getStoreId();

        TyStore store = storeService.getById(storeId);

        List<Map<String, Object>> maps = new ArrayList<>();
        Map<String, Object> map = null;
        TyComment comment = commentService.getById(id);
        if (comment != null) {
            map = new HashMap<>();
            Integer addTime = comment.getAddTime();
            if (addTime != null && !"".equals(addTime) && !"0".equals(addTime)) {
                map.put("replyTime", sdf.format(IntegerUtil.timeToLong(addTime)));
            } else {
                map.put("replyTime", "");
            }
            Integer userId = comment.getUserId();
            TyUsers user = usersService.getByUserId(userId);
            if (user != null) {
                map.put("headPic", user.getHeadPic());
                map.put("userName", user.getUserName());
            } else {
                map.put("headPic", "");
                map.put("userName", "");
            }
            map.put("content", comment.getContent());
            map.put("fromUser", "1");
            maps.add(map);
        }

        List<TyReply> replies = replyService.getByCommentId(id);

        if (replies != null) {
            for (TyReply reply : replies) {
                map = new HashMap<>();
                map.put("fromUser", "2");
                map.put("content", reply.getContent());
                String userName = reply.getUserName();
                map.put("userName", userName);
                map.put("headPic", store.getStoreLogo());
                Integer replyTime = reply.getReplyTime();
                if (replyTime != null && replyTime != 0) {
                    map.put("replyTime", sdf.format(IntegerUtil.timeToLong(replyTime)));
                } else {
                    map.put("replyTime", "");
                }
                maps.add(map);
            }
        }
        result.setData(maps);
        if (maps.size() > 0) {
            result.setSuccess("评论查询");
        } else {
            result.setNull("评论查询");
        }
        return result;
    }

    @ApiOperation("商品评论-编辑-回复")
    @RequestMapping(value = "/comment/reply/add", method = RequestMethod.POST)
    public Result addReply(
            @ApiParam @RequestParam String token,
            @ApiParam @RequestParam(required = false) Integer commentId,
            @ApiParam("内容") @RequestParam(required = false) String content,
            @ApiParam("回复人") @RequestParam(required = false) String userName,
            @ApiParam("被回复人") @RequestParam(required = false) String toName
    ) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        TyReply reply = new TyReply();
        reply.setUserName(seller.getSellerName());
        if (commentId != null && !"".equals(commentId)) {
            reply.setCommentId(commentId);
        }
        if (content != null && !"".equals(content)) {
            reply.setContent(content);
        }
        if (toName != null && !"".equals(toName)) {
            reply.setToName(toName);
        }
        Short zone = 0;
        reply.setDeleted(zone);
        Long time = System.currentTimeMillis();
        reply.setReplyTime(IntegerUtil.timeToInteger(time));
        Integer flag = replyService.insert(reply);
        if (flag > 0) {
            result.setSuccess("添加回复");
        } else {
            result.setFail("添加回复");
        }
        return result;
    }

    @ApiOperation("退货地址")
    @RequestMapping(value = "/retAddresses", method = RequestMethod.GET)
    public Result allReturnAddress(@ApiParam @RequestParam String token) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        Integer storeId = seller.getStoreId();
        List<Map<String, Object>> maps = new ArrayList<>();
        Map<String, Object> map = null;
        List<TyStoreReturnAddress> returnAddresses = sraService.getByStoreId(storeId);
        if (returnAddresses != null) {
            for (TyStoreReturnAddress returnAddress : returnAddresses) {
                map = new HashMap<>();
                map.put("id", returnAddress.getReturnAddressId());
                //收货人
                map.put("consignee", returnAddress.getConsignee());
                //邮编
                map.put("zipcode", returnAddress.getZipcode());
                //省
                Integer provinceId = returnAddress.getProvince();
                TyRegion regionProvince = regionService.getById(provinceId);
                if (regionProvince != null) {
                    map.put("province", regionProvince.getName());
                } else {
                    map.put("province", "");
                }
                //市
                Integer cityId = returnAddress.getCity();
                TyRegion regionCity = regionService.getById(cityId);
                if (regionCity != null) {
                    map.put("city", regionCity.getName());
                } else {
                    map.put("city", "");
                }
                //区
                Integer districtId = returnAddress.getDistrict();
                TyRegion regionDistrict = regionService.getById(districtId);
                if (regionDistrict != null) {
                    map.put("district", regionDistrict.getName());
                } else {
                    map.put("district", "");
                }
                map.put("address", returnAddress.getAddress());
                map.put("isDefault", returnAddress.getIsDefault().toString());
                map.put("mobile", returnAddress.getMobile());
                maps.add(map);
            }
        }
        result.setData(maps);
        if (maps.size() > 0) {
            result.setSuccess("退货地址查询");
        } else {
            result.setNull("退货地址");
        }
        return result;
    }

    @ApiOperation("退货地址-删除")
    @RequestMapping(value = "/retAddress/del", method = RequestMethod.DELETE)
    public Result deleteReturnAddress(
            @ApiParam @RequestParam String token,
            @ApiParam("退货地址编号") @RequestParam Integer id) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        Integer flag = sraService.deleteById(id);
        if (flag > 0) {
            result.setSuccess("退货地址删除");
        } else {
            result.setFail("退货地址删除");
        }
        return result;
    }


    @ApiOperation("退货地址-详情")
    @RequestMapping(value = "/retAddress/detail", method = RequestMethod.GET)
    public Result detailReturnAddress(
            @ApiParam @RequestParam String token,
            @ApiParam("退货地址编号") @RequestParam(required = false) Integer id) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        Map<String, Object> map = new HashMap<>();
        if (id == null) {
            result.setFailMessage("请输入id");
            return result;
        }
        TyStoreReturnAddress address = sraService.getById(id);
        if (address != null) {
            map.put("id", id.toString());
            String consignee = address.getConsignee();
            if (consignee != null) {
                map.put("consignee", consignee);
            } else {
                map.put("consignee", "");
            }
            String mobile = address.getMobile();
            if (mobile != null) {
                map.put("mobile", mobile);
            } else {
                map.put("mobile", "");
            }
            String zipCode = address.getZipcode();
            if (mobile != null) {
                map.put("zipCode", zipCode);
            } else {

                map.put("zipCode", "");
            }
            Integer province = address.getProvince();
            if (province != null) {
                map.put("province", province.toString());
            } else {
                map.put("province", "");
            }
            Integer city = address.getCity();
            if (city != null) {
                map.put("city", city.toString());
            } else {
                map.put("city", "");
            }
            Integer district = address.getDistrict();
            if (district != null) {

                map.put("district", district.toString());
            } else {

                map.put("district", "");
            }
            map.put("address", address.getAddress());
        }

        result.setData(map);
        if (!map.isEmpty()) {
            result.setSuccess("退货地址查询");
        } else {
            result.setFail("退货地址查询");
        }
        return result;
    }

    @ApiOperation("退货地址-新增/修改")
    @RequestMapping(value = "/retAddress/add", method = RequestMethod.POST)
    public Result modifyReturnAddress(
            @ApiParam @RequestParam String token,
            @ApiParam("退货地址编号") @RequestParam(required = false) Integer id,
            @ApiParam("收货人") @RequestParam(required = false) String consignee,
            @ApiParam("联系电话") @RequestParam(required = false) String mobile,
            @ApiParam("邮编") @RequestParam(required = false) String zipCode,
            @ApiParam("省id") @RequestParam(required = false) Integer province,
            @ApiParam("市id") @RequestParam(required = false) Integer city,
            @ApiParam("区id") @RequestParam(required = false) Integer district,
            @ApiParam("详细地址") @RequestParam(required = false) String address
    ) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        Integer storeId = seller.getStoreId();
        TyStoreReturnAddress returnAddress = null;
        if (id != null && !"".equals(id)) {
            returnAddress = sraService.getById(id);
        } else {
            returnAddress = new TyStoreReturnAddress();
            returnAddress.setStoreId(storeId);
        }

        if (consignee != null && !"".equals(consignee)) {
            returnAddress.setConsignee(consignee);
        }
        if (mobile != null && !"".equals(mobile)) {
            returnAddress.setMobile(mobile);
        }
        if (zipCode != null && !"".equals(zipCode)) {
            returnAddress.setZipcode(zipCode);
        }
        if (province != null && !"".equals(province)) {
            returnAddress.setProvince(province);
        }
        if (city != null && !"".equals(city)) {
            returnAddress.setCity(city);
        }
        if (district != null && !"".equals(district)) {
            returnAddress.setDistrict(district);
        }
        if (address != null && !"".equals(address)) {
            returnAddress.setAddress(address);
        }
        if (id != null && !"".equals(id)) {
            Integer flag = sraService.modifyById(returnAddress);
            if (flag > 0) {
                result.setSuccess("退货地址修改");
            } else {
                result.setFail("退货地址修改");
            }
        } else {
            Integer flag = sraService.insert(returnAddress);
            if (flag > 0) {
                result.setSuccess("退货地址添加");
            } else {
                result.setFail("退货地址添加");
            }
        }

        return result;

    }


    @ApiOperation("退货地址-是否默认")
    @RequestMapping(value = "/retAddress/isDefault", method = RequestMethod.GET)
    public Result isDefalutAddress(
            @ApiParam @RequestParam String token,
            @ApiParam @RequestParam Integer id
    ) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        TyStoreReturnAddress returnAddress = sraService.getById(id);
        Short isDefault = returnAddress.getIsDefault();
        Short zone = 0;
        Short one = 1;
        returnAddress.setIsDefault(isDefault == 1 ? zone : one);
        Integer flag = sraService.modifyById(returnAddress);
        if (isDefault == 0) {
            if (flag > 0) {
                result.setSuccess("设置默认地址");
            } else {
                result.setFail("设置默认地址");
            }
        } else {
            if (flag > 0) {
                result.setSuccess("取消默认地址");
            } else {
                result.setFail("取消默认地址");
            }
        }


        return result;
    }

}
