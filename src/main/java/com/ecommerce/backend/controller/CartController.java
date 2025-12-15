package com.ecommerce.backend.controller;

import com.ecommerce.backend.dto.CartDTO;
import com.ecommerce.backend.models.Cart;
import com.ecommerce.backend.repository.CartDao;
import com.ecommerce.backend.repository.CustomerDao;
import com.ecommerce.backend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;

@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private CartDao cartDao;

    @Autowired
    private CustomerDao customerDao;


    @PostMapping(value = "/cart/add")
    public ResponseEntity<Cart> addProductToCartHander(@RequestBody CartDTO cartdto , @RequestHeader("token")String token) throws LoginException {

        Cart cart = cartService.addProductToCart(cartdto, token);
        return new ResponseEntity<Cart>(cart, HttpStatus.CREATED);
    }

    //
    @GetMapping(value = "/cart")
    public ResponseEntity<Cart> getCartProductHandler(@RequestHeader("token")String token) throws LoginException {
        return new ResponseEntity<>(cartService.getCartProduct(token), HttpStatus.ACCEPTED);
    }


    @DeleteMapping(value = "/cart")
    public ResponseEntity<Cart> removeProductFromCartHander(@RequestBody CartDTO cartdto ,@RequestHeader("token")String token) throws LoginException {

        Cart cart = cartService.removeProductFromCart(cartdto, token);
        return new ResponseEntity<Cart>(cart,HttpStatus.OK);
    }


    @DeleteMapping(value = "/cart/clear")
    public ResponseEntity<Cart> clearCartHandler(@RequestHeader("token") String token) throws LoginException {
        return new ResponseEntity<>(cartService.clearCart(token), HttpStatus.ACCEPTED);
    }
}
