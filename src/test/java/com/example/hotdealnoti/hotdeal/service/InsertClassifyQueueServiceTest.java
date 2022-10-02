package com.example.hotdealnoti.hotdeal.service;

import com.example.hotdealnoti.hotdeal.dto.HotDealDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class InsertClassifyQueueServiceTest {

    @Autowired
    private InsertClassifyQueueService insertClassifyQueueService;
    @Test
    void insertClassifyQueue() {
        insertClassifyQueueService.insertClassifyQueue(HotDealDto.InsertClassifyQueueRequest.builder().hotDealId(-1l).hotDealTitle("test").build());
    }
}