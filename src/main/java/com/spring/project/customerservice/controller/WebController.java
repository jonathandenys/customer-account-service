package com.spring.project.customerservice.controller;

import com.spring.project.customerservice.model.NewAccount;
import com.spring.project.customerservice.service.CustomerActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class WebController {

    private final CustomerActionService customerActionService;

    @GetMapping("/index")
    public String indexPage(Model model) {
        model.addAttribute("customerIDSet", new NewAccount());
        return "index";
    }

    @PostMapping("/index")
    public String indexLoadWithCustomerId(@ModelAttribute NewAccount newAccount, Model model) {
        model.addAttribute("customerIDSet", newAccount);
        model.addAttribute("customer", customerActionService.getAllCustomerInformation(newAccount.getCustomerId()));
        return "index";
    }

    @GetMapping("/newAccount")
    public String newAccount(Model model) {
        model.addAttribute("newAccount", new NewAccount());
        return "newAccount";
    }

    @PostMapping("/newAccount")
    public String indexLoadWithNewAccount(@ModelAttribute NewAccount newAccount, Model model) {
        model.addAttribute("customerIDSet", newAccount);
        model.addAttribute("message", customerActionService.createNewAccount(newAccount.getCustomerId(), newAccount.getInitialCredit()));
        model.addAttribute("customer", customerActionService.getAllCustomerInformation(newAccount.getCustomerId()));
        return "index";
    }
}
