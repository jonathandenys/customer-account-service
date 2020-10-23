package com.spring.project.customerservice.persistence.repository;

import com.spring.project.customerservice.persistence.entity.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Integer> {

    @Query("SELECT a FROM Account a WHERE a.customerId = ?1")
    List<Account> findAllByCustomerId(String customerId);
}
