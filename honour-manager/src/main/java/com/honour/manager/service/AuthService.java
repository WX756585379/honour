package com.honour.manager.service;

import com.honour.manager.constant.Constant;
import com.honour.manager.dao.UserMapper;
import com.honour.manager.entity.pojo.User;
import com.honour.manager.entity.vo.BaseResponse;
import com.honour.manager.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserMapper userMapper;

    public Object login(String username, String password) {
        String token = null;
        try {
            UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
            Authentication authentication = authenticationManager.authenticate(upToken);//跳到UserDetailsServiceImpl
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            token = jwtTokenUtil.generateToken(userDetails.getUsername());
            if (token == null) {
                return BaseResponse.error("用户名或密码错误!");
            } else {
                updateLoginTimeByUsername(username);
                Map<String, String> tokenMap = new HashMap<>();
                tokenMap.put("token", token);
                tokenMap.put("tokenHead", JwtTokenUtil.TOKEN_HEAD);
                return BaseResponse.success(tokenMap);
            }
        } catch (Exception e) {
            log.error("登录异常:{}", e.getMessage());
            return BaseResponse.error("用户名或密码错误!");
        }
    }

    /**
     * 根据用户名修改登录时间
     */
    private void updateLoginTimeByUsername(String username) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        User record = new User();
        record.setAccount(username);
        record.setLoginIp(request.getRemoteAddr());
        record.setLoginTime(new Date());
        userMapper.updateByUserNameSelective(record);
    }

    public String refreshToken(String oldToken) {
        String token = oldToken.substring(JwtTokenUtil.TOKEN_HEAD.length());
        if (jwtTokenUtil.canRefresh(token)) {
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }


}
