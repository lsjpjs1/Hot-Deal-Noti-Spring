package com.example.hotdealnoti.hotdeal.domain;

import com.example.hotdealnoti.messagequeue.domain.HotDeal;
import com.example.hotdealnoti.product.domain.Product;
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
public class RecommendationHotDeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recommendationHotDealId;

    @ManyToOne(targetEntity = HotDeal.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "hot_deal_id")
    private HotDeal hotDeal;

}
