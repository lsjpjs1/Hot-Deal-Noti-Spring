package com.example.hotdealnoti.hotdeal.service;

import com.example.hotdealnoti.hotdeal.dto.HotDealDto;
import com.example.hotdealnoti.hotdeal.repository.HotDealQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetHotDealService {
    private final HotDealQueryRepository hotDealQueryRepository;

    @Transactional
    public Page<HotDealDto.HotDealPreview> getHotDeals(HotDealDto.GetHotDealsRequest getHotDealsRequest, Pageable pageable) {
        return hotDealQueryRepository.findHotDeals(getHotDealsRequest, pageable);
    }
}
