package com.tc.yuxiu.service.impl;

import com.tc.yuxiu.dao.TyReplyMapper;
import com.tc.yuxiu.model.TyReply;
import com.tc.yuxiu.service.TyReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author DELL
 * @date 2020-9-30 14:46
 */
@Service
public class TyReplyServiceImpl implements TyReplyService {

    @Autowired(required = false)
    private TyReplyMapper mapper;

    @Override
    public List<TyReply> getByCommentId(Integer commentId) {
        Example example=new Example(TyReply.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("commentId",commentId);
        List<TyReply> list = mapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list;
        }
        return null;
    }

    @Override
    public Integer insert(TyReply reply) {
        return mapper.insertSelective(reply);
    }
}
