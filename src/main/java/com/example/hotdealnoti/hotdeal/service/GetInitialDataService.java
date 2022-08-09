package com.example.hotdealnoti.hotdeal.service;

import com.example.hotdealnoti.hotdeal.domain.HotDealViewHistoryRedis;
import com.example.hotdealnoti.hotdeal.dto.HotDealDto;
import com.example.hotdealnoti.repository.jpa.JpaHotDealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class GetInitialDataService {

    private final JpaHotDealRepository jpaHotDealRepository;
    @Transactional
    public HotDealDto.GetInitialDataResponse getInitialDataResponse() {


        return HotDealDto.GetInitialDataResponse.builder()
                .recentUpdateTime(jpaHotDealRepository.findFirstByOrderByHotDealScrapingTimeDesc().getHotDealScrapingTime())
                .notice("롯데온, 옥션 특가 목록이 추가되었습니다.")
                .build();
    }
}
