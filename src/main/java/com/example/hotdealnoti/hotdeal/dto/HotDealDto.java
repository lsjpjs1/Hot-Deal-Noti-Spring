package com.example.hotdealnoti.hotdeal.dto;

import com.example.hotdealnoti.hotdeal.repository.HotDealQueryRepository;
import com.example.hotdealnoti.messagequeue.domain.HotDeal;
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
        private List<HotDeal> hotDeals;
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


}
