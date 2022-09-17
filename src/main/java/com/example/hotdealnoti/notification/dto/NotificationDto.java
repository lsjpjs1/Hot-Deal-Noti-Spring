package com.example.hotdealnoti.notification.dto;

import lombok.*;

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



}

