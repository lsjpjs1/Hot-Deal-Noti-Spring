package com.example.hotdealnoti.hotdeal.scheduler;

import com.example.hotdealnoti.hotdeal.domain.ConnectionHistory;
import com.example.hotdealnoti.hotdeal.domain.ConnectionHistoryRedis;
import com.example.hotdealnoti.messagequeue.domain.HotDeal;
import com.example.hotdealnoti.messagequeue.domain.HotDealRedis;
import com.example.hotdealnoti.repository.jpa.JpaConnectionHistoryRepository;
import com.example.hotdealnoti.repository.redis.RedisConnectionHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class ConnectionHistoryScheduler {
    private final JpaConnectionHistoryRepository jpaConnectionHistoryRepository;
    private final RedisConnectionHistoryRepository redisConnectionHistoryRepository;

    @Scheduled(cron = "0 0/1 * * * ?")
    @Transactional
    public void viewCountUpdateSchedule() {
        Iterable<ConnectionHistoryRedis> connectionHistoryRepositoryAll = redisConnectionHistoryRepository.findAll();
        connectionHistoryRepositoryAll.forEach(
                connectionHistoryRedis -> {
                    ConnectionHistory connectionHistory = ConnectionHistory.from(connectionHistoryRedis);
                    jpaConnectionHistoryRepository.save(connectionHistory);
                }
        );
        redisConnectionHistoryRepository.deleteAll();
    }
}
