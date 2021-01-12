package com.tc.yuxiu.service.impl;

import com.tc.yuxiu.dao.TyStoreGoodsClassMapper;
import com.tc.yuxiu.model.TyStoreGoodsClass;
import com.tc.yuxiu.service.TyStoreGoodsClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author DELL
 * @date 2020/9/21 16:11
 */
@Service
public class TyStoreGoodsClassServiceImpl implements TyStoreGoodsClassService {
    @Autowired(required = false)
    private TyStoreGoodsClassMapper mapper;

    @Override
    public List<TyStoreGoodsClass> getByStoreId(Integer storeId) {
        Example example=new Example(TyStoreGoodsClass.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId",storeId);
        List<TyStoreGoodsClass> list = mapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list;
        }
        return null;
    }

    @Override
    public Integer deleteById(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public TyStoreGoodsClass getById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer insert(TyStoreGoodsClass goodsClass) {
        return mapper.insertSelective(goodsClass);
    }
}
