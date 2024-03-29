package com.example.hotdealnoti.notification.controller;

import com.example.hotdealnoti.auth.domain.Account;
import com.example.hotdealnoti.auth.security.CustomSecurityContextHolder;
import com.example.hotdealnoti.notification.dto.NotificationDto;
import com.example.hotdealnoti.notification.service.KeywordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PostKeywordController {

    private final KeywordService keywordService;

    @PostMapping("/notification-keywords")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity postKeyword(@RequestBody NotificationDto.PostKeywordRequest postKeywordRequest) {

        Account accountFromToken = CustomSecurityContextHolder.getAccountFromToken();
        keywordService.postKeyword(accountFromToken, postKeywordRequest);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
