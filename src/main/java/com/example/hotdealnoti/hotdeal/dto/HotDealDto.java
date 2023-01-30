package com.example.hotdealnoti.hotdeal.dto;

import com.example.hotdealnoti.hotdeal.repository.HotDealQueryRepository;
import com.example.hotdealnoti.messagequeue.domain.HotDeal;
import com.example.hotdealnoti.product.domain.Product;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

public class HotDealDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class GetHotDealsRequest {
        private String searchBody;
        private List<String> sourceSites;
        private Long manufacturerId;
        private Long productPurposeId;
        private Boolean isShowReturnItem;

        private Integer minDiscountRate;
        private Integer maxDiscountRate;

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
    @AllArgsConstructor
    @ToString
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
