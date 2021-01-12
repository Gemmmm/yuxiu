package com.tc.yuxiu.service;

import com.tc.yuxiu.model.TyUsers;

/**
 * @author DELL
 * @date 2020/9/21 12:05
 */
public interface TyUsersService {
    TyUsers getByUserId(Integer userId);

    TyUsers getByUserName(String userName);
}
