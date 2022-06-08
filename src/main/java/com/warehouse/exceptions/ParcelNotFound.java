package com.warehouse.exceptions;

public class ParcelNotFound extends RuntimeException {
    public ParcelNotFound(String message) {
        super(message);
    }
}
