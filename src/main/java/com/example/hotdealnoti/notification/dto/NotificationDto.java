package com.example.hotdealnoti.notification.dto;

import com.example.hotdealnoti.auth.domain.Account;
import com.example.hotdealnoti.enums.NotificationType;
import com.example.hotdealnoti.enums.converter.NotificationTypeConverter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Convert;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.criteria.CriteriaBuilder;
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
        private Integer minPrice;
        private Integer maxPrice;
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
        private Integer minPrice;
        private Integer maxPrice;
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

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class NotificationResponseDto{
        private Long notificationId;

        private Timestamp notificationTime;

        private String notificationType;

        private Long notificationItemId;

        private Long accountId;

        private String notificationTitle;

        private String notificationBody;

        private Boolean isRead;
    }



}

