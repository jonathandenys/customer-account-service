package com.spring.project.customerservice.service;

import com.spring.project.customerservice.persistence.entity.Transaction;
import com.spring.project.customerservice.persistence.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TransactionService {

    final private TransactionRepository transactionRepo;

    public Transaction saveNewTransaction(String accountNumber, BigDecimal initialCredit){
        String transactionID = UUID.randomUUID().toString();
        return transactionRepo.save(Transaction
                .builder()
                .transactionId(transactionID)
                .amount(initialCredit)
                .accountNumber(accountNumber)
                .build());
    }
}
