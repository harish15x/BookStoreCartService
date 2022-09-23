package com.example.bookstore.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class CartNotFoundException extends RuntimeException{
    private int statusCode;
    private String statusMessage;

    public CartNotFoundException(int statusCode, String statusMessage) {
        super(statusMessage);
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }
}
