package com.example.demo.cart.service.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class CartItemResponseForm {
    final private Long id;
    final private Long productId;
    final private String productName;
    final private LocalDate createDate;
}
