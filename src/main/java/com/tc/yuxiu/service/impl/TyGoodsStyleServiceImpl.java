package com.tc.yuxiu.service.impl;

import com.tc.yuxiu.dao.TyGoodsStyleMapper;
import com.tc.yuxiu.model.TyGoodsStyle;
import com.tc.yuxiu.service.TyGoodsStyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DELL
 * @date 2020/9/16 15:46
 */
@Service
public class TyGoodsStyleServiceImpl implements TyGoodsStyleService {
    @Autowired(required = false)
    private TyGoodsStyleMapper mapper;

    @Override
    public List<TyGoodsStyle> getAll() {
        return mapper.selectAll();
    }
}
