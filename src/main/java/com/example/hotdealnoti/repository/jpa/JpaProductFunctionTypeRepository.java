package com.example.hotdealnoti.repository.jpa;

import com.example.hotdealnoti.product.domain.ProductFamily;
import com.example.hotdealnoti.product.domain.ProductFunctionType;
import com.example.hotdealnoti.product.domain.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaProductFunctionTypeRepository extends JpaRepository<ProductFunctionType,Long> {
    List<ProductFunctionType> findByProductTypeAndIsDisplay(ProductType productType, Boolean isDisplay);
    Optional<ProductFunctionType> findByProductFunctionTypeNameAndProductType(String productFunctionTypeName,ProductType productType);
}
