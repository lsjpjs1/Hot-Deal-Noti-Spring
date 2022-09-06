package com.example.hotdealnoti.customer.service;

import com.example.hotdealnoti.customer.domain.CustomerRequirement;
import com.example.hotdealnoti.customer.dto.CustomerDto;
import com.example.hotdealnoti.messagequeue.domain.HotDeal;
import com.example.hotdealnoti.product.domain.Manufacturer;
import com.example.hotdealnoti.product.domain.Product;
import com.example.hotdealnoti.product.dto.ProductDto;
import com.example.hotdealnoti.repository.jpa.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostCustomerRequirementService {


    private final JpaCustomerRequirementRepository jpaCustomerRequirementRepository;
    @Transactional
    public void postCustomerRequirement(CustomerDto.PostCustomerRequirementRequest postCustomerRequirementRequest) {
        CustomerRequirement customerRequirement = CustomerRequirement.builder()
                .customerRequirementBody(postCustomerRequirementRequest.getCustomerRequirementBody())
                .userIp(postCustomerRequirementRequest.getUserIp())
                .build();
        jpaCustomerRequirementRepository.save(customerRequirement);



    }
}
