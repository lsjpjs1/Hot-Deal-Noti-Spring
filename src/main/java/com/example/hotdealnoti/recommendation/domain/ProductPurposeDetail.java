package com.example.hotdealnoti.recommendation.domain;

import com.example.hotdealnoti.product.domain.Manufacturer;
import com.example.hotdealnoti.product.domain.ProductPurpose;
import com.example.hotdealnoti.product.domain.ProductType;
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
public class ProductPurposeDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productPurposeDetailId;

    private String productPurposeDetailTitle;
    private String productPurposeDetailBody;


}
