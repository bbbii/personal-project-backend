package com.example.demo.product.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productName;

    private Integer productPrice;

    private String productDescription;

    private String productTags;

    private String registerEmail;

    private String mainImageName;

    private List<String> imageNameList;

    public Product(String productName, Integer productPrice,
                   String productDescription, String productTags,
                   String registerEmail, String mainImageName,
                   List<String> imageNameList) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
        this.productTags = productTags;
        this.registerEmail = registerEmail;
        this.mainImageName = mainImageName;
        this.imageNameList = imageNameList;
    }
}