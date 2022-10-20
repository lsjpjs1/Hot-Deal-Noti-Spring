package com.example.hotdealnoti.repository.jpa;

import com.example.hotdealnoti.recommendation.domain.ProductPurposeDetail;
import com.example.hotdealnoti.recommendation.domain.RecommendationProductFamily;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaRecommendationProductFamily extends JpaRepository<RecommendationProductFamily,Long> {
    List<RecommendationProductFamily> findByProductPurposeDetail(ProductPurposeDetail productPurposeDetail);
}
