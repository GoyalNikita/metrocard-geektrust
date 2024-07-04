package com.geektrust.backend.exceptions;

public class PassengerNotFoundException extends RuntimeException {
    public PassengerNotFoundException(String ex) {
        super(ex);
    }

    public PassengerNotFoundException() {
        super();
    }
}
