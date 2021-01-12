package com.tc.yuxiu.service.impl;

import com.tc.yuxiu.dao.TyConfigMapper;
import com.tc.yuxiu.model.TyConfig;
import com.tc.yuxiu.service.TyConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class TyConfigServiceImpl implements TyConfigService {

    @Autowired(required = false)
    private TyConfigMapper mapper;

}
