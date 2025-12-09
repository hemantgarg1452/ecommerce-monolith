package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.CartDTO;
import com.ecommerce.backend.exception.CartItemNotFound;
import com.ecommerce.backend.exception.CustomerNotFoundException;
import com.ecommerce.backend.models.Cart;
import com.ecommerce.backend.models.CartItem;
import com.ecommerce.backend.models.Customer;
import com.ecommerce.backend.models.UserSession;
import com.ecommerce.backend.repository.CartDao;
import com.ecommerce.backend.repository.CustomerDao;
import com.ecommerce.backend.repository.ProductDao;
import com.ecommerce.backend.repository.SessionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    private CartDao cartDao;

    @Autowired
    private SessionDao sessionDao;

    @Autowired
    private CartItemService cartItemService;


    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private LoginLogoutService loginService;


    @Autowired
    private ProductDao productDao;

    @Override
    public Cart addProductToCart(CartDTO cartDto, String token) throws LoginException {


        if(!token.contains("customer")) {
            throw new LoginException("Invalid session token for customer");
        }

        loginService.checkTokenStatus(token);

        UserSession user = sessionDao.findByToken(token).get();

        Optional<Customer> opt = customerDao.findById(user.getUserId());

        if(opt.isEmpty())
            throw new CustomerNotFoundException("Customer does not exist");

        Customer existingCustomer = opt.get();

        Cart customerCart = existingCustomer.getCustomerCart();

        List<CartItem> cartItems = customerCart.getCartItems();

        CartItem item = cartItemService.createItemforCart(cartDto);


        if(cartItems.isEmpty()) {
            cartItems.add(item);
            customerCart.setCartTotal(item.getCartProduct().getPrice());
        }
        else {
            boolean flag = false;
            for(CartItem c: cartItems) {
                if(Objects.equals(c.getCartProduct().getProductId(), cartDto.getProductId())) {
                    c.setCartItemQuantity(c.getCartItemQuantity() + 1);
                    customerCart.setCartTotal(customerCart.getCartTotal() + c.getCartProduct().getPrice());
                    flag = true;
                }
            }
            if(!flag) {
                cartItems.add(item);
                customerCart.setCartTotal(customerCart.getCartTotal() + item.getCartProduct().getPrice());
            }
        }

        return cartDao.save(existingCustomer.getCustomerCart());


    }



    @Override
    public Cart getCartProduct(String token) throws LoginException {

        System.out.println("Inside get cart");

        if(!token.contains("customer")) {
            throw new LoginException("Invalid session token for customer");
        }

        loginService.checkTokenStatus(token);

        UserSession user = sessionDao.findByToken(token).get();

        Optional<Customer> opt = customerDao.findById(user.getUserId());


        if(opt.isEmpty())
            throw new CustomerNotFoundException("Customer does not exist");

        Customer existingCustomer = opt.get();

//		System.out.println(existingCustomer);
//
//		System.out.println(existingCustomer.getCustomerCart());
//
//		System.out.println("Here reached");
//
        Integer cartId = existingCustomer.getCustomerCart().getCartId();


        Optional<Cart> optCart= cartDao.findById(cartId);

        if(optCart.isEmpty()) {
            throw new CartItemNotFound("cart Not found by Id");
        }
//		return optCart.get().getProducts();

        return optCart.get();
//		return cart.getProducts();
    }



    @Override
    public Cart removeProductFromCart(CartDTO cartDto, String token) throws LoginException {
        if(!token.contains("customer")) {
            throw new LoginException("Invalid session token for customer");
        }

        loginService.checkTokenStatus(token);

        UserSession user = sessionDao.findByToken(token).get();

        Optional<Customer> opt = customerDao.findById(user.getUserId());

        if(opt.isEmpty())
            throw new CustomerNotFoundException("Customer does not exist");

        Customer existingCustomer = opt.get();

        Cart customerCart = existingCustomer.getCustomerCart();

        List<CartItem> cartItems = customerCart.getCartItems();

        if(cartItems.isEmpty()) {
            throw new CartItemNotFound("Cart is empty");
        }


        boolean flag = false;

        for(CartItem c: cartItems) {
            System.out.println("Item" + c.getCartProduct());
            if(Objects.equals(c.getCartProduct().getProductId(), cartDto.getProductId())) {
                c.setCartItemQuantity(c.getCartItemQuantity() - 1);

                customerCart.setCartTotal(customerCart.getCartTotal() - c.getCartProduct().getPrice());
                if(c.getCartItemQuantity() == 0) {

                    cartItems.remove(c);


                    return cartDao.save(customerCart);
                }
                flag = true;
            }
        }

        if(!flag) {
            throw new CartItemNotFound("Product not added to cart");
        }

        if(cartItems.isEmpty()) {
            cartDao.save(customerCart);
            throw new CartItemNotFound("Cart is empty now");
        }

        return cartDao.save(customerCart);
    }




    // Method to clear entire cart

    @Override
    public Cart clearCart(String token) throws LoginException {

        if(!token.contains("customer")) {
            throw new LoginException("Invalid session token for customer");
        }

        loginService.checkTokenStatus(token);

        UserSession user = sessionDao.findByToken(token).get();

        Optional<Customer> opt = customerDao.findById(user.getUserId());

        if(opt.isEmpty())
            throw new CustomerNotFoundException("Customer does not exist");

        Customer existingCustomer = opt.get();

        Cart customerCart = existingCustomer.getCustomerCart();

        if(customerCart.getCartItems().isEmpty()) {
            throw new CartItemNotFound("Cart already empty");
        }

        List<CartItem> emptyCart = new ArrayList<>();

        customerCart.setCartItems(emptyCart);

        customerCart.setCartTotal(0.0);

        return cartDao.save(customerCart);
    }
}
