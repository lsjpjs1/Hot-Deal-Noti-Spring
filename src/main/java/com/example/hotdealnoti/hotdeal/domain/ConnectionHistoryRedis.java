package com.example.hotdealnoti.hotdeal.domain;

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
@RedisHash(value = "connectionHistories", timeToLive = -1)
public class ConnectionHistoryRedis {
    @Id
    private String connectionHistoryId;

    private String userIp;

    private Timestamp connectionTime;

}
