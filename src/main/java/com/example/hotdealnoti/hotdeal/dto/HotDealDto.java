package com.example.hotdealnoti.hotdeal.dto;

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
    public static class HotDealPreview {
        private Long hotDealId;
        private String title;
        private Integer originalPrice;
        private Integer discountPrice;
        private Integer discountRate;
        private String link;
        private Timestamp uploadTime;
    }


}
