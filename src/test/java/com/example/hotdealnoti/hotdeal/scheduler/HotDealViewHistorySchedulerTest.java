package com.example.hotdealnoti.hotdeal.scheduler;

import com.example.hotdealnoti.hotdeal.domain.HotDealViewHistoryRedis;
import com.example.hotdealnoti.repository.redis.RedisHotDealViewHistoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class HotDealViewHistorySchedulerTest {

    @Autowired
    private RedisHotDealViewHistoryRepository redisHotDealViewHistoryRepository;
    @Autowired
    private HotDealViewHistoryScheduler hotDealViewHistoryScheduler;
    @Test
    void hotDealViewHistoryInsertSchedule() {

        Iterable<HotDealViewHistoryRedis> hotDealViewHistoryRepositoryAll = redisHotDealViewHistoryRepository.findAll();
        hotDealViewHistoryRepositoryAll.forEach(hotDealViewHistoryRedis -> {
            System.out.println(hotDealViewHistoryRedis.toString());
        });

    }
}