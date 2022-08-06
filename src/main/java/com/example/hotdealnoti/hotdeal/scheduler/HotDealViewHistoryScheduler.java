package com.example.hotdealnoti.hotdeal.scheduler;

import com.example.hotdealnoti.hotdeal.domain.ConnectionHistory;
import com.example.hotdealnoti.hotdeal.domain.ConnectionHistoryRedis;
import com.example.hotdealnoti.hotdeal.domain.HotDealViewHistory;
import com.example.hotdealnoti.hotdeal.domain.HotDealViewHistoryRedis;
import com.example.hotdealnoti.repository.jpa.JpaConnectionHistoryRepository;
import com.example.hotdealnoti.repository.jpa.JpaHotDealViewHistoryRepository;
import com.example.hotdealnoti.repository.redis.RedisConnectionHistoryRepository;
import com.example.hotdealnoti.repository.redis.RedisHotDealViewHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class HotDealViewHistoryScheduler {
    private final JpaHotDealViewHistoryRepository jpaHotDealViewHistoryRepository;
    private final RedisHotDealViewHistoryRepository redisHotDealViewHistoryRepository;

    @Scheduled(cron = "0 0/30 * * * ?")
    @Transactional
    public void hotDealViewHistoryInsertSchedule() {
        Iterable<HotDealViewHistoryRedis> hotDealViewHistoryRepositoryAll = redisHotDealViewHistoryRepository.findAll();
        hotDealViewHistoryRepositoryAll.forEach(
                hotDealViewHistoryRedis -> {
                    HotDealViewHistory hotDealViewHistory = HotDealViewHistory.from(hotDealViewHistoryRedis);
                    jpaHotDealViewHistoryRepository.save(hotDealViewHistory);
                }
        );
        redisHotDealViewHistoryRepository.deleteAll();
    }
}
