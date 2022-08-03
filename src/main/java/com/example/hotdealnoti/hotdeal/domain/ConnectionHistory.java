package com.example.hotdealnoti.hotdeal.domain;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

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
public class ConnectionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long connectionHistoryId;

    private String userIp;

    private Timestamp connectionTime;

    public static ConnectionHistory from(ConnectionHistoryRedis connectionHistoryRedis){
        return ConnectionHistory.builder()
                .connectionTime(connectionHistoryRedis.getConnectionTime())
                .userIp(connectionHistoryRedis.getUserIp())
                .build();
    }
}
