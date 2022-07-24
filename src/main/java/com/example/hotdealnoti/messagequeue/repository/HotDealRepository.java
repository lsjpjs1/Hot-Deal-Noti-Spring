package com.example.hotdealnoti.messagequeue.repository;

import com.example.hotdealnoti.messagequeue.domain.HotDeal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotDealRepository extends JpaRepository<HotDeal, Long> {
}
