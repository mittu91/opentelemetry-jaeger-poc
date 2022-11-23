package com.demo.project1.exception;

public class UserNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 2343485937521737780L;

    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
