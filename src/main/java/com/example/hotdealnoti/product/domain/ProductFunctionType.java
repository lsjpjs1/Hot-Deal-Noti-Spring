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
public class ProductFunctionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productFunctionTypeId;

    @ManyToOne(targetEntity = ProductType.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_type_id")
    private ProductType productType;

    private String productFunctionTypeName;
}
