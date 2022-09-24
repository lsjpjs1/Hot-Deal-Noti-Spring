package com.example.hotdealnoti.notification.domain;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table
@AllArgsConstructor
@Builder
@Setter
@DynamicInsert
@DynamicUpdate
@ToString
public class KeywordNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long keywordNotificationId;

    private String keywordNotificationBody;

    private Long accountId;

    private Timestamp keywordNotificationTime;

    private Boolean isDelete;

    private Boolean isOnRedis;
}
