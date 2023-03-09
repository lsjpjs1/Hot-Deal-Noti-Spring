package com.example.hotdealnoti.product.serivce;

import com.example.hotdealnoti.product.domain.ProductFunction;
import com.example.hotdealnoti.product.domain.ProductFunctionType;
import com.example.hotdealnoti.product.domain.ProductType;
import com.example.hotdealnoti.product.dto.ProductDto;
import com.example.hotdealnoti.product.repository.ProductQueryRepository;
import com.example.hotdealnoti.repository.jpa.JpaProductFunctionRepository;
import com.example.hotdealnoti.repository.jpa.JpaProductFunctionTypeRepository;
import com.example.hotdealnoti.repository.jpa.JpaProductPurposeRepository;
import com.example.hotdealnoti.repository.jpa.JpaProductTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetProductService {

    private final JpaProductPurposeRepository jpaProductPurposeRepository;
    private final JpaProductTypeRepository jpaProductTypeRepository;
    private final ProductQueryRepository productQueryRepository;
    private final JpaProductFunctionTypeRepository jpaProductFunctionTypeRepository;
    private final JpaProductFunctionRepository jpaProductFunctionRepository;

    @Transactional
    public ProductDto.GetProductResponse getProducts(ProductDto.GetProductRequest getProductRequest) {
        List<ProductDto.GetProductDTO> products = productQueryRepository.findProducts(getProductRequest);
        return ProductDto.GetProductResponse.builder()
                .products(products)
                .build();
    }

    @Transactional
    public ProductDto.GetProductsRankingResponse getProductsRanking(ProductDto.GetProductsRankingRequest getProductsRankingRequest) {
        List<ProductDto.GetProductsRankingDTO> getProductsRankingDTOList = productQueryRepository.findProductsRanking(getProductsRankingRequest);
        return ProductDto.GetProductsRankingResponse.builder()
                .productsRankingDTOList(getProductsRankingDTOList)
                .build();
    }

    @Transactional
    public ProductDto.GetProductFunctionTypesResponse getProductFunctionTypes(Long productTypeId) {
        List<ProductFunctionType> productFunctionTypes = jpaProductFunctionTypeRepository.findByProductType(ProductType.builder().productTypeId(productTypeId).build());
        List<ProductDto.GetProductFunctionTypeDTO> getProductFunctionTypeDTOS = productFunctionTypes.stream()
                .map(productFunctionType -> {
                    List<ProductFunction> productFunctions = jpaProductFunctionRepository.findByProductFunctionType(ProductFunctionType.builder().productFunctionTypeId(productFunctionType.getProductFunctionTypeId()).build());
                    List<ProductDto.GetProductFunctionDTO> getProductFunctionDTOS = productFunctions.stream()
                            .map(productFunction -> ProductDto.GetProductFunctionDTO.builder().productFunctionId(productFunction.getProductFunctionId()).productFunctionName(productFunction.getProductFunctionName()).build())
                            .toList();
                    return ProductDto.GetProductFunctionTypeDTO.builder()
                            .productFunctionTypeId(productFunctionType.getProductFunctionTypeId())
                            .productFunctionTypeName(productFunctionType.getProductFunctionTypeName())
                            .productFunctions(getProductFunctionDTOS)
                            .build();
                })
                .toList();
        return ProductDto.GetProductFunctionTypesResponse.builder().productFunctionTypes(getProductFunctionTypeDTOS).build();
    }
}
