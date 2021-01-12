package com.tc.yuxiu.service;

import com.tc.yuxiu.model.TySeller;

import java.util.List;

/**
 * @author DELL
 * @date 2020/9/16 10:50
 */
public interface TySellerService {

    List<TySeller> getAll();

    TySeller getByMobileAndPassword(String mobile, String password);

    TySeller getByToken(String token);

    Integer modify(TySeller seller);
}
