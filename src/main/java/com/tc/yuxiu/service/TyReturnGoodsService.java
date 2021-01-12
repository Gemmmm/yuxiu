package com.tc.yuxiu.service;

import com.tc.yuxiu.model.TyReturnGoods;

import java.io.OutputStream;
import java.util.List;

public interface TyReturnGoodsService {
    List<TyReturnGoods> getByStoreId(Integer storeId);

    List<TyReturnGoods> getByParametersAndTime(Integer storeId, String orderSn, Short type, Short status, String time);

    TyReturnGoods getById(String id);

    int deleteById(String id);

    int modify(TyReturnGoods returnGoods);

    Integer exportToExcel(List<TyReturnGoods> returnGoods, OutputStream outputStream);
}
