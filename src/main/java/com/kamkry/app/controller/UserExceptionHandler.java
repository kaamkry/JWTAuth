package com.kamkry.app.controller;

import com.kamkry.app.config.JwtAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice(assignableTypes = JwtAuthenticationFilter.class)
public class UserExceptionHandler {

    @ExceptionHandler(value = {UserNotFoundException.class,InternalAuthenticationServiceException.class})
    public @ResponseBody
    UserErrorResponse usernameNotFound(RuntimeException exception) {
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
