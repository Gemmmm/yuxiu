package com.tc.yuxiu.service.impl;

import com.tc.yuxiu.dao.TyGoodsTypeItemMapper;
import com.tc.yuxiu.model.TyGoodsTypeItem;
import com.tc.yuxiu.service.TyGoodsTypeItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author DELL
 * @date 2020/9/17 13:40
 */
@Service
public class TyGoodsTypeItemServiceImpl implements TyGoodsTypeItemService {
    @Autowired(required = false)
    private TyGoodsTypeItemMapper mapper;

    @Override
    public List<TyGoodsTypeItem> getByTypeId(Short typeId) {
        Example example=new Example(TyGoodsTypeItem.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("typeId",typeId);
        List<TyGoodsTypeItem> list = mapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list;
        }
        return null;
    }
}
