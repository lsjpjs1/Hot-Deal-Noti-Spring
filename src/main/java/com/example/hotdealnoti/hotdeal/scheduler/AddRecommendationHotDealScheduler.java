package com.example.hotdealnoti.hotdeal.scheduler;

import com.example.hotdealnoti.hotdeal.domain.HotDealViewHistory;
import com.example.hotdealnoti.hotdeal.domain.HotDealViewHistoryRedis;
import com.example.hotdealnoti.hotdeal.domain.RecommendationHotDeal;
import com.example.hotdealnoti.hotdeal.dto.HotDealDto;
import com.example.hotdealnoti.hotdeal.repository.HotDealQueryRepository;
import com.example.hotdealnoti.messagequeue.domain.HotDeal;
import com.example.hotdealnoti.repository.jpa.JpaHotDealRepository;
import com.example.hotdealnoti.repository.jpa.JpaHotDealViewHistoryRepository;
import com.example.hotdealnoti.repository.jpa.JpaRecommendationHotDeal;
import com.example.hotdealnoti.repository.redis.RedisHotDealViewHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class AddRecommendationHotDealScheduler {
    private final JpaHotDealRepository jpaHotDealRepository;
    private final JpaRecommendationHotDeal jpaRecommendationHotDeal;
    private final HotDealQueryRepository hotDealQueryRepository;
    @Scheduled(cron = "0 0/30 * * * ?")
    @Transactional
    public void addRecommendationHotDeal() {
        List<HotDealDto.HotDealPreview> entireWeeklyPopularHotDeals = hotDealQueryRepository.findEntireWeeklyPopularHotDeal();
        entireWeeklyPopularHotDeals.forEach((weeklyPopularHotDeal)->{
            if (!jpaRecommendationHotDeal.findByHotDeal(HotDeal.builder().hotDealId(weeklyPopularHotDeal.getHotDealId()).build()).isPresent()){
                RecommendationHotDeal recommendationHotDeal = RecommendationHotDeal.builder()
                        .hotDeal(HotDeal.builder().hotDealId(weeklyPopularHotDeal.getHotDealId()).build())
                        .build();
                jpaRecommendationHotDeal.save(recommendationHotDeal);
            }
        });
    }
}
