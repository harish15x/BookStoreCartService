package com.example.bookstore.dto;

import lombok.Data;

@Data
public class CartDTO {

    private long quantity;
    private long totalPrice;

}
