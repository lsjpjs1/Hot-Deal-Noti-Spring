package com.example.hotdealnoti.notification.repository;

import com.example.hotdealnoti.auth.domain.Account;
import com.example.hotdealnoti.hotdeal.dto.HotDealDto;
import com.example.hotdealnoti.notification.domain.QNotification;
import com.example.hotdealnoti.notification.dto.NotificationDto;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class NotificationQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;
    private final QNotification notification = QNotification.notification;


    public Page<NotificationDto.NotificationResponseDto> findNotifications(Account account, Pageable pageable) {
        List<NotificationDto.NotificationResponseDto> notifications = jpaQueryFactory
                .select(
                        getNotificationConstructorExpression()
                )
                .from(notification)
                .where(
                        getCondition(account)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(getAllOrderSpecifiers(pageable).stream().toArray(OrderSpecifier[]::new))
                .fetch();

        Long count = jpaQueryFactory
                .select(notification.count())
                .from(notification)
                .where(getCondition(account))
                .fetchOne();

        return new PageImpl(notifications, pageable, count);

    }

    private ConstructorExpression<NotificationDto.NotificationResponseDto> getNotificationConstructorExpression() {
        return Projections.constructor(NotificationDto.NotificationResponseDto.class,
                notification.notificationId,
                notification.notificationTime,
                notification.notificationType.stringValue(),
                notification.notificationItemId,
                notification.account.accountId,
                notification.notificationTitle,
                notification.notificationBody,
                notification.isRead
        );
    }


    private BooleanExpression getCondition(Account account) {

        return notification.account.accountId.eq(account.getAccountId());
    }



    private List<OrderSpecifier> getAllOrderSpecifiers(Pageable pageable) {
        if (pageable.getSort().isEmpty()) {
            return null;
        }
        List<OrderSpecifier> orderSpecifiers = new ArrayList<>();
        orderSpecifiers.add(new OrderSpecifier(Order.DESC, notification.notificationId));
        return orderSpecifiers;
    }

}
