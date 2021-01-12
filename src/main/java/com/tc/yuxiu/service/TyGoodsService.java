package com.tc.yuxiu.service;

import com.tc.yuxiu.model.TyGoods;
import tk.mybatis.mapper.entity.Example;

import java.io.OutputStream;
import java.util.List;

/**
 * @author DELL
 * @date 2020/9/16 18:07
 */
public interface TyGoodsService {
    Integer insert(TyGoods goods);
    /**
     * @param storeId
     * @param isOnSale  商品类型，1出售中，0仓库中
     * @return
     */
    List<TyGoods> getByStoreIdAndIsOnSale(Integer storeId,Short isOnSale);
    /**
     * @param storeId
     * @param catId3 分类编号
     * @param type 类型 0全部，1新品，2推荐
     * @param existType -1全部，0现货，1到期
     * @param keywords 商品关键词
     * @param time 上架时间
     * @param isOnSale 商品类型，1出售中，0仓库中
     * @return 商品列表信息
     */
    List<TyGoods> getByParametersAndTime(Integer storeId,Integer catId3, String type, Short existType, String keywords, String time, Short isOnSale);
    /**
     * @param goodsId
     * @return
     */
    TyGoods getById(Integer goodsId);
    /**
     * @param storeId
     * @return
     */
    List<TyGoods> getByStoreId(Integer storeId);
    /**
     * @param storeId
     * @param catId1
     * @param goodsState
     * @param goodsName
     * @return 商品列表
     */
    List<TyGoods> getByStoreIdAndCatIdAndGoodsStateAndGoodsName(Integer storeId, Integer catId1, Short goodsState, String goodsName);

    /**
     *
     * @param goods
     * @return
     */
    Integer modify(TyGoods goods);

    Integer deleteById(Integer id);

    List<TyGoods> getByStoreIdAndIsDel(Integer storeId, Short isDel);

    TyGoods getByExample(Example example);

    Integer exportToExcel(List<TyGoods> goods, OutputStream outputStream);
}
