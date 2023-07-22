package com.example.demo.cart.service;

import com.example.demo.account.entity.Account;
import com.example.demo.account.repository.AccountRepository;
import com.example.demo.cart.entity.Cart;
import com.example.demo.cart.entity.CartItem;
import com.example.demo.cart.repository.CartRepository;
import com.example.demo.product.entity.Product;
import com.example.demo.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final AccountRepository accountRepository;

    @Override
    public Cart createCart(Long accountId) {
        final Optional<Account> maybeAccount = accountRepository.findById(accountId);
        if (maybeAccount.isEmpty()) {
            log.info("계정을 찾을 수 없습니다.");
            return null;
        }

        Account account = maybeAccount.get();
        Cart cart = new Cart();
        cart.setAccount(account);
        cart.setCreateDate(LocalDate.now());
        return cartRepository.save(cart);
    }

    @Override
    public void addItemToCart(Long cartId, Long productId, Integer count) {
        final Optional<Cart> maybeCart = cartRepository.findById(cartId);
        if (maybeCart.isEmpty()) {
            log.info("카트를 찾을 수 없습니다.");
        }

        final Optional<Product> maybeProduct = productRepository.findById(productId);
        if (maybeProduct.isEmpty()) {
            log.info("상품을 찾을 수 없습니다.");
        }

        Cart cart = maybeCart.get();
        Product product = maybeProduct.get();

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setCount(count);
        cart.addCartItem(cartItem);
        cartRepository.save(cart);
    }
}