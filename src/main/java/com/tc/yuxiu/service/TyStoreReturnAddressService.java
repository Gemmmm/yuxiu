package com.tc.yuxiu.service;

import com.tc.yuxiu.model.TyStoreReturnAddress;

import java.util.List;

/**
 * @author DELL
 * @date 2020/9/21 14:19
 */
public interface TyStoreReturnAddressService {
    List<TyStoreReturnAddress> getByStoreId(Integer storeId);

    Integer deleteById(Integer id);

    TyStoreReturnAddress getById(Integer id);

    Integer modifyById(TyStoreReturnAddress returnAddress);

    Integer insert(TyStoreReturnAddress returnAddress);
}
