package com.example.hotdealnoti.messagequeue;

import com.example.hotdealnoti.hotdeal.dto.HotDealDto;
import com.example.hotdealnoti.hotdeal.service.InsertClassifyQueueService;
import com.example.hotdealnoti.messagequeue.domain.HotDealCandidate;
import com.example.hotdealnoti.messagequeue.domain.ReturnItem;
import com.example.hotdealnoti.notification.service.SendNotificationService;
import com.example.hotdealnoti.messagequeue.domain.HotDeal;
import com.example.hotdealnoti.messagequeue.dto.HotDealMessageDto;
import com.example.hotdealnoti.product.domain.Product;
import com.example.hotdealnoti.repository.jpa.JpaHotDealCandidateRepository;
import com.example.hotdealnoti.repository.jpa.JpaHotDealRepository;
import com.example.hotdealnoti.repository.jpa.JpaReturnItemRepository;
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
    private final InsertClassifyQueueService insertClassifyQueueService;
    private final JpaReturnItemRepository jpaReturnItemRepository;

    @RabbitListener(queues = "hotDeal")
    public void receiveMessage(String message) {
        try {
            HotDealMessageDto.HotDealMessageWrapper hotDealMessageWrapper = objectMapper.readValue(message, HotDealMessageDto.HotDealMessageWrapper.class);
            for(HotDealMessageDto.HotDealMessageContent hotDealMessageContent: hotDealMessageWrapper.getHotDealMessages()) {
                Optional<HotDeal> optionalHotDeal = hotDealRepository.findTopByHotDealTitleAndHotDealDiscountPrice(hotDealMessageContent.getTitle(),hotDealMessageContent.getDiscountPrice());


                if(optionalHotDeal.isPresent()){
                    HotDeal hotDeal = optionalHotDeal.get();

                    //영구삭제 아닌 경우
                    if (!hotDeal.getIsPermanentDelete()){
                        hotDeal.setHotDealScrapingTime(new Timestamp(System.currentTimeMillis()));
                        hotDeal.setHotDealThumbnailUrl(hotDealMessageContent.getHotDealThumbnailUrl());
                        hotDeal.setIsDelete(false);
                        hotDealRepository.save(hotDeal);
                    }

                    continue;
                }

                HotDeal beforeHotDeal = HotDeal.from(hotDealMessageContent);
                //후보 상품명 등록
                if (hotDealRepository.findTopByHotDealTitleOrderByHotDealIdDesc(hotDealMessageContent.getTitle()).isPresent()){
                    HotDeal hotDeal = hotDealRepository.findTopByHotDealTitleOrderByHotDealIdDesc(hotDealMessageContent.getTitle()).get();
                    beforeHotDeal.setProduct(hotDeal.getProduct());
                    beforeHotDeal.setIsCandidateProduct(false);
                }else {
                    if (hotDealMessageContent.getCandidateProductId()!=null){
                        beforeHotDeal.setProduct(Product.builder().productId(hotDealMessageContent.getCandidateProductId()).build());
                    }
                }

                //반품 상품인 경우 반품 정보 등록하고 반품 아이템 아이디 추가하기
                log.info(hotDealMessageContent.getReturnItemQuality());
                if (hotDealMessageContent.getReturnItemQuality()!=null){
                    ReturnItem returnItem = ReturnItem.builder()
                            .returnItemQuality(hotDealMessageContent.getReturnItemQuality())
                            .returnItemQualityDetail(hotDealMessageContent.getReturnItemQualityDetail())
                            .returnItemSaleStatus(hotDealMessageContent.getReturnItemSaleStatus())
                            .build();
                    ReturnItem persistReturnItem = jpaReturnItemRepository.save(returnItem);
                    beforeHotDeal.setReturnItem(persistReturnItem);
                }
                //수동 틍록인 경우
                if (hotDealMessageContent.getManualDeleteMode()!=null){
                    beforeHotDeal.setManualDeleteMode(hotDealMessageContent.getManualDeleteMode());
                }


                HotDeal hotDeal = hotDealRepository.save(beforeHotDeal);


                sendNotificationService.sendKeywordNotification(hotDeal);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
