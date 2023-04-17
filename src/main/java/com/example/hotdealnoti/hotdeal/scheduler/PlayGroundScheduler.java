package com.example.hotdealnoti.hotdeal.scheduler;

import com.example.hotdealnoti.hotdeal.repository.HotDealQueryRepository;
import com.example.hotdealnoti.product.domain.ProductAdditionalFunction;
import com.example.hotdealnoti.product.domain.ProductFunction;
import com.example.hotdealnoti.product.domain.ProductFunctionType;
import com.example.hotdealnoti.product.domain.ProductType;
import com.example.hotdealnoti.repository.jpa.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class PlayGroundScheduler {

    private final JpaProductFunctionTypeRepository jpaProductFunctionTypeRepository;
    private final JpaProductFunctionRepository jpaProductFunctionRepository;
    private final JpaProductAdditionalFunctionRepository jpaProductAdditionalFunctionRepository;


//    @Transactional
//    public void playGround() {
//
//        List<ProductAdditionalFunction> productAdditionalFunctions = jpaProductAdditionalFunctionRepository.findByProductFunctionTypeAndProductType("무게", 1l);
//        productAdditionalFunctions.forEach(productAdditionalFunction -> {
//            String productFunctionName = productAdditionalFunction.getProductFunction().getProductFunctionName();
//            try {
//                String onlyNumberWeight = productFunctionName.replaceAll("kg","");
//                Float weight = Float.valueOf(onlyNumberWeight);
//                log.info(weight.toString());
//                ProductFunction productFunction = null;
//                if (weight.compareTo(1.0f)<=0){
//                    log.info("1.0 이하");
//                    productFunction = jpaProductFunctionRepository.findByProductFunctionName("1kg 이하");
//                }else if (weight.compareTo(1.0f)>0&&weight.compareTo(1.5f)<=0){
//                    log.info("1.0 ~1.5");
//                    productFunction = jpaProductFunctionRepository.findByProductFunctionName("1kg ~ 1.5kg");
//                }else if (weight.compareTo(1.5f)>0&&weight.compareTo(2.0f)<=0){
//                    log.info("1.5 ~2");
//                    productFunction = jpaProductFunctionRepository.findByProductFunctionName("1.5kg ~ 2kg");
//                }else if (weight.compareTo(2.0f)>0&&weight.compareTo(2.5f)<0){
//                    log.info("2 ~2.5");
//                    productFunction = jpaProductFunctionRepository.findByProductFunctionName("2kg ~ 2.5kg");
//                }else if (weight.compareTo(2.5f)>=0){
//                    log.info("2.5~");
//                    productFunction = jpaProductFunctionRepository.findByProductFunctionName("2.5kg 이상");
//                }else{
//                    log.info("예외");
//                }
//
//                ProductAdditionalFunction weightRangeProductAdditionalFunction = ProductAdditionalFunction.builder()
//                        .product(productAdditionalFunction.getProduct())
//                        .productFunction(productFunction)
//                        .build();
//                log.info(weightRangeProductAdditionalFunction.toString());
//                jpaProductAdditionalFunctionRepository.save(weightRangeProductAdditionalFunction);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        });

    @Transactional
    public void playGround() {

        List<ProductAdditionalFunction> productAdditionalFunctions = jpaProductAdditionalFunctionRepository.findByProductFunctionTypeAndProductType("무게", 1l);
        productAdditionalFunctions.forEach(productAdditionalFunction -> {
            String productFunctionName = productAdditionalFunction.getProductFunction().getProductFunctionName();
            try {
                if(productFunctionName.matches("\\d+g")){
                    ProductFunction productFunction =jpaProductFunctionRepository.findByProductFunctionName("1kg 이하");
                    ProductAdditionalFunction weightRangeProductAdditionalFunction = ProductAdditionalFunction.builder()
                            .product(productAdditionalFunction.getProduct())
                            .productFunction(productFunction)
                            .build();
                    log.info(weightRangeProductAdditionalFunction.toString());
                    jpaProductAdditionalFunctionRepository.save(weightRangeProductAdditionalFunction);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });

    }
}
