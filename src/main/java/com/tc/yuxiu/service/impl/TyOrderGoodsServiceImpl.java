package com.tc.yuxiu.service.impl;

import com.tc.yuxiu.dao.TyOrderGoodsMapper;
import com.tc.yuxiu.model.TyGoods;
import com.tc.yuxiu.model.TyOrderGoods;
import com.tc.yuxiu.service.TyOrderGoodsService;
import com.tc.yuxiu.util.IntegerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author DELL
 * @date 2020-9-29 13:46
 */
@Service
public class TyOrderGoodsServiceImpl implements TyOrderGoodsService {
    @Autowired(required = false)
    private TyOrderGoodsMapper mapper;

    @Override
    public List<TyOrderGoods> getByOrderId(Integer orderId) {
        Example example=new Example(TyOrderGoods.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderId",orderId);
        List<TyOrderGoods> list = mapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list;
        }
        return null;
    }

    @Override
    public List<TyOrderGoods> getByStoreId(Integer storeId) {
        Example example=new Example(TyOrderGoods.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId",storeId);
        List<TyOrderGoods> list = mapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list;
        }
        return null;
    }

    @Override
    public List<TyOrderGoods> getByGoodsId(Integer goodsId) {
        Example example=new Example(TyOrderGoods.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("goodsId",goodsId);
        List<TyOrderGoods> list = mapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list;
        }
        return null;
    }


}
