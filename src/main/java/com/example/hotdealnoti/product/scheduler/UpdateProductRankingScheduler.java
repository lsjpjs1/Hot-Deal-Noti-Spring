package com.example.hotdealnoti.product.scheduler;

import com.example.hotdealnoti.hotdeal.domain.RecommendationHotDeal;
import com.example.hotdealnoti.hotdeal.dto.HotDealDto;
import com.example.hotdealnoti.hotdeal.repository.HotDealQueryRepository;
import com.example.hotdealnoti.messagequeue.domain.HotDeal;
import com.example.hotdealnoti.product.domain.Product;
import com.example.hotdealnoti.product.domain.ProductPurpose;
import com.example.hotdealnoti.product.domain.ProductRanking;
import com.example.hotdealnoti.product.dto.ProductDto;
import com.example.hotdealnoti.product.repository.ProductQueryRepository;
import com.example.hotdealnoti.repository.jpa.JpaHotDealRepository;
import com.example.hotdealnoti.repository.jpa.JpaProductRankingRepository;
import com.example.hotdealnoti.repository.jpa.JpaProductRepository;
import com.example.hotdealnoti.repository.jpa.JpaRecommendationHotDeal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class UpdateProductRankingScheduler {

    private final JpaProductRepository jpaProductRepository;
    private final JpaProductRankingRepository jpaProductRankingRepository;

    @Scheduled(cron = "0 0 19 * * ?")
    @Transactional
    public void updateProductRanking() {
        List<ProductDto.ProductRankingInfo> productRankingInfos = new ArrayList<>();

        productRankingInfos.addAll(jpaProductRepository.findTop100RankingProduct(1l));
        productRankingInfos.addAll(jpaProductRepository.findTop100RankingProduct(2l));

        productRankingInfos.forEach(productRankingInfo -> {
            Optional<ProductRanking> optionalProductRanking = jpaProductRankingRepository.findByProductPurposeAndProductRankingNumber(
                    ProductPurpose.builder().productPurposeId(productRankingInfo.getProductPurposeId()).build(), productRankingInfo.getProductRankingNumber()
            );
            if (optionalProductRanking.isEmpty()){
                ProductRanking productRanking = ProductRanking.builder()
                        .productRankingNumber(productRankingInfo.getProductRankingNumber())
                        .product(Product.builder().productId(productRankingInfo.getProductId()).build())
                        .productPurpose(ProductPurpose.builder().productPurposeId(productRankingInfo.getProductPurposeId()).build())
                        .build();
                jpaProductRankingRepository.save(productRanking);
            }else {
                ProductRanking oldProductRanking = optionalProductRanking.get();
                oldProductRanking.setProduct(Product.builder().productId(productRankingInfo.getProductId()).build());
                jpaProductRankingRepository.save(oldProductRanking);
            }
        });

    }
}
