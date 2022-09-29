package com.example.hotdealnoti.messagequeue.domain;

import com.example.hotdealnoti.messagequeue.dto.HotDealMessageDto;
import com.example.hotdealnoti.product.domain.Product;
import com.example.hotdealnoti.product.domain.ProductPurpose;
import com.example.hotdealnoti.product.domain.ProductType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
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
@DynamicUpdate
@ToString
public class HotDeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotDealId;

    private String hotDealTitle;

    private Integer hotDealOriginalPrice;

    private Integer hotDealDiscountPrice;

    private Integer hotDealDiscountRate;

    private String hotDealLink;

    private Timestamp hotDealUploadTime;

    private Integer hotDealViewCount;

    private Timestamp hotDealScrapingTime;

    private Boolean isDelete;

    private Boolean isPermanentDelete;

    private String sourceSite;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public static HotDeal from(HotDealMessageDto.HotDealMessageContent hotDealMessageContent){
        return HotDeal.builder()
                .hotDealDiscountPrice(hotDealMessageContent.getDiscountPrice())
                .hotDealDiscountRate(hotDealMessageContent.getDiscountRate())
                .hotDealLink(hotDealMessageContent.getUrl())
                .hotDealTitle(hotDealMessageContent.getTitle())
                .hotDealOriginalPrice(hotDealMessageContent.getOriginalPrice())
                .sourceSite(hotDealMessageContent.getSourceSite())
                .build();
    }

    public static HotDeal from(HotDealRedis hotDealRedis){
        return HotDeal.builder()
                .hotDealDiscountPrice(hotDealRedis.getHotDealDiscountPrice())
                .hotDealDiscountRate(hotDealRedis.getHotDealDiscountRate())
                .hotDealLink(hotDealRedis.getHotDealLink())
                .hotDealTitle(hotDealRedis.getHotDealTitle())
                .hotDealOriginalPrice(hotDealRedis.getHotDealOriginalPrice())
                .hotDealUploadTime(hotDealRedis.getHotDealUploadTime())
                .hotDealViewCount(hotDealRedis.getHotDealViewCount())
                .hotDealId(Long.parseLong(hotDealRedis.getHotDealId()))
                .build();
    }
}
