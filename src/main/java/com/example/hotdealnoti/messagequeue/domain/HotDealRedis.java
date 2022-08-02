package com.example.hotdealnoti.messagequeue.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Builder
@Setter
@ToString
@RedisHash(value = "hotDeals", timeToLive = -1)
public class HotDealRedis {

    @Id
    private String hotDealId;

    private String hotDealTitle;

    private Integer hotDealOriginalPrice;

    private Integer hotDealDiscountPrice;

    private Integer hotDealDiscountRate;

    private String hotDealLink;

    private Timestamp hotDealUploadTime;

    private Integer hotDealViewCount;

    public static HotDealRedis from(HotDeal hotDeal){
        return HotDealRedis.builder()
                .hotDealId(hotDeal.getHotDealId().toString())
                .hotDealTitle(hotDeal.getHotDealTitle())
                .hotDealOriginalPrice(hotDeal.getHotDealOriginalPrice())
                .hotDealDiscountPrice(hotDeal.getHotDealDiscountPrice())
                .hotDealDiscountRate(hotDeal.getHotDealDiscountRate())
                .hotDealLink(hotDeal.getHotDealLink())
                .hotDealUploadTime(hotDeal.getHotDealUploadTime())
                .hotDealViewCount(hotDeal.getHotDealViewCount())
                .build();
    }
}
