package com.example.hotdealnoti.repository.jpa;

import com.example.hotdealnoti.messagequeue.domain.HotDeal;
import com.example.hotdealnoti.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface JpaHotDealRepository extends JpaRepository<HotDeal, Long> {
    Optional<HotDeal> findTopByHotDealTitle(String hotDealTitle);
    List<HotDeal> findByHotDealScrapingTimeBeforeAndIsDelete(Timestamp timestamp, Boolean isDelete);
    List<HotDeal> findTop30ByProductAndIsDelete(Product product, Boolean isDelete);
    HotDeal findFirstByOrderByHotDealScrapingTimeDesc();

}
