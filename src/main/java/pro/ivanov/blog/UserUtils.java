package pro.ivanov.blog;

import org.springframework.security.core.context.SecurityContextHolder;

import pro.ivanov.blog.entity.User;

public class UserUtils {
    public static User getUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}