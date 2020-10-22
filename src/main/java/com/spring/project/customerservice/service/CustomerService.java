package com.spring.project.customerservice.service;

import com.spring.project.customerservice.persistence.entity.Customer;
import com.spring.project.customerservice.persistence.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    final private CustomerRepository customerRepo;

    public Customer findCustomerById(String customerId){
        return customerRepo.findByCustomerId(customerId);
    }
}
