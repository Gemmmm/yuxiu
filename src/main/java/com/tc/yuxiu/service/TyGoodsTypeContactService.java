package com.tc.yuxiu.service;

import com.tc.yuxiu.model.TyGoodsImages;
import com.tc.yuxiu.model.TyGoodsTypeContact;

import java.util.List;

/**
 * @author DELL
 * @date 2020/9/17 15:04
 */
public interface TyGoodsTypeContactService {
    Integer insertList(List<TyGoodsTypeContact> goodsTypeContactList);

    List<TyGoodsTypeContact> getByGoodsId(Integer id);


    int modify(TyGoodsTypeContact goodsTypeContact);

    Integer insert(TyGoodsTypeContact goodsTypeContact);

    TyGoodsTypeContact getByGoodsIdAndTypeIdAndItemId(Integer goodsId, Integer typeId, Integer itemId);
}
