package com.example.hotdealnoti.product.dto;

import com.example.hotdealnoti.messagequeue.dto.HotDealMessageDto;
import com.example.hotdealnoti.product.domain.Manufacturer;
import com.example.hotdealnoti.product.domain.ProductPurpose;
import com.example.hotdealnoti.product.domain.ProductType;
import lombok.*;

import java.util.List;

public class ProductDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class ClassifyProductInitDataResponse{
        private List<ProductType> productTypes;
        private List<ProductPurpose> productPurposes;
        private List<Manufacturer> manufacturers;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class GetProductRequest{
        private String modelName;
        private String manufacturer;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class ClassifyHotDealRequest{
        private Long hotDealId;
        private Long productId;
        private String modelName;
        private Long manufacturerId;
        private String manufacturer;
        private Long productPurposeId;
        private Long productTypeId;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class GetProductResponse{
        private List<GetProductDTO> products;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class GetProductDTO{
        private Long productId;
        private String modelName;
        private String productType;
        private String manufacturer;
        private Long manufacturerId;
        private String fullModelName;
    }


    public interface ProductRankingInfo{
        Long getProductId();
        Long getProductPurposeId();
        Integer getProductRankingNumber();
    }
}

