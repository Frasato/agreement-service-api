package com.frasato.consortium_service.infra.exception;

public class ConsortiumNotFoundException extends RuntimeException {
    public ConsortiumNotFoundException(String id) {
        super("Consortium not found on ID: " + id);
    }
}
