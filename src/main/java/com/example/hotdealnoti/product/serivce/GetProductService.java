package com.example.hotdealnoti.product.serivce;

import com.example.hotdealnoti.product.dto.ProductDto;
import com.example.hotdealnoti.product.repository.ProductQueryRepository;
import com.example.hotdealnoti.repository.jpa.JpaProductPurposeRepository;
import com.example.hotdealnoti.repository.jpa.JpaProductTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetProductService {

    private final JpaProductPurposeRepository jpaProductPurposeRepository;
    private final JpaProductTypeRepository jpaProductTypeRepository;
    private final ProductQueryRepository productQueryRepository;
    @Transactional
    public ProductDto.GetProductResponse getProducts(ProductDto.GetProductRequest getProductRequest) {
        List<ProductDto.GetProductDTO> products = productQueryRepository.findProducts(getProductRequest);
        return ProductDto.GetProductResponse.builder()
                .products(products)
                .build();
    }
}
