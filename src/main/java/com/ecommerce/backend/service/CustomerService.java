package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.CustomerDTO;
import com.ecommerce.backend.dto.CustomerUpdateDTO;
import com.ecommerce.backend.dto.SessionDTO;
import com.ecommerce.backend.exception.CustomerException;
import com.ecommerce.backend.exception.CustomerNotFoundException;
import com.ecommerce.backend.models.Address;
import com.ecommerce.backend.models.CreditCard;
import com.ecommerce.backend.models.Customer;
import com.ecommerce.backend.models.Order;

import java.util.List;

public interface CustomerService {
    public Customer addCustomer(Customer customer) throws CustomerException;

    public Customer getLoggedInCustomerDetails(String token) throws CustomerNotFoundException;

    public List<Customer> getAllCustomers(String token) throws CustomerNotFoundException;

    public Customer updateCustomer(CustomerUpdateDTO customer, String token) throws CustomerNotFoundException;

    public Customer updateCustomerMobileNoOrEmailId(CustomerUpdateDTO customerUpdateDTO, String token) throws CustomerNotFoundException;

    public Customer updateCreditCardDetails(String token, CreditCard card) throws CustomerException;

    public SessionDTO updateCustomerPassword(CustomerDTO customerDTO, String token) throws CustomerNotFoundException;

    public SessionDTO deleteCustomer(CustomerDTO customerDTO, String token) throws CustomerNotFoundException;

    public Customer updateAddress(Address address, String type, String token) throws CustomerException;

    public Customer deleteAddress(String type, String token) throws CustomerException, CustomerNotFoundException;

    public List<Order> getCustomerOrders(String token) throws CustomerException;

}
