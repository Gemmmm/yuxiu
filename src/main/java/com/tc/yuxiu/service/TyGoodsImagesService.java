package com.tc.yuxiu.service;

import com.tc.yuxiu.model.TyGoodsImages;

import java.util.List;

/**
 * @author DELL
 * @date 2020/9/17 11:27
 */
public interface TyGoodsImagesService {

    List<TyGoodsImages> getByGoodsId(Integer goodsId);

    Integer deleteByGoodsId(Integer goodsId);

    int insert(TyGoodsImages goodsImage);
}
