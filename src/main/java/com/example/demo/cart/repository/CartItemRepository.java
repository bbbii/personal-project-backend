package com.example.demo.cart.repository;

import com.example.demo.cart.entity.Cart;
import com.example.demo.cart.entity.CartItem;
import com.example.demo.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    @Query("SELECT ci FROM CartItem ci " + "JOIN FETCH ci.cart " +
            "JOIN FETCH ci.product " + "WHERE ci.cart = :cart")
    List<CartItem> findAllByCart(Cart cart);

    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);
}