package com.tc.yuxiu.service;

import com.tc.yuxiu.model.TyGoods;
import com.tc.yuxiu.model.TyOrder;
import com.tc.yuxiu.model.TyOrderGoods;

import java.util.List;

/**
 * @author DELL
 * @date 2020-9-29 13:45
 */
public interface TyOrderGoodsService {
    List<TyOrderGoods> getByOrderId(Integer orderId);

    List<TyOrderGoods> getByStoreId(Integer storeId);

    List<TyOrderGoods> getByGoodsId(Integer goodsId);
}
