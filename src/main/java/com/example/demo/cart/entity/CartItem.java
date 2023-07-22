package com.example.demo.cart.entity;

import com.example.demo.product.entity.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id")
    private Product product;

    private Integer count;

    public CartItem(Cart cart, Product product, Integer count) {
        this.cart = cart;
        this.product = product;
        this.count = count;
    }

    public void addCount(Integer count) {
        this.count += count;
    }
}
