package com.example.hotdealnoti.product.serivce;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ClassifyProductServiceTest {
    @Autowired
    private ClassifyProductService classifyProductService;
    @Test
    void getClassifyProductInitData() {
        System.out.println(classifyProductService.getClassifyProductInitData());
    }
}