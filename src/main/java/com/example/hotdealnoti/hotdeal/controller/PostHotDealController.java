package com.example.hotdealnoti.hotdeal.controller;

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


}
