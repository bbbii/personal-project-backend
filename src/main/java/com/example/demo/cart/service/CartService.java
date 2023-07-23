package com.example.demo.cart.service;

import com.example.demo.cart.service.response.CartItemResponseForm;

import java.util.List;

public interface CartService {
    List<CartItemResponseForm> cartList(String email);

//    void addItemToCart(Long cartId, Long productId, Integer count);
//
//    void removeItemFromCart(Long cartId, Long cartItemId);
}