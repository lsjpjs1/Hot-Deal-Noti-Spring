package com.example.hotdealnoti.repository.jpa;

import com.example.hotdealnoti.product.domain.ProductFunction;
import com.example.hotdealnoti.product.domain.ProductFunctionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaProductFunctionRepository extends JpaRepository<ProductFunction,Long> {
    List<ProductFunction> findByProductFunctionType(ProductFunctionType productFunctionType);
    ProductFunction findByProductFunctionName(String productFunctionName);
}
