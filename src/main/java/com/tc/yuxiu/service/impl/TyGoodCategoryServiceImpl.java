package com.tc.yuxiu.service.impl;

import com.tc.yuxiu.dao.TyGoodsCategoryMapper;
import com.tc.yuxiu.model.TyGoodsCategory;
import com.tc.yuxiu.model.TyGoodsType;
import com.tc.yuxiu.service.TyGoodCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author DELL
 * @date 2020/9/16 16:42
 */
@Service
public class TyGoodCategoryServiceImpl implements TyGoodCategoryService {
    @Autowired(required = false)
    private TyGoodsCategoryMapper mapper;

    @Override
    public List<TyGoodsCategory> getByLevelAndParentId(Short level, Integer parentId) {
        Example example = new Example(TyGoodsCategory.class);
        Example.Criteria criteria = example.createCriteria();
        if (parentId != null) {
            criteria.andEqualTo("level", level);
        }
        if (parentId != null) {
            criteria.andEqualTo("parentId", parentId);
        }
        List<TyGoodsCategory> list = mapper.selectByExample(example);
        if (list.size() > 0) {
            return list;
        }
        return null;
    }

    @Override
    public TyGoodsCategory getById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }
}
