package com.example.hotdealnoti.messagequeue.dto;

import lombok.*;

import java.util.List;


public class HotDealMessageDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class HotDealMessageWrapper{
        private List<HotDealMessageContent> hotDealMessages;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class HotDealMessageContent{
        private Integer discountRate;
        private Integer discountPrice;
        private Integer originalPrice;
        private String title;
        private String url;
        private String sourceSite;
    }

}

