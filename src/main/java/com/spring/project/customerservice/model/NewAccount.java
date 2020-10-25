package com.spring.project.customerservice.model;

public class NewAccount {

    private String customerId;
    private String initialCredit;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getInitialCredit() {
        return initialCredit;
    }

    public void setInitialCredit(String initialCredit) {
        this.initialCredit = initialCredit;
    }
}
