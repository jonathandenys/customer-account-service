package com.spring.project.customerservice.persistence.repository;

import com.spring.project.customerservice.persistence.entity.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Integer> {
}
