package com.example.hotdealnoti.auth.security;

import com.example.hotdealnoti.auth.dto.AuthDto;
import com.example.hotdealnoti.enums.AccountType;
import com.wantedtech.common.xpresso.strings.SequenceMatcher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


class TokenProviderTest {


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

    @Test
    void sequenceMatcherTest() {


        SequenceMatcher sequenceMatcher1 = new SequenceMatcher("그램360 14TD90P-GXFBK", "(113만최.종) LG그램360 14TD90P-GXFBK 램16GB 인텔 i5 터치 와콤펜 노트북 디지털대박");
        System.out.println(sequenceMatcher1.ratio());
    }
}