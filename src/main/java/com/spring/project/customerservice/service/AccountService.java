package com.spring.project.customerservice.service;

import com.spring.project.customerservice.persistence.entity.Account;
import com.spring.project.customerservice.persistence.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepo;

    //Save the new account
    public Account saveNewAccount(final String customerId, final String accountNumber){
        return accountRepo.save(Account
                .builder()
                .customerId(customerId)
                .accountNumber(accountNumber)
                .build());
    }
}
