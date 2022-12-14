package com.example.bookstore.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class ResponseClass {

    private int statusCode;
    private String statusMessage;
    private Object object;

    public ResponseClass() {

    }
}