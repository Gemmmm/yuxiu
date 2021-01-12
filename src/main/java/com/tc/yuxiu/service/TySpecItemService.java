package com.tc.yuxiu.service;

import com.tc.yuxiu.model.TySpecItem;

import java.util.List;

/**
 * @author DELL
 * @date 2020/9/23 11:18
 */
public interface TySpecItemService {
    List<TySpecItem> getBySpecId(Integer specId);

    TySpecItem getById(Integer id);


    int insert(TySpecItem specItem);

    TySpecItem getByStoreIdAndSpecIdAndItemName(Integer storeId, Integer specId, String itemName);
}
