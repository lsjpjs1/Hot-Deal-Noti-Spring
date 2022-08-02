package com.example.hotdealnoti.hotdeal.repository;

import com.example.hotdealnoti.hotdeal.dto.HotDealDto;
import com.example.hotdealnoti.messagequeue.domain.HotDeal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class RedisHotDealRepositoryTest {
    @Autowired
    private RedisHotDealRepository redisHotDealRepository;

    @Test
    void findHotDeals() {
        HotDeal redistest = HotDeal.builder().hotDealId(1222l).hotDealTitle("redistest").build();
        redisHotDealRepository.save(redistest);
        HotDeal redistest2 = HotDeal.builder().hotDealId(1222l).hotDealTitle("redistest수정본").build();
        redisHotDealRepository.save(redistest2);
        Iterable<HotDeal> all = redisHotDealRepository.findAll();
        for(HotDeal hotDeal: all){
            System.out.println(hotDeal);
        }
    }

}