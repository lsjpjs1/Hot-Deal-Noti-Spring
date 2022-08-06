package com.example.hotdealnoti.repository.redis;

import com.example.hotdealnoti.hotdeal.domain.HotDealViewHistoryRedis;
import com.example.hotdealnoti.messagequeue.domain.HotDealRedis;
import org.springframework.data.repository.CrudRepository;

public interface RedisHotDealViewHistoryRepository extends CrudRepository<HotDealViewHistoryRedis,String> {
}
