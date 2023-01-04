package com.example.hotdealnoti.messagequeue;

import com.example.hotdealnoti.messagequeue.dto.HotDealMessageDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@SpringBootTest
@AutoConfigureMockMvc
class MessageConsumerTest {

    private ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MessageConsumer messageConsumer;

    @Test
    void receiveMessage() throws JsonProcessingException {
        String hotDeals = objectMapper.writeValueAsString(HotDealMessageDto.HotDealMessageWrapper.builder()
                .hotDealMessages(
                        Arrays.asList(
                                HotDealMessageDto.HotDealMessageContent.builder()
                                        .url("https://www.coupang.com/vp/products/6631142902?itemId=15116999746&vendorItemId=82338784800&sourceType=CATEGORY&categoryId=497035")
                                        .title("델 2022 에일리언웨어 M15 R7 AMD 15.6, 1024GB, WIN11 Pro, DAWM15R7A-WP01KR, 다크 사이드 오브 더 문, 라이젠7, 16GB")
                                        .discountPrice(2699000)
                                        .hotDealThumbnailUrl("http://localhost:3000/static/media/IMG_0385_2.2e43eb8351c182a4f669.png")
                                        .sourceSite("쿠팡")
                                        .originalPrice(2999000)
                                        .discountRate(10)
                                        .build()
                        )
                )
                .build());
        System.out.println(hotDeals);
        messageConsumer.receiveMessage(hotDeals);
    }
}