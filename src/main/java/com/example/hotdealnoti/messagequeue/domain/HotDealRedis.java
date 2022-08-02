package com.example.hotdealnoti.messagequeue.domain;

import com.example.hotdealnoti.messagequeue.dto.HotDealMessageDto;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
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
    private Long hotDealId;

    private String hotDealTitle;

    private Integer hotDealOriginalPrice;

    private Integer hotDealDiscountPrice;

    private Integer hotDealDiscountRate;

    private String hotDealLink;

    private Timestamp hotDealUploadTime;

    private Integer hotDealViewCount;

}
