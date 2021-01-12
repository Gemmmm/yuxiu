package com.tc.yuxiu.service.impl;

import com.tc.yuxiu.dao.TyUserLevelMapper;
import com.tc.yuxiu.model.TyUserLevel;
import com.tc.yuxiu.service.TyUserLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Id;

/**
 * @author DELL
 * @date 2020/9/21 14:00
 */
@Service
public class TyUserLevelServiceImpl implements TyUserLevelService {
    @Autowired(required = false)
    private TyUserLevelMapper mapper;

    @Override
    public TyUserLevel getByLevelId(Short levelId) {
        return mapper.selectByPrimaryKey(levelId);
    }
}
