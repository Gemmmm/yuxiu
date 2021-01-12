package com.tc.yuxiu.service;

import com.tc.yuxiu.model.TyGoodsTypeItem;

import java.util.List;

/**
 * @author DELL
 * @date 2020/9/17 13:40
 */
public interface TyGoodsTypeItemService {
    List<TyGoodsTypeItem> getByTypeId(Short typeId);
}
