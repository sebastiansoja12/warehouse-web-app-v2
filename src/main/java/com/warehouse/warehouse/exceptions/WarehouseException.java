package com.warehouse.warehouse.exceptions;

public class WarehouseException extends RuntimeException {
    public WarehouseException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public WarehouseException(String exMessage) {
        super(exMessage);
    }
}
