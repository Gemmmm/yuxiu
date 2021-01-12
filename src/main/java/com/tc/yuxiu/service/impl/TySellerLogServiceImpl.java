package com.tc.yuxiu.service.impl;

import com.tc.yuxiu.dao.TySellerLogMapper;
import com.tc.yuxiu.model.TySellerLog;
import com.tc.yuxiu.service.TySellerLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author DELL
 * @date 2020/9/16 13:43
 */
@Service
public class TySellerLogServiceImpl implements TySellerLogService {
    @Autowired(required = false)
    private TySellerLogMapper mapper;
    @Override
    public Integer insert(TySellerLog sellerLog) {
        return mapper.insertSelective(sellerLog);
    }
}
