package com.tc.yuxiu.service.impl;

import com.tc.yuxiu.dao.TyActivityMapper;
import com.tc.yuxiu.model.TyActivity;
import com.tc.yuxiu.service.TyActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author DELL
 * @date 2020/9/21 15:47
 */
@Service
public class TyActivityServiceImpl implements TyActivityService {

    @Autowired(required = false)
    private TyActivityMapper mapper;

    @Override
    public List<TyActivity> getByStoreId(Integer storeId) {
        Example example =new Example(TyActivity.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId",storeId);
        List<TyActivity> list = mapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list;
        }
        return null;
    }
}
