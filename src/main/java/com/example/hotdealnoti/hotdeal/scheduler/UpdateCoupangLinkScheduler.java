package com.example.hotdealnoti.hotdeal.scheduler;

import com.example.hotdealnoti.common.util.coupang.CoupangPartnersLinkGenerator;
import com.example.hotdealnoti.hotdeal.domain.HotDealViewHistory;
import com.example.hotdealnoti.hotdeal.domain.HotDealViewHistoryRedis;
import com.example.hotdealnoti.messagequeue.domain.HotDeal;
import com.example.hotdealnoti.repository.jpa.JpaHotDealRepository;
import com.example.hotdealnoti.repository.jpa.JpaHotDealViewHistoryRepository;
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
public class UpdateCoupangLinkScheduler {
    private final JpaHotDealRepository jpaHotDealRepository;

    private final CoupangPartnersLinkGenerator coupangPartnersLinkGenerator;
    @Scheduled(cron = "0 0/1 * * * ?")
    @Transactional
    public void hotDealViewHistoryInsertSchedule() {
        List<HotDeal> coupangHotDeals = jpaHotDealRepository.findTop40BySourceSiteAndIsDeleteAndHotDealLinkNotLike( "쿠팡", false,"%link%");
        coupangHotDeals.forEach(
                coupangHotDeal -> {
                        try {
                            String generateLink = coupangPartnersLinkGenerator.generateLink(coupangHotDeal.getHotDealLink());
                            coupangHotDeal.setHotDealLink(generateLink);
                            jpaHotDealRepository.save(coupangHotDeal);
                            log.info(generateLink+"변환성공");
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                }
        );
    }
}
