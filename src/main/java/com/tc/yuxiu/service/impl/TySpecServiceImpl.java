package com.tc.yuxiu.service.impl;

import com.tc.yuxiu.dao.TySpecMapper;
import com.tc.yuxiu.model.TySpec;
import com.tc.yuxiu.model.TySpecItem;
import com.tc.yuxiu.service.TySpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author DELL
 * @date 2020/9/23 11:15
 */
@Service
public class TySpecServiceImpl implements TySpecService {

    @Autowired(required = false)
    private TySpecMapper mapper;

    @Override
    public List<TySpec> getByStoreId(Integer storeId) {
        Example example=new Example(TySpec.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId",storeId);
        List<TySpec> list = mapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list;
        }
        return null;
    }

    @Override
    public TySpec getById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }
}
