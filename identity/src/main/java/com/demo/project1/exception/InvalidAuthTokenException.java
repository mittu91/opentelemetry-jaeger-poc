package com.demo.project1.exception;

public class InvalidAuthTokenException extends RuntimeException {

    private static final long serialVersionUID = 5980004451519341801L;

    public InvalidAuthTokenException() {
    }

    public InvalidAuthTokenException(String message) {
        super(message);
    }
}
