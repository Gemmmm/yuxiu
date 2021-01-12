package com.tc.yuxiu.controller;

import com.tc.yuxiu.model.*;
import com.tc.yuxiu.service.TyGoodsService;
import com.tc.yuxiu.service.TyOrderGoodsService;
import com.tc.yuxiu.service.TyOrderService;
import com.tc.yuxiu.service.TySellerService;
import com.tc.yuxiu.util.DateUtil;
import com.tc.yuxiu.util.IntegerUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Api(tags = "统计报表")
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private TyOrderService orderService;
    @Autowired
    private TySellerService sellerService;
    @Autowired
    private TyOrderGoodsService ogService;
    @Autowired
    private TyGoodsService goodsService;

    @ApiOperation("店铺概述")
    @GetMapping("/index")
    public Result indexDate(
            @RequestParam String token,
            @RequestParam(required = false) String time
    ) {
        Result result = new Result();
        Map<String, Object> resultMap = new HashMap<>();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        Integer storeId = seller.getStoreId();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Integer start = null;
        Integer end = null;
        Date startTime = null;
        Date endTime = null;
        int n = 0;
        if (time != null) {
            System.out.println("time:" + time);
            String[] times = time.split(",");
            try {
                if (times != null && times.length > 0) {
                    startTime = sdf.parse(times[0].trim());
                    start = IntegerUtil.timeToInteger(startTime.getTime());
                    endTime = sdf.parse(times[1].trim());
                    end = IntegerUtil.timeToInteger(endTime.getTime());
                    n = (end - start) / (24 * 60 * 60);
                }
            } catch (ParseException e) {
            }

        } else {
            long thirtyDaysAgoLong = DateUtil.thirtyDaysAgo();
            start = IntegerUtil.timeToInteger(thirtyDaysAgoLong);
            n = 30;
        }
        List<Map<String, Object>> maps = new ArrayList<>();
        Map<String, Object> map = null;
        long saleMoney = 0;
        long averagePrice = 0;
        int totalOrderNum = 0;
        int cancelOrderNum = 0;

        for (int i = 0; i < n; i++) {
            map = new HashMap<>();
            map.put("id", i);
            Integer startDate = start + 24 * 60 * 60 * i;
            Integer endDate = startDate + (24 * 60 * 60);

            map.put("startDate", sdf.format(new Date(IntegerUtil.timeToLong(startDate))));

            map.put("endDate", sdf.format(new Date(IntegerUtil.timeToLong(endDate))));
            List<TyOrder> orders = orderService.getByStoreIdAndTime(storeId, startDate, endDate);
            Integer orderNum = 0;
            long totalPayPrice = 0;
            long totalSinglePrice = 0;
            if (orders != null) {
                orderNum = orders.size();
                for (TyOrder order : orders) {
                    totalPayPrice += order.getPayPrice().longValue();
                }
            }
            map.put("orderNum", orderNum);
            map.put("totalPayPrice", totalPayPrice);
            map.put("totalSinglePrice", totalSinglePrice);
            maps.add(map);
        }
        resultMap.put("echartDate", maps);
        resultMap.put("saleMoney", saleMoney);
        resultMap.put("averagePrice", averagePrice);
        resultMap.put("totalOrderNum", totalOrderNum);
        resultMap.put("cancelOrderNum", cancelOrderNum);
        result.setData(resultMap);
        if (maps.size() > 0) {
            result.setSuccess("店铺概况查询");
        } else {
            result.setNull("店铺概况查询");
        }
        return result;
    }


    @ApiOperation("运营状况")
    @GetMapping("/finance")
    public Result financeData(
            @RequestParam String token,
            @RequestParam(required = false) String time
    ) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        Integer storeId = seller.getStoreId();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Integer start = null;
        Integer end = null;
        Date startTime = null;
        Date endTime = null;
        int n = 0;
        if (time != null) {
            String[] times = time.split(",");
            try {
                if (times != null && times.length > 0) {
                    startTime = sdf.parse(times[0].trim());
                    start = IntegerUtil.timeToInteger(startTime.getTime());
                    endTime = sdf.parse(times[1].trim());
                    end = IntegerUtil.timeToInteger(endTime.getTime());
                    n = (end - start) / (24 * 60 * 60);
                }
            } catch (ParseException e) {
            }

        } else {
            long thirtyDaysAgoLong = DateUtil.thirtyDaysAgo();
            start = IntegerUtil.timeToInteger(thirtyDaysAgoLong);
            n = 30;
        }
        List<Map<String, Object>> maps = new ArrayList<>();
        Map<String, Object> map = null;
        for (int i = 0; i < n; i++) {
            map = new HashMap<>();
            map.put("id", i);
            Integer startDate = start + 24 * 60 * 60 * i;
            Integer endDate = startDate + (24 * 60 * 60);

            map.put("startDate", sdf.format(new Date(IntegerUtil.timeToLong(startDate))));

            map.put("endDate", sdf.format(new Date(IntegerUtil.timeToLong(endDate))));
            List<TyOrder> orders = orderService.getByStoreIdAndTime(storeId, startDate, endDate);

            long orderGoodsTotalPrice = 0;
            long orderCouponTotalPrice = 0;
            long totalCost = 0;
            long shippingTotalPrice = 0;


            if (orders != null) {
                for (TyOrder order : orders) {
                    orderGoodsTotalPrice += order.getGoodsPrice().longValue();
                    orderCouponTotalPrice += order.getCouponPrice().longValue();
                    shippingTotalPrice += order.getShippingPrice().longValue();

                }
            }
            map.put("orderGoodsTotalPrice", orderGoodsTotalPrice);
            map.put("orderCouponTotalPrice", orderCouponTotalPrice);
            map.put("totalCost", totalCost);
            map.put("shippingTotalPrice", shippingTotalPrice);
            maps.add(map);
        }
        result.setData(maps);
        if (maps.size() > 0) {
            result.setSuccess("运营成本查询");
        } else {
            result.setNull("运营成本查询");
        }

        return result;
    }

    @ApiOperation("店铺概述/运营状况-订单列表")
    @GetMapping("/orderGoods")
    public Result indexDate(
            @RequestParam String token,
            @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String endTime
    ) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        Integer start = null;
        Integer end = null;


        Integer storeId = seller.getStoreId();
        Map<String, Object> resultMap = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (startTime != null && endTime != null) {
            resultMap.put("startTime", startTime);
            resultMap.put("endTime", endTime);
            try {
                start = IntegerUtil.timeToInteger(sdf.parse(startTime).getTime());
                end = IntegerUtil.timeToInteger(sdf.parse(endTime).getTime());
            } catch (ParseException e) {
                System.out.println("时间解析错误");
            }

        } else {
            resultMap.put("startTime", "");
            resultMap.put("endTime", "");
        }
        List<TyOrder> orders = null;
        if (start != null && end != null) {
            orders = orderService.getByStoreIdAndTime(storeId, start, end);
        } else {
            orders = orderService.getByStoreId(storeId);
        }
        List<Map<String, Object>> maps = new ArrayList<>();
        Map<String, Object> map = null;
        if (orders != null) {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (TyOrder order : orders) {
                List<TyOrderGoods> orderGoodsList = ogService.getByOrderId(order.getOrderId());
                Integer payTime = order.getPayTime();
                String time = "";
                if (payTime != null && payTime != 0) {
                    time = sdf.format(IntegerUtil.timeToLong(payTime));
                }
                if (orderGoodsList != null) {
                    for (TyOrderGoods goods : orderGoodsList) {
                        map=new HashMap<>();
                        map.put("id", goods.getRecId().toString());
                        map.put("goodsName", goods.getGoodsNum());
                        map.put("goodsSn", goods.getGoodsSn());
                        map.put("goodsNum", goods.getGoodsNum());
                        map.put("goodsPrice", goods.getGoodsPrice().toString());
                        map.put("memberGoodsPrice",goods.getMemberGoodsPrice());
                        map.put("costPrice", goods.getCostPrice().toString());
                        map.put("time", time);
                        maps.add(map);
                    }
                }

            }
        }
        resultMap.put("goodsData", maps);
        result.setData(resultMap);
        if (maps.size() > 0) {
            result.setSuccess("商品信息");
        } else {
            result.setNull("商品信息");
        }
        return result;
    }

    @ApiOperation("销售排行")
    @GetMapping("/saleTop")
    public Result saleTopDate(
            @RequestParam String token,
            @RequestParam(required = false) String time,
            @RequestParam(required = false) Integer sort
    ) {
        //System.out.println("1:"+sdf.format(new Date()));
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        List<Map<String, Object>> maps = new ArrayList<>();
        Map<String, Object> map = null;
        Map<String, Integer> goodsMap = new HashMap<>();
        Integer storeId = seller.getStoreId();
        List<TyOrder> orderList = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<TyOrderGoods> orderGoodsList = new ArrayList<>();
        if (time != null) {
            Integer start = null;
            Integer end = null;
            Date startTime = null;
            Date endTime = null;
            if (time != null) {
                String[] times = time.split(",");
                try {
                    if (times != null && times.length > 0) {
                        startTime = sdf.parse(times[0].trim());
                        start = IntegerUtil.timeToInteger(startTime.getTime());
                        endTime = sdf.parse(times[1].trim());
                        end = IntegerUtil.timeToInteger(endTime.getTime());
                    }
                } catch (ParseException e) {
                }
            }
            orderList = orderService.getByStoreIdAndTime(storeId, start, end);
        } else {
            orderList = orderService.getByStoreId(storeId);
        }


        if (orderList != null) {
            for (TyOrder order : orderList) {
                List<TyOrderGoods> orderGoods = ogService.getByOrderId(order.getOrderId());
                for (TyOrderGoods orderGood : orderGoods) {
                    orderGoodsList.add(orderGood);
                    String goodsId = orderGood.getGoodsId().toString();
                    if (goodsMap.containsKey(goodsId)) {
                        Integer count = goodsMap.get(goodsId);
                        count++;
                        goodsMap.put(goodsId, count);
                    } else {
                        goodsMap.put(goodsId, 1);
                    }
                }


            }

        }
        // System.out.println("2:"+sdf.format(new Date()));
        //注意 ArrayList<>() 括号里要传入map.entrySet()
        List<Map.Entry<String, Integer>> list = new ArrayList<>(goodsMap.entrySet());
//        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
//            @Override
//            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
//                //按照value值，从小到大排序
////                return o1.getValue() - o2.getValue();
//                //按照value值，从大到小排序
//                return o2.getValue() - o1.getValue();
//                //按照value值，用compareTo()方法默认是从小到大排序
////                return o1.getValue().compareTo(o2.getValue());
//            }
//        });

        Collections.sort(list, (o1, o2) -> o2.getValue() - o1.getValue());

        int n = list.size() > 10 ? 11 : list.size();
        if (sort == null || "".equals(sort)) {
            for (Integer i = 1; i < n; i++) {
                map = new HashMap<>();
                Map.Entry s = list.get(i - 1);
                Integer goodsId = Integer.valueOf(s.getKey().toString());
                TyGoods goods = goodsService.getById(goodsId);
                if (goods != null) {
                    map.put("goodsName", goods.getGoodsName());
                    map.put("goodsSn", goods.getGoodsSn());
                } else {
                    map.put("goodsSn", "");
                    map.put("goodsName", "");
                }
                Long totalPrice = 0L;
                int totalNum = 1;
                orderGoodsList = ogService.getByGoodsId(goodsId);
                if (orderGoodsList != null) {
                    for (TyOrderGoods orderGoods : orderGoodsList) {
                        if (orderGoods != null) {
                            totalPrice += orderGoods.getGoodsNum() * orderGoods.getGoodsPrice().longValue();
                        }
                    }
                    totalNum = orderGoodsList.size();
                }
                map.put("average", totalPrice / totalNum);
                map.put("totalPrice", totalPrice.toString());
                map.put("sort", i.toString());
                map.put("goodsId", goodsId.toString());
                map.put("count", s.getValue().toString());
                maps.add(map);
            }
        } else {
            Map.Entry s = list.get(sort - 1);
            map = new HashMap<>();
            Integer goodsId = Integer.valueOf(s.getKey().toString());
            TyGoods goods = goodsService.getById(goodsId);
            if (goods != null) {
                map.put("goodsSn", goods.getGoodsSn());
                map.put("goodsName", goods.getGoodsName());
            } else {
                map.put("goodsSn", "");
                map.put("goodsName", "");
            }
            Long totalPrice = 0L;
            int totalNum = 1;
            orderGoodsList = ogService.getByGoodsId(goodsId);
            if (orderGoodsList != null) {
                totalNum = orderGoodsList.size();
                for (TyOrderGoods orderGoods : orderGoodsList) {
                    if (orderGoods != null) {
                        totalPrice += orderGoods.getGoodsNum() * orderGoods.getGoodsPrice().longValue();
                    }

                }
            }
            map.put("average", totalPrice / totalNum);
            map.put("totalPrice", totalPrice.toString());
            map.put("sort", sort.toString());
            map.put("goodsId", goodsId.toString());
            map.put("count", s.getValue().toString());
            maps.add(map);
        }


        //System.out.println("4:"+sdf.format(new Date()));
        result.setData(maps);
        if (maps.size() > 0) {
            result.setSuccess("销售排行查询");
        } else {
            result.setNull("销售排行");
        }
        return result;
    }
}
