package com.tc.yuxiu.service;

import com.tc.yuxiu.model.TyShippingArea;

import java.util.List;

/**
 * @author DELL
 * @date 2020/9/18 16:24
 */
public interface TyShippingAreaService {
    List<TyShippingArea> getByStoreId(Integer storeId);

    Integer deleteById(Integer id);

    TyShippingArea getById(Integer id);
}
