package com.spring.project.customerservice.service;

import com.spring.project.customerservice.model.CustomerInformation;
import com.spring.project.customerservice.persistence.entity.Customer;
import com.spring.project.customerservice.persistence.entity.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.*;

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
        if (!doesCustomerExist(customerId)){
            return "Customer with id " + customerId + " does not exist";
        }

        //CATCH EXCEPTION IF NAN
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

    //Get all the customers information
    public CustomerInformation getAllCustomerInformation(final String customerId) {
        if (!doesCustomerExist(customerId)){
            return CustomerInformation.builder().
                    status("Customer with id " + customerId + " does not exist")
                    .build();
        }else{
            Customer customer = customerService.findCustomerById(customerId);
            Map<String, List<Transaction>> accountsAndTransactions = getAccountsAndTransactions(customerId);
            return CustomerInformation.builder()
                    .customerName(customer.getCustomerName())
                    .customerSurname(customer.getCustomerSurname())
                    .balance(getCustomerBalance(accountsAndTransactions))
                    .accountsAndTransactions(accountsAndTransactions)
                    .status("")
                    .build();
        }
    }

    //For each account, het transactions and add it to a map with the account number as key
    private Map<String, List<Transaction>> getAccountsAndTransactions(final String customerId){
        Map<String, List<Transaction>> returnMap = new HashMap<>();
        accountService.getAccountsByCustomerId(customerId).forEach(account -> {
            returnMap.put(account.getAccountNumber(), transactionService.getTransactionsByAccountNumber(account.getAccountNumber()));
        });
        return returnMap;
    }

    private BigDecimal getCustomerBalance(final Map<String, List<Transaction>> accountsAndTransactions){
        BigDecimal returnValue = new BigDecimal(0);
        for (List<Transaction> value : accountsAndTransactions.values()) {
            returnValue = returnValue.add(value.stream()
                    .map(tran -> tran.getAmount())
                    .reduce(BigDecimal.ZERO, BigDecimal::add));
        }
        return returnValue;
    }

    public boolean doesCustomerExist(String customerId){
        if (customerService.findCustomerById(customerId) == null){
            return false;
        }
        return true;
    }
}
