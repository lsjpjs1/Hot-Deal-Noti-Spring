package com.example.hotdealnoti.messagequeue;

import com.example.hotdealnoti.messagequeue.dto.HotDealMessageDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
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
                                        .url("삼성갤럭시북2 프로 S.E NT950XFT-A51A 13세대 i5-1340P/16GB/256GB/15인치 신제품출시 노트북")
                                        .title("삼성전자 갤럭시북2 프로 S.E NT950XFG-K71A")
                                        .discountPrice(1190000)
                                        .hotDealThumbnailUrl("https://cdn.011st.com/11dims/resize/600x600/quality/75/11src/pd/v2/2/7/2/5/4/2/rqRKy/5431272542_B.jpg")
                                        .sourceSite("11번가")
                                        .originalPrice(1599000)
                                        .discountRate(26)
                                        .manualDeleteMode(true)
                                        .build()
                        )
                )
                .build());
        System.out.println(hotDeals);
        messageConsumer.receiveMessage(hotDeals);
    }
}