package com.spring.project.customerservice.persistence.repository;

import com.spring.project.customerservice.persistence.entity.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
}
