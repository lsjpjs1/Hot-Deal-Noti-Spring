package com.example.hotdealnoti.product.serivce;

import com.example.hotdealnoti.hotdeal.domain.ConnectionHistoryRedis;
import com.example.hotdealnoti.product.dto.ProductDto;
import com.example.hotdealnoti.repository.jpa.JpaConnectionHistoryRepository;
import com.example.hotdealnoti.repository.jpa.JpaProductPurposeRepository;
import com.example.hotdealnoti.repository.jpa.JpaProductTypeRepository;
import com.example.hotdealnoti.repository.redis.RedisConnectionHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class ClassifyProductService {

    private final JpaProductPurposeRepository jpaProductPurposeRepository;
    private final JpaProductTypeRepository jpaProductTypeRepository;
    @Transactional
    public ProductDto.ClassifyProductInitDataResponse getClassifyProductInitData() {

        return ProductDto.ClassifyProductInitDataResponse.builder()
                .productPurposes(jpaProductPurposeRepository.findAll())
                .productTypes(jpaProductTypeRepository.findAll())
                .build();
    }
}
