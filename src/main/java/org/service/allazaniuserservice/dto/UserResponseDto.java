package org.service.allazaniuserservice.dto;

import lombok.Data;
import org.service.allazaniuserservice.entity.Role;

@Data
public class UserResponseDto {
    Long id;
    String firstname;
    String lastname;
    String email;
    String username;
    String phone;
    Role role;
}
