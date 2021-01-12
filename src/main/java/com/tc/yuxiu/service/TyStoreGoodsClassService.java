package com.tc.yuxiu.service;

import com.tc.yuxiu.model.TyStoreGoodsClass;

import java.util.List;

/**
 * @author DELL
 * @date 2020/9/21 16:11
 */
public interface TyStoreGoodsClassService {

    List<TyStoreGoodsClass> getByStoreId(Integer storeId);

    Integer deleteById(Integer id);

    TyStoreGoodsClass getById(Integer id);

    Integer insert(TyStoreGoodsClass goodsClass);
}
