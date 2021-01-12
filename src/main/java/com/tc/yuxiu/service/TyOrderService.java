package com.tc.yuxiu.service;

import com.tc.yuxiu.model.TyOrder;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @author DELL
 * @date 2020/9/18 9:35
 */
public interface TyOrderService {
    List<TyOrder> getByStoreId(Integer storeId);

    TyOrder getByOrderId(Integer orderId);

    List<TyOrder> getByParametersAndTime(
            Integer storeId, String consignee, String orderSn, String time, Integer payStatus,
            String payName, Integer shippingStatus, Integer orderStatus, Integer type);

    Integer deleteById(Integer orderId);

    Integer exportToExcel(List<TyOrder> orderList, OutputStream outputStream);

    TyOrder getByOrderSn(String orderSn);

    Integer modifyByOrderId(TyOrder order);

    List<TyOrder> getByStoreIdAndTime(Integer storeId, Integer start, Integer end);

    List<TyOrder> getByStoreIdAndOrderSnAndTimeNoOrderStatus(Integer storeId, String orderSn, String time, Integer orderStatus);
}
