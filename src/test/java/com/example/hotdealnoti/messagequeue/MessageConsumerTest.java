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
                                        .url("http://item.gmarket.co.kr/Item?goodscode=2379255209")
                                        .title("HP 2022 노트북 17s, cu0016TU, WIN11 Home, 8GB, 256GB, 코어i3, 네추럴 실버")
                                        .discountPrice(19900)
                                        .hotDealThumbnailUrl("http://localhost:3000/static/media/IMG_0385_2.2e43eb8351c182a4f669.png")
                                        .sourceSite("G마켓")
                                        .originalPrice(1100000)
                                        .discountRate(10)
                                        .build()
                        )
                )
                .build());
        System.out.println(hotDeals);
        messageConsumer.receiveMessage(hotDeals);
    }
}