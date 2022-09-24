package com.example.hotdealnoti.notification.service;

import com.example.hotdealnoti.auth.domain.Account;
import com.example.hotdealnoti.exception.CustomException;
import com.example.hotdealnoti.exception.ErrorCode;
import com.example.hotdealnoti.notification.domain.KeywordNotification;
import com.example.hotdealnoti.notification.dto.NotificationDto;
import com.example.hotdealnoti.notification.repository.NotificationQueryRepository;
import com.example.hotdealnoti.repository.jpa.JpaKeywordNotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetNotificationService {

    private final NotificationQueryRepository notificationQueryRepository;
    @Transactional
    public Page<NotificationDto.NotificationResponseDto> getNotifications(Account account, Pageable pageable) {
        return notificationQueryRepository.findNotifications(account, pageable);
    }

}
