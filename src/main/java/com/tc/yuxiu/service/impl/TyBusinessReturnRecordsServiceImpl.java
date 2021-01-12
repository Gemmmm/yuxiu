package com.tc.yuxiu.service.impl;

import com.tc.yuxiu.dao.TyBusinessReturnRecordsMapper;
import com.tc.yuxiu.model.TyBusinessReturnRecords;
import com.tc.yuxiu.service.TyBusinessReturnRecordsService;
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
 * @date 2020/9/21 11:12
 */
@Service
public class TyBusinessReturnRecordsServiceImpl implements TyBusinessReturnRecordsService {
    @Autowired(required = false)
    private TyBusinessReturnRecordsMapper mapper;

    @Override
    public List<TyBusinessReturnRecords> getByStoreId(Integer storeId) {
        Example example = new Example(TyBusinessReturnRecords.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId", storeId);
        List<TyBusinessReturnRecords> list = mapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }

    @Override
    public List<TyBusinessReturnRecords> getByParametersAndTime(Integer storeId, String orderSn, Short type, Short status, String time) {
        Example example = new Example(TyBusinessReturnRecords.class);
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
            criteria.andBetween("operationTime", start, end);
        }
        List<TyBusinessReturnRecords> list = mapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }

    @Override
    public TyBusinessReturnRecords getById(String id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int insert(TyBusinessReturnRecords returnRecords) {
        return mapper.insertSelective(returnRecords);
    }

    @Override
    public int exportToExcel(List<TyBusinessReturnRecords> retRecords, OutputStream outputStream) {
        // 1.创建工作簿

        HSSFWorkbook hwb = new HSSFWorkbook();
        // 2.创建工作表
        HSSFSheet sheet = hwb.createSheet("订单表");
        HSSFRow row2 = sheet.createRow(0);

        // 3.2创建第二行及单元格
        String[] row2Cell = {"编号", "订单SN", "类型", "状态", "处理备注", "操作时间"};
        for (int i = 0; i < row2Cell.length; i++) {
            row2.createCell(i).setCellValue(row2Cell[i]);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (retRecords != null) {
            for (int i = 0; i < retRecords.size(); i++) {
                HSSFRow row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(retRecords.get(i).getId().toString());
                row.createCell(1).setCellValue(retRecords.get(i).getOrderSn());
                Short type = retRecords.get(i).getType();
                if (type == 1) {
                    row.createCell(2).setCellValue("退款");
                } else if (type == 2) {
                    row.createCell(2).setCellValue("退款退货");
                } else if (type == 3) {
                    row.createCell(2).setCellValue("换货");
                }
                Short status = retRecords.get(i).getStatus();
                if (status == 1) {
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
                } else if (status == 7) {
                    row.createCell(3).setCellValue("处理中");
                } else if (status == 8) {
                    row.createCell(3).setCellValue("退款完成");
                } else if (status == 9) {
                    row.createCell(3).setCellValue("撤销审核");
                } else {
                    row.createCell(3).setCellValue("未处理");
                }

                Integer operationTime = retRecords.get(i).getOperationTime();
                if (operationTime != null && operationTime != 0) {

                    row.createCell(4).setCellValue(sdf.format(IntegerUtil.timeToLong(operationTime)));
                } else {
                    row.createCell(4).setCellValue("");
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
