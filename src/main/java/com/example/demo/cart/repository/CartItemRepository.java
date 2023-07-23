package com.example.demo.cart.repository;

import com.example.demo.cart.entity.Cart;
import com.example.demo.cart.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    @Query("SELECT ci FROM CartItem ci " + "JOIN FETCH ci.cart " +
            "JOIN FETCH ci.product " + "WHERE ci.cart = :cart")
    List<CartItem> findAllByCart(Cart cart);
}
