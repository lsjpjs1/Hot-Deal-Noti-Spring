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
        private String hotDealThumbnailUrl;
        private String returnItemQuality;
        private String returnItemQualityDetail;
        private String returnItemSaleStatus;

        public Boolean nullCheck(){
            if (discountRate==null) return true;
            if (discountPrice==null) return true;
            if (originalPrice==null) return true;

            if (title==null) return true;
            else if (title.equals("")) return true;

            if (sourceSite==null) return true;
            else if (sourceSite.equals("")) return true;

            if (url==null) return true;
            else if (url.equals("")) return true;

            if (hotDealThumbnailUrl==null) return true;

            return false;
        }
    }

}

