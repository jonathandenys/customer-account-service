package com.spring.project.customerservice.model;

import com.spring.project.customerservice.persistence.entity.Transaction;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Builder
@Data
public class CustomerInformation {

    private String customerName;
    private String customerSurname;
    private BigDecimal balance;
    private Map<String, List<Transaction>> accountsAndTransactions;
    private String status;
}
