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
        AuthDto.KakaoLoginRequest kakaoLoginRequest = AuthDto.KakaoLoginRequest.builder().code("49jd3vbRTg5hQU5jggtxMMZzr3nyanTAFXmT1cfCXrnYosrI9wC9jtMSIkKHCU-F0e5QsgopcBQAAAGCl0aZPg").build();
        kakaoLoginService.kakaoLogin(kakaoLoginRequest);
    }
}