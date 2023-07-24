package com.example.demo.cart.controller.form;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CartRequestForm {

    final private String email;

    final private Long productId;

    final private Integer productCount;
}