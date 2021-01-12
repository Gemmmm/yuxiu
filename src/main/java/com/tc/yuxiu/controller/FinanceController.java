package com.tc.yuxiu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.regexp.internal.RE;
import com.tc.yuxiu.model.*;
import com.tc.yuxiu.service.*;
import com.tc.yuxiu.util.IntegerUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.ss.formula.functions.Odd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Api(tags = "财务管理")
@RestController
@RequestMapping("/finance")
public class FinanceController {

    @Autowired
    private TySellerService sellerService;
    @Autowired
    private TyStoreWithdrawalsService swService;
    @Autowired
    private TyStoreRemittanceService srService;
    @Autowired
    private TyBusinessReturnRecordsService brrService;
    @Autowired
    private TyStoreService storeService;
    @Autowired
    private TyOrderService orderService;

    @Autowired
    private TyOrderStatisService osService;

    @ApiOperation("提现申请")
    @GetMapping("/withdrawals")
    public Result withdrawalsData(
            @ApiParam(required = true) @RequestParam String token,
            @ApiParam @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @ApiParam @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @ApiParam("提现状态0申请中1申请成功2申请失败") @RequestParam(required = false) Short status,
            @ApiParam("银行账号") @RequestParam(required = false) String accountBank,
            @ApiParam("账户名") @RequestParam(required = false) String accountName,
            @ApiParam("时间") @RequestParam(required = false) String time
    ) {
        Result result = new Result();
        Map<String, Object> resultMap = new HashMap<>();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        Integer storeId = seller.getStoreId();
        TyStore store = storeService.getById(storeId);
        BigDecimal storeMoney = store.getStoreMoney();
        if (storeMoney != null) {
            resultMap.put("storeMoney", storeMoney.toString());
        } else {
            resultMap.put("storeMoney", "0");
        }
        BigDecimal pendingMoney = store.getPendingMoney();
        if (pendingMoney != null) {
            resultMap.put("pendingMoney", pendingMoney.toString());
        } else {

            resultMap.put("pendingMoney", "0");
        }

        PageHelper.startPage(pageNum, pageSize);
        List<TyStoreWithdrawals> withdrawals = null;
        if (status != null || accountBank != null || accountName != null || time != null) {
            withdrawals = swService.getByParametersAndTime(storeId, status, accountBank, accountName, time);
        } else {
            withdrawals = swService.getByStoreId(storeId);
        }

        List<Map<String, Object>> maps = new ArrayList<>();
        Map<String, Object> map = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        PageInfo<TyStoreWithdrawals> page = new PageInfo<>(new ArrayList<>());
        if (withdrawals != null) {
            page = new PageInfo<>(withdrawals);
            for (TyStoreWithdrawals withdrawal : withdrawals) {
                map = new HashMap<>();
                map.put("id", withdrawal.getId().toString());
                Integer createTime = withdrawal.getCreateTime();
                if (createTime != null && createTime != 0) {
                    map.put("createTime", sdf.format(IntegerUtil.timeToLong(createTime)));
                } else {
                    map.put("createTime", "");
                }
                //申请金额
                map.put("money", withdrawal.getMoney());
                //银行名称
                map.put("bankName", withdrawal.getBankName());
                //银行账号
                map.put("accountBank", withdrawal.getAccountBank());
                //银行账户
                map.put("accountName", withdrawal.getAccountName());
                status = withdrawal.getStatus();
                if (status == 0) {
                    map.put("status", "申请中");
                } else if (status == 1) {
                    map.put("status", "申请成功");
                } else if (status == 2) {
                    map.put("status", "申请失败");
                }
                map.put("remark", withdrawal.getRemark());
                maps.add(map);
            }
        }
        resultMap.put("page", page);
        resultMap.put("withdrawals", maps);
        result.setData(resultMap);
        if (maps.size() == 0) {
            result.setNull("提现申请");
        } else {
            result.setSuccess("提现申请查询");
        }
        return result;
    }

    @ApiOperation("提现申请-导出excel")
    @GetMapping("/withdrawals/toExcel")
    public Result withdrawalsToExcel(
            @ApiParam(required = true) @RequestParam String token,
            @ApiParam("提现状态0申请中1申请成功2申请失败") @RequestParam(required = false) Short status,
            @ApiParam("银行账号") @RequestParam(required = false) String accountBank,
            @ApiParam("账户名") @RequestParam(required = false) String accountName,
            @ApiParam("时间") @RequestParam(required = false) String time,
            HttpServletResponse response
    ) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        Integer storeId = seller.getStoreId();
        List<TyStoreWithdrawals> withdrawals = null;
        if (status != null || accountBank != null || accountName != null || time != null) {
            withdrawals = swService.getByParametersAndTime(storeId, status, accountBank, accountName, time);
        } else {
            withdrawals = swService.getByStoreId(storeId);
        }
        try {
            // 导出excel
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("提现申请表.xls", "UTF-8"));
            response.setContentType("application/x-excel;charset=UTF-8");
            OutputStream outputStream = response.getOutputStream();
            Integer flag = swService.exportToExcel(withdrawals, outputStream);
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

    @ApiOperation("提现申请-详情")
    @GetMapping("/withdrawal")
    public Result withdrawalsDetail(
            @ApiParam(required = true) @RequestParam String token,
            @ApiParam @RequestParam Integer id
    ) {
        Result result = new Result();
        Map<String, Object> resultMap = new HashMap<>();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }


        TyStoreWithdrawals withdrawal = swService.getById(id);
        Map<String, Object> map = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (withdrawal != null) {
            map.put("id", withdrawal.getId().toString());
            Integer createTime = withdrawal.getCreateTime();
            if (createTime != null && createTime != 0) {
                map.put("createTime", sdf.format(IntegerUtil.timeToLong(createTime)));
            } else {
                map.put("createTime", "");
            }
            //申请金额
            map.put("money", withdrawal.getMoney());
            //银行名称
            map.put("bankName", withdrawal.getBankName());
            //银行账号
            map.put("accountBank", withdrawal.getAccountBank());
            //银行账户
            map.put("accountName", withdrawal.getAccountName());
            Short status = withdrawal.getStatus();
            if (status == 0) {
                map.put("status", "申请中");
            } else if (status == 1) {
                map.put("status", "申请成功");
            } else if (status == 2) {
                map.put("status", "申请失败");
            }
            map.put("remark", withdrawal.getRemark());
        }

        result.setData(map);
        if (resultMap.isEmpty()) {
            result.setNull("提现申请");
        } else {
            result.setSuccess("提现申请查询");
        }
        return result;
    }

    @ApiOperation("提现申请-添加/修改")
    @PostMapping("/withdrawal/add")
    public Result addWithdrawalsData(
            @ApiParam(required = true) @RequestParam String token,
            @ApiParam("收款账号") @RequestParam(required = false) String accountBank,
            @ApiParam("开户人") @RequestParam(required = false) String accountName,
            @ApiParam("提现金额") @RequestParam(required = false) String money,
            @ApiParam("银行名称") @RequestParam(required = false) String bankName,
            @ApiParam("备注") @RequestParam(required = false) String remark,
            @ApiParam("id") @RequestParam(required = false) Integer id
    ) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        Integer storeId = seller.getStoreId();

        TyStoreWithdrawals withdrawal = new TyStoreWithdrawals();
        if (id != null) {
            withdrawal = swService.getById(id);
        }
        withdrawal.setStoreId(storeId);
        long timeMillis = System.currentTimeMillis();
        Integer crateTime = IntegerUtil.timeToInteger(timeMillis);
        withdrawal.setCreateTime(crateTime);
        if (money == null || "".equals(money) || "0".equals(money)) {
            result.setFailMessage("提现金额不能为空");
            return result;
        }
        withdrawal.setMoney(new BigDecimal(money));
        if (bankName == null || "".equals(bankName)) {
            result.setFailMessage("银行名称不能为空");
            return result;
        }
        withdrawal.setBankName(bankName);
        if (accountBank == null || "".equals(accountBank)) {
            result.setFailMessage("收款账号不能为空");
            return result;
        }
        withdrawal.setAccountBank(accountBank);
        if (accountName == null || "".equals(accountName)) {
            result.setFailMessage("开户名不能为空");
            return result;
        }
        withdrawal.setAccountName(accountName);
        withdrawal.setRemark(remark);
        int flag = 0;
        if (id != null) {
            flag = swService.modify(withdrawal);
            if (flag > 0) {
                result.setSuccess("提现申请修改");
            } else {
                result.setFail("提现申请修改");
            }
        } else {
            flag = swService.insert(withdrawal);
            if (flag > 0) {
                result.setSuccess("提现申请添加");
            } else {
                result.setFail("提现申请添加");
            }
        }

        return result;
    }

    @ApiOperation("提现申请-删除")
    @PostMapping("/withdrawal/del")
    public Result deleteWithdrawals(
            @ApiParam(required = true) @RequestParam String token,
            @ApiParam(required = true) @RequestParam Integer id,
            @ApiParam("类型，1软删除，0删除") @RequestParam Integer type
    ) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        int flag = 0;
        if (type == 0) {
            flag = swService.deleteById(id);
        } else {
            TyStoreWithdrawals withdrawals = swService.getById(id);
            withdrawals.setDeleted((short) 1);
            long timeMillis = System.currentTimeMillis();
            withdrawals.setDeletedTime(IntegerUtil.timeToInteger(timeMillis));
            flag = swService.modify(withdrawals);
        }
        if (flag > 0) {
            result.setSuccess("删除");
        } else {
            result.setFail("删除");
        }
        return result;
    }

    @ApiOperation("汇款记录")
    @GetMapping("/remittances")
    public Result remittancesData(
            @ApiParam(required = true) @RequestParam String token,
            @ApiParam @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @ApiParam @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @ApiParam("银行账号") @RequestParam(required = false) String accountBank,
            @ApiParam("账户名") @RequestParam(required = false) String accountName,
            @ApiParam("开始时间") @RequestParam(required = false) String time
    ) {
        Result result = new Result();
        Map<String,Object> resultMap=new HashMap<>();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        Integer storeId = seller.getStoreId();
        PageHelper.startPage(pageNum, pageSize);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<TyStoreRemittance> remittances = null;
        if (accountBank != null || accountName != null || time != null) {
            remittances = srService.getByParametersAndTime(storeId, accountBank, accountName, time);
        } else {

            remittances = srService.getByStoreId(storeId);
        }
        if (remittances == null) {
            result.setData(new ArrayList<>());
            result.setNull("汇款表");
            return result;
        }
        List<Map<String, Object>> maps = new ArrayList<>();
        Map<String, Object> map = null;
        PageInfo<TyStoreRemittance> page = new PageInfo<>(new ArrayList<>());
        if (remittances != null) {
            page = new PageInfo<>(remittances);
            for (TyStoreRemittance remittance : remittances) {
                map = new HashMap<>();
                map.put("id", remittance.getId().toString());
                map.put("bankName", remittance.getBankName());
                map.put("accountBank", remittance.getAccountBank());
                map.put("accountName", remittance.getAccountName());
                map.put("money", remittance.getMoney());
                Short status = remittance.getStatus();
                if (status == 1) {
                    map.put("status", "汇款成功");
                } else {

                    map.put("status", "汇款失败");
                }
                Integer createTime = remittance.getCreateTime();
                if (createTime != null && createTime != 0) {
                    map.put("createTime", sdf.format(IntegerUtil.timeToLong(createTime)));
                } else {
                    map.put("createTime", "");
                }
                map.put("remark", remittance.getRemark());
                maps.add(map);
            }
        }
        // TODO: 2020/11/3  分页
        resultMap.put("list",maps);
        resultMap.put("page",page);
        result.setPage(page);
        result.setData(maps);
        result.setSuccess("汇款单查询");
        return result;

    }

    @ApiOperation("汇款记录-导出excel")
    @GetMapping("/remittances/toExcel")
    public Result remittancesToExcel(
            @ApiParam(required = true) @RequestParam String token,
            @ApiParam("银行账号") @RequestParam(required = false) String accountBank,
            @ApiParam("账户名") @RequestParam(required = false) String accountName,
            @ApiParam("时间") @RequestParam(required = false) String time,
            HttpServletResponse response
    ) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        Integer storeId = seller.getStoreId();

        List<TyStoreRemittance> remittances = null;
        if (accountBank != null || accountName != null || time != null) {
            remittances = srService.getByParametersAndTime(storeId, accountBank, accountName, time);
        } else {
            remittances = srService.getByStoreId(storeId);
        }
        System.out.println("remittances::" + remittances);
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("汇款记录.xls", "UTF-8"));
            response.setContentType("application/x-excel;charset=UTF-8");
            OutputStream outputStream = response.getOutputStream();
            int flag = srService.exportToExcel(remittances, outputStream);
            if (flag > 0) {
                result.setSuccess("汇款记录");
            } else {
                result.setFail("汇款记录");
            }
        } catch (IOException e) {
            result.setFail("未完成列表导出");
        }

        return null;

    }

    @ApiOperation("商家结算记录")
    @GetMapping("/orderStatises")
    public Result statisOrder(
            @ApiParam @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @ApiParam @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @ApiParam(required = true) @RequestParam String token,
            @ApiParam @RequestParam(required = false) String time
    ) {
        Result result = new Result();
        Map<String,Object> resultMap=new HashMap<>();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        List<Map<String, Object>> maps = new ArrayList<>();
        Map<String, Object> map = null;
        Integer storeId = seller.getStoreId();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<TyOrderStatis> orderStatises = null;
        PageHelper.startPage(pageNum, pageSize);
        if (time != null) {
            orderStatises = osService.getByStoreIdAndTime(storeId, time);
        } else {
            orderStatises = osService.getByStoreId(storeId);
        }
        PageInfo<TyOrderStatis> page = new PageInfo<>(new ArrayList<>());
        if (orderStatises != null) {
            page = new PageInfo<>(orderStatises);
            for (TyOrderStatis orderStatis : orderStatises) {
                map = new HashMap<>();
                map.put("id", orderStatis.getId().toString());
                Integer startDate = orderStatis.getStartDate();
                if (startDate != null && startDate != 0) {
                    map.put("startDate", sdf.format(IntegerUtil.timeToLong(startDate)));
                } else {
                    map.put("startDate", "");
                }
                Integer endDate = orderStatis.getEndDate();
                if (endDate != null && endDate != 0) {
                    map.put("endDate", sdf.format(IntegerUtil.timeToLong(endDate)));
                } else {
                    map.put("endDate", "");
                }
                map.put("orderTotals", orderStatis.getOrderTotals().toString());
                map.put("shippingTotals", orderStatis.getShippingTotals().toString());
                map.put("commisTotals", orderStatis.getCommisTotals().toString());
                map.put("resultTotals", orderStatis.getResultTotals().toString());

                Integer createDate = orderStatis.getCreateDate();
                if (createDate != null && createDate != 0) {
                    map.put("createDate", sdf.format(IntegerUtil.timeToLong(createDate)));
                } else {
                    map.put("createDate", "");
                }
                maps.add(map);

            }
        }
        resultMap.put("list",maps);
        resultMap.put("page",page);
        result.setPage(page);
        // TODO: 2020/11/3 分页
        result.setData(maps);
        if (maps.size() > 0) {
            result.setSuccess("商家结算记录查询");
        } else {
            result.setNull("商家结算记录");
        }


        return result;
    }


    @ApiOperation("商家结算记录-到处excel")
    @GetMapping("/orderStatises/toExcel")
    public Result statisOrderToExcel(
            @ApiParam(required = true) @RequestParam String token,
            @ApiParam @RequestParam(required = false) String time,
            HttpServletResponse response
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<TyOrderStatis> orderStatises = null;
        if (time != null) {
            orderStatises = osService.getByStoreIdAndTime(storeId, time);
        } else {
            orderStatises = osService.getByStoreId(storeId);
        }
        try {
            // 导出excel
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("商家结算记录.xls", "UTF-8"));
            response.setContentType("application/x-excel;charset=UTF-8");
            OutputStream outputStream = response.getOutputStream();
            int flag = osService.exportToExcel(orderStatises, outputStream);
            if (flag > 0) {
                result.setSuccess("未完成列表导出");
            } else {
                result.setFail("未完成列表导出");
            }
        } catch (IOException e) {
            result.setFail("未完成列表导出");
        }

        return null;
    }

    @ApiOperation("未完成订单")
    @GetMapping("/orders/unfinished")
    public Result unFinishedOrder(
            @ApiParam(required = true) @RequestParam String token,
            @ApiParam @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @ApiParam @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @ApiParam @RequestParam(required = false) String orderSn,
            @ApiParam @RequestParam(required = false) String time
    ) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        Integer storeId = seller.getStoreId();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Map<String, Object>> maps = new ArrayList<>();
        Map<String, Object> map = null;
        PageHelper.startPage(pageNum, pageSize);
        List<TyOrder> orders = orderService.getByStoreIdAndOrderSnAndTimeNoOrderStatus(storeId, orderSn, time, 4);
        if (orders == null) {
            result.setData(new ArrayList<>());
            result.setNull("未完成订单为空");
            return result;
        }
        PageInfo<TyOrder> page=new PageInfo<>(new ArrayList<>());
        if (orders != null) {
            page=new PageInfo<>(orders);
            for (TyOrder order : orders) {
                map = new HashMap<>();
                map.put("id", order.getOrderId());
                map.put("orderSn", order.getOrderSn());
                Integer shippingStatus = order.getShippingStatus();
                if (shippingStatus == 1) {
                    map.put("shippingStatus", "已发货");
                } else {

                    map.put("shippingStatus", "未发货");
                }
                Integer addTime = order.getAddTime();
                if (addTime != null && addTime != 0) {
                    map.put("addTime", sdf.format(IntegerUtil.timeToLong(addTime)));
                } else {
                    map.put("addTime", "");
                }
                Integer orderStatus = order.getOrderStatus();
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
                } else if (orderStatus == 4) {
                    map.put("orderStatus", "退货（换货）中");
                }

                Integer shippingTime = order.getShippingTime();
                if (shippingTime != null && shippingTime != 0) {
                    map.put("shippingTime", sdf.format(IntegerUtil.timeToLong(shippingTime)));
                } else {
                    map.put("shippingTime", "未发货");
                }
                maps.add(map);
            }
        }
        // TODO: 2020/11/3 分页
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("list",maps);
        resultMap.put("page",page);
        result.setPage(page);
        result.setData(maps);
        result.setSuccess("未完成订单查询");
        return result;

    }


    @ApiOperation("未完成订单-导出excel")
    @GetMapping("/orders/unfinished/toExcel")
    public Result unFinishedOrderToExcel(
            @ApiParam(required = true) @RequestParam String token,
            @ApiParam @RequestParam(required = false) String orderSn,
            @ApiParam @RequestParam(required = false) String time,
            HttpServletResponse response) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        Integer storeId = seller.getStoreId();
        List<TyOrder> orders = null;
        if (orderSn != null || time != null) {
            orders = orderService.getByStoreIdAndOrderSnAndTimeNoOrderStatus(storeId, orderSn, time, 4);
        } else {
            orders = orderService.getByStoreIdAndOrderSnAndTimeNoOrderStatus(storeId, null, null, 4);
        }
        try {
            // 导出excel
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("未完成订单.xls", "UTF-8"));
            response.setContentType("application/x-excel;charset=UTF-8");
            OutputStream outputStream = response.getOutputStream();
            int flag = orderService.exportToExcel(orders, outputStream);
            if (flag > 0) {
                result.setSuccess("未完成列表导出");
            } else {
                result.setFail("未完成列表导出");
            }
        } catch (IOException e) {
            result.setFail("未完成列表导出");
        }

        return null;

    }

    @ApiOperation("退货操作记录")
    @GetMapping("/recRecords")
    public Result allRetRecords(
            @ApiParam(required = true) @RequestParam String token,
            @ApiParam @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @ApiParam @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @ApiParam @RequestParam(required = false) String orderSn,
            @ApiParam @RequestParam(required = false) String time
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<TyBusinessReturnRecords> retRecords = null;
        PageHelper.startPage(pageNum, pageSize);
        if (time != null || orderSn != null) {
            retRecords = brrService.getByParametersAndTime(storeId, orderSn, null, null, time);
        } else {

            retRecords = brrService.getByStoreId(storeId);
        }
        PageInfo<TyBusinessReturnRecords> page=new PageInfo<>(new ArrayList<>());
        if (retRecords == null) {
            result.setData(new ArrayList<>());
            result.setNull("退货操作记录");
            return result;
        }
        page=new PageInfo<>(retRecords);
        for (TyBusinessReturnRecords retRecord : retRecords) {
            map = new HashMap<>();
            map.put("id", retRecord.getId().toString());
            map.put("orderSn", retRecord.getOrderSn());
            Short type = retRecord.getType();
            if (type == 1) {
                map.put("type", "退款");
            } else if (type == 2) {
                map.put("type", "退货退款");
            } else if (type == 3) {
                map.put("type", "换货");
            }
            Short status = retRecord.getStatus();
            if (status == 1) {
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
            } else if (status == 7) {
                map.put("status", "处理中");
            } else if (status == 8) {
                map.put("status", "退款完成");
            } else if (status == 9) {
                map.put("status", "撤销审核");
            } else {
                map.put("status", "未处理");
            }
            map.put("note", retRecord.getNote());

            Integer operationTime = retRecord.getOperationTime();
            if (operationTime != null && operationTime != 0) {
                map.put("operationTime", sdf.format(IntegerUtil.timeToLong(operationTime)));
            } else {
                map.put("operationTime", "");
            }
            maps.add(map);
        }
        // TODO: 2020/11/3 分页
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("list",maps);
        resultMap.put("page",page);
        result.setPage(page);

        result.setData(maps);
        result.setSuccess("退货操作记录查询");
        return result;
    }

    @ApiOperation("退货操作记录-导出Excel")
    @GetMapping("/recRecords/toExcel")
    public Result retRecordsToExcel(
            @ApiParam(required = true) @RequestParam String token,
            @ApiParam @RequestParam(required = false) String orderSn,
            @ApiParam @RequestParam(required = false) String time,
            HttpServletResponse response
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<TyBusinessReturnRecords> retRecords = null;
        if (time != null || orderSn != null) {
            retRecords = brrService.getByParametersAndTime(storeId, orderSn, null, null, time);
        } else {

            retRecords = brrService.getByStoreId(storeId);
        }


        try {
            // 导出excel
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("退货操作订单.xls", "UTF-8"));
            response.setContentType("application/x-excel;charset=UTF-8");
            OutputStream outputStream = response.getOutputStream();
            int flag = brrService.exportToExcel(retRecords, outputStream);
            if (flag > 0) {
                result.setSuccess("未完成列表导出");
            } else {
                result.setFail("未完成列表导出");
            }
        } catch (IOException e) {
            result.setFail("未完成列表导出");
        }

        return null;
    }

}
