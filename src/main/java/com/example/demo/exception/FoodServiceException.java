package com.example.demo.exception;


public class FoodServiceException extends RuntimeException{
    public FoodServiceException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
