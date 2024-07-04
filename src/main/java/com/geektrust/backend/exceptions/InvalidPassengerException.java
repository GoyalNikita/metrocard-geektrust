package com.geektrust.backend.exceptions;

public class InvalidPassengerException extends RuntimeException {
    public InvalidPassengerException(String ex) {
        super(ex);
    }

    public InvalidPassengerException() {
        super();
    }
}
