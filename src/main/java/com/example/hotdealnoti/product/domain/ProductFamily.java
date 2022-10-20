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
public class ProductFamily {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productFamilyId;

    private String productFamilyName;
    private String productFamilyDescription;


}
