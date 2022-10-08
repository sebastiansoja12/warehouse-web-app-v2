package com.warehouse.reroute.domain.exception;

public class ParcelNotFoundException extends RuntimeException {
    public ParcelNotFoundException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public ParcelNotFoundException(String exMessage) {
        super(exMessage);
    }
}