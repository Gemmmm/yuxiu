package com.tc.yuxiu.service.impl;

import com.tc.yuxiu.dao.TySellerMapper;
import com.tc.yuxiu.model.TySeller;
import com.tc.yuxiu.service.TySellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author DELL
 * @date 2020/9/16 10:50
 */
@Service
public class TySellerServiceImpl implements TySellerService {
    @Autowired(required = false)
    private TySellerMapper mapper;

    @Override
    public List<TySeller> getAll() {
        return mapper.selectAll();
    }

    @Override
    public TySeller getByMobileAndPassword(String mobile, String password) {
        Example example = new Example(TySeller.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("mobile", mobile);
        criteria.andEqualTo("password", password);
        List<TySeller> list = mapper.selectByExample(example);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public TySeller getByToken(String token) {
        Example example = new Example(TySeller.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("token", token);
        List<TySeller> list = mapper.selectByExample(example);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public Integer modify(TySeller seller) {
        return mapper.updateByPrimaryKeySelective(seller);
    }
}
