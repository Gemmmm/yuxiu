package com.tc.yuxiu.service.impl;

import com.tc.yuxiu.dao.TyGoodsImagesMapper;
import com.tc.yuxiu.model.TyGoodsImages;
import com.tc.yuxiu.service.TyGoodsImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author DELL
 * @date 2020/9/17 11:27
 */
@Service
public class TyGoodsImagesServiceImpl implements TyGoodsImagesService {
    @Autowired(required = false)
    private TyGoodsImagesMapper mapper;


    @Override
    public  List<TyGoodsImages> getByGoodsId(Integer goodsId) {
        Example example=new Example(TyGoodsImages.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("goodsId",goodsId);
        List<TyGoodsImages> list = mapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list;
        }
        return null;
    }

    @Override
    public Integer deleteByGoodsId(Integer goodsId) {
        Example example=new Example(TyGoodsImages.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("goodsId",goodsId);
        return  mapper.deleteByExample(example);
    }

    @Override
    public int insert(TyGoodsImages goodsImage) {
        return mapper.insertSelective(goodsImage);
    }
}
