package com.example.hotdealnoti.repository.jpa;

import com.example.hotdealnoti.hotdeal.domain.RecommendationHotDeal;
import com.example.hotdealnoti.messagequeue.domain.HotDeal;
import com.example.hotdealnoti.recommendation.domain.ProductPurposeDetail;
import com.example.hotdealnoti.recommendation.domain.RecommendationProductFamily;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaRecommendationHotDeal extends JpaRepository<RecommendationHotDeal,Long> {
    Optional<RecommendationHotDeal> findByHotDeal(HotDeal hotDeal);
}
