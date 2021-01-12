package com.tc.yuxiu.service;

import com.tc.yuxiu.model.TyComment;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author DELL
 * @date 2020/9/21 11:45
 */
public interface TyCommentService {

    List<TyComment> getByStoreId(Integer storeId);

    Integer deleteById(Integer id);

    TyComment getById(Integer id);

//    List<TyComment> getByParametersAndTime(Integer commentId, Integer storeId, String userName, String orderSn, Integer startTime, Integer endTime);
}
