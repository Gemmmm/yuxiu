package com.tc.yuxiu.service;

import com.tc.yuxiu.model.TyUserLevel;

/**
 * @author DELL
 * @date 2020/9/21 14:00
 */
public interface TyUserLevelService {
    TyUserLevel getByLevelId(Short levelId);
}
