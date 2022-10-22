package com.example.hotdealnoti.recommendation.controller;

import com.example.hotdealnoti.product.domain.ProductFamily;
import com.example.hotdealnoti.recommendation.domain.ProductPurposeDetail;
import com.example.hotdealnoti.recommendation.dto.RecommendationDto;
import com.example.hotdealnoti.recommendation.service.ManageRecommendationService;
import com.example.hotdealnoti.repository.jpa.JpaProductFamilyRepository;
import com.example.hotdealnoti.repository.jpa.JpaProductPurposeDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ManageRecommendationController {

    private final ManageRecommendationService manageRecommendationService;
    private final JpaProductFamilyRepository jpaProductFamilyRepository;
    private final JpaProductPurposeDetailRepository jpaProductPurposeDetailRepository;

    @PostMapping(value = "/product-families")
    public ResponseEntity postProductFamily(@RequestBody RecommendationDto.PostProductFamilyRequest postProductFamilyRequest) {

        manageRecommendationService.postProductFamily(postProductFamilyRequest);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();

    }

    @PostMapping(value = "/recommendation-product-family")
    public ResponseEntity postRecommendationProductFamily(@RequestBody RecommendationDto.PostRecommendationProductFamilyRequest postRecommendationProductFamilyRequest) {

        manageRecommendationService.postRecommendationProductFamily(postRecommendationProductFamilyRequest);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();

    }




    @GetMapping(value = "/product-purpose-details")
    public ResponseEntity<RecommendationDto.GetProductPurposeDetailsResponse> getProductPurposeDetails() {
        List<ProductPurposeDetail> productPurposeDetails = jpaProductPurposeDetailRepository.findAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(RecommendationDto.GetProductPurposeDetailsResponse.builder().productPurposeDetails(productPurposeDetails).build());

    }

    @PatchMapping(value = "/products/product-family")
    public ResponseEntity setProductFamily(@RequestBody RecommendationDto.SetProductFamilyRequest setProductFamilyRequest) {

        manageRecommendationService.setProductFamily(setProductFamilyRequest);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();

    }

    @GetMapping(value = "/product-families")
    public ResponseEntity<RecommendationDto.GetProductFamiliesResponse> getProductFamilies(@ModelAttribute RecommendationDto.GetProductFamiliesRequest getProductFamiliesRequest) {
        List<ProductFamily> productFamilies = jpaProductFamilyRepository.findByProductFamilyNameContains(getProductFamiliesRequest.getProductFamilyName());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(RecommendationDto.GetProductFamiliesResponse.builder().productFamilies(productFamilies).build());

    }
}
