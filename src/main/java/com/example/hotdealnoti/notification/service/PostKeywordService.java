package com.example.hotdealnoti.notification.service;

import com.example.hotdealnoti.auth.domain.Account;
import com.example.hotdealnoti.hotdeal.domain.ConnectionHistoryRedis;
import com.example.hotdealnoti.notification.domain.KeywordNotification;
import com.example.hotdealnoti.notification.dto.NotificationDto;
import com.example.hotdealnoti.repository.jpa.JpaConnectionHistoryRepository;
import com.example.hotdealnoti.repository.jpa.JpaKeywordNotificationRepository;
import com.example.hotdealnoti.repository.redis.RedisConnectionHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class PostKeywordService {

    private final JpaKeywordNotificationRepository jpaKeywordNotificationRepository;
    @Transactional
    public void postKeyword(Account account, NotificationDto.PostKeywordRequest postKeywordRequest) {
        KeywordNotification keywordNotification = KeywordNotification.builder()
                .keywordNotificationBody(postKeywordRequest.getKeyword())
                .accountId(account.getAccountId())
                .build();
        jpaKeywordNotificationRepository.save(keywordNotification);
    }
}
