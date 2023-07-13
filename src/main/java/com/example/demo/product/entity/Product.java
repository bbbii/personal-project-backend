package com.example.demo.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Setter
    private String productName;

    @Setter
    private Integer productPrice;

    @Setter
    private String productDescription;

    @Setter
    private String productTags;

    private String email;

    public Product(String productName, Integer productPrice, String productDescription, String productTags, String email) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
        this.productTags = productTags;
        this.email = email;
    }
}