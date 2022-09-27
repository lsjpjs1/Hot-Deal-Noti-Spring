package com.example.hotdealnoti.notification.scheduler;

import com.example.hotdealnoti.messagequeue.domain.HotDeal;
import com.example.hotdealnoti.messagequeue.domain.HotDealRedis;
import com.example.hotdealnoti.notification.domain.KeywordNotification;
import com.example.hotdealnoti.notification.domain.KeywordNotificationRedis;
import com.example.hotdealnoti.repository.jpa.JpaHotDealRepository;
import com.example.hotdealnoti.repository.jpa.JpaKeywordNotificationRepository;
import com.example.hotdealnoti.repository.redis.RedisHotDealRepository;
import com.example.hotdealnoti.repository.redis.RedisKeywordNotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class UpdateRedisKeywordsScheduler {

    private final RedisKeywordNotificationRepository redisKeywordNotificationRepository;
    private final JpaKeywordNotificationRepository jpaKeywordNotificationRepository;

    @Scheduled(cron = "0 0/5 * * * ?")
    @Transactional
    public void updateRedisKeywords() {
        List<KeywordNotification> keywordNotifications = jpaKeywordNotificationRepository.findByIsOnRedis( false);
        keywordNotifications.forEach(keywordNotification -> {
            redisKeywordNotificationRepository.save(KeywordNotificationRedis.from(keywordNotification));
            keywordNotification.setIsOnRedis(true);
            jpaKeywordNotificationRepository.save(keywordNotification);
        });
    }

}
