package com.example.hotdealnoti.hotdeal.controller;

import com.example.hotdealnoti.hotdeal.dto.HotDealDto;
import com.example.hotdealnoti.hotdeal.service.GetInitialDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GetInitialDataController {

    private final GetInitialDataService getInitialDataService;
    @GetMapping(value = "/init-data")
    public ResponseEntity<HotDealDto.GetInitialDataResponse> getInitialData() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(getInitialDataService.getInitialDataResponse());

    }



}
