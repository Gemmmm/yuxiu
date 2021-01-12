package com.tc.yuxiu.service;

import com.tc.yuxiu.model.TyOrder;
import com.tc.yuxiu.model.TyOrderAction;

import java.util.List;

/**
 * @author DELL
 * @date 2020-9-29 14:08
 */
public interface TyOrderActionService {

    List<TyOrderAction> getByOrderId(Integer orderId);
}
