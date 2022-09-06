package com.example.hotdealnoti.customer.dto;

import com.example.hotdealnoti.messagequeue.domain.HotDeal;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

public class CustomerDto {


    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class PostCustomerRequirementRequest {
        private String customerRequirementBody;
        private String userIp;
    }


}
