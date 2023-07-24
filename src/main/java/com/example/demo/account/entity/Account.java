package com.example.demo.account.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity
@ToString
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(unique = true)
    private String email;

    private String password;

    @OneToOne(mappedBy = "account", fetch = FetchType.LAZY)
    private AccountRole accountRole;

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void setAccountRole(AccountRole accountRole) {
        this.accountRole = accountRole;

        accountRole.setAccount(this);
    }
}