package com.example.bookstore.service;

import com.example.bookstore.dto.CartDTO;
import com.example.bookstore.model.CartModel;
import com.example.bookstore.util.ResponseClass;

import java.util.List;

public interface ICartService {

    ResponseClass addCart(CartDTO cartDTO);

    ResponseClass updateCart(String token, CartDTO cartDTO, long bookId);

    ResponseClass removeBookFromCart(long cartId, String token);

    List<CartModel> getAllCartItemsForUser(long cartId, String token);

    List<CartModel> getAllCartItems(String token);
}
