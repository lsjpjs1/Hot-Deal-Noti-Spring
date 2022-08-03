package com.example.hotdealnoti.repository.redis;

import com.example.hotdealnoti.hotdeal.domain.ConnectionHistory;
import com.example.hotdealnoti.hotdeal.domain.ConnectionHistoryRedis;
import com.example.hotdealnoti.messagequeue.domain.HotDealRedis;
import org.springframework.data.repository.CrudRepository;

public interface RedisConnectionHistoryRepository extends CrudRepository<ConnectionHistoryRedis,String> {
}
