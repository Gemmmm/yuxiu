package com.tc.yuxiu.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author DELL
 * @date 2020/9/16 10:32
 */
public class Result {

    /**
     * 1 ：数据操作成功
     * -1 ：数据操作失败
     * -2 ：登录失效
     */
    private String code;
    /**
     *  返回信息
     */
    private String message;
    /**
     * 返回数据
     */
    private Object data;

    private  Object page;

    public Object getPage() {
        return page;
    }

    public void setPage(Object page) {
        this.page = page;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setSuccess(String option) {
        message = option + "成功";
        code = "1";
    }

    public void setFailToken() {
        message = "登录失效，请重新登录";
        code = "-2";
    }

    public void setFailLogin() {
        this.message = "登录失败，账号或者密码错误";
        this.code = "-2";
    }

    public void setFail(String option) {
        this. message = option + "失败";
        this.code = "-1";
    }
    public void setFailMessage(String message) {
        this.message = message;
        this.code = "-1";
    }
    public void setNull(String option) {
        this.message = option + "为空";
        this.code = "1";
    }


}
