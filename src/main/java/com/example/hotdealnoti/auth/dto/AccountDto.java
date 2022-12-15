package com.example.hotdealnoti.auth.dto;

import com.example.hotdealnoti.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

public class AccountDto {


    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class GetUserEmailResponse {

        private String userEmail;


    }

}
