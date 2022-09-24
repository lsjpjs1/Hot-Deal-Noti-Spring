package com.example.hotdealnoti.notification.domain;

import com.example.hotdealnoti.messagequeue.domain.HotDeal;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Builder
@Setter
@ToString
@RedisHash(value = "keywordNotification", timeToLive = -1)
public class KeywordNotificationRedis {

    @Id
    private Long keywordNotificationId;

    private String keywordNotificationBody;

    private Long accountId;

    private Timestamp keywordNotificationTime;

    private Boolean isDelete;

    public static KeywordNotificationRedis from(KeywordNotification keywordNotification){
        return KeywordNotificationRedis.builder()
                .keywordNotificationId(keywordNotification.getKeywordNotificationId())
                .keywordNotificationBody(keywordNotification.getKeywordNotificationBody())
                .accountId(keywordNotification.getAccountId())
                .keywordNotificationTime(keywordNotification.getKeywordNotificationTime())
                .isDelete(keywordNotification.getIsDelete())
                .build();
    }
}
