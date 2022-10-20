package com.example.hotdealnoti.recommendation.domain;

import com.example.hotdealnoti.product.domain.Manufacturer;
import com.example.hotdealnoti.product.domain.ProductFamily;
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
public class RecommendationProductFamily {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recommendationProductFamilyId;


    @ManyToOne(targetEntity = ProductPurposeDetail.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_purpose_detail_id")
    private ProductPurposeDetail productPurposeDetail;

    @ManyToOne(targetEntity = ProductFamily.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_family_id")
    private ProductFamily productFamily;



}
