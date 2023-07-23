package com.example.demo.cart.entity;

import com.example.demo.product.entity.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class CartItem {

    @Id
    @Column(name = "cart_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id")
    private Product product;

    private Integer count;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDate;

    public CartItem(Cart cart, Product product, Integer count, LocalDate createDate) {
        this.cart = cart;
        this.product = product;
        this.count = count;
        this.createDate = createDate;
    }
}
