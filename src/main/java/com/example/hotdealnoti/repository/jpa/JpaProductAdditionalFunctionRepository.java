package com.example.hotdealnoti.repository.jpa;

import com.example.hotdealnoti.product.domain.ProductAdditionalFunction;
import com.example.hotdealnoti.product.domain.ProductFunction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaProductAdditionalFunctionRepository extends JpaRepository<ProductAdditionalFunction,Long> {
    @Query(nativeQuery = true,value = "select * " +
            "from product_additional_function " +
            "left join product_function pf on product_additional_function.product_function_id = pf.product_function_id " +
            "left join product_function_type pft on pf.product_function_type_id = pft.product_function_type_id " +
            "where pft.product_function_type_name = :productFunctionTypeName and pft.product_type_id = :productTypeId ")
    List<ProductAdditionalFunction> findByProductFunctionTypeAndProductType(@Param("productFunctionTypeName") String productFunctionTypeName,@Param("productTypeId") Long productTypeId);
}
