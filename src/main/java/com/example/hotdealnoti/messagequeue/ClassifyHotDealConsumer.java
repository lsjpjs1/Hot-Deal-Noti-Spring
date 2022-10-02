package com.example.hotdealnoti.messagequeue;

import com.example.hotdealnoti.hotdeal.dto.HotDealDto;
import com.example.hotdealnoti.hotdeal.service.InsertClassifyQueueService;
import com.example.hotdealnoti.messagequeue.domain.HotDeal;
import com.example.hotdealnoti.messagequeue.domain.HotDealCandidate;
import com.example.hotdealnoti.messagequeue.dto.HotDealMessageDto;
import com.example.hotdealnoti.notification.service.SendNotificationService;
import com.example.hotdealnoti.product.domain.Product;
import com.example.hotdealnoti.product.dto.ProductDto;
import com.example.hotdealnoti.repository.jpa.JpaHotDealCandidateRepository;
import com.example.hotdealnoti.repository.jpa.JpaHotDealRepository;
import com.example.hotdealnoti.repository.jpa.JpaProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wantedtech.common.xpresso.strings.SequenceMatcher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class ClassifyHotDealConsumer {

    private final JpaProductRepository jpaProductRepository;
    private final JpaHotDealCandidateRepository jpaHotDealCandidateRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();


    @RabbitListener(queues = "classifyHotDeal")
    public void receiveMessage(String message) {
        HotDealDto.InsertClassifyQueueRequest insertClassifyQueueRequest;
        try {
            insertClassifyQueueRequest = objectMapper.readValue(message, HotDealDto.InsertClassifyQueueRequest.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
            return;
        } catch (JsonProcessingException jsonProcessingException) {
            jsonProcessingException.printStackTrace();
            return;
        }

        List<Product> allProducts = jpaProductRepository.findAll();
        HotDealDto.ProductWithSimilarity candidate = null;
        for (Product product: allProducts){

            Double similarity = new SequenceMatcher(insertClassifyQueueRequest.getHotDealTitle(), product.getModelName()).ratio();
            if (candidate==null){
                candidate= HotDealDto.ProductWithSimilarity.builder().similarity(similarity).product(product).build();
            }else{
                if (candidate.getSimilarity().compareTo(similarity)==-1){
                    candidate=HotDealDto.ProductWithSimilarity.builder().similarity(similarity).product(product).build();
                }
            }
        }

        jpaHotDealCandidateRepository.save(
                HotDealCandidate.builder()
                        .hotDealId(insertClassifyQueueRequest.getHotDealId())
                        .candidateProductId(candidate.getProduct().getProductId())
                        .build()
        );


    }

}
