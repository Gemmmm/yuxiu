package com.tc.yuxiu.service;

import com.tc.yuxiu.model.TyUserAddress;

public interface TyUserAddressService {
    TyUserAddress getByUserId(Integer userId);

}
