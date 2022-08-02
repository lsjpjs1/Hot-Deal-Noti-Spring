package com.example.hotdealnoti.hotdeal.controller;

import com.example.hotdealnoti.hotdeal.service.ViewCountHotDealService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ViewHotDealController {

    private final ViewCountHotDealService viewCountHotDealService;

    @PatchMapping(value = "/hot-deals/{hotDealId}/view")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity viewHotDeals(@PathVariable Long hotDealId){
        viewCountHotDealService.increaseViewCount(hotDealId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
