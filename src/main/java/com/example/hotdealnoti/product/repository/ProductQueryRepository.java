package com.example.hotdealnoti.product.repository;

import com.example.hotdealnoti.product.domain.QProduct;
import com.example.hotdealnoti.product.dto.ProductDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Locale;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ProductQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    private final QProduct product = QProduct.product;

    public List<ProductDto.GetProductDTO> findProducts(ProductDto.GetProductRequest getProductRequest) {
        return jpaQueryFactory
                .select(Projections.constructor(ProductDto.GetProductDTO.class,
                        product.productId,
                        product.modelName,
                        product.productType.productTypeName,
                        product.manufacturer.manufacturerName
                        ))
                .from(product)
                .where(getCondition(getProductRequest))
                .fetch();
    }

    private BooleanExpression getCondition(ProductDto.GetProductRequest getProductRequest) {
        if (getProductRequest == null) return null;
        log.info(getProductRequest.getModelName());
        return product.modelName.toLowerCase().contains(getProductRequest.getModelName()!=null? getProductRequest.getModelName().toLowerCase() : "")
                .and(product.manufacturer.manufacturerName.toLowerCase().contains(getProductRequest.getManufacturer()!=null? getProductRequest.getManufacturer().toLowerCase() : ""));

    }
}
