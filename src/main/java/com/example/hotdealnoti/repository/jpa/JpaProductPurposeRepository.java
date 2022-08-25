package com.example.hotdealnoti.repository.jpa;

import com.example.hotdealnoti.product.domain.Product;
import com.example.hotdealnoti.product.domain.ProductPurpose;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductPurposeRepository extends JpaRepository<ProductPurpose,Long> {
}
