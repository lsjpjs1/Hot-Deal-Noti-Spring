package com.example.hotdealnoti.hotdeal.domain;

import com.example.hotdealnoti.hotdeal.dto.HotDealDto;
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
@RedisHash(value = "hotDealViewHistories", timeToLive = -1)
public class HotDealViewHistoryRedis {
    @Id
    private String hotDealViewHistoryId;

    private String userIp;
    private String sortCondition;
    private String searchBody;

    private Timestamp hotDealViewTime;



}
