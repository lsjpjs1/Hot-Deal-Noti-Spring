package com.example.hotdealnoti.hotdeal.repository;

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
//        HotDeal redistest = HotDeal.builder().hotDealId(1222l).hotDealTitle("redistest").build();
//        redisHotDealRepository.save(redistest);
//        HotDeal redistest2 = HotDeal.builder().hotDealId(1222l).hotDealTitle("redistest수정본").build();
//        redisHotDealRepository.save(redistest2);
//        Iterable<HotDeal> all = redisHotDealRepository.findAll();
//        for(HotDeal hotDeal: all){
//            System.out.println(hotDeal);
//        }
        redisHotDealRepository.deleteAll();
    }

}