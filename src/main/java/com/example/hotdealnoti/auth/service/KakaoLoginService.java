package com.example.hotdealnoti.auth.service;

import com.example.hotdealnoti.auth.domain.Account;
import com.example.hotdealnoti.auth.dto.AuthDto;
import com.example.hotdealnoti.auth.security.TokenProvider;
import com.example.hotdealnoti.enums.AccountType;
import com.example.hotdealnoti.exception.CustomException;
import com.example.hotdealnoti.exception.ErrorCode;
import com.example.hotdealnoti.hotdeal.domain.ConnectionHistoryRedis;
import com.example.hotdealnoti.repository.jpa.JpaAccountRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class KakaoLoginService {

    @Value("${oauth.kakao.rest-api-key}")
    private String restApiKey;

    private final ObjectMapper objectMapper;
    private final JpaAccountRepository jpaAccountRepository;
    private final TokenProvider tokenProvider;

    private final RestTemplate restTemplate = new RestTemplate();
    @PersistenceContext
    private final EntityManager entityManager;

    @Transactional
    public AuthDto.LoginResponse kakaoLogin(AuthDto.KakaoLoginRequest kakaoLoginRequest) {

        String accessToken = kakaoLoginRequest.getAccessToken()==null?
                getAccessToken(kakaoLoginRequest): kakaoLoginRequest.getAccessToken();

        AuthDto.KakaoUserInfo kakaoUserInfo = getKakaoUserInfo(accessToken);
        Optional<Account> optionalAccount = jpaAccountRepository.findByAccountTypeAndOauthId(AccountType.KAKAO, kakaoUserInfo.getId());
        if(optionalAccount.isPresent()){
            Account account = optionalAccount.get();

            //fcm 알림 토큰 업데이트
            if (StringUtils.isNotBlank(kakaoLoginRequest.getNotificationToken())){
                account.setNotificationToken(kakaoLoginRequest.getNotificationToken());
                jpaAccountRepository.save(account);
            }

            String token = tokenProvider.generateToken(AuthDto.TokenRequest.builder().accountType(account.getAccountType()).oauthId(account.getOauthId()).build());
            return AuthDto.LoginResponse.builder().token(token).build();
        }else{
            Account account = Account.builder().accountType(AccountType.KAKAO).oauthId(kakaoUserInfo.getId()).notificationToken(kakaoLoginRequest.getNotificationToken()).build();
            jpaAccountRepository.save(account);
            entityManager.refresh(account);
            String token = tokenProvider.generateToken(AuthDto.TokenRequest.builder().accountType(account.getAccountType()).oauthId(account.getOauthId()).build());
            return AuthDto.LoginResponse.builder().token(token).build();
        }
    }

    private AuthDto.KakaoUserInfo getKakaoUserInfo(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        String url = "https://kapi.kakao.com/v2/user/me";
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
            return objectMapper.readValue(response.getBody(), AuthDto.KakaoUserInfo.class);
        }catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(ErrorCode.LOGIN_FAIL);
        }
    }

    private String getAccessToken(AuthDto.KakaoLoginRequest kakaoLoginRequest){
        String grantType = "authorization_code";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", grantType);
        params.add("client_id", restApiKey);
        params.add("redirect_uri", kakaoLoginRequest.getCallBackUrl());
        params.add("code", kakaoLoginRequest.getCode());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        String url = "https://kauth.kakao.com/oauth/token";

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
            AuthDto.KakaoAccessTokenResponse kakaoAccessTokenResponse = objectMapper.readValue(response.getBody(), AuthDto.KakaoAccessTokenResponse.class);
            return kakaoAccessTokenResponse.getAccessToken();
        }catch (Exception e){
            e.printStackTrace();
            throw new CustomException(ErrorCode.LOGIN_FAIL);
        }
    }

}
