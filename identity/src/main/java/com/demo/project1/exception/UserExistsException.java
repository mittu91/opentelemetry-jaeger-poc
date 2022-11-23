package com.demo.project1.exception;

public class UserExistsException extends RuntimeException {
    private static final long serialVersionUID = 2343485937521737780L;

    public UserExistsException() {
    }

    public UserExistsException(String message) {
        super(message);
    }
}
