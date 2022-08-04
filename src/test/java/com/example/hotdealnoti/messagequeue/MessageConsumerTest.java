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
                                        .title("HP 빅터스 16-d1120TX i5-12500H RTX3050Ti 113만")
                                        .build()
                        )
                )
                .build());
        messageConsumer.receiveMessage(hotDeals);
    }
}