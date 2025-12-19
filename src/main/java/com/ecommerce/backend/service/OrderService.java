package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.OrderDTO;
import com.ecommerce.backend.exception.LoginException;
import com.ecommerce.backend.exception.OrderException;
import com.ecommerce.backend.models.Customer;
import com.ecommerce.backend.models.Order;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    public Order saveOrder(OrderDTO odto, String token) throws LoginException, OrderException, javax.security.auth.login.LoginException;

    public Order getOrderByOrderId(Integer OrderId) throws OrderException;

    public List<Order> getAllOrders() throws OrderException;

    public Order cancelOrderByOrderId(Integer OrderId,String token) throws OrderException;

    public Order updateOrderByOrder(OrderDTO order,Integer OrderId,String token) throws OrderException, LoginException, javax.security.auth.login.LoginException;

    public List<Order> getAllOrdersByDate(LocalDate date) throws OrderException;

    public Customer getCustomerByOrderid(Integer orderId) throws OrderException;

    //public Customer getCustomerIdByToken(String token) throws CustomerNotFoundException;

}
