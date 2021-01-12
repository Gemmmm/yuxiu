package com.tc.yuxiu;


import com.tc.yuxiu.util.RSACodeUtil;

import java.util.Map;

/** *//**
 *
 *   使用Junit4 来进行单元测试。
 * @version 1.0
 * @since 1.0
 */
public class RSACoderTest {
    private static String publicKey;
    private static String privateKey;

    public static void main(String[] args) {
        try {
            setUp();
            testPri2Pub();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void setUp() throws Exception {
        Map<String, Object> keyMap = RSACodeUtil.initKey();

        publicKey = RSACodeUtil.getPublicKey(keyMap);
        privateKey = RSACodeUtil.getPrivateKey(keyMap);
        System.out.println("公钥: \n\r" + publicKey);
        System.out.println("私钥： \n\r" + privateKey);
    }

    public static  void testPub2Pri() throws Exception {
        System.out.println("公钥加密——私钥解密");
        String inputStr = "abc";
        byte[] data = inputStr.getBytes();

        byte[] encodedData = RSACodeUtil.encryptByPublicKey(data, publicKey);

        byte[] decodedData = RSACodeUtil.decryptByPrivateKey(encodedData,
                privateKey);
        String outputStr = new String(decodedData);

        System.out.println("加密前: " + inputStr );
        System.out.println("密文: " + encodedData );
        System.out.println("解密后: " + outputStr );


    }

    public static  void testPri2Pub() throws Exception {
        System.out.println("私钥加密——公钥解密");
        String inputStr = "sign";
        byte[] data = inputStr.getBytes();

        byte[] encodedData = RSACodeUtil.encryptByPrivateKey(data, privateKey);

        byte[] decodedData = RSACodeUtil.decryptByPublicKey(encodedData, publicKey);

        String outputStr = new String(decodedData);
        System.out.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);


        System.out.println("私钥签名——公钥验证签名");
        // 产生签名   这里的encodedData可以与下面的encodedData同时换成new int[]{2,45}
        String sign = RSACodeUtil.sign(encodedData, privateKey); //数字签名只要公钥人拿到签名的sign对比
        //，自己公钥通过同样的byte[]运算得到签名是否一致。是到致代表这个公钥就是对的，就是为现在发私钥人服务的。
        System.out.println("签名:" + sign);

        // 验证签名   
        boolean status = RSACodeUtil.verify(encodedData, publicKey, sign);
        System.out.println("状态:" + status);

    }

}