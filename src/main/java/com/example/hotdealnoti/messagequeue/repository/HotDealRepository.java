package com.example.hotdealnoti.messagequeue.repository;

import com.example.hotdealnoti.messagequeue.domain.HotDeal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HotDealRepository extends JpaRepository<HotDeal, Long> {
    Optional<HotDeal> findByHotDealTitleAndHotDealLink(String hotDealTitle,String hotDealLink);
}
