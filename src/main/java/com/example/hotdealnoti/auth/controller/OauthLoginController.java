package com.example.hotdealnoti.auth.controller;

import com.example.hotdealnoti.auth.dto.AuthDto;
import com.example.hotdealnoti.auth.service.KakaoLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OauthLoginController {


    private final KakaoLoginService kakaoLoginService;
    @GetMapping("/oauth/callback/kakao")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AuthDto.LoginResponse> kakaoLogin(@ModelAttribute AuthDto.KakaoLoginRequest kakaoLoginRequest){
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(kakaoLoginService.kakaoLogin(kakaoLoginRequest));
    }
}
