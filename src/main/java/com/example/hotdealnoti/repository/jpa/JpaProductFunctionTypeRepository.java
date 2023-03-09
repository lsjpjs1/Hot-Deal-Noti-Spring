package com.example.hotdealnoti.repository.jpa;

import com.example.hotdealnoti.product.domain.ProductFamily;
import com.example.hotdealnoti.product.domain.ProductFunctionType;
import com.example.hotdealnoti.product.domain.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaProductFunctionTypeRepository extends JpaRepository<ProductFunctionType,Long> {
    List<ProductFunctionType> findByProductType(ProductType productType);
}
