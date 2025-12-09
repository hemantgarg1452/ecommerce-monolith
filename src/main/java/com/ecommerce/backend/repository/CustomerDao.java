package com.ecommerce.backend.repository;

import com.ecommerce.backend.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByMobileNo(String mobileNo);

    Optional<Customer> findByEmailId(String emailId);

    Optional<Customer> findByMobileNoOrEmailId(String mobileNo, String emailId);

}
