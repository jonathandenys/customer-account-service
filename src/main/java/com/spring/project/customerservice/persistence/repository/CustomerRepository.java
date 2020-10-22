package com.spring.project.customerservice.persistence.repository;

import com.spring.project.customerservice.persistence.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
