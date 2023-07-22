package com.example.demo.cart.controller.form;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CartRequestForm {

    final private Long accountId;

    final private Long productId;

    final private Integer count;
}