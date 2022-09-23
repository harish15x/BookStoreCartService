package com.example.bookstore.controller;

import com.example.bookstore.dto.CartDTO;
import com.example.bookstore.model.CartModel;
import com.example.bookstore.service.ICartService;
import com.example.bookstore.util.ResponseClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bookservice")
public class CartController {

    @Autowired
    ICartService cartService;

    @PostMapping("/addcart")
    public ResponseEntity<ResponseClass> addCart(@RequestBody CartDTO cartDTO){
        ResponseClass responseClass = cartService.addCart(cartDTO);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }

    @PutMapping("updatecart/{bookId}")
    public ResponseEntity<ResponseClass> updateCart(@RequestHeader String token, @RequestBody CartDTO cartDTO, @PathVariable long bookId){
        ResponseClass responseClass = cartService.updateCart(token, cartDTO, bookId);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }

    @DeleteMapping("removebookfromcart")
    public ResponseEntity<ResponseClass> removeBookFromCart(@PathVariable long cartId, @RequestHeader String token){
        ResponseClass responseClass = cartService.removeBookFromCart(cartId, token);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }

    @GetMapping("getAllCartItemsForUser")
    public ResponseEntity<List<?>> getAllCartItemsForUser(@PathVariable long cartId, @RequestHeader String token){
        List<CartModel> responseClass = cartService.getAllCartItemsForUser(cartId, token);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }

    @GetMapping("/getallcartitems")
    public ResponseEntity<List<?>> getAllCartItems(@RequestHeader String token) {
        List<CartModel> responseClass = cartService.getAllCartItems(token);
        return new ResponseEntity<>(responseClass,HttpStatus.OK);
    }




}
