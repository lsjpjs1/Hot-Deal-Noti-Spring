package com.example.hotdealnoti.notification.controller;

import com.example.hotdealnoti.auth.domain.Account;
import com.example.hotdealnoti.auth.security.CustomSecurityContextHolder;
import com.example.hotdealnoti.notification.dto.NotificationDto;
import com.example.hotdealnoti.notification.service.GetNotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class NotificationController {

    private final GetNotificationService getNotificationService;

    @GetMapping("/notifications")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<NotificationDto.NotificationResponseDto>> getNotifications(Pageable pageable) {

        Account accountFromToken = CustomSecurityContextHolder.getAccountFromToken();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(getNotificationService.getNotifications(accountFromToken,pageable));
    }
}
