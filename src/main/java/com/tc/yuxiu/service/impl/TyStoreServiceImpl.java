package com.tc.yuxiu.service.impl;

import com.tc.yuxiu.dao.TyStoreMapper;
import com.tc.yuxiu.model.TyStore;
import com.tc.yuxiu.service.TyStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author DELL
 * @date 2020/9/16 15:18
 */
@Service
public class TyStoreServiceImpl implements TyStoreService {
    @Autowired(required = false)
    private TyStoreMapper mapper;
    @Override
    public TyStore getById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer modify(TyStore store) {
        return mapper.updateByPrimaryKeySelective(store);
    }
}
