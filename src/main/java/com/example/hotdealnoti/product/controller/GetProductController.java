package com.example.hotdealnoti.product.controller;

import com.example.hotdealnoti.product.dto.ProductDto;
import com.example.hotdealnoti.product.serivce.GetProductService;
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
public class GetProductController {

    private final GetProductService getProductService;

    @GetMapping(value = "/products")
    public ResponseEntity<ProductDto.GetProductResponse> getProducts(@ModelAttribute ProductDto.GetProductRequest getProductRequest) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(getProductService.getProducts(getProductRequest));

    }

    @GetMapping(value = "/products/ranking")
    public ResponseEntity<ProductDto.GetProductsRankingResponse> getProductsRanking(@ModelAttribute ProductDto.GetProductsRankingRequest getProductsRankingRequest) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(getProductService.getProductsRanking(getProductsRankingRequest));

    }
}
