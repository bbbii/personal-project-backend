package com.example.demo.cart.service;

import com.example.demo.account.entity.Account;
import com.example.demo.account.repository.AccountRepository;
import com.example.demo.cart.controller.form.CartRequestForm;
import com.example.demo.cart.entity.Cart;
import com.example.demo.cart.entity.CartItem;
import com.example.demo.cart.repository.CartItemRepository;
import com.example.demo.cart.repository.CartRepository;
import com.example.demo.cart.service.response.CartItemResponseForm;
import com.example.demo.product.entity.Product;
import com.example.demo.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    final private AccountRepository accountRepository;
    final private ProductRepository productRepository;
    final private CartRepository cartRepository;
    final private CartItemRepository cartItemRepository;

    @Override
    @Transactional
    public List<CartItemResponseForm> getList(String email) {

        Optional<Account> maybeAccount = accountRepository.findByEmail(email);
        if(maybeAccount.isEmpty()) {
            log.info("사용자 확인 불가");
            return null;
        }
        Account account = maybeAccount.get();

        Optional<Cart> maybeCart = cartRepository.findByAccount(account);
        if(maybeCart.isEmpty()) {
            log.info("카트가 없습니다.");
            return null;
        }

        Cart cart = maybeCart.get();
        List<CartItem> cartItemList = cartItemRepository.findAllByCart(cart);
        List<CartItemResponseForm> responseFormList = new ArrayList<>();

        for (CartItem item: cartItemList) {
            CartItemResponseForm responseForm = new CartItemResponseForm(
                    item.getId(), item.getProduct().getProductId(),
                    item.getProduct().getProductName(), item.getProduct().getProductPrice(),
                    item.getItemCount(), item.getProduct().getMainImageName()
            );
            responseFormList.add(responseForm);
        }
        return responseFormList;
    }

    @Override
    @Transactional
    public List<CartItemResponseForm> addItem(CartRequestForm requestForm) {
        Optional<Account> maybeAccount = accountRepository.findByEmail(requestForm.getEmail());
        if (maybeAccount.isEmpty()) {
            log.info("사용자를 찾을 수 없습니다");
            return null;
        }
        Optional<Product> maybeProduct = productRepository.findById(requestForm.getProductId());
        if (maybeProduct.isEmpty()) {
            log.info("상품을 찾을 수 없습니다");
            return null;
        }
        Account account = maybeAccount.get();
        Product product = maybeProduct.get();

        Optional<Cart> maybeCart = cartRepository.findByAccount(account);

        if (maybeCart.isEmpty()) {
            Cart cart = new Cart();
            List<CartItem> cartItemList = new ArrayList<>();
            CartItem cartItem = new CartItem(requestForm.getProductCount(), cart, product);

            cartItemList.add(cartItem);

            cart.setCount(cartItemList.size());
            cart.setAccount(account);
            cartRepository.save(cart);
            cartItemRepository.save(cartItem);

            List<CartItemResponseForm> responseFormList = new ArrayList<>();

            for (CartItem item : cartItemList) {
                CartItemResponseForm responseForm = new CartItemResponseForm(
                        item.getId(), item.getProduct().getProductId(),
                        item.getProduct().getProductName(), item.getProduct().getProductPrice(),
                        item.getItemCount(), item.getProduct().getMainImageName()
                );
                responseFormList.add(responseForm);
            }

            return responseFormList;
        }

        Cart cart = maybeCart.get();
        List<CartItem> cartItemList = cartItemRepository.findAllByCart(cart);

        Optional<CartItem> maybeCartItem = cartItemRepository.findByCartAndProduct(cart, product);

        if (maybeCartItem.isPresent()) {
            CartItem cartItem = maybeCartItem.get();
            int updatedItemCount = cartItem.getItemCount() + requestForm.getProductCount();
            cartItem.updateItemCount(updatedItemCount);
            cartItemRepository.save(cartItem);
        } else {
            CartItem cartItem = new CartItem(requestForm.getProductCount(), cart, product);
            cartItemList.add(cartItem);
            cartItemRepository.save(cartItem);
        }

        cart.setCount(cartItemList.size());
        cart.setAccount(account);
        cartRepository.save(cart);

        List<CartItemResponseForm> responseFormList = new ArrayList<>();

        for (CartItem item : cartItemList) {
            CartItemResponseForm responseForm = new CartItemResponseForm(
                    item.getId(), item.getProduct().getProductId(),
                    item.getProduct().getProductName(), item.getProduct().getProductPrice(),
                    item.getItemCount(), item.getProduct().getMainImageName()
            );
            responseFormList.add(responseForm);
        }

        return responseFormList;
    }

    @Override
    @Transactional
    public List<CartItemResponseForm> delete(String email, Long id) {

        Optional<Account> maybeAccount = accountRepository.findByEmail(email);
        if (maybeAccount.isEmpty()) {
            log.info("사용자 확인 불가");
            return null;
        }
        Account account = maybeAccount.get();
        Optional<Cart> maybeCart = cartRepository.findByAccount(account);
        if (maybeCart.isEmpty()) {
            log.info("카트가 없습니다.");
            return null;
        }
        Cart cart = maybeCart.get();
        List<CartItem> maybeCartItemList = cartItemRepository.findAllByCart(cart);
        for (CartItem cartItem: maybeCartItemList) {
            if (cartItem.getId().equals(id)) {
                cartItemRepository.deleteById(id);
                cart.setCount(cart.getCount() - 1);
            }
        }
        cartRepository.save(cart);

        List<CartItem> cartItemList = cartItemRepository.findAllByCart(cart);
        List<CartItemResponseForm> responseFormList = new ArrayList<>();

        for (CartItem item: cartItemList) {
            CartItemResponseForm responseForm = new CartItemResponseForm(
                    item.getId(), item.getProduct().getProductId(),
                    item.getProduct().getProductName(), item.getProduct().getProductPrice(),
                    item.getItemCount(), item.getProduct().getMainImageName()
            );
            responseFormList.add(responseForm);
        }
        return responseFormList;
    }

    @Override
    @Transactional
    public List<CartItemResponseForm> deleteList(String email, List<Long> idList) {
        Optional<Account> maybeAccount = accountRepository.findByEmail(email);
        if(maybeAccount.isEmpty()) {
            log.info("사용자 확인 불가");
            return null;
        }
        Account account = maybeAccount.get();
        Optional<Cart> maybeCart = cartRepository.findByAccount(account);
        if(maybeCart.isEmpty()) {
            log.info("카트가 없습니다.");
            return null;
        }

        Cart cart = maybeCart.get();

        List<CartItem> maybeCartItemList = cartItemRepository.findAllByCart(cart);

        for(CartItem cartItem: maybeCartItemList) {
            for(int i = 0; i < idList.size(); i++ ){
                if(cartItem.getId().equals(idList.get(i))) {
                    cartItemRepository.deleteById(idList.get(i));
                    cart.setCount(cart.getCount() - 1);
                }
            }
        }
        log.info("삭제 완료");
        cartRepository.save(cart);

        List<CartItem> cartItemList = cartItemRepository.findAllByCart(cart);
        List<CartItemResponseForm> responseFormList = new ArrayList<>();

        for (CartItem item: cartItemList) {
            CartItemResponseForm responseForm = new CartItemResponseForm(
                    item.getId(), item.getProduct().getProductId(),
                    item.getProduct().getProductName(), item.getProduct().getProductPrice(),
                    item.getItemCount(), item.getProduct().getMainImageName()
            );
            responseFormList.add(responseForm);
        }
        return responseFormList;
    }
}