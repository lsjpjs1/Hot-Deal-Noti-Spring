package com.example.hotdealnoti.product.dto;

import com.example.hotdealnoti.messagequeue.dto.HotDealMessageDto;
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
    }
}
