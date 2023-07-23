package com.example.demo.cart.service;

import com.example.demo.account.entity.Account;
import com.example.demo.account.repository.AccountRepository;
import com.example.demo.cart.entity.Cart;
import com.example.demo.cart.entity.CartItem;
import com.example.demo.cart.repository.CartItemRepository;
import com.example.demo.cart.repository.CartRepository;
import com.example.demo.cart.service.response.CartItemResponseForm;
import com.example.demo.product.entity.Product;
import com.example.demo.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final AccountRepository accountRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public List<CartItemResponseForm> cartList(String email){
        Optional<Account> maybeAccount = accountRepository.findByEmail(email);
        if(maybeAccount.isEmpty()) {
            log.info("등록된 사용자가 아닙니다");
            return null;
        }
        Account account  = maybeAccount.get();

        Optional<Cart> maybeCart = cartRepository.findByAccount(account);
        if (maybeCart.isEmpty()) {
            log.info("카트를 찾을 수 없습니다.");
            return null;
        }

        Cart cart = maybeCart.get();
        List<CartItem> cartItemList = cartItemRepository.findAllByCart(cart);
        List<CartItemResponseForm> responseFormList = new ArrayList<>();

        for(CartItem cartItem: cartItemList) {
            CartItemResponseForm responseForm = new CartItemResponseForm(
                    cartItem.getId(),
                    cartItem.getProduct().getProductId(),
                    cartItem.getProduct().getProductName(),
                    cartItem.getCreateDate()
            );
            responseFormList.add(responseForm);
        }
        return responseFormList;
    }
//
//    @Override
//    public void addItemToCart(Long cartId, Long productId, Integer count) {
//        final Optional<Cart> maybeCart = cartRepository.findById(cartId);
//        if (maybeCart.isEmpty()) {
//            log.info("카트를 찾을 수 없습니다.");
//        }
//
//        final Optional<Product> maybeProduct = productRepository.findById(productId);
//        if (maybeProduct.isEmpty()) {
//            log.info("상품을 찾을 수 없습니다.");
//        }
//
//        Cart cart = maybeCart.get();
//        Product product = maybeProduct.get();
//
//        CartItem cartItem = new CartItem();
//        cartItem.setCart(cart);
//        cartItem.setProduct(product);
//        cartItem.setCount(count);
//        cartRepository.save(cart);
//    }
//
//    @Override
//    public void removeItemFromCart(Long cartId, Long cartItemId) {
//        final Optional<Cart> maybeCart = cartRepository.findById(cartId);
//
//        if (maybeCart.isEmpty()) {
//            throw new RuntimeException("카트를 찾을 수 없습니다.");
//        }
//
//        Cart cart = maybeCart.get();
//
//        throw new RuntimeException("카트 항목을 찾을 수 없습니다.");
//
//    }
}