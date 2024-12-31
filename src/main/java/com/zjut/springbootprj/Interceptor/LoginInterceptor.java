package com.zjut.springbootprj.Interceptor;


import com.zjut.springbootprj.common.Constants;
import com.zjut.springbootprj.utils.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class LoginInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //令牌验证
        String token = request.getHeader("token");
        token=token.substring(1,token.length()-1);
        //验证token
        try {
            JwtUtil.parseToken(token);
            //放行
            return true;
        } catch (Exception e) {
            System.out.println(token);
            System.out.println("不放行");
            //http响应状态码为401
            response.setStatus(Integer.parseInt(Constants.CODE_401));
            //不放行
            return false;
        }
    }
}
