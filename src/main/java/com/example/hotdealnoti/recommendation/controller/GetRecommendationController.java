package com.example.hotdealnoti.recommendation.controller;

import com.example.hotdealnoti.product.dto.ProductDto;
import com.example.hotdealnoti.product.serivce.GetProductService;
import com.example.hotdealnoti.recommendation.dto.RecommendationDto;
import com.example.hotdealnoti.recommendation.service.GetRecommendationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GetRecommendationController {

    private final GetRecommendationService getRecommendationService;

    @GetMapping(value = "/recommendations")
    public ResponseEntity<RecommendationDto.GetRecommendationsResponse> getRecommendations() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(getRecommendationService.getRecommendations());

    }
}
