package com.example.hotdealnoti.notification.domain;

import com.example.hotdealnoti.auth.domain.Account;
import com.example.hotdealnoti.enums.NotificationType;
import com.example.hotdealnoti.enums.converter.AccountTypeConverter;
import com.example.hotdealnoti.enums.converter.NotificationTypeConverter;
import com.example.hotdealnoti.product.domain.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    private Timestamp notificationTime;

    @Convert(converter = NotificationTypeConverter.class)
    private NotificationType notificationType;

    private Long notificationItemId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    private String notificationTitle;

    private String notificationBody;

    private Boolean isRead;

}
