package com.example.hotdealnoti.repository.jpa;

import com.example.hotdealnoti.auth.domain.Account;
import com.example.hotdealnoti.enums.AccountType;
import com.example.hotdealnoti.product.domain.ProductPurpose;
import com.example.hotdealnoti.product.domain.ProductRanking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaProductRankingRepository extends JpaRepository<ProductRanking,Long> {

    Optional<ProductRanking> findByProductPurposeAndProductRankingNumber(ProductPurpose productPurpose, Integer productRankingNumber);
}
