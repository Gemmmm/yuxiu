package com.tc.yuxiu.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class StringUtil {

    //随机生成6位数主键
    public static Integer verifyCode() {
        //随机生成6位随机数
        return (int) ((Math.random() * 9 + 1) * 100000);
    }

    //随机生成19位数主键
    public static Long seqGenerate() {
        //获取当前时间毫秒数
        long mTime = System.currentTimeMillis();
        //随机生成6位随机数
        long radomNum = (int) ((Math.random() * 9 + 1) * 100000);
        long seq = mTime * 1000000 + radomNum;
        return seq;
    }

    //MD5加密
    public static String encoderByMd5(String string) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(string.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getOrderStatus(Integer orderStatus) {
        if (orderStatus == 0) {
            return "待支付";
        } else if (orderStatus == 1) {
            return "待支付（待收货）";
        } else if (orderStatus == 2) {
            return "待评价";
        } else if (orderStatus == 3) {
            return "待取消";
        } else if (orderStatus == 4) {
            return "已完成";
        } else if (orderStatus == 5) {
            return "退货（换货）中";
        }
        return "";
    }

    public static void main(String[] args) {

        String s = encoderByMd5("123456");
        System.out.println(s);
    }



}
