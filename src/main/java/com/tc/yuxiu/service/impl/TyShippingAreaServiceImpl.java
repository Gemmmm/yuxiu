package com.tc.yuxiu.service.impl;

import com.tc.yuxiu.dao.TyShippingAreaMapper;
import com.tc.yuxiu.model.TyShippingArea;
import com.tc.yuxiu.service.TyShippingAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author DELL
 * @date 2020/9/18 16:24
 */
@Service
public class TyShippingAreaServiceImpl implements TyShippingAreaService {

    @Autowired(required = false)
    private TyShippingAreaMapper mapper;

    @Override
    public List<TyShippingArea> getByStoreId(Integer storeId) {
        Example example=new Example(TyShippingArea.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId",storeId);
        List<TyShippingArea> list = mapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return  list;
        }
        return null;
    }

    @Override
    public Integer deleteById(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public TyShippingArea getById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }
}
