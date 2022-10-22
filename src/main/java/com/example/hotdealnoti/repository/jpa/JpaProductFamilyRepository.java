package com.example.hotdealnoti.repository.jpa;

import com.example.hotdealnoti.product.domain.ProductFamily;
import com.example.hotdealnoti.product.domain.ProductPurpose;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaProductFamilyRepository extends JpaRepository<ProductFamily,Long> {
    List<ProductFamily> findByProductFamilyNameContains(String productFamilyName);
}
