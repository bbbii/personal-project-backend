package com.example.demo.cart.service;

import com.example.demo.cart.controller.form.CartRequestForm;
import com.example.demo.cart.service.response.CartItemResponseForm;

import java.util.List;

public interface CartService {

    List<CartItemResponseForm> getList(String email);

    List<CartItemResponseForm> addItem(CartRequestForm requestForm);

    List<CartItemResponseForm> delete(String email, Long id);

    List<CartItemResponseForm> deleteList(String email, List<Long> idList);

    List<CartItemResponseForm> updateCartItemProductCount(String email, Long productId, Integer dCount);
}