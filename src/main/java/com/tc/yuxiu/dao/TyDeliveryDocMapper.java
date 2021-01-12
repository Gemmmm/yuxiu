package com.tc.yuxiu.dao;

import com.tc.yuxiu.model.TyDeliveryDoc;

import java.util.List;

import com.tc.yuxiu.util.MyMapper;
import org.apache.ibatis.annotations.Param;

public interface TyDeliveryDocMapper extends MyMapper<TyDeliveryDoc> {

    //List<TyDeliveryDoc> selectByParameters(@Param("id")Integer id,@Param("storeId") Integer storeId, @Param("consignee") String consignee, @Param("orderSn") String orderSn, @Param("shippingStatus") Integer shippingStatus, @Param("start") Integer start, @Param("end") Integer end);
}