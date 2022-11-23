package com.demo.project1.exception;

public class InvalidSessionException extends RuntimeException {
    private static final long serialVersionUID = 2343485937521737780L;

    public InvalidSessionException() {
    }

    public InvalidSessionException(String message) {
        super(message);
    }
}
