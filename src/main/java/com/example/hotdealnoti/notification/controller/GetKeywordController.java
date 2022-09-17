package com.example.hotdealnoti.notification.controller;

import com.example.hotdealnoti.auth.domain.Account;
import com.example.hotdealnoti.auth.security.CustomSecurityContextHolder;
import com.example.hotdealnoti.notification.dto.NotificationDto;
import com.example.hotdealnoti.notification.service.PostKeywordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GetKeywordController {

    private final PostKeywordService postKeywordService;

    @GetMapping("/notification-keywords")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<NotificationDto.GetKeywordsResponse> getKeywords() {

        Account accountFromToken = CustomSecurityContextHolder.getAccountFromToken();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(postKeywordService.getKeywords(accountFromToken));
    }
}
