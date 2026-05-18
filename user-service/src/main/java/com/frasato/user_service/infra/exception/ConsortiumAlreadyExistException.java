package com.frasato.user_service.infra.exception;

public class ConsortiumAlreadyExistException extends RuntimeException {
    public ConsortiumAlreadyExistException(String userId) {
        super("Consortium already exist in user: " + userId);
    }
}
