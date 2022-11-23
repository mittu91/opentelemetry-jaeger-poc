package com.demo.project1.exception;

public class InvalidCredentialsException extends RuntimeException {
    private static final long serialVersionUID = 2343485937521737780L;

    public InvalidCredentialsException() {
    }

    public InvalidCredentialsException(String message) {
        super(message);
    }
}
