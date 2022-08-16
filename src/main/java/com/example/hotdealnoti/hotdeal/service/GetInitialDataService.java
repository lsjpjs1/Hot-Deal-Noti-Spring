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
                .notice("현재 알림이 가능한 안드로이드 앱을 개발 중입니다.\n빠른 시일 내에 개발 완료하여 배포하도록 하겠습니다. 감사합니다.")
                .build();
    }
}
