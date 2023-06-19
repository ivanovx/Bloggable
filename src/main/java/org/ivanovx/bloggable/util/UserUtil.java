package org.ivanovx.bloggable.util;

import org.springframework.security.core.context.SecurityContextHolder;

import org.ivanovx.bloggable.entity.User;

public class UserUtil {
   // public static User getUser(Principal authentication) {
       /* if(!(authentication instanceof UsernamePasswordAuthenticationToken)) {
            throw new UnauthorizedException("Error: User should be authenticated");
        }*/
     //   var token = (UsernamePasswordAuthenticationToken) authentication;
        /*if(token.getPrincipal() == null || !(token.getPrincipal() instanceof User)) {
            throw new UnauthorizedException("Error: User should be authenticated");
        }*/
       // var user = (User) token.getPrincipal();
       // return user;
    //}

    public static User getActiveUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
