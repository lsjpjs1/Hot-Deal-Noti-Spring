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

        connectWebsiteService.postConnectionHistory(request.getRemoteAddr());
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
