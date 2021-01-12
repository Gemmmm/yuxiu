package com.tc.yuxiu.controller;

import com.tc.yuxiu.model.Result;
import com.tc.yuxiu.model.TySeller;
import com.tc.yuxiu.model.TySellerLog;
import com.tc.yuxiu.service.TySellerLogService;
import com.tc.yuxiu.service.TySellerService;
import com.tc.yuxiu.util.IntegerUtil;
import com.tc.yuxiu.util.IpUtil;
import com.tc.yuxiu.util.StringUtil;
import com.tc.yuxiu.util.VerifyCodeImgUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author DELL
 * @date 2020/9/16 10:32
 */
@Api(tags = "登录")
@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private TySellerService sellerService;
    @Autowired
    private TySellerLogService sellerLogService;

    private String verifyCode = null;


    //@RequestMapping(value = "/verifyCodeImg", method = RequestMethod.GET)
    public void getCodeImg(
            HttpServletRequest request, HttpServletResponse response
    ) {
        response.setHeader("Content-Type", "image/jpeg");
        BufferedImage bi = new BufferedImage(68, 22, BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.getGraphics();
        Color c = new Color(200, 150, 255);
        //背景颜色
        g.setColor(c);
        //背景框
        g.fillRect(0, 0, 68, 22);
        //字母数字组合：
        char[] ch = "abcdefghijklmnopqrstuvwxyz123456789".toCharArray();
        Random r = new Random();
        int len = ch.length;
        int index;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            index = r.nextInt(len);
            //给字体一个随机的颜色
            g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt(255)));
            g.drawString(ch[index] + "", (i * 15) + 3, 18);
            sb.append(ch[index]);
        }
        verifyCode=sb.toString();
        try {
            ImageIO.write(bi, "png", response.getOutputStream());
        } catch (IOException e) {
        }
    }


    @ApiOperation("验证码图片")
    @GetMapping("verifyCodeImg")
    public void getVerifyCodeImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置页面不缓存
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.getOutputStream();
        String vCode = VerifyCodeImgUtil.generateTextCode(VerifyCodeImgUtil.TYPE_ALL_MIXED, 4, null);
        verifyCode=vCode;
        //设置输出的内容的类型为JPEG图像
        response.setContentType("image/jpeg");
        BufferedImage bufferedImage = VerifyCodeImgUtil.generateImageCode(verifyCode, 90, 30, 3, true, Color.WHITE, Color.BLACK, null);

        //写给浏览器
        ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
    }

    @ApiOperation("登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result loginForm(
            @ApiParam @RequestParam(required = false) String mobile,
            @ApiParam @RequestParam(required = false) String password,
            //@ApiParam @RequestParam(required = false) String code,
            HttpServletRequest request
    ) {
        Result result = new Result();
//        if(verifyCode==null||"".equals(verifyCode)){
//            result.setFailMessage("验证码错误。");
//            return result;
//        }
//        if(code==null||"".equals(code)){
//            result.setFailMessage("请输入验证码!");
//            return result;
//        }
//        if(!verifyCode.toLowerCase().equals(code.toLowerCase())){
//            result.setFailMessage("验证码错误!");
//            return result;
//        }
        if(mobile==null||"".equals(mobile)){
            result.setFailMessage("请输入账号!");
            return result;
        }
        if(password==null||"".equals(password)){
            result.setFailMessage("请输入密码!");
            return result;
        }

        Map<String, Object> map = new HashMap<>();
        TySeller seller = sellerService.getByMobileAndPassword(mobile, StringUtil.encoderByMd5(password));
        if (seller != null) {
            String token = StringUtil.encoderByMd5(StringUtil.seqGenerate().toString());
            map.put("token", token);
            Long currentTime = System.currentTimeMillis();
            Integer timeToInteger = IntegerUtil.timeToInteger(currentTime);
            seller.setToken(token);
            seller.setLastLoginTime(timeToInteger);
            sellerService.modify(seller);

            TySellerLog sellerLog = new TySellerLog();
            sellerLog.setLogSellerId(seller.getSellerId());
            sellerLog.setLogSellerName(seller.getSellerName());
            sellerLog.setLogStoreId(seller.getStoreId());
            sellerLog.setLogContent("商家管理中心登录");
            sellerLog.setLogUrl("/seller/login");
            String ipAddr = IpUtil.getIpAddr(request);
            if(ipAddr!=null){

                sellerLog.setLogSellerIp(ipAddr);
            }else{
                sellerLog.setLogSellerIp("");
            }
            sellerLog.setLogTime(timeToInteger);
            byte state = 0;
            sellerLog.setLogState(state);
            sellerLogService.insert(sellerLog);
            result.setSuccess("登录");
        } else {
            result.setFailLogin();
            map.put("token", "");
        }
        result.setData(map);
        return result;
    }

    @ApiOperation("修改密码")
    @PostMapping("/modifyPassword")
    public Result mofidyPassword(
            @ApiParam @RequestParam String token,
            @ApiParam @RequestParam String oldPassword,
            @ApiParam @RequestParam String newPassword
    ){
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
//        if(!seller.getPassword().equals(oldPassword)){
//            result.setFailMessage("原密码不正确");
//            return result;
//        }
        if(oldPassword.equals(newPassword)){
            result.setFailMessage("新密码不能与原密码相同");
            return result;
        }
        seller.setPassword(newPassword);
        Integer flag = sellerService.modify(seller);
        if(flag>0){
            result.setSuccess("密码修改");
        }else{
            result.setFail("密码修改");
        }
        return result;
    }
}
