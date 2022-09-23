package com.example.bookstore.model;

import com.example.bookstore.dto.CartDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cartservice")
public class CartModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private long userId;
    private long bookId;
    private long quantity;
    private long totalPrice;

    public CartModel(CartDTO cartDTO) {
        this.quantity = cartDTO.getQuantity();
    }


    public CartModel() {

    }
}
