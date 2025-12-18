package org.service.allazaniuserservice.controller;

import lombok.RequiredArgsConstructor;
import org.service.allazaniuserservice.dto.UserRequestDto;
import org.service.allazaniuserservice.dto.UserResponseDto;
import org.service.allazaniuserservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    @Operation(summary = "Register a new user")
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto registeredUser = userService.registerUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

}
