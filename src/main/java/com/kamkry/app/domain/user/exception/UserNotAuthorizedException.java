package com.kamkry.app.domain.user.exception;

public class UserNotAuthorizedException extends RuntimeException {

    public UserNotAuthorizedException() {
        super("User is not authorized.");
    }

    public UserNotAuthorizedException(String user) {
        super("User: " + user + " is not authorized.");
    }

    public UserNotAuthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotAuthorizedException(Throwable cause) {
        super(cause);
    }
}
