package com.example.hotdealnoti.notification.dto;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;


public class NotificationDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class PostKeywordRequest{
        private String keyword;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class KeywordNotificationDto{
        private Long keywordNotificationId;
        private String keywordNotificationBody;
        private Timestamp keywordNotificationTime;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class GetKeywordsResponse{
        private List<KeywordNotificationDto> keywords;
    }



}

