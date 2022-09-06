package com.example.hotdealnoti.customer.controller;

import com.example.hotdealnoti.customer.dto.CustomerDto;
import com.example.hotdealnoti.customer.service.PostCustomerRequirementService;
import com.example.hotdealnoti.product.dto.ProductDto;
import com.example.hotdealnoti.product.serivce.ClassifyProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PostCustomerRequirementController {

    private final PostCustomerRequirementService postCustomerRequirementService;

    @PostMapping(value = "/customer-requirement")
    public ResponseEntity postCustomerRequest(@RequestBody CustomerDto.PostCustomerRequirementRequest postCustomerRequirementRequest, HttpServletRequest request) {
        postCustomerRequirementRequest.setUserIp(getIpFromRequest(request));
        postCustomerRequirementService.postCustomerRequirement(postCustomerRequirementRequest);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();

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
