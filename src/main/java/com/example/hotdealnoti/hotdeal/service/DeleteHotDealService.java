package com.example.hotdealnoti.hotdeal.service;

import com.example.hotdealnoti.hotdeal.domain.HotDealViewHistoryRedis;
import com.example.hotdealnoti.hotdeal.dto.HotDealDto;
import com.example.hotdealnoti.hotdeal.repository.HotDealQueryRepository;
import com.example.hotdealnoti.messagequeue.domain.HotDeal;
import com.example.hotdealnoti.product.domain.Product;
import com.example.hotdealnoti.repository.jpa.JpaHotDealRepository;
import com.example.hotdealnoti.repository.jpa.JpaProductRepository;
import com.example.hotdealnoti.repository.redis.RedisHotDealViewHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class DeleteHotDealService {

    private final JpaHotDealRepository jpaHotDealRepository;


    @Transactional
    public void deletePermanentHotDeal(Long hotDealId) {

        HotDeal hotDeal = jpaHotDealRepository.findById(hotDealId).get();
        hotDeal.setIsDelete(true);
        hotDeal.setIsPermanentDelete(true);
        jpaHotDealRepository.save(hotDeal);
    }

    @Transactional
    public void deleteHotDeal(Long hotDealId) {

        HotDeal hotDeal = jpaHotDealRepository.findById(hotDealId).get();
        hotDeal.setIsDelete(true);
        jpaHotDealRepository.save(hotDeal);
    }

}
