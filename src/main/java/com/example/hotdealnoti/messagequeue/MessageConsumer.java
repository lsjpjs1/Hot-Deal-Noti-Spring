package com.example.hotdealnoti.messagequeue;

import com.example.hotdealnoti.messagequeue.domain.HotDeal;
import com.example.hotdealnoti.messagequeue.dto.HotDealMessageDto;
import com.example.hotdealnoti.messagequeue.repository.HotDealRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.message.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class MessageConsumer {

    private final HotDealRepository hotDealRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @RabbitListener(queues = "test")
    public void receiveMessage(String message) {
        try {
            HotDealMessageDto.HotDealMessageWrapper hotDealMessageWrapper = objectMapper.readValue(message, HotDealMessageDto.HotDealMessageWrapper.class);
            for(HotDealMessageDto.HotDealMessageContent hotDealMessageContent: hotDealMessageWrapper.getHotDealMessages()) {
                hotDealRepository.save(HotDeal.from(hotDealMessageContent));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
