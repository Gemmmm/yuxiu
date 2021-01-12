package com.tc.yuxiu.service;

import com.tc.yuxiu.model.TySpecGoodsPrice;

import java.util.List;

/**
 * @author DELL
 * @date 2020/9/23 14:51
 */
public interface TySpecGoodsPriceService {
    int insert(TySpecGoodsPrice goodsPrice);

    List<TySpecGoodsPrice> getByGoodsId(Integer goodsId);

    TySpecGoodsPrice getByStoreIdGoodsIdAndKeyValue(Integer storeId, Integer goodsId, String keyValue);

    Integer modify(TySpecGoodsPrice goodsPrice);

    List<TySpecGoodsPrice> getByStoreIdGoodsId(Integer storeId, Integer goodsId);

    int deleteByStoreIdAndGoodsId(Integer storeId, Integer goodsId);
}
