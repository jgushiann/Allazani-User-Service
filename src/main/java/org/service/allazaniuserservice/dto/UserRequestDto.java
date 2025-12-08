package org.service.allazaniuserservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    String firstname;

    String lastname;

    @Email
    @NotBlank
    String email;

    String password;

    @NotBlank
    String username;

    String phone;
}
