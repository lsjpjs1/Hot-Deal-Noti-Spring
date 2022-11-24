package com.example.hotdealnoti.hotdeal.controller;

import com.example.hotdealnoti.auth.domain.Account;
import com.example.hotdealnoti.auth.security.CustomSecurityContextHolder;
import com.example.hotdealnoti.hotdeal.dto.HotDealDto;
import com.example.hotdealnoti.hotdeal.service.DeleteHotDealService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DeleteHotDealController {

    private final DeleteHotDealService deleteHotDealService;


    @DeleteMapping("/hot-deals/{hotDealId}/permanent")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity deletePermanentHotDeal(@PathVariable Long hotDealId) {

        deleteHotDealService.deletePermanentHotDeal(hotDealId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @DeleteMapping("/hot-deals/{hotDealId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity deleteHotDeal(@PathVariable Long hotDealId) {

        deleteHotDealService.deleteHotDeal(hotDealId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @DeleteMapping("/hot-deals/{hotDealId}/favorite")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity deleteFavoriteHotDeal(@PathVariable Long hotDealId) {

        Account accountFromToken = CustomSecurityContextHolder.getAccountFromToken();
        HotDealDto.DeleteFavoriteHotDealRequest deleteFavoriteHotDealRequest = HotDealDto.DeleteFavoriteHotDealRequest.builder()
                .accountId(accountFromToken.getAccountId())
                .hotDealId(hotDealId)
                .build();
        deleteHotDealService.deleteFavoriteHotDeal(deleteFavoriteHotDealRequest);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }




}
