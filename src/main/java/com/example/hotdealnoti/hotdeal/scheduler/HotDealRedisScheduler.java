package com.example.hotdealnoti.hotdeal.scheduler;

import com.example.hotdealnoti.messagequeue.domain.HotDeal;
import com.example.hotdealnoti.messagequeue.domain.HotDealRedis;
import com.example.hotdealnoti.repository.jpa.JpaHotDealRepository;
import com.example.hotdealnoti.repository.redis.RedisHotDealRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class HotDealRedisScheduler {

    private final RedisHotDealRepository redisHotDealRepository;
    private final JpaHotDealRepository jpaHotDealRepository;

    @Scheduled(cron = "0 0/15 * * * ?")
    @Transactional
    public void viewCountUpdateSchedule() {
        Iterable<HotDealRedis> hotDealRepositoryAll = redisHotDealRepository.findAll();
        hotDealRepositoryAll.forEach(
                hotDealRedis -> {
                    HotDeal hotDeal = HotDeal.from(hotDealRedis);
                    jpaHotDealRepository.save(hotDeal);
                }
        );
        redisHotDealRepository.deleteAll();
    }
}
