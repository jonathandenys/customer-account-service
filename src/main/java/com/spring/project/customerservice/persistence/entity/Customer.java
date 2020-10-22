package com.spring.project.customerservice.persistence.entity;

import lombok.Builder;
import lombok.Data;
import javax.persistence.*;

@Builder
@Data
@Entity
@Table
public class Customer {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String customerId;
    @Column
    private String customerName;
    @Column
    private String customerSurname;
}