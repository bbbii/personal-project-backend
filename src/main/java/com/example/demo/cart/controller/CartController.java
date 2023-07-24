package com.example.demo.cart.controller;

import com.example.demo.cart.controller.form.CartRequestForm;
import com.example.demo.cart.service.CartService;
import com.example.demo.cart.service.response.CartItemResponseForm;
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
    public List<CartItemResponseForm> getList(@RequestParam String email) {
        return cartService.getList(email);
    }

    @PostMapping("/add-item")
    public List<CartItemResponseForm> addItem(@RequestBody CartRequestForm requestForm) {
        return cartService.addItem(requestForm);
    }

    @DeleteMapping("/{id}")
    public List<CartItemResponseForm> deleteCartItem(@RequestParam String email, @PathVariable("id") Long id) {
        return cartService.delete(email, id);
    }

    @DeleteMapping
    public List<CartItemResponseForm> deleteCartItemList(String email, @RequestParam("idList") List<Long> idList) {
        return cartService.deleteList(email, idList);
    }
}