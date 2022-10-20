package com.example.hotdealnoti.recommendation.service;

import com.example.hotdealnoti.product.dto.ProductDto;
import com.example.hotdealnoti.product.repository.ProductQueryRepository;
import com.example.hotdealnoti.recommendation.domain.ProductPurposeDetail;
import com.example.hotdealnoti.recommendation.domain.RecommendationProductFamily;
import com.example.hotdealnoti.recommendation.dto.RecommendationDto;
import com.example.hotdealnoti.repository.jpa.JpaProductPurposeDetailRepository;
import com.example.hotdealnoti.repository.jpa.JpaProductPurposeRepository;
import com.example.hotdealnoti.repository.jpa.JpaProductTypeRepository;
import com.example.hotdealnoti.repository.jpa.JpaRecommendationProductFamily;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetRecommendationService {

    private final JpaProductPurposeDetailRepository jpaProductPurposeDetailRepository;
    private final JpaRecommendationProductFamily jpaRecommendationProductFamily;
    private final ProductQueryRepository productQueryRepository;

    @Transactional
    public RecommendationDto.GetRecommendationsResponse getRecommendations() {
        List<ProductPurposeDetail> productPurposeDetails = jpaProductPurposeDetailRepository.findAll();
        List<RecommendationDto.Recommendation> recommendations = productPurposeDetails.stream()
                .map(productPurposeDetail -> {
                    List<RecommendationProductFamily> recommendationProductFamilies = jpaRecommendationProductFamily.findByProductPurposeDetail(productPurposeDetail);
                    List<RecommendationDto.ProductFamilyDto> productFamilyDtos = recommendationProductFamilies.stream()
                            .map(recommendationProductFamily -> {
                                List<RecommendationDto.RecommendationProduct> recommendationProducts = productQueryRepository.findRecommendationProductsByProductFamily(recommendationProductFamily.getProductFamily());
                                return RecommendationDto.ProductFamilyDto.builder()
                                                .productFamily(recommendationProductFamily.getProductFamily())
                                                .products(recommendationProducts)
                                                .build();
                                    }
                            ).toList();
                    return RecommendationDto.Recommendation.builder()
                            .productPurposeDetail(productPurposeDetail)
                            .productFamilies(productFamilyDtos)
                            .build();
                })
                .toList();
        return RecommendationDto.GetRecommendationsResponse.builder().recommendations(recommendations).build();
    }
}
