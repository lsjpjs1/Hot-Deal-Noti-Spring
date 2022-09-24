package com.example.hotdealnoti.repository.redis;

import com.example.hotdealnoti.messagequeue.domain.HotDealRedis;
import com.example.hotdealnoti.notification.domain.KeywordNotificationRedis;
import org.springframework.data.repository.CrudRepository;

public interface RedisKeywordNotificationRepository extends CrudRepository<KeywordNotificationRedis,String> {
}
