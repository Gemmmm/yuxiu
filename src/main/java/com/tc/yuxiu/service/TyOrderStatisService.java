package com.tc.yuxiu.service;

import com.tc.yuxiu.model.TyOrderStatis;

import java.io.OutputStream;
import java.util.List;

public interface TyOrderStatisService {
    List<TyOrderStatis> getByStoreId(Integer storeId);

    List<TyOrderStatis> getByStoreIdAndTime(Integer storeId, String time);

    int exportToExcel(List<TyOrderStatis> orderStatises, OutputStream outputStream);

}
