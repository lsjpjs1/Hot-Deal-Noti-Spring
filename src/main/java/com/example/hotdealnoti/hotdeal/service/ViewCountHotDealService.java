package com.example.hotdealnoti.hotdeal.service;

import com.example.hotdealnoti.exception.CustomException;
import com.example.hotdealnoti.exception.ErrorCode;
import com.example.hotdealnoti.messagequeue.domain.HotDeal;
import com.example.hotdealnoti.messagequeue.domain.HotDealRedis;
import com.example.hotdealnoti.repository.jpa.JpaHotDealRepository;
import com.example.hotdealnoti.repository.redis.RedisHotDealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ViewCountHotDealService {
    private final JpaHotDealRepository jpaHotDealRepository;
    private final RedisHotDealRepository redisHotDealRepository;
    public void increaseViewCount(Long hotDealId){
        Optional<HotDealRedis> optionalHotDealRedis = redisHotDealRepository.findById(hotDealId.toString());
        if(optionalHotDealRedis.isPresent()){
            HotDealRedis hotDealRedis = optionalHotDealRedis.get();
            hotDealRedis.setHotDealViewCount(hotDealRedis.getHotDealViewCount()+1);
            redisHotDealRepository.save(hotDealRedis);
        }else{
            HotDeal hotDeal = jpaHotDealRepository.findById(hotDealId)
                    .orElseThrow(() -> new CustomException(ErrorCode.HOT_DEAL_ID_NOT_FOUND));
            HotDealRedis hotDealRedis = HotDealRedis.from(hotDeal);
            hotDealRedis.setHotDealViewCount(hotDealRedis.getHotDealViewCount()+1);
            redisHotDealRepository.save(hotDealRedis);
        }

    }
}
