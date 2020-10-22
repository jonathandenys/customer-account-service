package com.spring.project.customerservice.persistence.entity;

import lombok.Builder;
import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;

@Builder
@Data
@Entity
@Table
public class Transaction{

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String transactionId;
    @Column
    private String accountNumber;
    @Column
    private BigDecimal amount;
}
