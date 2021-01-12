package com.tc.yuxiu.service.impl;

import com.tc.yuxiu.dao.TyReturnGoodsDetailMapper;
import com.tc.yuxiu.model.TyReturnGoodsDetail;
import com.tc.yuxiu.service.TyReturnGoodsDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class TyReturnGoodsDetailServiceImpl implements TyReturnGoodsDetailService {

    @Autowired(required = false)
    private TyReturnGoodsDetailMapper mapper;

    @Override
    public TyReturnGoodsDetail getByReturnId(Integer id) {
        Example example=new Example(TyReturnGoodsDetail.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("returnId",id);
        List<TyReturnGoodsDetail> list = mapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }
}
