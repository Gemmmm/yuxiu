package com.tc.yuxiu.service.impl;

import com.tc.yuxiu.dao.TyGoodsTypeContactMapper;
import com.tc.yuxiu.model.TyGoodsType;
import com.tc.yuxiu.model.TyGoodsTypeContact;
import com.tc.yuxiu.service.TyGoodsTypeContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author DELL
 * @date 2020/9/17 15:04
 */
@Service
public class TyGoodTypeContactServiceImpl implements TyGoodsTypeContactService {
    @Autowired(required = false)
    private TyGoodsTypeContactMapper mapper;

    @Override
    public Integer insertList(List<TyGoodsTypeContact> goodsTypeContactList) {
        return mapper.insertList(goodsTypeContactList);
    }

    @Override
    public List<TyGoodsTypeContact> getByGoodsId(Integer goodsId) {
        Example example = new Example(TyGoodsTypeContact.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("goodsId", goodsId);
        List<TyGoodsTypeContact> list = mapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;

    }

    @Override
    public TyGoodsTypeContact getByGoodsIdAndTypeIdAndItemId(Integer goodsId, Integer typeId, Integer itemId) {
        Example example = new Example(TyGoodsTypeContact.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("goodsId", goodsId);
        if (typeId != null) {
            criteria.andEqualTo("typeId", typeId);
        }
        if (itemId != null) {
            criteria.andEqualTo("typeItemId", itemId);
        }
        List<TyGoodsTypeContact> list = mapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }

        return null;
    }

    @Override
    public int modify(TyGoodsTypeContact goodsTypeContact) {
        return mapper.updateByPrimaryKeySelective(goodsTypeContact);
    }

    @Override
    public Integer insert(TyGoodsTypeContact goodsTypeContact) {
        return mapper.insertSelective(goodsTypeContact);
    }
}
