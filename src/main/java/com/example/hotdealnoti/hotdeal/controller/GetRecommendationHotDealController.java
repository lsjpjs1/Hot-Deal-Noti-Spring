package com.example.hotdealnoti.hotdeal.controller;

import com.example.hotdealnoti.hotdeal.dto.HotDealDto;
import com.example.hotdealnoti.hotdeal.service.GetHotDealService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GetRecommendationHotDealController {

    private final GetHotDealService getHotDealService;

    @GetMapping(value = "/hot-deals/recommendation")
    public ResponseEntity<List<HotDealDto.HotDealPreview>> getRecommendationHotDeals() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(getHotDealService.getRecommendationHotDeals());

    }



}
