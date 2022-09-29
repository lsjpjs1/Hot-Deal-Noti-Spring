package com.example.hotdealnoti.messagequeue;

import com.example.hotdealnoti.notification.service.SendNotificationService;
import com.example.hotdealnoti.messagequeue.domain.HotDeal;
import com.example.hotdealnoti.messagequeue.dto.HotDealMessageDto;
import com.example.hotdealnoti.repository.jpa.JpaHotDealRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class MessageConsumer {

    private final JpaHotDealRepository hotDealRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final SendNotificationService sendNotificationService;
    @RabbitListener(queues = "hotDeal")
    public void receiveMessage(String message) {
        try {
            HotDealMessageDto.HotDealMessageWrapper hotDealMessageWrapper = objectMapper.readValue(message, HotDealMessageDto.HotDealMessageWrapper.class);
            for(HotDealMessageDto.HotDealMessageContent hotDealMessageContent: hotDealMessageWrapper.getHotDealMessages()) {
                Optional<HotDeal> optionalHotDeal = hotDealRepository.findTopByHotDealTitle(hotDealMessageContent.getTitle());
                if(optionalHotDeal.isPresent()){
                    HotDeal hotDeal = optionalHotDeal.get();

                    //영구삭제된 경우
                    if (!hotDeal.getIsPermanentDelete()){
                        hotDeal.setHotDealScrapingTime(new Timestamp(System.currentTimeMillis()));
                        hotDeal.setIsDelete(false);
                        hotDealRepository.save(hotDeal);
                    }

                    continue;
                }

                HotDeal hotDeal = hotDealRepository.save(HotDeal.from(hotDealMessageContent));
                sendNotificationService.sendKeywordNotification(hotDeal);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
