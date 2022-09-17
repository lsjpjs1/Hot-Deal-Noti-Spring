package com.example.hotdealnoti.notification.controller;

import com.example.hotdealnoti.auth.domain.Account;
import com.example.hotdealnoti.auth.security.CustomSecurityContextHolder;
import com.example.hotdealnoti.notification.dto.NotificationDto;
import com.example.hotdealnoti.notification.service.KeywordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DeleteKeywordController {

    private final KeywordService keywordService;

    @DeleteMapping("/notification-keywords/{keywordNotificationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity deleteKeyword(@PathVariable Long keywordNotificationId) {

        Account accountFromToken = CustomSecurityContextHolder.getAccountFromToken();
        keywordService.deleteKeyword(accountFromToken, keywordNotificationId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
