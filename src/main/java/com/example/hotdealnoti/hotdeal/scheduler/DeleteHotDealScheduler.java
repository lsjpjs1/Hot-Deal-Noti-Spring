package com.example.hotdealnoti.hotdeal.scheduler;

import com.example.hotdealnoti.messagequeue.domain.HotDeal;
import com.example.hotdealnoti.messagequeue.domain.ReturnItem;
import com.example.hotdealnoti.repository.jpa.JpaHotDealRepository;
import com.example.hotdealnoti.repository.redis.RedisHotDealRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class DeleteHotDealScheduler {
    private final RedisHotDealRepository redisHotDealRepository;
    private final JpaHotDealRepository jpaHotDealRepository;

    @Scheduled(cron = "0 50 0/3 * * ?")
    @Transactional
    public void deleteHotDeals() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR,-8);
        List<HotDeal> hotDeals = jpaHotDealRepository.findByHotDealScrapingTimeBeforeAndIsDelete(new Timestamp(calendar.getTimeInMillis()), false);
        hotDeals.stream()
                .forEach(
                        hotDeal -> {
                            hotDeal.setIsDelete(Boolean.TRUE);
                            jpaHotDealRepository.save(hotDeal);
                        }
                );

    }

    @Scheduled(cron = "0 0/10 * * * ?")
    @Transactional
    public void deleteReturnItems() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR,-3);
        List<HotDeal> hotDeals = jpaHotDealRepository.findByHotDealScrapingTimeBeforeAndIsDeleteAndReturnItemNot(new Timestamp(calendar.getTimeInMillis()), false, ReturnItem.builder().returnItemId(0l).build());
        hotDeals.stream()
                .forEach(
                        hotDeal -> {
                            hotDeal.setIsDelete(Boolean.TRUE);
                            jpaHotDealRepository.save(hotDeal);
                        }
                );

    }
}
