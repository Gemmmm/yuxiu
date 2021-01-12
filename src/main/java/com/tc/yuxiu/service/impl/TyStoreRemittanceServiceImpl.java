package com.tc.yuxiu.service.impl;

import com.tc.yuxiu.dao.TyStoreRemittanceMapper;
import com.tc.yuxiu.model.TyStoreRemittance;
import com.tc.yuxiu.service.TyStoreRemittanceService;
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
public class TyStoreRemittanceServiceImpl implements TyStoreRemittanceService {

    @Autowired(required = false)
    private TyStoreRemittanceMapper mapper;


    @Override
    public List<TyStoreRemittance> getByStoreId(Integer storeId) {
        Example example = new Example(TyStoreRemittance.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId", storeId);
        List<TyStoreRemittance> list = mapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }

    @Override
    public List<TyStoreRemittance> getByParametersAndTime(Integer storeId, String accountBank, String accountName, String time) {
        Example example = new Example(TyStoreRemittance.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId", storeId);
        if (accountBank != null) {
            criteria.andEqualTo("accountBank", accountBank);
        }
        if (accountName != null) {
            criteria.andEqualTo("accountName", accountName);
        }
        if (time != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Integer start = null;
            Integer end = null;
            String[] times = time.split(",");
            if (times != null && times.length > 1) {
                try {
                    Date startDate = sdf.parse(times[0].trim());
                    start = IntegerUtil.timeToInteger(startDate.getTime());
                    Date endDate = sdf.parse(times[1].trim());
                    end = IntegerUtil.timeToInteger(endDate.getTime());

                } catch (ParseException e) {
                    System.out.println("时间解析错误");
                }
            }
            if (start != null && end != null) {
                criteria.andBetween("createTime", start, end);
            }
        }
        List<TyStoreRemittance> list = mapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list;
        }

        return null;
    }

    @Override
    public int exportToExcel(List<TyStoreRemittance> remittances, OutputStream outputStream) {


        // 1.创建工作簿
        HSSFWorkbook hwb = new HSSFWorkbook();
        // 2.创建工作表
        HSSFSheet sheet = hwb.createSheet("汇款记录表");
        HSSFRow row2 = sheet.createRow(0);
        // 3.2创建第二行及单元格
        String[] row2Cell = {"#", "编号", "申请时间", "申请金额", "银行名称", "银行账号", "银行账户", "状态", "备注"};
        for (int i = 0; i < row2Cell.length; i++) {
            row2.createCell(i).setCellValue(row2Cell[i]);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (remittances != null && remittances.size() > 0) {
            for (int i = 0; i < remittances.size(); i++) {
                HSSFRow row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(i + 1);
                row.createCell(1).setCellValue(remittances.get(i).getId().toString());
                Integer createTime = remittances.get(i).getCreateTime();
                if (createTime != null && createTime != 0) {
                    row.createCell(2).setCellValue(sdf.format(IntegerUtil.timeToLong(createTime)));
                } else {
                    row.createCell(2).setCellValue("");
                }
                row.createCell(3).setCellValue(remittances.get(i).getMoney().toString());
                row.createCell(4).setCellValue(remittances.get(i).getBankName());
                row.createCell(5).setCellValue(remittances.get(i).getAccountBank());
                row.createCell(6).setCellValue(remittances.get(i).getAccountName());
                Short status = remittances.get(i).getStatus();
                if (status == 0) {
                    row.createCell(7).setCellValue("汇款失败");
                } else if (status == 1) {
                    row.createCell(7).setCellValue("汇款成功");
                }
                row.createCell(8).setCellValue(remittances.get(i).getRemark());

            }
        }

        try {
            hwb.write(outputStream);
            return 1;
        } catch (IOException e) {
            return -1;
        }

    }
}
