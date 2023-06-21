package org.ivanovx.bloggable.util;

import org.springframework.security.core.context.SecurityContextHolder;

import org.ivanovx.bloggable.entity.User;

public class UserUtil {
    public static User getActiveUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}