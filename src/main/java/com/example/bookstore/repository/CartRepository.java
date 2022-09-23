package com.example.bookstore.repository;

import com.example.bookstore.model.CartModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<CartModel, Long> {
    List<CartModel> findByUserId(long cartId);
}
