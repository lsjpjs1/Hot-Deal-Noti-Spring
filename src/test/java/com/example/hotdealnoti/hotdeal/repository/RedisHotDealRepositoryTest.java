package com.example.hotdealnoti.hotdeal.repository;

import com.example.hotdealnoti.messagequeue.domain.HotDealRedis;
import com.example.hotdealnoti.repository.redis.RedisHotDealRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedisHotDealRepositoryTest {
    @Autowired
    private RedisHotDealRepository redisHotDealRepository;

    @Test
    void findHotDeals() {
        HotDealRedis redistest = HotDealRedis.builder().hotDealId("1222").hotDealTitle("redistest").build();
        redisHotDealRepository.save(redistest);
        HotDealRedis redistest2 = HotDealRedis.builder().hotDealId("1222").hotDealTitle("redistest수정본").build();
        redisHotDealRepository.save(redistest2);
        Iterable<HotDealRedis> all = redisHotDealRepository.findAll();
        for(HotDealRedis hotDeal: all){
            System.out.println(hotDeal);
        }

//        redisHotDealRepository.deleteAll();
    }

}