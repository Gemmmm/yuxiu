package com.tc.yuxiu.service;

import com.tc.yuxiu.model.TyGoodsCategory;

import java.util.List;

/**
 * @author DELL
 * @date 2020/9/16 16:42
 */
public interface TyGoodCategoryService {

    List<TyGoodsCategory> getByLevelAndParentId(Short level, Integer parentId);

    TyGoodsCategory getById(Integer id);
}
