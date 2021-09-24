package com.vaibhav.service1.exceptions;

public class CustomExceptionSchema {

    private String message;
    private String details;
    private String hint;


    public CustomExceptionSchema() {
    }

    public CustomExceptionSchema(String message, String details, String hint) {
        this.message = message;
        this.details = details;
        this.hint = hint;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }
}