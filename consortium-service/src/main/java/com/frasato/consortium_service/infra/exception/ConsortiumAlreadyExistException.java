package com.frasato.consortium_service.infra.exception;

public class ConsortiumAlreadyExistException extends RuntimeException {
    public ConsortiumAlreadyExistException() {
        super("Consortium already exist");
    }
}
