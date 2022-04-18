package com.ecommerce.bootcampecommerce.repository;

import com.ecommerce.bootcampecommerce.entity.Customer;
import com.ecommerce.bootcampecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByUserId(Long id);

}
