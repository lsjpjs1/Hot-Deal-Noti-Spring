package com.example.hotdealnoti.messagequeue;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.message.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageConsumer {
    @RabbitListener(queues = "test")
    public void receiveMessage(String message) {
        log.info(message);
    }
}
