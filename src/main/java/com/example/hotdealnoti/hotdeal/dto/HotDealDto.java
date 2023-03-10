package com.example.hotdealnoti.hotdeal.dto;

import com.example.hotdealnoti.product.domain.Product;
import com.example.hotdealnoti.product.dto.ProductDto;
import lombok.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class HotDealDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    @NoArgsConstructor
    public static class GetHotDealsRequest {
        private String searchBody;
        private List<String> sourceSites;
        private Long manufacturerId;
        private Long productPurposeId;
        private Boolean isShowReturnItem;

        private Integer minDiscountRate;
        private Integer maxDiscountRate;

        private String productFunctionFiltersJsonString;
        private List<ProductFunctionFilter> productFunctionFilters;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    @NoArgsConstructor
    public static class ProductFunctionFiltersWrapper {
        private List<ProductFunctionFilter> productFunctionFilters;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    @NoArgsConstructor
    public static class ProductFunctionFilter {
        private Long productFunctionTypeId;
        private Boolean isAndFilter;
        private List<Long> productFunctionIdList;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class PostFavoriteHotDealRequest {
        private Long hotDealId;
        private Long accountId;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class DeleteFavoriteHotDealRequest {
        private Long hotDealId;
        private Long accountId;
    }
    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    @NoArgsConstructor
    public static class InsertClassifyQueueRequest {
        private Long hotDealId;
        private String hotDealTitle;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class ProductWithSimilarity {
        private Double similarity;
        private Product product;
    }




    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class GetHotDealsResponse {
        private List<HotDealPreview> hotDeals;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class GetNotClassifiedHotDealsResponse {
        private List<NotClassifiedHotDeal> hotDeals;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class HotDealPreview {
        private Long hotDealId;
        private String title;
        private Integer originalPrice;
        private Integer discountPrice;
        private Integer discountRate;
        private String link;
        private Timestamp uploadTime;
        private Integer viewCount;
        private String sourceSite;

        private Long productId;
        private String modelName;
        private String manufacturer;
        private String productPurpose;

        private Boolean isDelete;

        private String hotDealThumbnailUrl;

        private Long returnItemId;
        private String returnItemQuality;
        private String returnItemQualityDetail;
        private String returnItemSaleStatus;

        private Integer productRanking;
        private Long productPurposeId;

        private Boolean isCandidateProduct;

        //얘는 항시 맨 밑에 두고 필드 추가되면 아래 생성자에 추가해줘야 함
        private List<ProductDto.ProductAdditionalFunctionDTO> productAdditionalFunctionDTOList = new ArrayList<>();


        public HotDealPreview(Long hotDealId, String title, Integer originalPrice, Integer discountPrice, Integer discountRate, String link, Timestamp uploadTime, Integer viewCount, String sourceSite, Long productId, String modelName, String manufacturer, String productPurpose, Boolean isDelete, String hotDealThumbnailUrl, Long returnItemId, String returnItemQuality, String returnItemQualityDetail, String returnItemSaleStatus, Integer productRanking, Long productPurposeId, Boolean isCandidateProduct) {
            this.hotDealId = hotDealId;
            this.title = title;
            this.originalPrice = originalPrice;
            this.discountPrice = discountPrice;
            this.discountRate = discountRate;
            this.link = link;
            this.uploadTime = uploadTime;
            this.viewCount = viewCount;
            this.sourceSite = sourceSite;
            this.productId = productId;
            this.modelName = modelName;
            this.manufacturer = manufacturer;
            this.productPurpose = productPurpose;
            this.isDelete = isDelete;
            this.hotDealThumbnailUrl = hotDealThumbnailUrl;
            this.returnItemId = returnItemId;
            this.returnItemQuality = returnItemQuality;
            this.returnItemQualityDetail = returnItemQualityDetail;
            this.returnItemSaleStatus = returnItemSaleStatus;
            this.productRanking = productRanking;
            this.productPurposeId = productPurposeId;
            this.isCandidateProduct = isCandidateProduct;
        }
    }


    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class GetInitialDataResponse {
        private String notice;
        private Timestamp recentUpdateTime;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class NotClassifiedHotDeal {
        private Long hotDealId;
        private String hotDealTitle;
        private Integer hotDealOriginalPrice;
        private Integer hotDealDiscountPrice;
        private Integer hotDealDiscountRate;
        private String hotDealLink;
        private Timestamp hotDealUploadTime;
        private String candidateProductName;
    }


}
