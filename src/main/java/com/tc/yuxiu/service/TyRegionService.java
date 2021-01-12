package com.tc.yuxiu.service;

import com.tc.yuxiu.model.TyRegion;

import java.util.List;

/**
 * @author DELL
 * @date 2020/9/21 14:38
 */
public interface TyRegionService {

    TyRegion getById(Integer id);

    List<TyRegion> getByLevelAndParentId(Short level, Integer parentId);
}
