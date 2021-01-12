package com.tc.yuxiu.service;

import com.tc.yuxiu.model.TyBusinessReturnRecords;

import java.io.OutputStream;
import java.util.List;

/**
 * @author DELL
 * @date 2020/9/21 11:12
 */
public interface TyBusinessReturnRecordsService {
    List<TyBusinessReturnRecords> getByStoreId(Integer storeId);

    List<TyBusinessReturnRecords> getByParametersAndTime(Integer storeId, String orderSn, Short type, Short status, String time);

    TyBusinessReturnRecords getById(String id);

    int insert(TyBusinessReturnRecords returnRecords);

    int exportToExcel(List<TyBusinessReturnRecords> retRecords, OutputStream outputStream);
}
