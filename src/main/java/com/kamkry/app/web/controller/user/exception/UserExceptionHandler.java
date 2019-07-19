package com.kamkry.app.web.controller.user.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class UserExceptionHandler{

    @ExceptionHandler
    public @ResponseBody
    UserExceptionResponse usernameNotFound(UserNotFoundException exception) {
        return new UserExceptionResponse(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                System.currentTimeMillis()
        );
    }

    @ExceptionHandler
    public @ResponseBody
    UserExceptionResponse usernameAlreadyExists(UserAlreadyExistsException exception) {
        return new UserExceptionResponse(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                System.currentTimeMillis()
        );
    }

    @ExceptionHandler
    public @ResponseBody
    UserExceptionResponse userNotAuthorized(UserNotAuthorizedException exception) {
        return new UserExceptionResponse(
                HttpStatus.FORBIDDEN.value(),
                exception.getMessage(),
                System.currentTimeMillis()
        );
    }

    @ExceptionHandler
    public @ResponseBody
    UserExceptionResponse handleException(Exception exception) {
        return new UserExceptionResponse(
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                System.currentTimeMillis()
        );
    }


}
