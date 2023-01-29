package com.example.hotdealnoti.product.domain;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

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
public class ProductRanking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productRankingId;

    private Integer productRankingNumber;

    @ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(targetEntity = ProductPurpose.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_purpose_id")
    private ProductPurpose productPurpose;


}
