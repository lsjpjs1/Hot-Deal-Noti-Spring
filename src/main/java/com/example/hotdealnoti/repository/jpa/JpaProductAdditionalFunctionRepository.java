package com.example.hotdealnoti.repository.jpa;

import com.example.hotdealnoti.product.domain.ProductAdditionalFunction;
import com.example.hotdealnoti.product.domain.ProductFunction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductAdditionalFunctionRepository extends JpaRepository<ProductAdditionalFunction,Long> {
}
