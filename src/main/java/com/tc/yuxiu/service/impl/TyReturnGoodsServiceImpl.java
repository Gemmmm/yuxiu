package com.tc.yuxiu.service.impl;

import com.tc.yuxiu.dao.TyReturnGoodsMapper;
import com.tc.yuxiu.model.TyReturnGoods;
import com.tc.yuxiu.service.TyReturnGoodsService;
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
public class TyReturnGoodsServiceImpl implements TyReturnGoodsService {

    @Autowired(required = false)
    private TyReturnGoodsMapper mapper;

    @Override
    public List<TyReturnGoods> getByStoreId(Integer storeId) {
        Example example = new Example(TyReturnGoods.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId", storeId);
        example.orderBy("addtime").desc();
        List<TyReturnGoods> list = mapper.selectByExample(example);
        if (list != null) {
            return list;
        }
        return null;
    }

    @Override
    public List<TyReturnGoods> getByParametersAndTime(Integer storeId, String orderSn, Short type, Short status, String time) {
        Example example = new Example(TyReturnGoods.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId", storeId);
        if (orderSn != null) {
            criteria.andEqualTo("orderSn", orderSn);
        }
        if (type != null) {
            criteria.andEqualTo("type", type);
        }
        if (status != null) {
            criteria.andEqualTo("status", status);
        }
        String[] times = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startTime = null;
        Date endTime = null;
        Integer start = null;
        Integer end = null;
        if (time != null) {
            times = time.split(",");
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
            criteria.andBetween("addtime", start, end);
        }

        example.orderBy("addtime").desc();
        List<TyReturnGoods> list = mapper.selectByExample(example);
        if (list != null) {
            return list;
        }

        return null;
    }

    @Override
    public TyReturnGoods getById(String id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteById(String id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int modify(TyReturnGoods returnGoods) {
        return mapper.updateByPrimaryKeySelective(returnGoods);
    }

    @Override
    public Integer exportToExcel(List<TyReturnGoods> returnGoods, OutputStream outputStream) {

        // 1.创建工作簿
        HSSFWorkbook hwb = new HSSFWorkbook();
        // 2.创建工作表
        HSSFSheet sheet = hwb.createSheet("退货单表");
        HSSFRow row2 = sheet.createRow(0);
        // 3.2创建第二行及单元格
        String[] row2Cell = {"编号", "订单编号", "退换金额", "退换类型", "退换状态",
                "申请日期"};
        for (int i = 0; i < row2Cell.length; i++) {
            row2.createCell(i).setCellValue(row2Cell[i]);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (returnGoods != null) {
            for (int i = 0; i < returnGoods.size(); i++) {
                HSSFRow row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(returnGoods.get(i).getId().toString());
                row.createCell(1).setCellValue(returnGoods.get(i).getOrderSn());
                row.createCell(2).setCellValue(returnGoods.get(i).getReturnMoney().toString());
                Short status = returnGoods.get(i).getStatus();
                if (status == 0) {
                    row.createCell(3).setCellValue("未处理");
                } else if (status == 1) {
                    row.createCell(3).setCellValue("处理中");
                } else if (status == 2) {
                    row.createCell(3).setCellValue("审核通过");
                } else if (status == 3) {
                    row.createCell(3).setCellValue("拒绝审核");
                } else if (status == 4) {
                    row.createCell(3).setCellValue("已发快递");
                } else if (status == 5) {
                    row.createCell(3).setCellValue("已收快递");
                } else if (status == 6) {
                    row.createCell(3).setCellValue("平台介入");
                } else if (status == 8) {
                    row.createCell(3).setCellValue("退款完成");
                } else if (status == 9) {
                    row.createCell(3).setCellValue("撤销审核");
                }

               Short type = returnGoods.get(i).getType();
                if (type == 1) {
                    row.createCell(4).setCellValue("退款");
                } else if (type == 2) {
                    row.createCell(4).setCellValue("退款退货");
                } else if (type == 3) {
                    row.createCell(4).setCellValue("换货");
                }
                //map.put("note", returnGood.getNote());
                Integer addtime = returnGoods.get(i).getAddtime();
                if (addtime != null && addtime != 0) {
                    row.createCell(5).setCellValue(sdf.format(IntegerUtil.timeToLong(addtime)));
                } else {
                    row.createCell(5).setCellValue("");
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
}
