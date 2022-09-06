package com.example.hotdealnoti.repository.jpa;

import com.example.hotdealnoti.auth.domain.Account;
import com.example.hotdealnoti.customer.domain.CustomerRequirement;
import com.example.hotdealnoti.enums.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaCustomerRequirementRepository extends JpaRepository<CustomerRequirement,Long> {
}
