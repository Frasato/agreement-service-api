package com.frasato.user_service.infra.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String id) {
        super("User not found on ID: " + id);
    }
}
