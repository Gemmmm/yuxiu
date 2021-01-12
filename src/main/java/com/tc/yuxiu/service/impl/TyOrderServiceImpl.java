package com.tc.yuxiu.service.impl;

import com.tc.yuxiu.dao.TyOrderMapper;
import com.tc.yuxiu.model.TyOrder;
import com.tc.yuxiu.service.TyOrderService;
import com.tc.yuxiu.util.IntegerUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author DELL
 * @date 2020/9/18 9:35
 */
@Service
public class TyOrderServiceImpl implements TyOrderService {

    @Autowired(required = false)
    private TyOrderMapper mapper;

    @Override
    public List<TyOrder> getByStoreId(Integer storeId) {
        Example example = new Example(TyOrder.class);
        example.orderBy("addTime").desc();
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId", storeId);
        List<TyOrder> list = mapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }

    @Override
    public TyOrder getByOrderId(Integer orderId) {
        return mapper.selectByPrimaryKey(orderId);
    }

    @Override
    public List<TyOrder> getByParametersAndTime(
            Integer storeId, String consignee, String orderSn, String time, Integer payStatus,
            String payName, Integer shippingStatus, Integer orderStatus, Integer type) {
        Example example = new Example(TyOrder.class);
        Example.Criteria criteria = example.createCriteria();
        System.out.println("storeId:" + storeId);
        criteria.andEqualTo("storeId", storeId);
        if (consignee != null) {
            criteria.andEqualTo("consignee", consignee);
        }
        if (orderSn != null) {
            criteria.andEqualTo("orderSn", orderSn);
        }
        if (payStatus != null) {
            criteria.andEqualTo("payStatus", payStatus);
        }
        if (payName != null) {
            criteria.andEqualTo("payName", payName);
        }
        if (shippingStatus != null) {
            criteria.andEqualTo("shippingStatus", shippingStatus);
        }
        if (orderStatus != null) {
            if(orderStatus==-1){
                criteria.andNotEqualTo("orderStatus",3);
            }else{
                criteria.andEqualTo("orderStatus", orderStatus);
            }
        }
        if (type != null) {
            criteria.andEqualTo("type", type);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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
        if (start != null && end != null) {
            criteria.andBetween("addTime", start, end);
        }
        example.orderBy("addTime").desc();
        List<TyOrder> list = mapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }

    @Override
    public Integer deleteById(Integer orderId) {
        return mapper.deleteByPrimaryKey(orderId);
    }

    @Override
    public Integer exportToExcel(List<TyOrder> orderList, OutputStream outputStream) {

        // 1.创建工作簿
        HSSFWorkbook hwb = new HSSFWorkbook();
        // 2.创建工作表
        HSSFSheet sheet = hwb.createSheet("订单表");
        HSSFRow row2 = sheet.createRow(0);
        // 3.2创建第二行及单元格
        String[] row2Cell = {"#", "编号", "订单编号", "收货人", "订单金额", "支付金额",
                "订单类型", "订单状态", "支付状态", "发货状态", "支付方式", "配送方式", "下单时间", "支付时间"};
        for (int i = 0; i < row2Cell.length; i++) {
            row2.createCell(i).setCellValue(row2Cell[i]);
        }
        // 3.3创建第三行及单元格
        if (orderList != null) {
            for (int i = 0; i < orderList.size(); i++) {
                HSSFRow row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(i + 1);
                row.createCell(1).setCellValue(orderList.get(i).getOrderId().toString());
                row.createCell(2).setCellValue(orderList.get(i).getOrderSn());
                row.createCell(3).setCellValue(orderList.get(i).getConsignee());
                row.createCell(4).setCellValue(orderList.get(i).getOrderAmount().toString());
                row.createCell(5).setCellValue(orderList.get(i).getPayPrice().toString());
                Integer type = orderList.get(i).getType();
                if (type == 0) {
                    row.createCell(6).setCellValue("商品订单");
                } else if (type == 1) {
                    row.createCell(6).setCellValue("订货订单");
                } else if (type == 2) {
                    row.createCell(6).setCellValue("拼团订单");
                } else if (type == 3) {
                    row.createCell(6).setCellValue("推广员订单");
                }

                Integer orderStatus = orderList.get(i).getOrderStatus();
                if (orderStatus == 0) {
                    row.createCell(7).setCellValue("待支付");
                } else if (orderStatus == 1) {
                    row.createCell(7).setCellValue("待发货(待收货)");
                } else if (orderStatus == 2) {
                    row.createCell(7).setCellValue("待评价");
                } else if (orderStatus == 3) {
                    row.createCell(7).setCellValue("已取消");
                } else if (orderStatus == 4) {
                    row.createCell(7).setCellValue("已完成");
                } else if (orderStatus == 5) {
                    row.createCell(7).setCellValue("退货（换货）中");
                }
                Integer payStatus = orderList.get(i).getPayStatus();
                if (payStatus == 1) {
                    row.createCell(8).setCellValue("已支付");
                } else {
                    row.createCell(8).setCellValue("未支付");
                }
                Integer shippingStatus = orderList.get(i).getShippingStatus();
                if (shippingStatus == 1) {
                    row.createCell(9).setCellValue("已发货");
                } else {
                    row.createCell(9).setCellValue("未发货");
                }
                row.createCell(10).setCellValue(orderList.get(i).getPayName());
                row.createCell(11).setCellValue(orderList.get(i).getShippingName());
                Integer addTime = orderList.get(i).getAddTime();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                if (addTime != null && addTime != 0) {
                    row.createCell(12).setCellValue(sdf.format(IntegerUtil.timeToLong(addTime)));
                } else {
                    row.createCell(12).setCellValue("");
                }
                //支付时间
                Integer payTime = orderList.get(i).getPayTime();
                if (payTime != null && payTime != 0) {
                    row.createCell(13).setCellValue(sdf.format(IntegerUtil.timeToLong(payTime)));
                } else {
                    row.createCell(13).setCellValue("");
                }

            }
        }
        // 5.输出
        try {
            hwb.write(outputStream);
            return 1;
        } catch (IOException e) {
            return -1;
        }
    }

    @Override
    public TyOrder getByOrderSn(String orderSn) {
        Example example = new Example(TyOrder.class);
        example.orderBy("addTime").desc();
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderSn", orderSn);
        List<TyOrder> list = mapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public Integer modifyByOrderId(TyOrder order) {
        return mapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public List<TyOrder> getByStoreIdAndTime(Integer storeId, Integer start, Integer end) {
        Example example = new Example(TyOrder.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId", storeId);
        if (start != null && end != null) {
            criteria.andBetween("addTime", start, end);
        }
        List<TyOrder> list = mapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }

    @Override
    public List<TyOrder> getByStoreIdAndOrderSnAndTimeNoOrderStatus(Integer storeId, String orderSn, String time, Integer orderStatus) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Example example = new Example(TyOrder.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId", storeId);
        if (orderSn != null) {
            criteria.andEqualTo("orderSn", orderSn);
        }
        if (orderStatus != null) {
            criteria.andNotEqualTo("orderStatus", orderStatus);
        }
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
        if (start != null && end != null) {
            criteria.andBetween("addTime", start, end);
        }
        List<TyOrder> list = mapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }

}
