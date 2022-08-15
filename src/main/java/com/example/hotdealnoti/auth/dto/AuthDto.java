package com.example.hotdealnoti.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

public class AuthDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class KakaoLoginRequest {
        private String code;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class KakaoAccessTokenResponse {

        @JsonProperty("access_token")
        private String accessToken;
        @JsonProperty("token_type")
        private String tokenType;
        @JsonProperty("refresh_token")
        private String refreshToken;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class KakaoUserInfo {
        @JsonProperty("id")
        private Long id;

        @JsonProperty("connected_at")
        private String connectedAt;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class LoginResponse {
        private String token;
    }
}
