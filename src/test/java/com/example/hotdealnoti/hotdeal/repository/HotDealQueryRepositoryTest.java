package com.example.hotdealnoti.hotdeal.repository;

import com.example.hotdealnoti.config.TestConfig;
import com.example.hotdealnoti.hotdeal.dto.HotDealDto;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Import(TestConfig.class)
class HotDealQueryRepositoryTest {
    Logger log = (Logger) LoggerFactory.getLogger(HotDealQueryRepositoryTest.class);
    @Autowired
    private HotDealQueryRepository hotDealQueryRepository;
    @Test
    void findHotDeals() {
        HotDealDto.GetHotDealsRequest getHotDealsRequest = HotDealDto.GetHotDealsRequest.builder().build();
        PageRequest pageRequest = PageRequest.of(0, 20, Sort.Direction.DESC, "DISCOUNT_RATE");
        Page<HotDealDto.HotDealPreview> hotDeals = hotDealQueryRepository.findHotDeals(getHotDealsRequest, pageRequest);
        hotDeals.getContent().stream()
                .forEach(hotDealPreview -> log.info(hotDealPreview.toString()));
    }
}