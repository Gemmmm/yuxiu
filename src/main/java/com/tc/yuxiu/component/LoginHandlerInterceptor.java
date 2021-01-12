package com.tc.yuxiu.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author DELL
 * @date 2020/8/13 16:13
 */
@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {
    private static Logger logger = LoggerFactory.getLogger(LoginHandlerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        Object seller = null;
//        //seller = request.getSession().getAttribute("seller");
//        System.out.println("handler::"+handler);
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = new Date();
//        if (seller == null) {
//            System.out.println("未登录,时间:"+sdf.format(date));
//               request.getRequestDispatcher("/login").forward(request,response);
//        }else{
//            System.out.println("已登录,时间:"+sdf.format(date));
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
