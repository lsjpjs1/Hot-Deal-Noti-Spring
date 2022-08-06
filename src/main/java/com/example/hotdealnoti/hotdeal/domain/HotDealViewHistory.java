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
public class HotDealViewHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotDealViewHistoryId;

    private String userIp;
    private String sortCondition;
    private String searchBody;

    private Timestamp hotDealViewTime;

    public static HotDealViewHistory from(HotDealViewHistoryRedis hotDealViewHistoryRedis) {
        return HotDealViewHistory.builder()
                .userIp(hotDealViewHistoryRedis.getUserIp())
                .sortCondition(hotDealViewHistoryRedis.getSortCondition())
                .searchBody(hotDealViewHistoryRedis.getSearchBody())
                .hotDealViewTime(hotDealViewHistoryRedis.getHotDealViewTime())
                .build();
    }

}
