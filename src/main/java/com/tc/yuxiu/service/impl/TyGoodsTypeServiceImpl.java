package com.tc.yuxiu.service.impl;

import com.tc.yuxiu.dao.TyGoodsTypeMapper;
import com.tc.yuxiu.model.TyGoodsType;
import com.tc.yuxiu.service.TyGoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author DELL
 * @date 2020/9/16 16:05
 */
@Service
public class TyGoodsTypeServiceImpl implements TyGoodsTypeService {
    @Autowired(required = false)
    private TyGoodsTypeMapper mapper;


    @Override
    public List<TyGoodsType> getByCatIds(Short catId1, Short catId2, Short catId3) {
        Example example=new Example(TyGoodsType.class);
        Example.Criteria criteria = example.createCriteria();
        if(catId1!=null){

            criteria.andEqualTo("catId1",catId1);
        }
        if(catId2!=null){

            criteria.andEqualTo("catId2",catId2);
        }
        if(catId3!=null){

            criteria.andEqualTo("catId3",catId3);
        }
        List<TyGoodsType> list = mapper.selectByExample(example);
        if(list.size()>0){
            return list;
        }
        return null;
    }
}
