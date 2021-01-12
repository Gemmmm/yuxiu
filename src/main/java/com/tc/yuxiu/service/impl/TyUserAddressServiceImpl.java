package com.tc.yuxiu.service.impl;

import com.tc.yuxiu.dao.TyUserAddressMapper;
import com.tc.yuxiu.model.TyUserAddress;
import com.tc.yuxiu.model.TyUsers;
import com.tc.yuxiu.service.TyUserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class TyUserAddressServiceImpl implements TyUserAddressService {

    @Autowired(required = false)
    private TyUserAddressMapper mapper;

    @Override
    public TyUserAddress getByUserId(Integer userId) {
        Example example=new Example(TyUserAddress.class);
        Example.Criteria criteria = example.createCriteria();
        if(userId!=null){
            criteria.andEqualTo("userId",userId);
        }
        List<TyUserAddress> list = mapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }
}
