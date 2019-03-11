package com.honour.server.utils;

import com.honour.server.entity.pojo.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtil {

    public static User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
