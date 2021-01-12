package com.tc.yuxiu.service;

import com.tc.yuxiu.model.TySpec;
import com.tc.yuxiu.model.TySpecItem;

import java.util.List;

/**
 * @author DELL
 * @date 2020/9/23 10:37
 */
public interface TySpecService {

    List<TySpec> getByStoreId(Integer storeId);

    TySpec getById(Integer id);
}
