package com.tc.yuxiu.service.impl;

import com.tc.yuxiu.dao.TyCommentMapper;
import com.tc.yuxiu.model.TyComment;
import com.tc.yuxiu.service.TyCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author DELL
 * @date 2020/9/21 11:45
 */
@Service
public class TyCommentServiceImpl implements TyCommentService {
    @Autowired(required = false)
    private TyCommentMapper mapper;

    @Override
    public List<TyComment> getByStoreId(Integer storeId) {
        Example example=new Example(TyComment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storeId",storeId);
        List<TyComment> list = mapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list;
        }
        return null;
    }

    @Override
    public Integer deleteById(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public TyComment getById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

//    @Override
//    public List<TyComment> getByParametersAndTime(Integer commentId, Integer storeId, String userName, String orderSn, Integer startTime, Integer endTime) {
//        return mapper.selectByParameters(commentId,storeId,userName,orderSn,startTime,endTime);
//    }


}
