package com.geektrust.backend.exceptions;

public class NoSuchCommandException extends RuntimeException {
    public NoSuchCommandException(String ex) {
        super(ex);
    }

    public NoSuchCommandException() {
        super();
    }
}
