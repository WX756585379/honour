package com.honour.server.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class FailureAuthenticationHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res, AuthenticationException e) throws IOException, ServletException {
        res.setContentType("application/json;charset=utf-8");
        PrintWriter out = res.getWriter();
        res.setStatus(401);
        Map<String, Object> map = new HashMap<>();
        map.put("status", 401);
        if (e instanceof BadCredentialsException || e instanceof UsernameNotFoundException) {
            map.put("msg", "账户名或密码输入错误,登录失败!");
        } else if (e instanceof LockedException) {
            map.put("msg", "用户被锁定，请联系管理员!");
        } else if (e instanceof CredentialsExpiredException) {
            map.put("msg", "密码过期，请联系管理员!");
        } else if (e instanceof AccountExpiredException) {
            map.put("msg", "账户过期，请联系管理员!");
        } else if (e instanceof DisabledException) {
            map.put("msg", "账户被禁用，请联系管理员!");
        } else {
            map.put("msg", "登录失败！");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        out.write(objectMapper.writeValueAsString(map));
        out.flush();
        out.close();
    }
}
