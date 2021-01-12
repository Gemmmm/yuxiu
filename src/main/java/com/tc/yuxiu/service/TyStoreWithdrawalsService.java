package com.tc.yuxiu.service;

import com.tc.yuxiu.model.TyStoreWithdrawals;

import java.io.OutputStream;
import java.util.List;

public interface TyStoreWithdrawalsService {
    List<TyStoreWithdrawals> getByStoreId(Integer storeId);

    List<TyStoreWithdrawals> getByParametersAndTime(Integer storeId, Short status, String accountBank, String accountName, String time);

    int insert(TyStoreWithdrawals withdrawal);

    int exportToExcel(List<TyStoreWithdrawals> withdrawals, OutputStream outputStream);

    TyStoreWithdrawals getById(Integer id);

    int modify(TyStoreWithdrawals withdrawal);

    int deleteById(Integer id);
}
