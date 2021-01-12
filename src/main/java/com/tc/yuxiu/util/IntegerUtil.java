package com.tc.yuxiu.util;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * @author DELL
 * @date 2020/9/22 18:53
 */
public class IntegerUtil {
    public static Integer timeToInteger(Long num) {
        Long value = (Long) num / 1000;
        return value.intValue();
    }

    public static Long timeToLong(Integer num) {
        Long value = num * 1000L;
        return value;
    }


    public static Integer eval(String str) throws Exception {
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine eng = factory.getEngineByName("javascript");
        return (int) (double) eng.eval(str);
    }


    public static Integer calcuShopPrice(String costPrice) {
        float price = Float.valueOf(costPrice);
        return (int) ((price * 11 * 1.13) / 4);
    }

}
