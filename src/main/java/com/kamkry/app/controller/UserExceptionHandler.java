package com.kamkry.app.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class UserExceptionHandler{

    @ExceptionHandler
    public @ResponseBody
    UserErrorResponse usernameNotFound(UserNotFoundException exception) {
        return new UserErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                System.currentTimeMillis()
        );
    }

    @ExceptionHandler
    public @ResponseBody
    UserErrorResponse usernameAlreadyExists(UserAlreadyExistsException exception) {
        return new UserErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                System.currentTimeMillis()
        );
    }

    @ExceptionHandler
    public @ResponseBody
    UserErrorResponse handleException(Exception exception) {
        return new UserErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                System.currentTimeMillis()
        );
    }
}
