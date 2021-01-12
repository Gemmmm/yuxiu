package com.tc.yuxiu.service.impl;

import com.tc.yuxiu.dao.TyRegionMapper;
import com.tc.yuxiu.model.TyRegion;
import com.tc.yuxiu.service.TyRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author DELL
 * @date 2020/9/21 14:38
 */
@Service
public class TyRegionServiceImpl implements TyRegionService {
    @Autowired(required = false)
    private TyRegionMapper mapper;

    @Override
    public TyRegion getById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TyRegion> getByLevelAndParentId(Short level, Integer parentId) {
        Example example=new Example(TyRegion.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("level",level);
        criteria.andEqualTo("parentId",parentId);
        List<TyRegion> list = mapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list;
        }
        return null;
    }
}
