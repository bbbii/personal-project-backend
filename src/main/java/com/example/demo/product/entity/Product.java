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

    private String productOrigin;

    private String productProducer;

    private Integer productAmount;

    private String productAmountUnit;

    private Integer productWeight;

    private String productWeightUnit;

    private String startDate;

    private String endDate;

    private String productDescription;

    private String mainImageName;

    private List<String> imageNameList;

    public Product(String productName, Integer productPrice,
                   String productOrigin, String productProducer,
                   Integer productAmount, String productAmountUnit,
                   Integer productWeight, String productWeightUnit,
                   String startDate, String endDate, String productDescription,
                   String mainImageName, List<String> imageNameList) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productOrigin = productOrigin;
        this.productProducer = productProducer;
        this.productAmount = productAmount;
        this.productAmountUnit = productAmountUnit;
        this.productWeight = productWeight;
        this.productWeightUnit = productWeightUnit;
        this.startDate = startDate;
        this.endDate = endDate;
        this.productDescription = productDescription;
        this.mainImageName = mainImageName;
        this.imageNameList = imageNameList;
    }
}