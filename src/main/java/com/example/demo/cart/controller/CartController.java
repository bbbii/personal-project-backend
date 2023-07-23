package com.example.demo.cart.controller;

import com.example.demo.cart.controller.form.CartRequestForm;
import com.example.demo.cart.entity.Cart;
import com.example.demo.cart.service.CartService;
import com.example.demo.cart.service.response.CartItemResponseForm;
import com.example.demo.product.entity.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    final private CartService cartService;

    @GetMapping("/list")
    public List<CartItemResponseForm> getCartList(@RequestParam String email) {
        return cartService.cartList(email);
    }

//    @PostMapping("/addItem")
//    public void addItemToCart(@PathVariable Long cartId, @RequestBody CartRequestForm cartRequestForm) {
//        cartService.addItemToCart(cartId, cartRequestForm.getProductId(), cartRequestForm.getCount());
//    }
//
//    @DeleteMapping("/{cartId}/items/{cartItemId}")
//    public void removeItemFromCart(@PathVariable Long cartId, @PathVariable Long cartItemId) {
//        cartService.removeItemFromCart(cartId, cartItemId);
//    }
}