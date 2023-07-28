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
    final private String productOrigin;
    final private String productProducer;
    final private Integer productAmount;
    final private String productAmountUnit;
    final private Integer productWeight;
    final private String productWeightUnit;
    final private String startDate;
    final private String endDate;
    final private String productDescription;

    final private String mainImageName;
    final private List<String> imageNameList;

    public Product toProduct(){
        return new Product(productName, productPrice, productOrigin, productProducer, productAmount,
                productAmountUnit, productWeight, productWeightUnit, startDate, endDate,
                productDescription, mainImageName, imageNameList);
    }
}