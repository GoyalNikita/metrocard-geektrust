package com.geektrust.backend.exceptions;

public class StationNotFoundException extends RuntimeException {
    public StationNotFoundException(String ex) {
        super(ex);
    }

    public StationNotFoundException() {
        super();
    }
}
