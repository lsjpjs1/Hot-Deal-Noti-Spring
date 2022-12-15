package com.example.hotdealnoti.common.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class MailDTO {
    @Getter
    @AllArgsConstructor
    @Builder
    @Setter
    public static class MailRequest{
        private String receiverEmail;
        private String subject;
        private String body;
    }
}
