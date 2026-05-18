package com.frasato.user_service.infra.exception;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String document) {
        super("User already registered with document: " + document);
    }
}
