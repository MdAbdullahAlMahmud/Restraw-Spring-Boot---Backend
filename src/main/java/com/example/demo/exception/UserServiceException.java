package com.example.demo.exception;

public class UserServiceException extends RuntimeException{

    public UserServiceException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
