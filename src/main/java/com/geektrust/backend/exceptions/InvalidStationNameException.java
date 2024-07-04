package com.geektrust.backend.exceptions;

public class InvalidStationNameException extends RuntimeException {
    public InvalidStationNameException(String ex) {
        super(ex);
    }

    public InvalidStationNameException() {
        super();
    }
}
