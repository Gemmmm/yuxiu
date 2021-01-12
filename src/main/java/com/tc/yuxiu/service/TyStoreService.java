package com.tc.yuxiu.service;

import com.tc.yuxiu.model.TyStore;

/**
 * @author DELL
 * @date 2020/9/16 15:18
 */
public interface TyStoreService {

    TyStore getById(Integer storeId);

    Integer modify(TyStore store);
}
