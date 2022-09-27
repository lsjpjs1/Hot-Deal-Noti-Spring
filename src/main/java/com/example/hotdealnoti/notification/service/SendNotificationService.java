package com.example.hotdealnoti.notification.service;

import com.example.hotdealnoti.auth.domain.Account;
import com.example.hotdealnoti.enums.NotificationType;
import com.example.hotdealnoti.hotdeal.domain.ConnectionHistoryRedis;
import com.example.hotdealnoti.messagequeue.domain.HotDeal;
import com.example.hotdealnoti.notification.domain.KeywordNotificationRedis;
import com.example.hotdealnoti.notification.domain.Notification;
import com.example.hotdealnoti.repository.jpa.JpaAccountRepository;
import com.example.hotdealnoti.repository.jpa.JpaConnectionHistoryRepository;
import com.example.hotdealnoti.repository.jpa.JpaNotificationRepository;
import com.example.hotdealnoti.repository.redis.RedisConnectionHistoryRepository;
import com.example.hotdealnoti.repository.redis.RedisKeywordNotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SendNotificationService {

    private final JpaNotificationRepository jpaNotificationRepository;
    private final RedisKeywordNotificationRepository redisKeywordNotificationRepository;
    private final JpaAccountRepository jpaAccountRepository;
    private final FCMNotificationService fcmNotificationService;

    @Async
    @Transactional
    public void sendKeywordNotificationAsync(HotDeal hotDeal) {
        //해당 핫딜 키워드로 등록해놓은 user 찾기
        Iterable<KeywordNotificationRedis> keywordNotificationRedisAll = redisKeywordNotificationRepository.findAll();

        keywordNotificationRedisAll.forEach(keywordNotificationRedis -> {

            if (hotDeal.getHotDealTitle().toLowerCase().replace(" ","").contains(keywordNotificationRedis.getKeywordNotificationBody().toLowerCase().replace(" ",""))){

                String notificationTitle = new StringBuilder()
                        .append("\"").append(keywordNotificationRedis.getKeywordNotificationBody()).append("\" 키워드 알림!").toString();
                Account account = jpaAccountRepository.findById(keywordNotificationRedis.getAccountId()).get();

                //보낼 알림 디비에 저장
                Notification notification = Notification.builder()
                        .notificationTitle(notificationTitle)
                        .notificationBody(hotDeal.getHotDealTitle())
                        .account(account)
                        .notificationItemId(hotDeal.getHotDealId())
                        .notificationType(NotificationType.KEYWORD)
                        .build();
                jpaNotificationRepository.save(notification);


                //해당 유저들에게 알림 보내기
                fcmNotificationService.sendMessage(account.getNotificationToken(),notification,notification.getNotificationTitle(),notification.getNotificationBody());


            }
        });




    }
}
