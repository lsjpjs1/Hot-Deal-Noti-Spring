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
public class ProductFunction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productFunctionId;

    @ManyToOne(targetEntity = ProductFunctionType.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_function_type_id")
    private ProductFunctionType productFunctionType;

    private String productFunctionName;
}
