package com.tc.yuxiu.service.impl;

import com.tc.yuxiu.dao.TyUsersMapper;
import com.tc.yuxiu.model.TyUsers;
import com.tc.yuxiu.service.TyUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author DELL
 * @date 2020/9/21 12:05
 */
@Service
public class TyUsersServiceImpl implements TyUsersService {
    @Autowired(required = false)
    private TyUsersMapper mapper;

    @Override
    public TyUsers getByUserId(Integer userId) {
        return mapper.selectByPrimaryKey(userId);
    }

    @Override
    public TyUsers getByUserName(String userName) {
        Example example=new Example(TyUsers.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userName",userName);
        List<TyUsers> list = mapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }
}
