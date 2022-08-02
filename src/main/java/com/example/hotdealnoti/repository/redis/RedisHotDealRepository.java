package com.example.hotdealnoti.repository.redis;

import com.example.hotdealnoti.messagequeue.domain.HotDeal;
import com.example.hotdealnoti.messagequeue.domain.HotDealRedis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface RedisHotDealRepository extends CrudRepository<HotDealRedis,String> {
}
