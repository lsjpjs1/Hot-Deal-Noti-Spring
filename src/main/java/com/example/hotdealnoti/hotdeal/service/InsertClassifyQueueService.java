package com.example.hotdealnoti.hotdeal.service;

import com.example.hotdealnoti.hotdeal.dto.HotDealDto;
import com.example.hotdealnoti.messagequeue.domain.HotDeal;
import com.example.hotdealnoti.repository.jpa.JpaHotDealRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InsertClassifyQueueService {

    @Value("${mq.routing-key.classify-queue}")
    private String CLASSIFY_QUEUE_ROUTING_KEY;

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public void insertClassifyQueue(HotDealDto.InsertClassifyQueueRequest insertClassifyQueueRequest) {
        String payload;
        try {
            payload = objectMapper.writeValueAsString(insertClassifyQueueRequest);
        }catch (JsonProcessingException jsonProcessingException) {
            jsonProcessingException.printStackTrace();
            return;
        }

        rabbitTemplate.convertAndSend("amq.direct",CLASSIFY_QUEUE_ROUTING_KEY,payload);

    }

}
