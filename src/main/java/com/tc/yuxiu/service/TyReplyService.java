package com.tc.yuxiu.service;

import com.tc.yuxiu.model.TyReply;

import java.util.List;

/**
 * @author DELL
 * @date 2020-9-30 14:46
 */
public interface TyReplyService {
    List<TyReply> getByCommentId(Integer commentId);

    Integer insert(TyReply reply);
}
