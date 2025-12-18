package org.service.allazaniuserservice.exception;

import org.service.allazaniuserservice.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<UserNotFoundException> handleUserNotFoundException(UserNotFoundException e) {
        UserNotFoundException userNotFoundException = new UserNotFoundException(e.getMessage());
        return new ResponseEntity<>(userNotFoundException, HttpStatus.NOT_FOUND);
    }
}
