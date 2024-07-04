package com.geektrust.backend.exceptions;

public class MetroCardNotFoundException extends RuntimeException {
    public MetroCardNotFoundException(String ex) {
        super(ex);
    }

    public MetroCardNotFoundException() {
        super();
    }
}
