package com.spring.project.customerservice.model;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Builder
@Data
public class CustomerInformation {

    private String customerName;
    private String customerSurname;
    private BigDecimal balance;
    private String accountNumber;
}
