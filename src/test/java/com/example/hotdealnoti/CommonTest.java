package com.example.hotdealnoti;

import com.example.hotdealnoti.common.util.MailDTO;
import com.example.hotdealnoti.common.util.MailUtil;
import com.example.hotdealnoti.messagequeue.MessageConsumer;
import com.example.hotdealnoti.messagequeue.dto.HotDealMessageDto;
import com.example.hotdealnoti.repository.jpa.JpaProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import java.util.Arrays;

@SpringBootTest
@AutoConfigureMockMvc
class CommonTest {
    @Autowired
    private MailUtil mailUtil;

    @Autowired
    private JpaProductRepository jpaProductRepository;

    @Test
    void sendMail(){
        try {
            mailUtil.sendMail(MailDTO.MailRequest.builder()
                    .body("특가: 리전프로\n" +
                            "가격: 10,000 원\n" +
                            "whendiscount.com/123")
                    .receiverEmail("lsjpjs1@naver.com")
                    .subject("title")
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void queryTest(){
        jpaProductRepository.findTop100RankingProduct(1l);
    }

}
