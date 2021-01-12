package com.tc.yuxiu.service.impl;

import com.tc.yuxiu.dao.TyOrderStatisMapper;
import com.tc.yuxiu.model.TyOrderStatis;
import com.tc.yuxiu.service.TyOrderStatisService;
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

@Service
public class TyOrderStatisServiceImpl implements TyOrderStatisService {

    @Autowired(required = false)
    private TyOrderStatisMapper mapper;

    @Override
    public List<TyOrderStatis> getByStoreId(Integer storeId) {
        Example example = new Example(TyOrderStatis.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId", storeId);
        List<TyOrderStatis> list = mapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }

    @Override
    public List<TyOrderStatis> getByStoreIdAndTime(Integer storeId, String time) {
        Example example = new Example(TyOrderStatis.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId", storeId);
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
                System.out.println("时间解析失败");
            }
        }
        if (start != null && end != null) {
            criteria.andBetween("createDate", start, end);
        }
        List<TyOrderStatis> list = mapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }

    @Override
    public int exportToExcel(List<TyOrderStatis> orderStatises, OutputStream outputStream) {
        // 1.创建工作簿
        HSSFWorkbook hwb = new HSSFWorkbook();
        // 2.创建工作表
        HSSFSheet sheet = hwb.createSheet("商家结算记录");
        HSSFRow row2 = sheet.createRow(0);
        // 3.2创建第二行及单元格
        String[] row2Cell = {"编号", "开始时间", "结束时间", "订单商品金额", "运费",
                "平台抽成", "本期应结", "创建记录日期", "优惠价", "优惠券抵扣", "分销金额"};
        for (int i = 0; i < row2Cell.length; i++) {
            row2.createCell(i).setCellValue(row2Cell[i]);
        }
        if (orderStatises != null) {
            for (int i = 0; i < orderStatises.size(); i++) {
                HSSFRow row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(orderStatises.get(i).getId().toString());

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Integer startDate = orderStatises.get(i).getStartDate();
                if (startDate != null && startDate != 0) {
                    row.createCell(1).setCellValue(sdf.format(IntegerUtil.timeToLong(startDate)));
                } else {
                    row.createCell(1).setCellValue("");
                }
                Integer endDate = orderStatises.get(i).getEndDate();
                if (startDate != null && startDate != 0) {
                    row.createCell(2).setCellValue(sdf.format(IntegerUtil.timeToLong(endDate)));
                } else {
                    row.createCell(2).setCellValue("");
                }

                row.createCell(3).setCellValue(orderStatises.get(i).getOrderTotals().toString());
                row.createCell(4).setCellValue(orderStatises.get(i).getShippingTotals().toString());
                row.createCell(5).setCellValue(orderStatises.get(i).getCommisTotals().toString());
                row.createCell(6).setCellValue(orderStatises.get(i).getResultTotals().toString());
                Integer createDate = orderStatises.get(i).getCreateDate();
                if (createDate != null && createDate != 0) {

                    row.createCell(7).setCellValue(sdf.format(IntegerUtil.timeToLong(createDate)));
                } else {
                    row.createCell(7).setCellValue("");
                }

                row.createCell(8).setCellValue(orderStatises.get(i).getOrderPromAmount().toString());
                row.createCell(9).setCellValue(orderStatises.get(i).getCouponPrice().toString());
                row.createCell(10).setCellValue(orderStatises.get(i).getDistribut().toString());

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
}
