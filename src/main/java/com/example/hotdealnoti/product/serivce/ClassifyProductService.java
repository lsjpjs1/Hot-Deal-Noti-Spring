package com.example.hotdealnoti.product.serivce;

import com.example.hotdealnoti.hotdeal.domain.ConnectionHistoryRedis;
import com.example.hotdealnoti.messagequeue.domain.HotDeal;
import com.example.hotdealnoti.product.domain.Manufacturer;
import com.example.hotdealnoti.product.domain.Product;
import com.example.hotdealnoti.product.domain.ProductPurpose;
import com.example.hotdealnoti.product.domain.ProductType;
import com.example.hotdealnoti.product.dto.ProductDto;
import com.example.hotdealnoti.repository.jpa.*;
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
    private final JpaHotDealRepository jpaHotDealRepository;
    private final JpaManufacturerRepository jpaManufacturerRepository;
    private final JpaProductRepository jpaProductRepository;
    @Transactional
    public ProductDto.ClassifyProductInitDataResponse getClassifyProductInitData() {

        return ProductDto.ClassifyProductInitDataResponse.builder()
                .productPurposes(jpaProductPurposeRepository.findAll())
                .productTypes(jpaProductTypeRepository.findAll())
                .manufacturers(jpaManufacturerRepository.findAll())
                .build();
    }

    @Transactional
    public void classifyHotDeal(ProductDto.ClassifyHotDealRequest classifyHotDealRequest) {
        HotDeal hotDeal = jpaHotDealRepository.findById(classifyHotDealRequest.getHotDealId()).get();
        hotDeal.setIsCandidateProduct(false);
        if(classifyHotDealRequest.getProductId()!=null){
            hotDeal.setProduct(jpaProductRepository.findById(classifyHotDealRequest.getProductId()).get());
            jpaHotDealRepository.save(hotDeal);
        }else {
            if(classifyHotDealRequest.getManufacturerId()!=null){
                Product product = Product.builder()
                        .productPurpose(jpaProductPurposeRepository.findById(classifyHotDealRequest.getProductPurposeId()).get())
                        .productType(jpaProductTypeRepository.findById(classifyHotDealRequest.getProductTypeId()).get())
                        .manufacturer(jpaManufacturerRepository.findById(classifyHotDealRequest.getManufacturerId()).get())
                        .modelName(classifyHotDealRequest.getModelName())
                        .modelNameSearch(classifyHotDealRequest.getModelName().replace(" ",""))
                        .build();
                jpaProductRepository.save(product);
                hotDeal.setProduct(product);
                jpaHotDealRepository.save(hotDeal);
            }else{
                Manufacturer manufacturer = Manufacturer.builder().manufacturerName(classifyHotDealRequest.getManufacturer()).build();
                jpaManufacturerRepository.save(manufacturer);
                Product product = Product.builder()
                        .productPurpose(jpaProductPurposeRepository.findById(classifyHotDealRequest.getProductPurposeId()).get())
                        .productType(jpaProductTypeRepository.findById(classifyHotDealRequest.getProductTypeId()).get())
                        .manufacturer(manufacturer)
                        .modelName(classifyHotDealRequest.getModelName())
                        .modelNameSearch(classifyHotDealRequest.getModelName().replace(" ",""))
                        .build();
                jpaProductRepository.save(product);
                hotDeal.setProduct(product);
                jpaHotDealRepository.save(hotDeal);
            }

        }

    }

    @Transactional
    public void passHotDeal(Long hotDealId) {

        jpaHotDealRepository.findById(hotDealId).ifPresent(hotDeal -> {
            hotDeal.setIsCandidateProduct(false);
            jpaHotDealRepository.save(hotDeal);
        });
    }
}
