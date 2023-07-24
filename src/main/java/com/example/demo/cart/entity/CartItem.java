package com.example.demo.cart.entity;

import com.example.demo.product.entity.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CartItem {

    @Id
    @Column(name = "cart_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer itemCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public CartItem(Integer itemCount, Cart cart, Product product) {
        this.itemCount = itemCount;
        this.cart = cart;
        this.product = product;
    }

    public void updateItemCount(Integer newCount) {
        this.itemCount = newCount;
    }
}