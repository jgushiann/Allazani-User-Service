package org.service.allazaniuserservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.service.allazaniuserservice.dto.UserRequestDto;
import org.service.allazaniuserservice.dto.UserResponseDto;
import org.service.allazaniuserservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/auth/register")
    @Operation(summary = "Register a new user")
    public ResponseEntity<UserResponseDto> registerUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        UserResponseDto registeredUser = userService.registerUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

    @PostMapping("/auth/login")
    @Operation(summary = "Login and receive JWT")
    public ResponseEntity<String> loginUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        String message = userService.loginUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    /*
     * Implement this
     */
    @PostMapping("/auth/validate")
    @Operation(summary = "Validate JWT")
    public ResponseEntity<UserResponseDto> validateToken(@Valid @RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /*
     * Implement this
     */
    @GetMapping("/user/me")
    @Operation(summary = "Get current user info")
    public ResponseEntity<UserResponseDto> getCurrentUser() {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/user/{id}")
    @Operation(summary = "Get user info by its id")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id) {
        UserResponseDto user = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    /*
    * Implement this
    */
    @PutMapping("/user/me")
    @Operation(summary = "Update current user")
    public ResponseEntity<UserResponseDto> updateCurrentUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/user/{id}/role")
    @Operation(summary = "Assign role")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id) {
        UserResponseDto updatedUser = userService.updateRole(id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    @DeleteMapping("/user/me")
    @Operation(summary = "Delete current user")
    public ResponseEntity<String> deleteCurrentUser(){
        userService.deleteCurrentUser();
        return ResponseEntity.status(HttpStatus.OK).body("User has been deleted");
    }

    @DeleteMapping("/user/{id}")
    @Operation(summary = "Delete user by its id")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("User has been deleted");
    }

}
