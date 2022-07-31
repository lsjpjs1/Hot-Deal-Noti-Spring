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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GetHotDealController {

    private final GetHotDealService getHotDealService;

    @GetMapping(value = "/hot-deals")
    public ResponseEntity<Page<HotDealDto.HotDealPreview>> getHotDeals(@ModelAttribute HotDealDto.GetHotDealsRequest getHotDealsRequest, Pageable pageable) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(getHotDealService.getHotDeals(getHotDealsRequest,pageable));

    }
}
