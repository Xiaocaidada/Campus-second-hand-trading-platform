package com.campusmarket.config;

import com.campusmarket.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 放行 OPTIONS 预检请求
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        String test_url = request.getRequestURI();
        System.out.println("当前拦截到的URI：" + test_url);

        // 放行公开接口
        String uri = request.getRequestURI();
        if (uri.equals("/user/login") || uri.equals("/user/register") || uri.startsWith("/images/")) {
            return true;
        }

        // 其他接口需要校验 Token
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            try {
                Claims claims = jwtUtil.parseToken(token);
                request.setAttribute("userId", Long.valueOf(claims.getSubject()));
                request.setAttribute("role", claims.get("role"));
                return true;
            } catch (Exception e) {
                throw new RuntimeException("token无效");
            }
        }
        throw new RuntimeException("未登录");
    }
}