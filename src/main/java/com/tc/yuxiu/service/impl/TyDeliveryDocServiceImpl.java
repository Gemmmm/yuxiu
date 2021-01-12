package com.tc.yuxiu.service.impl;

import com.tc.yuxiu.dao.TyDeliveryDocMapper;
import com.tc.yuxiu.model.TyDeliveryDoc;
import com.tc.yuxiu.model.TyOrder;
import com.tc.yuxiu.service.TyDeliveryDocService;
import com.tc.yuxiu.util.IntegerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author DELL
 * @date 2020/9/18 11:08
 */
@Service
public class TyDeliveryDocServiceImpl implements TyDeliveryDocService {
    @Autowired(required = false)
    private TyDeliveryDocMapper mapper;

    @Override
    public List<TyDeliveryDoc> getByStoreId(Integer storeId) {
        Example example=new Example(TyDeliveryDoc.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId",storeId);
        List<TyDeliveryDoc> list = mapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }

    @Override
    public Integer deleteById(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<TyDeliveryDoc> getByParametersAndTime(Integer storeId, String consignee, String orderSn, Integer shippingStatus, String time) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Example example=new Example(TyDeliveryDoc.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId",storeId);
        if (consignee!=null){
            criteria.andEqualTo("consignee",consignee);
        }
        if (consignee!=null){
            criteria.andEqualTo("orderSn",orderSn);
        }
        Integer start = null;
        Integer end = null;
        Date startTime = null;
        Date endTime = null;
        if (time != null) {
            String[] times = time.split(",");
            try {
                if (times != null && times.length > 0) {
                    startTime = sdf.parse(times[0].trim());
                    endTime = sdf.parse(times[1].trim());
                }
            } catch (ParseException e) {
            }
        }
        if (startTime != null && endTime != null) {
            start = IntegerUtil.timeToInteger(startTime.getTime());
            end = IntegerUtil.timeToInteger(endTime.getTime());
        }
        List<TyDeliveryDoc> list = mapper.selectByExample(example);

        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }

    @Override
    public TyDeliveryDoc getById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int modify(TyDeliveryDoc deliveryDoc) {

        return mapper.updateByPrimaryKeySelective(deliveryDoc);
    }

    @Override
    public List<TyDeliveryDoc>  getByOrderId(Integer orderId) {

        Example example=new Example(TyDeliveryDoc.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderId",orderId);
        List<TyDeliveryDoc> list = mapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }

    @Override
    public int insert(TyDeliveryDoc deliveryDoc) {
        return mapper.insertSelective(deliveryDoc);
    }
}
