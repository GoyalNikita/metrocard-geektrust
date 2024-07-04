package com.geektrust.backend.exceptions;

public class InvalidAmountException extends RuntimeException {
    public InvalidAmountException(String ex) {
        super(ex);
    }

    public InvalidAmountException() {
        super();
    }
}
