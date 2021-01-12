package com.tc.yuxiu.service.impl;

import com.tc.yuxiu.dao.TyGoodsMapper;
import com.tc.yuxiu.model.TyGoods;
import com.tc.yuxiu.model.TyGoodsCategory;
import com.tc.yuxiu.service.TyGoodCategoryService;
import com.tc.yuxiu.service.TyGoodsService;
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
import java.util.HashMap;
import java.util.List;

/**
 * @author DELL
 * @date 2020/9/16 18:07
 */
@Service
public class TyGoodsServiceImpl implements TyGoodsService {
    @Autowired(required = false)
    private TyGoodsMapper mapper;
    @Autowired(required = false)
    private TyGoodCategoryService gcService;

    @Override
    public Integer insert(TyGoods goods) {
        return mapper.insertSelective(goods);
    }

    @Override
    public List<TyGoods> getByStoreIdAndIsOnSale(Integer storeId, Short isOnSale) {
        Example example = new Example(TyGoods.class);
        example.orderBy("addTime").desc();
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId", storeId);
        criteria.andEqualTo("isOnSale", isOnSale);
        criteria.andEqualTo("isDel", 0);
        List<TyGoods> list = mapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }

    @Override
    public List<TyGoods> getByParametersAndTime(Integer storeId, Integer catId3, String type, Short existType, String keywords, String time, Short isOnSale) {
        Example example = new Example(TyGoods.class);
        example.orderBy("addTime").desc();

        Example.Criteria criteria = example.createCriteria();
        if (storeId != null) {
            criteria.andEqualTo("storeId", storeId);
        }
        if (catId3 != null) {
            criteria.andEqualTo("catId3", catId3);
        }
        if ("1".equals(type)) {
            criteria.andEqualTo("isNew", 1);
        } else if ("2".equals(type)) {
            criteria.andEqualTo("isRecommend", 1);
        }
        if (existType != null) {
            criteria.andEqualTo("existType", existType);
        }
        if (keywords != null) {
            criteria.andLike("keywords", "%" + keywords + "%");
        }
        String[] timeList=null;
        if(time!=null){
           timeList = time.split(",");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Integer start=null;
        Integer end=null;
        Date startTime = null;
        Date endTime = null;
        try {
            if(timeList!=null&&timeList.length>0){
                startTime = sdf.parse(timeList[0].trim());
                start=IntegerUtil.timeToInteger(startTime.getTime());
                endTime = sdf.parse(timeList[1].trim());
                end=IntegerUtil.timeToInteger(endTime.getTime());
            }
        } catch (ParseException e) {

        }
        if (start != null && end != null) {

            criteria.andBetween("onTime", start, end);
        }
        if (isOnSale != null) {
            criteria.andEqualTo("isOnSale", isOnSale);
        }
        criteria.andEqualTo("isDel", 0);
        List<TyGoods> list = mapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }

    @Override
    public TyGoods getById(Integer goodsId) {
        return mapper.selectByPrimaryKey(goodsId);
    }

    @Override
    public List<TyGoods> getByStoreId(Integer storeId) {
        Example example = new Example(TyGoods.class);
        example.orderBy("addTime").desc();
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId", storeId);
        criteria.andEqualTo("isDel", 0);
        List<TyGoods> list = mapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }

    @Override
    public List<TyGoods> getByStoreIdAndCatIdAndGoodsStateAndGoodsName(Integer storeId, Integer catId, Short goodsState, String goodsName) {
        Example example = new Example(TyGoods.class);
        example.orderBy("addTime").desc();
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId", storeId);
        criteria.andEqualTo("catId1", catId);
        criteria.andEqualTo("goodsState", goodsState);
        criteria.andLike("goodsName", goodsName);
        criteria.andEqualTo("isDel", 1);
        List<TyGoods> list = mapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }

    @Override
    public Integer modify(TyGoods goods) {
        return mapper.updateByPrimaryKeySelective(goods);
    }

    @Override
    public Integer deleteById(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<TyGoods> getByStoreIdAndIsDel(Integer storeId, Short isDel) {
        Example example = new Example(TyGoods.class);
        example.orderBy("addTime").desc();
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId", storeId);
        criteria.andEqualTo("isDel", isDel);
        List<TyGoods> list = mapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }

    @Override
    public TyGoods getByExample(Example example) {

        List<TyGoods> list = mapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public Integer exportToExcel(List<TyGoods> goods, OutputStream outputStream) {
        // 1.创建工作簿

        // 1.创建工作簿
        HSSFWorkbook hwb = new HSSFWorkbook();
        // 2.创建工作表
        HSSFSheet sheet = hwb.createSheet("商品表");
        HSSFRow row2 = sheet.createRow(0);
        // 3.2创建第二行及单元格
        String[] row2Cell = {"商品编号", "商品名称", "货号", "分类", "对象",
                "价格", "店铺推荐", "店铺推荐排序", "新品", "新品排序", "热卖", "热卖排序", "库存", "上架时间", "审核状态", "上/下架"};
        for (int i = 0; i < row2Cell.length; i++) {
            row2.createCell(i).setCellValue(row2Cell[i]);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (goods != null) {
            for (int i = 0; i < goods.size(); i++) {
                HSSFRow row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(goods.get(i).getGoodsId().toString());
                row.createCell(1).setCellValue(goods.get(i).getGoodsName());
                row.createCell(2).setCellValue(goods.get(i).getGoodsSn());
                //分类
                Integer catId1 = goods.get(i).getCatId1();
                TyGoodsCategory goodsCategory = gcService.getById(catId1);
                if (goodsCategory != null) {
                    row.createCell(3).setCellValue(goodsCategory.getName());
                } else {
                    row.createCell(3).setCellValue("");
                }
                row.createCell(4).setCellValue(goods.get(i).getExistType() == 1 ? "期货" : "现货");
                row.createCell(5).setCellValue(goods.get(i).getShopPrice().toString());
                row.createCell(6).setCellValue(goods.get(i).getStoreIsRecommend() == 1 ? "是" : "否");
                row.createCell(7).setCellValue(goods.get(i).getStoreRecommendSort().toString());
                row.createCell(8).setCellValue(goods.get(i).getIsNew() == 1 ? "是" : "否");
                row.createCell(9).setCellValue(goods.get(i).getStoreNewSort().toString());
                row.createCell(10).setCellValue(goods.get(i).getIsHot() == 1 ? "是" : "否");
                row.createCell(11).setCellValue(goods.get(i).getStoreHotSort().toString());
                row.createCell(12).setCellValue(goods.get(i).getStoreCount().toString());
                //上架时间
                Integer onTime = goods.get(i).getOnTime();
                if (onTime != null && onTime != 0) {
                    row.createCell(13).setCellValue(sdf.format(IntegerUtil.timeToLong(onTime)));
                } else {
                    row.createCell(13).setCellValue("");
                }


                Short goodsState = goods.get(i).getGoodsState();
                if (goodsState == 1) {
                    row.createCell(14).setCellValue("审核通过");
                } else if (goodsState == 2) {
                    row.createCell(14).setCellValue("审核失败");
                } else if (goodsState == 3) {
                    row.createCell(14).setCellValue("违规下架");
                } else {
                    row.createCell(14).setCellValue("待审核");
                }
                Short isOnSale = goods.get(i).getIsOnSale();
                if(isOnSale==0){
                    row.createCell(15).setCellValue("下架");
                }else{
                    row.createCell(15).setCellValue("上架");
                }
            }
        }
        // 5.输出
        try {
            hwb.write(outputStream);
        } catch (IOException e) {
            System.out.println("商品导出 -1");
            return -1;
        }
        return 1;

    }

}
