package com.example.demo.cart.entity;

import com.example.demo.account.entity.Account;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
public class Cart {

    @Id
    @Getter
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private int count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account")
    @Setter
    private Account account;
}