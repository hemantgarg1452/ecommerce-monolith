package com.ecommerce.backend.controller;

import com.ecommerce.backend.dto.OrderDTO;
import com.ecommerce.backend.models.Customer;
import com.ecommerce.backend.models.Order;
import com.ecommerce.backend.repository.OrderDao;
import com.ecommerce.backend.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderDao oDao;

    @Autowired
    private OrderService oService;

    @PostMapping("/order/place")
    public ResponseEntity<Order> addTheNewOrder(@Valid @RequestBody OrderDTO odto, @RequestHeader("token") String token) throws LoginException {

        Order savedorder = oService.saveOrder(odto,token);
        return new ResponseEntity<Order>(savedorder, HttpStatus.CREATED);

    }

    @GetMapping("/orders")
    public List<Order> getAllOrders(){


        List<Order> listOfAllOrders = oService.getAllOrders();
        return listOfAllOrders;

    }

    @GetMapping("/orders/{orderId}")
    public Order getOrdersByOrderId(@PathVariable("orderId") Integer orderId) {

        return oService.getOrderByOrderId(orderId);

    }

    @DeleteMapping("/orders/{orderId}")
    public Order cancelTheOrderByOrderId(@PathVariable("orderId") Integer orderId,@RequestHeader("token") String token){

        return oService.cancelOrderByOrderId(orderId,token);
    }

    @PutMapping("/orders/{orderId}")
    public ResponseEntity<Order> updateOrderByOrder(@Valid @RequestBody OrderDTO orderdto, @PathVariable("orderId") Integer orderId,@RequestHeader("token") String token) throws LoginException {

        Order updatedOrder= oService.updateOrderByOrder(orderdto,orderId,token);

        return new ResponseEntity<Order>(updatedOrder,HttpStatus.ACCEPTED);

    }

    @GetMapping("/orders/by/date")
    public List<Order> getOrdersByDate(@RequestParam("date") String date){

        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate ld=LocalDate.parse(date,dtf);
        return oService.getAllOrdersByDate(ld);
    }

    @GetMapping("/customer/{orderId}")
    public Customer getCustomerDetailsByOrderId(@PathVariable("orderId") Integer orderId) {
        return oService.getCustomerByOrderid(orderId);
    }
}
