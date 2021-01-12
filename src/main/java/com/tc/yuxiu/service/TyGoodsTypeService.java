package com.tc.yuxiu.service;

import com.tc.yuxiu.model.TyGoodsType;

import java.util.List;

/**
 * @author DELL
 * @date 2020/9/16 16:05
 */
public interface TyGoodsTypeService {
    List<TyGoodsType> getByCatIds(Short catId1, Short catId2, Short catId3);
}
