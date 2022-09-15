package com.example.hotdealnoti.auth.service;

import com.example.hotdealnoti.auth.dto.AuthDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class KakaoLoginServiceTest {
@Autowired
private KakaoLoginService kakaoLoginService;
    @Test
    void kakaoLogin() {
        AuthDto.KakaoLoginRequest kakaoLoginRequest = AuthDto.KakaoLoginRequest.builder().accessToken("BncF1aze2I86NILvnm18AIcIh6HwMumObscz_dqOCj1ylwAAAYNA-U4A").build();
        AuthDto.LoginResponse loginResponse = kakaoLoginService.kakaoLogin(kakaoLoginRequest);
        System.out.println(loginResponse);
    }
}