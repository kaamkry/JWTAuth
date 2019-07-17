package com.kamkry.app.web.controller.user.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(Integer id) {
        super("There's no user with id:" + id);
    }

    public UserNotFoundException(String message) {
        super("There's no such user: "+message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }
}
