package com.spring.project.customerservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerActionService {

    private final CustomerService customerService;

    private final AccountService accountService;

    private final TransactionService transactionService;

    //Creates a new account and checks if there is initial credit allocated to the account
    public String createNewAccount(String customerId, String initialCredit){

        String successMessage;
        BigDecimal bigInitialCredit;
        final String accountNumber = UUID.randomUUID().toString();

        //CHECK IF USER EXISTS
        if (customerService.findCustomerById(customerId) == null){
            return "Customer with id " + customerId + " does not exist";
        }

        try {
            bigInitialCredit = new BigDecimal(initialCredit);
        } catch (Exception e){
            e.printStackTrace();
            return "Please enter a valid amount/number!";
        }

        if(accountService.saveNewAccount(customerId, accountNumber).getAccountNumber() != null){
            successMessage = "A new current account has been created for Customer ID - " + customerId;
        }else{
            return "Account has not been created";
        }

        if(!successMessage.isEmpty()) {
            if (!bigInitialCredit.equals(new BigDecimal(0))) {
                if (transactionService.saveNewTransaction(accountNumber, bigInitialCredit).getTransactionId() != null) {
                    successMessage += " and an amount of €" + bigInitialCredit + " has been credited to the account";
                } else {
                    return "A new current Account has been created for Customer ID " + customerId + " but credit was not allocated to the account";
                }
            }
        }
        return successMessage;
    }
}
