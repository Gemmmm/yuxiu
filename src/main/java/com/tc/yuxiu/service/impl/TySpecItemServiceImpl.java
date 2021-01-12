package com.tc.yuxiu.service.impl;

import com.tc.yuxiu.dao.TySpecItemMapper;
import com.tc.yuxiu.model.TySpecItem;
import com.tc.yuxiu.service.TySpecItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.persistence.Id;
import java.util.List;

/**
 * @author DELL
 * @date 2020/9/23 11:18
 */
@Service
public class TySpecItemServiceImpl implements TySpecItemService {
    @Autowired(required = false)
    private TySpecItemMapper mapper;

    @Override
    public List<TySpecItem> getBySpecId(Integer specId) {
        Example example=new Example(TySpecItem.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("specId",specId);
        List<TySpecItem> list = mapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list;
        }

        return null;
    }

    @Override
    public TySpecItem getById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public TySpecItem getByStoreIdAndSpecIdAndItemName(Integer storeId,Integer specId, String itemName) {
        Example example=new Example(TySpecItem.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId",storeId);
        criteria.andEqualTo("specId",specId);
        criteria.andEqualTo("item",itemName);
        List<TySpecItem> list = mapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public int insert(TySpecItem specItem) {
        return mapper.insertSelective(specItem);
    }
}
