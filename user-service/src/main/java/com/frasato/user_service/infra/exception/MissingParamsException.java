package com.frasato.user_service.infra.exception;

public class MissingParamsException extends RuntimeException {
    public MissingParamsException(String param) {
        super(param + " can't be empty!");
    }
}
