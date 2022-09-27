package com.example.hotdealnoti.repository.jpa;

import com.example.hotdealnoti.auth.domain.Account;
import com.example.hotdealnoti.enums.AccountType;
import com.example.hotdealnoti.notification.domain.KeywordNotification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaKeywordNotificationRepository extends JpaRepository<KeywordNotification,Long> {
    List<KeywordNotification> findByAccountIdAndIsDelete(Long accountId,Boolean isDelete);
    List<KeywordNotification> findByIsDeleteAndIsOnRedis(Boolean isDelete,Boolean isOnRedis);
    List<KeywordNotification> findByIsOnRedis(Boolean isOnRedis);
}
