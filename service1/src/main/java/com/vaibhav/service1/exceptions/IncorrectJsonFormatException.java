package com.vaibhav.service1.exceptions;

public class IncorrectJsonFormatException extends RuntimeException {
    private String message;
    private String details;
    private String hint;

    protected IncorrectJsonFormatException() {
    }

    public IncorrectJsonFormatException(
            String message, String details, String hint) {
        this.message = message;
        this.details = details;
        this.hint = hint;
    }

    @Override
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