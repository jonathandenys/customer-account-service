package com.spring.project.customerservice.controller;

import com.spring.project.customerservice.model.CustomerInformation;
import com.spring.project.customerservice.service.CustomerActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("customerAction/")
public class CustomerActionController {

    private final CustomerActionService customerActionService;

    @PostMapping(value = "/createNewAccount/{customerId}/{initialCredit}")
    public String createNewAccount(@PathVariable final String customerId, @PathVariable final String initialCredit) {
        return customerActionService.createNewAccount(customerId, initialCredit);
    }

    @GetMapping(value = "/getCustomerInformation/{customerId}")
    public CustomerInformation getCustomerInformation(@PathVariable final String customerId) {
        return customerActionService.getAllCustomerInformation(customerId);
    }
}
