package com.codeup.springblog.services;

import com.codeup.springblog.models.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class Util {

    public static User login() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
