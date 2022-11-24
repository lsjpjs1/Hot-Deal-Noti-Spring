package com.example.hotdealnoti.hotdeal.service;

import com.example.hotdealnoti.auth.domain.Account;
import com.example.hotdealnoti.exception.CustomException;
import com.example.hotdealnoti.exception.ErrorCode;
import com.example.hotdealnoti.hotdeal.domain.FavoriteHotDeal;
import com.example.hotdealnoti.hotdeal.dto.HotDealDto;
import com.example.hotdealnoti.messagequeue.domain.HotDeal;
import com.example.hotdealnoti.messagequeue.dto.HotDealMessageDto;
import com.example.hotdealnoti.repository.jpa.JpaFavoriteHotDealRepository;
import com.example.hotdealnoti.repository.jpa.JpaHotDealRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostHotDealService {

    @Value("${mq.routing-key.hot-deal-queue}")
    private String HOT_DEAL_QUEUE_ROUTING_KEY;

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    private final JpaFavoriteHotDealRepository jpaFavoriteHotDealRepository;

    @Transactional
    public void insertHotDealQueue(HotDealMessageDto.HotDealMessageContent hotDealMessageContent) {


        if (hotDealMessageContent.nullCheck()){
            throw new CustomException(ErrorCode.BAD_REQUEST_CONTENT);
        }

        HotDealMessageDto.HotDealMessageWrapper hotDealMessageWrapper = HotDealMessageDto.HotDealMessageWrapper.builder()
                .hotDealMessages(Arrays.asList(hotDealMessageContent))
                .build();
        HotDealMessageDto.HotDealMessageWrapper hot = hotDealMessageWrapper;

        String payload;
        try {
            payload = objectMapper.writeValueAsString(hotDealMessageWrapper);
        }catch (JsonProcessingException jsonProcessingException) {
            jsonProcessingException.printStackTrace();
            return;
        }

        rabbitTemplate.convertAndSend("amq.direct",HOT_DEAL_QUEUE_ROUTING_KEY,payload);

    }

    @Transactional
    public void postFavoriteHotDeal(HotDealDto.PostFavoriteHotDealRequest postFavoriteHotDealRequest){
        Optional<FavoriteHotDeal> optionalFavoriteHotDeal
                = jpaFavoriteHotDealRepository.findByAccount_AccountIdAndHotDeal_HotDealId(postFavoriteHotDealRequest.getAccountId(), postFavoriteHotDealRequest.getHotDealId());

        if (optionalFavoriteHotDeal.isPresent()){
            FavoriteHotDeal favoriteHotDeal = optionalFavoriteHotDeal.get();
            if (favoriteHotDeal.getIsDelete()){
                favoriteHotDeal.setIsDelete(false);
                jpaFavoriteHotDealRepository.save(favoriteHotDeal);
            }else{
                throw new CustomException(ErrorCode.DUPLICATE_RESOURCE);
            }
        }else{
            FavoriteHotDeal favoriteHotDeal = FavoriteHotDeal.builder()
                    .hotDeal(HotDeal.builder().hotDealId(postFavoriteHotDealRequest.getHotDealId()).build())
                    .account(Account.builder().accountId(postFavoriteHotDealRequest.getAccountId()).build())
                    .build();
            jpaFavoriteHotDealRepository.save(favoriteHotDeal);
        }


    }

}
