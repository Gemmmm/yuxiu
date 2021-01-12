package com.tc.yuxiu.service.impl;

import com.tc.yuxiu.dao.TySpecGoodsPriceMapper;
import com.tc.yuxiu.model.TySpecGoodsPrice;
import com.tc.yuxiu.service.TySpecGoodsPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author DELL
 * @date 2020/9/23 14:51
 */
@Service
public class TySpecGoodsPriceServiceImpl implements TySpecGoodsPriceService {

    @Autowired(required = false)
    private TySpecGoodsPriceMapper mapper;

    @Override
    public int insert(TySpecGoodsPrice goodsPrice) {
        return mapper.insertSelective(goodsPrice);
    }

    @Override
    public List<TySpecGoodsPrice> getByGoodsId(Integer goodsId) {
        Example example = new Example(TySpecGoodsPrice.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("goodsId", goodsId);
        List<TySpecGoodsPrice> list = mapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }

    @Override
    public TySpecGoodsPrice getByStoreIdGoodsIdAndKeyValue(Integer storeId, Integer goodsId, String keyValue) {
        Example example = new Example(TySpecGoodsPrice.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId", storeId);
        criteria.andEqualTo("goodsId", goodsId);
        criteria.andEqualTo("keyValue", keyValue);
        List<TySpecGoodsPrice> list = mapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }

        return null;
    }

    @Override
    public Integer modify(TySpecGoodsPrice goodsPrice) {
        return mapper.updateByPrimaryKeySelective(goodsPrice);
    }

    @Override
    public List<TySpecGoodsPrice> getByStoreIdGoodsId(Integer storeId, Integer goodsId) {

        Example example = new Example(TySpecGoodsPrice.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId", storeId);
        criteria.andEqualTo("goodsId", goodsId);
        List<TySpecGoodsPrice> list = mapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list;
        }

        return null;
    }

    @Override
    public int deleteByStoreIdAndGoodsId(Integer storeId, Integer goodsId) {
        Example example = new Example(TySpecGoodsPrice.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId", storeId);
        criteria.andEqualTo("goodsId", goodsId);
        return mapper.deleteByExample(example);
    }
}
