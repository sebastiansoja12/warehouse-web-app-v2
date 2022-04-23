package com.warehouse.warehouse.exceptions;

public class ParcelNotFound extends RuntimeException {
    public ParcelNotFound(String message) {
        super(message);
    }
}
