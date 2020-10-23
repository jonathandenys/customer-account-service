package com.spring.project.customerservice.service;

import com.spring.project.customerservice.persistence.entity.Customer;
import com.spring.project.customerservice.persistence.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerService {

    final private CustomerRepository customerRepo;

    public Customer findCustomerById(final String customerId){
        return customerRepo.findByCustomerId(customerId);
    }
}
