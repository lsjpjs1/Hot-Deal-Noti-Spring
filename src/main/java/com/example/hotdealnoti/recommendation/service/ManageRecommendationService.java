package com.example.hotdealnoti.recommendation.service;

import com.example.hotdealnoti.exception.CustomException;
import com.example.hotdealnoti.exception.ErrorCode;
import com.example.hotdealnoti.product.domain.Product;
import com.example.hotdealnoti.product.domain.ProductFamily;
import com.example.hotdealnoti.product.repository.ProductQueryRepository;
import com.example.hotdealnoti.recommendation.domain.ProductPurposeDetail;
import com.example.hotdealnoti.recommendation.domain.RecommendationProductFamily;
import com.example.hotdealnoti.recommendation.dto.RecommendationDto;
import com.example.hotdealnoti.repository.jpa.JpaProductFamilyRepository;
import com.example.hotdealnoti.repository.jpa.JpaProductPurposeDetailRepository;
import com.example.hotdealnoti.repository.jpa.JpaProductRepository;
import com.example.hotdealnoti.repository.jpa.JpaRecommendationProductFamily;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManageRecommendationService {

    private final JpaProductFamilyRepository jpaProductFamilyRepository;
    private final JpaProductRepository jpaProductRepository;
    private final JpaProductPurposeDetailRepository jpaProductPurposeDetailRepository;
    private final JpaRecommendationProductFamily jpaRecommendationProductFamily;

    @Transactional
    public void postProductFamily(RecommendationDto.PostProductFamilyRequest postProductFamilyRequest) {
        ProductFamily productFamily = ProductFamily.builder()
                .productFamilyDescription(postProductFamilyRequest.getProductFamilyDescription())
                .productFamilyName(postProductFamilyRequest.getProductFamilyName())
                .build();

        jpaProductFamilyRepository.save(productFamily);
    }

    @Transactional
    public void postRecommendationProductFamily(RecommendationDto.PostRecommendationProductFamilyRequest postRecommendationProductFamilyRequest) {
        ProductFamily productFamily = jpaProductFamilyRepository.findById(postRecommendationProductFamilyRequest.getProductFamilyId())
                .orElseThrow(() -> new CustomException(ErrorCode.PRODUCT_FAMILY_ID_NOT_FOUND));
        ProductPurposeDetail productPurposeDetail = jpaProductPurposeDetailRepository.findById(postRecommendationProductFamilyRequest.getProductPurposeDetailId())
                .orElseThrow(() -> new CustomException(ErrorCode.PRODUCT_PURPOSE_DETAIL_ID_NOT_FOUND));


        jpaRecommendationProductFamily.save(RecommendationProductFamily.builder().productFamily(productFamily).productPurposeDetail(productPurposeDetail).build());

    }

    @Transactional
    public void setProductFamily(RecommendationDto.SetProductFamilyRequest setProductFamilyRequest) {
        if(setProductFamilyRequest.getProductFamilyId()==null || setProductFamilyRequest.getProductId()==null){
            throw new CustomException(ErrorCode.BAD_REQUEST_CONTENT);
        }
        Product product = jpaProductRepository.findById(setProductFamilyRequest.getProductId())
                .orElseThrow(() -> new CustomException(ErrorCode.PRODUCT_ID_NOT_FOUND));
        product.setProductFamily(ProductFamily.builder().productFamilyId(setProductFamilyRequest.getProductFamilyId()).build());
        jpaProductRepository.save(product);
    }

    @Transactional
    public void updateProductFamily(RecommendationDto.UpdateProductFamilyRequest updateProductFamilyRequest) {

        ProductFamily productFamily = jpaProductFamilyRepository.findById(updateProductFamilyRequest.getProductFamilyId())
                .orElseThrow(() -> new CustomException(ErrorCode.PRODUCT_FAMILY_ID_NOT_FOUND));
        productFamily.setProductFamilyDescription(updateProductFamilyRequest.getProductFamilyDescription());
        jpaProductFamilyRepository.save(productFamily);
    }
}
