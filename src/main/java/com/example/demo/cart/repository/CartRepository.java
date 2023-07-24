package com.example.demo.cart.repository;

import com.example.demo.account.entity.Account;
import com.example.demo.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query("SELECT c FROM Cart c JOIN FETCH c.account m WHERE c.account = :account")
    Optional<Cart> findByAccount(Account account);
}