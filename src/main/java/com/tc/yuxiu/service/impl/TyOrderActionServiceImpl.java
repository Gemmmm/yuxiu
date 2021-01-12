package com.tc.yuxiu.service.impl;

import com.tc.yuxiu.dao.TyOrderActionMapper;
import com.tc.yuxiu.model.TyOrderAction;
import com.tc.yuxiu.service.TyOrderActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author DELL
 * @date 2020-9-29 14:08
 */
@Service
public class TyOrderActionServiceImpl implements TyOrderActionService
{
    @Autowired(required = false)
    private TyOrderActionMapper mapper;

    @Override
    public List<TyOrderAction> getByOrderId(Integer orderId) {
        Example example=new Example(TyOrderAction.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderId",orderId);
        List<TyOrderAction> list = mapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list;
        }
        return null;
    }
}
