package com.frasato.user_service.infra.exception;

public class AuthenticationFailedException extends RuntimeException {
    public AuthenticationFailedException() {
        super("Document or Password was wrong");
    }
}
