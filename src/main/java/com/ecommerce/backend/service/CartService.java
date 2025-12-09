package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.CartDTO;
import com.ecommerce.backend.models.Cart;

import javax.security.auth.login.LoginException;

public interface CartService {
    public Cart addProductToCart(CartDTO cart, String token) throws CartItemNotFound, LoginException;
    public Cart getCartProduct(String token) throws LoginException;
    public Cart removeProductFromCart(CartDTO cartDto, String token) throws ProductNotFound, LoginException;
//	public Cart changeQuantity(Product product,Customer customer,Integer quantity);

    public Cart clearCart(String token) throws LoginException;
}
