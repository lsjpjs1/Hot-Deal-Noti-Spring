package com.example.hotdealnoti.hotdeal.scheduler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class HotDealViewHistorySchedulerTest {

    @Autowired
    private HotDealViewHistoryScheduler hotDealViewHistoryScheduler;
    @Test
    void hotDealViewHistoryInsertSchedule() {
        hotDealViewHistoryScheduler.hotDealViewHistoryInsertSchedule();
    }
}