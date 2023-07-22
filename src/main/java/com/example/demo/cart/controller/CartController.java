package com.example.demo.cart.controller;

import com.example.demo.cart.controller.form.CartRequestForm;
import com.example.demo.cart.entity.Cart;
import com.example.demo.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    final private CartService cartService;

    @PostMapping
    public Cart createCart(@RequestBody CartRequestForm cartRequestForm) {
        return cartService.createCart(cartRequestForm.getAccountId());
    }

    @PostMapping("/addItem")
    public void addItemToCart(@PathVariable Long cartId, @RequestBody CartRequestForm cartRequestForm) {
        cartService.addItemToCart(cartId, cartRequestForm.getProductId(), cartRequestForm.getCount());
    }
}