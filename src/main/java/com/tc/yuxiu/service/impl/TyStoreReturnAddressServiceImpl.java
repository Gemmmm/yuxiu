package com.tc.yuxiu.service.impl;

import com.tc.yuxiu.dao.TyStoreReturnAddressMapper;
import com.tc.yuxiu.model.TyStoreReturnAddress;
import com.tc.yuxiu.service.TyStoreReturnAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author DELL
 * @date 2020/9/21 14:19
 */
@Service
public class TyStoreReturnAddressServiceImpl implements TyStoreReturnAddressService {
    @Autowired(required = false)
    private TyStoreReturnAddressMapper mapper;

    @Override
    public List<TyStoreReturnAddress> getByStoreId(Integer storeId) {
        Example example=new Example(TyStoreReturnAddress.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId",storeId);
        List<TyStoreReturnAddress> list = mapper.selectByExample(example);
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
    public TyStoreReturnAddress getById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer modifyById(TyStoreReturnAddress returnAddress) {
        return mapper.updateByPrimaryKey(returnAddress);
    }

    @Override
    public Integer insert(TyStoreReturnAddress returnAddress) {
        return mapper.insertSelective(returnAddress);
    }
}
