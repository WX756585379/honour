package com.honour.server.config.security;

import com.honour.server.entity.pojo.User;
import com.honour.server.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserService userService;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // String flagKey = "loginFailFlag:" + username;
        // String value = stringRedisTemplate.opsForValue().get(flagKey);
        // if (!StringUtils.isEmpty(value)) {
        //
        // }
        User user = userService.findUserInfoAndRolesByAccount(username);
        if (user == null) {
            throw new UsernameNotFoundException("账户不存在！");
        }

        SecurityUserDetails userDetails = new SecurityUserDetails();
        BeanUtils.copyProperties(user, userDetails);
        return userDetails;
    }
}
