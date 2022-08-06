package com.example.hotdealnoti.hotdeal.service;

import com.example.hotdealnoti.hotdeal.domain.HotDealViewHistory;
import com.example.hotdealnoti.hotdeal.domain.HotDealViewHistoryRedis;
import com.example.hotdealnoti.hotdeal.dto.HotDealDto;
import com.example.hotdealnoti.hotdeal.repository.HotDealQueryRepository;
import com.example.hotdealnoti.repository.jpa.JpaHotDealViewHistoryRepository;
import com.example.hotdealnoti.repository.redis.RedisHotDealViewHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class GetHotDealService {
    private final HotDealQueryRepository hotDealQueryRepository;
    private final RedisHotDealViewHistoryRepository redisHotDealViewHistoryRepository;

    @Transactional
    public Page<HotDealDto.HotDealPreview> getHotDeals(HotDealDto.GetHotDealsRequest getHotDealsRequest, Pageable pageable, String userIp) {
        HotDealViewHistoryRedis hotDealViewHistoryRedis = HotDealViewHistoryRedis.builder()
                .userIp(userIp)
                .searchBody(getHotDealsRequest.getSearchBody())
                .sortCondition(pageable.getSort().toString())
                .hotDealViewTime(new Timestamp(System.currentTimeMillis()))
                .build();
        redisHotDealViewHistoryRepository.save(hotDealViewHistoryRedis);
        return hotDealQueryRepository.findHotDeals(getHotDealsRequest, pageable);
    }
}
