package com.example.hotdealnoti.hotdeal.controller;

import com.example.hotdealnoti.auth.domain.Account;
import com.example.hotdealnoti.auth.security.CustomSecurityContextHolder;
import com.example.hotdealnoti.hotdeal.dto.HotDealDto;
import com.example.hotdealnoti.hotdeal.service.DeleteHotDealService;
import com.example.hotdealnoti.hotdeal.service.GetHotDealService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DeleteHotDealController {

    private final DeleteHotDealService deleteHotDealService;


    @DeleteMapping("/hot-deals/{hotDealId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity deleteHotDeal(@PathVariable Long hotDealId) {

        deleteHotDealService.deleteHotDeal(hotDealId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }


}
