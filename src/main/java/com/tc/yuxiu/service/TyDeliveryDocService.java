package com.tc.yuxiu.service;

import com.tc.yuxiu.model.TyDeliveryDoc;
import com.tc.yuxiu.model.TyOrder;

import java.util.List;

/**
 * @author DELL
 * @date 2020/9/18 11:08
 */
public interface TyDeliveryDocService {

    List<TyDeliveryDoc> getByStoreId(Integer storeId);

    Integer deleteById(Integer id);

    List<TyDeliveryDoc> getByParametersAndTime(Integer storeId, String consignee, String orderSn, Integer shippingStatus, String time);

    TyDeliveryDoc getById(Integer id);

    int modify(TyDeliveryDoc deliveryDoc);

    List<TyDeliveryDoc>  getByOrderId(Integer orderId);

    int insert(TyDeliveryDoc deliveryDoc);

}
