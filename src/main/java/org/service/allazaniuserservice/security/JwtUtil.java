package org.service.allazaniuserservice.security;

import org.service.allazaniuserservice.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    public String generateToken(UserDetails  user){
        return "";
    }
}
