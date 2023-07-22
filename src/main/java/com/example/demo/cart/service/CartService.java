package com.example.demo.cart.service;

import com.example.demo.cart.entity.Cart;

public interface CartService {
    Cart createCart(Long accountId);

    void addItemToCart(Long cartId, Long productId, Integer count);
}