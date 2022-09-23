package com.example.bookstore.service;

import com.example.bookstore.dto.CartDTO;
import com.example.bookstore.exception.CartNotFoundException;
import com.example.bookstore.model.CartModel;
import com.example.bookstore.repository.CartRepository;
import com.example.bookstore.util.ResponseClass;
import com.example.bookstore.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public ResponseClass addCart(CartDTO cartDTO) {
        CartModel cartModel = new CartModel(cartDTO);
        cartModel.setQuantity((cartModel.getQuantity()));
        cartModel.setTotalPrice(cartDTO.getTotalPrice());
        cartRepository.save(cartModel);
        return new ResponseClass(200, "Sucessfull", cartModel);
    }

    @Override
    public ResponseClass updateCart(String token, CartDTO cartDTO, long cartId) {
        boolean isBookPresent = restTemplate.getForObject("http://localhost:8095/bookstore/validate/" + token, Boolean.class);
        if (isBookPresent){
            Optional<CartModel> isCartPresent = cartRepository.findById(cartId);
            if (isCartPresent.isPresent()){
                isCartPresent.get().setQuantity(cartDTO.getQuantity());
                isCartPresent.get().setTotalPrice(cartDTO.getTotalPrice());
                cartRepository.save(isCartPresent.get());
                return new ResponseClass(200, "Sucessfull",isCartPresent.get());
            }
            throw new CartNotFoundException(400, "Token is wrong");
        }
        throw new CartNotFoundException(400, "User Not Found");
    }

    @Override
    public ResponseClass removeBookFromCart(long cartId, String token) {
        boolean isBookPresent = restTemplate.getForObject("http://localhost:8095/bookstore/validate/" + token, Boolean.class);
        if (isBookPresent){
            Optional<CartModel> isBookAvailable = cartRepository.findById(cartId);
            if(isBookAvailable.isPresent()){
                cartRepository.delete(isBookAvailable.get());
                return new ResponseClass(200, "Sucessfull",isBookAvailable.get());
            }
            throw new CartNotFoundException(400, "token is wrong");
        }
        throw new CartNotFoundException(400, "User not Found");
    }

    @Override
    public List<CartModel>getAllCartItemsForUser(long cartId, String token) {
        boolean isBookPresent = restTemplate.getForObject("http://localhost:8095/bookstore/validate/" + token, Boolean.class);
        if (isBookPresent){
            List<CartModel> isBookAvailabel = cartRepository.findByUserId(cartId);
            if (isBookAvailabel.size() > 0);
            return isBookAvailabel;
        }
        throw new CartNotFoundException(400, "USer not found");
    }

    @Override
    public List<CartModel> getAllCartItems(String token) {
        boolean isBookPresent = restTemplate.getForObject("http://localhost:8095/bookstore/validate/" + token, Boolean.class);
        if (isBookPresent){
            List<CartModel> isBookAvailabel = cartRepository.findAll();
            if (isBookAvailabel.size() > 0);
            return isBookAvailabel;
        }
        throw new CartNotFoundException(400, "USer not found");
    }


}
