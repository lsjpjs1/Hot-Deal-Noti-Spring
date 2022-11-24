package com.example.hotdealnoti.hotdeal.controller;

import com.example.hotdealnoti.auth.domain.Account;
import com.example.hotdealnoti.auth.security.CustomSecurityContextHolder;
import com.example.hotdealnoti.hotdeal.dto.HotDealDto;
import com.example.hotdealnoti.hotdeal.service.DeleteHotDealService;
import com.example.hotdealnoti.hotdeal.service.PostHotDealService;
import com.example.hotdealnoti.messagequeue.dto.HotDealMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PostHotDealController {

    private final PostHotDealService postHotDealService;


    @PostMapping("/hot-deals")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity insertHotDealQueue(@RequestBody HotDealMessageDto.HotDealMessageContent hotDealMessageContent) {


        postHotDealService.insertHotDealQueue(hotDealMessageContent);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PostMapping("/hot-deals/{hotDealId}/favorite")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity postFavoriteHotDeal(@PathVariable Long hotDealId) {


        Account accountFromToken = CustomSecurityContextHolder.getAccountFromToken();
        HotDealDto.PostFavoriteHotDealRequest postFavoriteHotDealRequest = HotDealDto.PostFavoriteHotDealRequest.builder()
                .hotDealId(hotDealId)
                .accountId(accountFromToken.getAccountId())
                .build();
        postHotDealService.postFavoriteHotDeal(postFavoriteHotDealRequest);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }


}
