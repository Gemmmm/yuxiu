package com.tc.yuxiu.service;

import com.tc.yuxiu.model.TyActivity;

import java.util.List;

/**
 * @author DELL
 * @date 2020/9/21 15:47
 */
public interface TyActivityService {
    List<TyActivity> getByStoreId(Integer storeId);
}
