package com.example.hotdealnoti.product.controller;

import com.example.hotdealnoti.hotdeal.dto.HotDealDto;
import com.example.hotdealnoti.product.dto.ProductDto;
import com.example.hotdealnoti.product.serivce.ClassifyProductService;
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
public class ClassifyProductController {

    private final ClassifyProductService classifyProductService;

    @GetMapping(value = "/products/classify/init-data")
    public ResponseEntity<ProductDto.ClassifyProductInitDataResponse> getClassifyProductInitData() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(classifyProductService.getClassifyProductInitData());

    }
}
