package com.example.sileo.exeception;

public class APIError {

    private String message;

    public APIError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
