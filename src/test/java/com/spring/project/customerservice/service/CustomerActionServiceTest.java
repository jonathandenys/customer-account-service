package com.spring.project.customerservice.service;

import com.spring.project.customerservice.model.CustomerInformation;
import com.spring.project.customerservice.persistence.entity.Transaction;
import com.spring.project.customerservice.service.CustomerActionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertEquals;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerActionServiceTest {

    @Autowired
    private CustomerActionService customerActionService;

    @Test
    public void givenExistingCustomerIdAndInitialCreditIsZero_expect_newAccountCreatedOnly() {
        //GIVEN
        String customerId = "001";
        String initialCredit = "0";
        //WHEN
        String testResult = customerActionService.createNewAccount(customerId, initialCredit);
        //THEN
        assertEquals(testResult, "A new current account has been created for Customer ID - " + customerId);
    }

    @Test
    public void givenExistingCustomerIdAndInitialCreditIsAllocated_expect_newAccountCreatedAndCredited() {
        //GIVEN
        String customerId = "001";
        String initialCredit = "5000";
        //WHEN
        String testResult = customerActionService.createNewAccount(customerId, initialCredit);
        //THEN
        assertEquals(testResult, "A new current account has been created for Customer ID - " + customerId + " and an amount of €" + initialCredit + " has been credited to the account");
    }

    @Test
    public void givenCustomerIdDoesNotExist_expect_userDoesNotExistMessage() {
        //GIVEN
        String customerId = "006";
        String initialCredit = "0";
        //WHEN
        String testResult = customerActionService.createNewAccount(customerId, initialCredit);
        //THEN
        assertEquals(testResult, "Customer with id " + customerId + " does not exist");
    }

    @Test
    public void givenCustomerIdExistsButNotValidNumber_expect_requireValidNumberMessage() {
        //GIVEN
        String customerId = "005";
        String initialCredit = "b";
        //WHEN
        String testResult = customerActionService.createNewAccount(customerId, initialCredit);
        //THEN
        assertEquals(testResult, "Please enter a valid amount/number!");
    }

    @Test
    public void givenCustomerIdDoesNotExists_expect_messageDoesNotExist() {
        //GIVEN
        String customerId = "006";
        Map<String, List<Transaction>> accountsAndTransactions = null;
        CustomerInformation expectedModel = CustomerInformation.builder()
                .status("Customer with id " + customerId + " does not exist")
                .build();
        //WHEN
        CustomerInformation testResult = customerActionService.getAllCustomerInformation(customerId);
        //THEN
        assertEquals(testResult.getStatus(), expectedModel.getStatus());
    }

    @Test
    public void givenCustomerIdExistsAndThereAreTransactions_expect_correctCustomerInformation() {
        //GIVEN
        String customerId = "005";
        Map<String, List<Transaction>> accountsAndTransactions = new HashMap<>();
        CustomerInformation expectedModel = CustomerInformation.builder()
                .customerName("Simon")
                .customerSurname("Neil")
                .balance(new BigDecimal(0))
                .accountsAndTransactions(accountsAndTransactions)
                .status("")
                .build();
        //WHEN
        CustomerInformation testResult = customerActionService.getAllCustomerInformation(customerId);
        //THEN
        assertEquals(testResult.getCustomerName(), expectedModel.getCustomerName());
        assertEquals(testResult.getCustomerSurname(), expectedModel.getCustomerSurname());
        assertEquals(testResult.getBalance(), expectedModel.getBalance());
        assertEquals(testResult.getAccountsAndTransactions(), expectedModel.getAccountsAndTransactions());
        assertEquals(testResult.getStatus(), expectedModel.getStatus());
    }
}
