package com.example.hotdealnoti.notification.service;

import com.example.hotdealnoti.auth.domain.Account;
import com.example.hotdealnoti.auth.domain.EmailVerification;
import com.example.hotdealnoti.common.util.MailDTO;
import com.example.hotdealnoti.common.util.MailUtil;
import com.example.hotdealnoti.enums.NotificationType;
import com.example.hotdealnoti.exception.CustomException;
import com.example.hotdealnoti.exception.ErrorCode;
import com.example.hotdealnoti.hotdeal.domain.ConnectionHistoryRedis;
import com.example.hotdealnoti.messagequeue.domain.HotDeal;
import com.example.hotdealnoti.notification.domain.KeywordNotification;
import com.example.hotdealnoti.notification.domain.KeywordNotificationRedis;
import com.example.hotdealnoti.notification.domain.Notification;
import com.example.hotdealnoti.repository.jpa.JpaAccountRepository;
import com.example.hotdealnoti.repository.jpa.JpaConnectionHistoryRepository;
import com.example.hotdealnoti.repository.jpa.JpaKeywordNotificationRepository;
import com.example.hotdealnoti.repository.jpa.JpaNotificationRepository;
import com.example.hotdealnoti.repository.redis.RedisConnectionHistoryRepository;
import com.example.hotdealnoti.repository.redis.RedisKeywordNotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SendNotificationService {

    private final JpaNotificationRepository jpaNotificationRepository;
    private final RedisKeywordNotificationRepository redisKeywordNotificationRepository;
    private final JpaKeywordNotificationRepository jpaKeywordNotificationRepository;
    private final JpaAccountRepository jpaAccountRepository;
    private final FCMNotificationService fcmNotificationService;
    private final MailUtil mailUtil;
    @Transactional
    public void sendKeywordNotification(HotDeal hotDeal) {
        //해당 핫딜 키워드로 등록해놓은 user 찾기
        List<KeywordNotification> keywordNotifications = jpaKeywordNotificationRepository.findByIsDelete(false);


        keywordNotifications.forEach(keywordNotificationRedis -> {

            if (hotDeal.getHotDealTitle().toLowerCase().replace(" ","").contains(keywordNotificationRedis.getKeywordNotificationBody().toLowerCase().replace(" ",""))){

                Integer minPrice = 0;
                Integer maxPrice = 100000000;
                if (keywordNotificationRedis.getMinPrice()!=null){
                    minPrice = keywordNotificationRedis.getMinPrice();
                }
                if (keywordNotificationRedis.getMaxPrice()!=null){
                    maxPrice = keywordNotificationRedis.getMaxPrice();
                }

                if (hotDeal.getHotDealDiscountPrice()>=minPrice && hotDeal.getHotDealDiscountPrice()<=maxPrice){
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
                    if (account.getNotificationToken()!=null){
                        fcmNotificationService.sendMessage(account.getNotificationToken(),notification,notification.getNotificationTitle(),notification.getNotificationBody());
                    }


                    //이메일 알림
                    if (!account.getEmail().isBlank()){
                        try {
                            DecimalFormat decFormat = new DecimalFormat("###,###");
                            mailUtil.sendMail(
                                    MailDTO.MailRequest.builder()
                                            .receiverEmail(account.getEmail())
                                            .subject("[특가어디가] "+notificationTitle)
                                            .body("제목: "+hotDeal.getHotDealTitle()+"\n" +
                                                    "특가: "+decFormat.format(hotDeal.getHotDealDiscountPrice())+" 원\n" +
                                                    "링크: https://whendiscount.com/hot-deals/"+hotDeal.getHotDealId())
                                            .build()
                            );
                        } catch (MessagingException e) {
                            e.printStackTrace();
                            throw new CustomException(ErrorCode.SEND_EMAIL_FAIL);
                        }
                    }


                }



            }
        });




    }
}
