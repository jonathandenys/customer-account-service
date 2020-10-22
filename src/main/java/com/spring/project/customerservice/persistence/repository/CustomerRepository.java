package com.spring.project.customerservice.persistence.repository;

import com.spring.project.customerservice.persistence.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    @Query("SELECT c FROM Customer c WHERE c.customerId = ?1")
    Customer findByCustomerId(String customerId);
}