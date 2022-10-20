package com.example.hotdealnoti.product.repository;

import com.example.hotdealnoti.messagequeue.domain.QHotDeal;
import com.example.hotdealnoti.messagequeue.domain.QHotDealCandidate;
import com.example.hotdealnoti.product.domain.ProductFamily;
import com.example.hotdealnoti.product.domain.QProduct;
import com.example.hotdealnoti.product.dto.ProductDto;
import com.example.hotdealnoti.recommendation.dto.RecommendationDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.JPAExpressions;
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
    private final QHotDeal hotDeal = QHotDeal.hotDeal;

    public List<ProductDto.GetProductDTO> findProducts(ProductDto.GetProductRequest getProductRequest) {
        return jpaQueryFactory
                .select(Projections.constructor(ProductDto.GetProductDTO.class,
                        product.productId,
                        product.modelNameSearch,
                        product.productType.productTypeName,
                        product.manufacturer.manufacturerName,
                        product.manufacturer.manufacturerId,
                        product.modelName
                ))
                .from(product)
                .where(getCondition(getProductRequest))
                .orderBy(product.modelNameSearch.desc())
                .fetch();
    }

    public List<RecommendationDto.RecommendationProduct> findRecommendationProductsByProductFamily(ProductFamily productFamily) {
        return jpaQueryFactory
                .select(Projections.constructor(RecommendationDto.RecommendationProduct.class,
                        product.productId,
                        product.modelName,
                        new CaseBuilder()
                                .when(JPAExpressions.select(hotDeal.hotDealId.count())
                                        .from(hotDeal)
                                        .where(
                                                hotDeal.product.productId.eq(product.productId),
                                                hotDeal.isDelete.eq(false)
                                        )
                                        .gt(0l)).then(Boolean.TRUE)
                                .otherwise(Boolean.FALSE),
                        JPAExpressions.select(hotDeal.hotDealDiscountPrice)
                                .from(hotDeal)
                                .where(
                                        hotDeal.product.productId.eq(product.productId),
                                        hotDeal.isDelete.eq(false)
                                )
                                .orderBy(hotDeal.hotDealDiscountPrice.asc())
                                .limit(1L)
                ))
                .from(product)
                .where(product.productFamily.productFamilyId.eq(productFamily.getProductFamilyId()))
                .orderBy(product.modelNameSearch.desc())
                .fetch();
    }

    private BooleanExpression getCondition(ProductDto.GetProductRequest getProductRequest) {
        if (getProductRequest == null) return null;
        log.info(getProductRequest.getModelName());
        return product.modelNameSearch.toLowerCase().contains(getProductRequest.getModelName()!=null? getProductRequest.getModelName().toLowerCase().replace(" ","") : "")
                .and(product.manufacturer.manufacturerName.toLowerCase().contains(getProductRequest.getManufacturer()!=null? getProductRequest.getManufacturer().toLowerCase() : ""));

    }
}
