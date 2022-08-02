package com.example.hotdealnoti.messagequeue.domain;

import com.example.hotdealnoti.messagequeue.dto.HotDealMessageDto;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table
@AllArgsConstructor
@Builder
@Setter
@DynamicInsert
@ToString
@RedisHash(value = "hotDeals", timeToLive = 30)
public class HotDeal {
    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotDealId;

    private String hotDealTitle;

    private Integer hotDealOriginalPrice;

    private Integer hotDealDiscountPrice;

    private Integer hotDealDiscountRate;

    private String hotDealLink;

    private Timestamp hotDealUploadTime;

    public static HotDeal from(HotDealMessageDto.HotDealMessageContent hotDealMessageContent){
        return HotDeal.builder()
                .hotDealDiscountPrice(hotDealMessageContent.getDiscountPrice())
                .hotDealDiscountRate(hotDealMessageContent.getDiscountRate())
                .hotDealLink(hotDealMessageContent.getUrl())
                .hotDealTitle(hotDealMessageContent.getTitle())
                .hotDealOriginalPrice(hotDealMessageContent.getOriginalPrice())
                .build();
    }
}
