package com.spring.project.customerservice.controller;

import com.spring.project.customerservice.service.CustomerActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("customerAction/")
public class CustomerActionController {

    private final CustomerActionService customerActionService;

    @PostMapping(value = "/createNewAccount/{customerId}/{initialCredit}")
    public String createNewAccount(@PathVariable final String customerId, @PathVariable final String initialCredit) {
        return customerActionService.createNewAccount(customerId, initialCredit);
    }
}
