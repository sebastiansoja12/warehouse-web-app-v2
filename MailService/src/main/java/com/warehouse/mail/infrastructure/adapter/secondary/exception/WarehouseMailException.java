package com.warehouse.mail.infrastructure.adapter.secondary.exception;

public class WarehouseMailException extends RuntimeException {
    public WarehouseMailException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public WarehouseMailException(String exMessage) {
        super(exMessage);
    }
}
