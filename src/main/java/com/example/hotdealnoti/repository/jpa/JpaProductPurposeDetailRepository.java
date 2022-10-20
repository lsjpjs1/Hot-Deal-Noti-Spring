package com.example.hotdealnoti.repository.jpa;

import com.example.hotdealnoti.auth.domain.Account;
import com.example.hotdealnoti.enums.AccountType;
import com.example.hotdealnoti.recommendation.domain.ProductPurposeDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaProductPurposeDetailRepository extends JpaRepository<ProductPurposeDetail,Long> {
}
