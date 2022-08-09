package com.example.hotdealnoti.repository.jpa;

import com.example.hotdealnoti.messagequeue.domain.HotDeal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface JpaHotDealRepository extends JpaRepository<HotDeal, Long> {
    Optional<HotDeal> findTopByHotDealTitleAndHotDealLink(String hotDealTitle, String hotDealLink);
    List<HotDeal> findByHotDealScrapingTimeBefore(Timestamp timestamp);
    HotDeal findFirstByOrderByHotDealScrapingTimeDesc();
}
