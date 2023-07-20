package com.example.demo.product.controller.form;

import com.example.demo.product.entity.Product;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ProductRequestForm {

    final private String productName;
    final private Integer productPrice;
    final private String productDescription;
    final private String productTags;
    final private String receivedEmail;
    final private String mainImageName;
    final private List<String> imageNameList;

    public Product toProduct(){
        return new Product(productName, productPrice,
                           productDescription ,productTags,
                           receivedEmail, mainImageName, imageNameList);
    }
}