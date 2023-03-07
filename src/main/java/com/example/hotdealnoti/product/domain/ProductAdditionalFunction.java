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
public class ProductAdditionalFunction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productAdditionalFunctionId;

    @ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(targetEntity = ProductFunction.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_function_id")
    private ProductFunction productFunction;
}
