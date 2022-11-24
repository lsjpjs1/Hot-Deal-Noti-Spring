package com.example.hotdealnoti.hotdeal.domain;

import com.example.hotdealnoti.auth.domain.Account;
import com.example.hotdealnoti.messagequeue.domain.HotDeal;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table
@AllArgsConstructor
@Builder
@Setter
@DynamicInsert
@ToString
public class FavoriteHotDeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long favoriteHotDealId;

    @ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;


    @ManyToOne(targetEntity = HotDeal.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "hot_deal_id")
    private HotDeal hotDeal;


    private Boolean isDelete;

}
