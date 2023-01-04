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
    @Scheduled(cron = "0 0/10 * * * ?")
    @Transactional
    public void hotDealViewHistoryInsertSchedule() {
        List<HotDeal> coupangHotDeals = jpaHotDealRepository.findBySourceSiteAndIsDelete("쿠팡", false);
        coupangHotDeals.forEach(
                coupangHotDeal -> {
                    if (!coupangHotDeal.getHotDealLink().matches(".*link.*")){
                        try {
                            String generateLink = coupangPartnersLinkGenerator.generateLink(coupangHotDeal.getHotDealLink());
                            coupangHotDeal.setHotDealLink(generateLink);
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    jpaHotDealRepository.save(coupangHotDeal);
                }
        );
    }
}
