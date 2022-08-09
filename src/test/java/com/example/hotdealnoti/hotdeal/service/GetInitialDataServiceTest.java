package com.example.hotdealnoti.hotdeal.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class GetInitialDataServiceTest {

    @Autowired
    private GetInitialDataService getInitialDataService;
    @Test
    void getInitialDataResponse() {
        System.out.println(getInitialDataService.getInitialDataResponse());
    }
}