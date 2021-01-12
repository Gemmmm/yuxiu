package com.tc.yuxiu.service.impl;

import com.tc.yuxiu.dao.TyPluginMapper;
import com.tc.yuxiu.model.TyPlugin;
import com.tc.yuxiu.service.TyPluginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class TyPluginServiceImpl implements TyPluginService {

    @Autowired(required = false)
    private TyPluginMapper mapper;

    @Override
    public List<TyPlugin> getByType(String type) {
        Example example=new Example(TyPlugin.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("type",type);
        List<TyPlugin> list = mapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list;
        }
        return null;
    }
}
