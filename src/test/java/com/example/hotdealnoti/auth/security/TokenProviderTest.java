package com.example.hotdealnoti.auth.security;

import com.example.hotdealnoti.auth.dto.AuthDto;
import com.example.hotdealnoti.enums.AccountType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TokenProviderTest {

    @Autowired
    private TokenProvider tokenProvider;
    @Test
    void generateToken() {
//        AuthDto.TokenRequest tokenRequest = AuthDto.TokenRequest.builder().accountType(AccountType.KAKAO).oauthId().build();
//        System.out.println(tokenProvider.generateToken(tokenRequest));

    }

    @Test
    void getAuthentication() {
//        AuthDto.TokenRequest tokenRequest = AuthDto.TokenRequest.builder().accountType(AccountType.KAKAO).oauthId().build();
//        tokenProvider.getAuthentication(tokenProvider.generateToken(tokenRequest));
    }

    @Test
    void validateToken() {
//        AuthDto.TokenRequest tokenRequest = AuthDto.TokenRequest.builder().accountType(AccountType.KAKAO).oauthId().build();
//        tokenProvider.validateToken(tokenProvider.generateToken(tokenRequest));
    }
}