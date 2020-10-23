package com.spring.project.customerservice.persistence.repository;

import com.spring.project.customerservice.persistence.entity.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

    @Query("SELECT t FROM Transaction t WHERE t.accountNumber = ?1")
    List<Transaction> findByAccountNumber(String accountNumber);
}
