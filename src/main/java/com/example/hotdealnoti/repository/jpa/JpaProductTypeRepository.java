package com.example.hotdealnoti.repository.jpa;

import com.example.hotdealnoti.product.domain.Product;
import com.example.hotdealnoti.product.domain.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductTypeRepository extends JpaRepository<ProductType,Long> {
}
