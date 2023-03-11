package com.example.hotdealnoti.hotdeal.controller;

import com.example.hotdealnoti.auth.domain.Account;
import com.example.hotdealnoti.auth.security.CustomSecurityContextHolder;
import com.example.hotdealnoti.hotdeal.dto.HotDealDto;
import com.example.hotdealnoti.hotdeal.service.GetHotDealService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GetHotDealController {

    private final GetHotDealService getHotDealService;
    private final ObjectMapper objectMapper;

    @GetMapping(value = "/hot-deals")
    public ResponseEntity<Page<HotDealDto.HotDealPreview>> getHotDeals(@ModelAttribute HotDealDto.GetHotDealsRequest getHotDealsRequest, Pageable pageable, HttpServletRequest httpServletRequest) throws JsonProcessingException {
        if (getHotDealsRequest.getProductFunctionFiltersJsonString()!=null){
            HotDealDto.ProductFunctionFiltersWrapper productFunctionFiltersWrapper = objectMapper.readValue((getHotDealsRequest.getProductFunctionFiltersJsonString()), HotDealDto.ProductFunctionFiltersWrapper.class);
            getHotDealsRequest.setProductFunctionFilters(productFunctionFiltersWrapper.getProductFunctionFilters());
        }

        String ip = getIpFromRequest(httpServletRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(getHotDealService.getHotDeals(getHotDealsRequest,pageable,ip));

    }

    @GetMapping(value = "/hot-deals/weekly-popular")
    public ResponseEntity<Page<HotDealDto.HotDealPreview>> getWeeklyPopularHotDeals(@ModelAttribute HotDealDto.GetHotDealsRequest getHotDealsRequest, Pageable pageable, HttpServletRequest httpServletRequest) {

        String ip = getIpFromRequest(httpServletRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(getHotDealService.getWeeklyPopularHotDeals(getHotDealsRequest,pageable,ip));

    }

    @GetMapping(value = "/hot-deals/not-classified")
    public ResponseEntity<HotDealDto.GetNotClassifiedHotDealsResponse> getNotClassifiedHotDeals() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(getHotDealService.getNotClassifiedHotDeals());

    }

    @GetMapping(value = "/hot-deals/{productId}")
    public ResponseEntity<HotDealDto.GetHotDealByProductIdResponse> getHotDealsByProductId(Pageable pageable, HttpServletRequest httpServletRequest, @PathVariable Long productId) {

        String ip = getIpFromRequest(httpServletRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(getHotDealService.getHotDealsByProductId(productId,pageable,ip));

    }

    @GetMapping(value = "/hot-deals/hot-deal/{hotDealId}")
    public ResponseEntity<HotDealDto.HotDealPreview> getHotDealByHotDealId(@PathVariable Long hotDealId) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(getHotDealService.getHotDealByHotDealId(hotDealId));

    }

    @GetMapping(value = "/hot-deals/favorite")
    public ResponseEntity<List<HotDealDto.HotDealPreview>> getFavoriteHotDeals() {

        Account accountFromToken = CustomSecurityContextHolder.getAccountFromToken();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(getHotDealService.getFavoriteHotDeals(accountFromToken));

    }

    private String getIpFromRequest(HttpServletRequest httpServletRequest) {
        String ip = httpServletRequest.getHeader("X-Forwarded-For");

        if (ip == null) {
            ip = httpServletRequest.getHeader("X-FORWARDED-FOR");
        }
        // X-FORWARDED-FOR 가 비어있다면 요청한 IP를 로드
        if (ip == null) {
            ip = httpServletRequest.getRemoteAddr();
        }
        return ip;
    }
}
