package com.tc.yuxiu.service;

import com.tc.yuxiu.model.TyStoreRemittance;

import java.io.OutputStream;
import java.util.List;

public interface TyStoreRemittanceService {
    List<TyStoreRemittance> getByStoreId(Integer storeId);

    List<TyStoreRemittance> getByParametersAndTime(Integer storeId, String accountBank, String accountName, String time);

    int exportToExcel(List<TyStoreRemittance> remittances, OutputStream outputStream);
}
