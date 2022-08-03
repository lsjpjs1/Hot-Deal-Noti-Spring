package com.example.hotdealnoti.hotdeal.controller;

import com.example.hotdealnoti.hotdeal.service.ConnectWebsiteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ConnectWebsiteController {

    private final ConnectWebsiteService connectWebsiteService;
    @PostMapping("/connect")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity   connectWebsite(HttpServletRequest request){

        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null) {
            ip = request.getHeader("X-FORWARDED-FOR");
        }
        // X-FORWARDED-FOR 가 비어있다면 요청한 IP를 로드
        if (ip == null) {
            ip = request.getRemoteAddr();
        }

        connectWebsiteService.postConnectionHistory(ip);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
