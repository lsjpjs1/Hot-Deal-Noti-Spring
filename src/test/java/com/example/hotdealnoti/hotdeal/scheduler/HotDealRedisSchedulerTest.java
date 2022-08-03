package com.example.hotdealnoti.hotdeal.scheduler;

import com.example.hotdealnoti.repository.redis.RedisHotDealRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class HotDealRedisSchedulerTest {
    @Autowired
    private HotDealRedisScheduler hotDealRedisScheduler;
    @Test
    void viewCountUpdateSchedule() {
        hotDealRedisScheduler.viewCountUpdateSchedule();
    }
}