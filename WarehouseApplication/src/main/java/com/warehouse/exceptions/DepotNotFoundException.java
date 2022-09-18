package com.warehouse.exceptions;

public class DepotNotFoundException extends RuntimeException {
    public DepotNotFoundException(String message) {
        super(message);
    }
}
