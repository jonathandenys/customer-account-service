package com.spring.project.customerservice.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class AccountServiceTest {

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
        log.info(testResult);
        assertEquals(testResult, "A new current account has been created for Customer ID - " + customerId);
    }

    @Test
    public void givenCustomerIdDoesNotExist_expect_userDoesNotExistMessage() {
        //GIVEN
        String customerId = "006";
        String initialCredit = "0";
        //WHEN
        String testResult = customerActionService.createNewAccount(customerId, initialCredit);
        //THEN
        log.info(testResult);
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
        log.info(testResult);
        assertEquals(testResult, "Please enter a valid amount/number!");
    }

    @Test
    public void givenExistingCustomerIdAndInitialCreditIsAllocated_expect_newAccountCreatedAndCredited() {
        //GIVEN
        String customerId = "001";
        String initialCredit = "5000";
        //WHEN
        String testResult = customerActionService.createNewAccount(customerId, initialCredit);
        //THEN
        log.info(testResult);
        assertEquals(testResult, "A new current account has been created for Customer ID - " + customerId + " and an amount of €" + initialCredit + " has been credited to the account");
    }
}
