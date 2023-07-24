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
    public List<CartItemResponseForm> deleteCartItem(@RequestParam("email") String email, @PathVariable("id") Long id) {
        return cartService.delete(email, id);
    }

    @DeleteMapping
    public List<CartItemResponseForm> deleteCartItemList(@RequestParam("email") String email,
                                                         @RequestParam("arr") List<Long> idArr) {
        return cartService.deleteList(email, idArr);
    }

//    @PutMapping("/update")
//    public List<CartItemResponseForm> updateCartItemProductCount(String email, Long productId, Integer dCount) {
//        List<CartItemResponseForm> responseFormList = cartService.updateCartItemProductCount(email, productId, dCount);
//
//        if(responseFormList == null) {
//            return ResponseEntity.badRequest().build();
//        }
//
//        return ResponseEntity.ok(responseFormList);
//    }
}