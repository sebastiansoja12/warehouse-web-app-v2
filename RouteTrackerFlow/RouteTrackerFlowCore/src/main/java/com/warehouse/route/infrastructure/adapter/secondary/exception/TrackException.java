package com.warehouse.route.infrastructure.adapter.secondary.exception;

public class TrackException extends RuntimeException {
    public TrackException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public TrackException(String exMessage) {
        super(exMessage);
    }
}
