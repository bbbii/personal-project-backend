package com.example.demo.cart.service.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CartItemResponseForm {
    final private Long id;
    final private Long productId;
    final private String productName;
    final private Integer productPrice;
    final private Integer productCount;
    final private String mainImageName;
}
