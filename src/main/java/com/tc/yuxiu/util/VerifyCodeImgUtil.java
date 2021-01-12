package com.tc.yuxiu.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;

/**
 * @author DELL
 * @date 2020/9/25 9:48
 */
public class VerifyCodeImgUtil {


    /**
     * 验证码类型为仅数字,即0~9
     */
    public static final int TYPE_NUM_ONLY = 0;

    /**
     * 验证码类型为仅字母,即大小写字母混合
     */
    public static final int TYPE_LETTER_ONLY = 1;

    /**
     * 验证码类型为数字和大小写字母混合
     */
    public static final int TYPE_ALL_MIXED = 2;

    /**
     * 验证码类型为数字和大写字母混合
     */
    public static final int TYPE_NUM_UPPER = 3;

    /**
     * 验证码类型为数字和小写字母混合
     */
    public static final int TYPE_NUM_LOWER = 4;

    /**
     * 验证码类型为仅大写字母
     */
    public static final int TYPE_UPPER_ONLY = 5;

    /**
     * 验证码类型为仅小写字母
     */
    public static final int TYPE_LOWER_ONLY = 6;

    public static String generateTextCode(int type, int length, String excludeString) {
        if (length <= 0) {
            return "";
        }
        StringBuilder verifyCode = new StringBuilder();
        int i = 0;
        Random random = new Random();
        switch (type) {
            case TYPE_NUM_ONLY:
                while (i < length) {
                    int t = random.nextInt(10);
                    //排除特殊字符
                    if (null == excludeString || !excludeString.contains(t + "")) {
                        verifyCode.append(t);
                        i++;
                    }
                }
                break;
            case TYPE_LETTER_ONLY:
                while (i < length) {
                    int t = random.nextInt(123);
                    if ((t >= 97 || (t >= 65 && t <= 90)) && (null == excludeString || excludeString.indexOf((char) t) < 0)) {
                        verifyCode.append((char) t);
                        i++;
                    }
                }
                break;
            case TYPE_ALL_MIXED:
                while (i < length) {
                    int t = random.nextInt(123);
                    if ((t >= 97 || (t >= 65 && t <= 90) || (t >= 48 && t <= 57)) && (null == excludeString || excludeString.indexOf((char) t) < 0)) {
                        verifyCode.append((char) t);
                        i++;
                    }
                }
                break;
            case TYPE_NUM_UPPER:
                while (i < length) {
                    int t = random.nextInt(91);
                    if ((t >= 65 || (t >= 48 && t <= 57)) && (null == excludeString || excludeString.indexOf((char) t) < 0)) {
                        verifyCode.append((char) t);
                        i++;
                    }
                }
                break;
            case TYPE_NUM_LOWER:
                while (i < length) {
                    int t = random.nextInt(123);
                    if ((t >= 97 || (t >= 48 && t <= 57)) && (null == excludeString || excludeString.indexOf((char) t) < 0)) {
                        verifyCode.append((char) t);
                        i++;
                    }
                }
                break;
            case TYPE_UPPER_ONLY:
                while (i < length) {
                    int t = random.nextInt(91);
                    if ((t >= 65) && (null == excludeString || excludeString.indexOf((char) t) < 0)) {
                        verifyCode.append((char) t);
                        i++;
                    }
                }
                break;
            case TYPE_LOWER_ONLY:
                while (i < length) {
                    int t = random.nextInt(123);
                    if ((t >= 97) && (null == excludeString || excludeString.indexOf((char) t) < 0)) {
                        verifyCode.append((char) t);
                        i++;
                    }
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        return verifyCode.toString();
    }

    public static Color generateRandomColor() {
        Random random = new Random();
        return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }

    public static BufferedImage generateImageCode(String textCode, int width, int height, int interLine, boolean randomLocation, Color backColor, Color foreColor, Color lineColor) {
        //创建内存图像
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //获取图形上下文
        Graphics graphics = bufferedImage.getGraphics();
        //画背景图
        graphics.setColor(null == backColor ? generateRandomColor() : backColor);
        graphics.fillRect(0, 0, width, height);
        //画干扰线
        Random random = new Random();
        if (interLine > 0) {
            int x = 0, y, y1;
            for (int i = 0; i < interLine; i++) {
                graphics.setColor(null == lineColor ? generateRandomColor() : lineColor);
                y = random.nextInt(height);
                y1 = random.nextInt(height);
                graphics.drawLine(x, y, width, y1);
            }
        }
        //字体大小为图片高度的80%
        int fsize = (int) (height * 0.8);
        int fx = height - fsize;
        int fy = fsize;
        //设定字体
        graphics.setFont(new Font("Default", Font.PLAIN, fsize));
        //写验证码字符
        for (int i = 0; i < textCode.length(); i++) {
            fy = randomLocation ? (int) ((Math.random() * 0.3 + 0.6) * height) : fy;
            graphics.setColor(null == foreColor ? generateRandomColor() : foreColor);
            //将验证码字符显示到图象中
            graphics.drawString(textCode.charAt(i) + "", fx, fy);
            fx += fsize * 0.9;
        }
        graphics.dispose();
        return bufferedImage;
    }






    /**
     *
     * @return 6位数字验证码图片
     */
    public static String generate() {
        String path = Constants.PicPath + "CodeImg.png";
        BufferedImage bi = new BufferedImage(68, 22, BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.getGraphics();
        Color c = new Color(200, 150, 255);
        //背景颜色
        g.setColor(c);
        //背景框
        g.fillRect(0, 0, 90, 22);

        //字母数字组合：
        char[] ch = "abcdefghijklmnopqrstuvwxyz123456789".toCharArray();
        Random random = new Random();
        int len = ch.length;
        int index;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            index =random.nextInt(len);
            g.setColor(new Color(random.nextInt(88), random.nextInt(188), random.nextInt(255)));
            g.drawString(ch[index] + "", (i * 15) + 3, 18);
            sb.append(ch[index]);
        }


        try {
            ImageIO.write(bi, "PNG", new File(path));
            return sb.toString();
        } catch (IOException e) {

        }
        return null;

    }
}