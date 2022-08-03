package com.example.hotdealnoti.hotdeal.service;

import com.example.hotdealnoti.hotdeal.domain.ConnectionHistoryRedis;
import com.example.hotdealnoti.hotdeal.dto.HotDealDto;
import com.example.hotdealnoti.repository.jpa.JpaConnectionHistoryRepository;
import com.example.hotdealnoti.repository.redis.RedisConnectionHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class ConnectWebsiteService {

    private final JpaConnectionHistoryRepository jpaConnectionHistoryRepository;
    private final RedisConnectionHistoryRepository redisConnectionHistoryRepository;
    @Transactional
    public void postConnectionHistory(String userIp) {
        ConnectionHistoryRedis connectionHistoryRedis = ConnectionHistoryRedis.builder()
                .userIp(userIp)
                .connectionTime(new Timestamp(System.currentTimeMillis()))
                .build();
        redisConnectionHistoryRepository.save(connectionHistoryRedis);
    }
}
