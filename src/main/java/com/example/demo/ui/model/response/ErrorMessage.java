package com.example.demo.ui.model.response;

public enum ErrorMessage {

    MISSING_REQUIRED_FIELD("Missing required field , Please check documentation"),
    RECORD_ALREADY_EXISTS("Record already exists"),
    INTERNAL_SERVER_ERROR("Internal server error"),
    NO_RECORD_FOUND("Record with provided id is not found"),
    AUTHENTICATION_FAILED("Authentication failed"),
    COULD_NOT_UPDATE_RECORD("Could not update record"),
    COULD_NOT_DELETE_RECORD("Could not delete record"),
    EMAIL_ADDRESS_NOT_VERIFIED("Email address could not verified"),
    ;


    private String errorMessage;
    ErrorMessage(String errorMessage) {
        this.errorMessage =errorMessage;
    }


    public String getErrorMessage(){
        return this.errorMessage;
    }
    public void setErrorMessage(String errorMessage){
        this.errorMessage =errorMessage;
    }
}
