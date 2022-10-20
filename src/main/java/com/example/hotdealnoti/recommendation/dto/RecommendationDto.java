package com.example.hotdealnoti.recommendation.dto;

import com.example.hotdealnoti.product.domain.Manufacturer;
import com.example.hotdealnoti.product.domain.ProductFamily;
import com.example.hotdealnoti.product.domain.ProductPurpose;
import com.example.hotdealnoti.product.domain.ProductType;
import com.example.hotdealnoti.product.dto.ProductDto;
import com.example.hotdealnoti.recommendation.domain.ProductPurposeDetail;
import lombok.*;

import java.util.List;

public class RecommendationDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class GetRecommendationsResponse{
        private List<Recommendation> recommendations;
    }
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class Recommendation{
        private ProductPurposeDetail productPurposeDetail;
        private List<ProductFamilyDto> productFamilies;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class ProductFamilyDto{
        private ProductFamily productFamily;
        private List<RecommendationProduct> products;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class RecommendationProduct{
        private Long productId;
        private String modelName;
        private Boolean isHotDealExist;
        private Integer minHotDealPrice;
    }


}

