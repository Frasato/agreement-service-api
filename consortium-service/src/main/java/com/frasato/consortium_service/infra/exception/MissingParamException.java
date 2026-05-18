package com.frasato.consortium_service.infra.exception;

public class MissingParamException extends RuntimeException {
    public MissingParamException(String param) {
        super(param + " can't be empty");
    }
}
