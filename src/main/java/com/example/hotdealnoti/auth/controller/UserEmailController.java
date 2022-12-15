package com.example.hotdealnoti.auth.controller;

import com.example.hotdealnoti.auth.domain.Account;
import com.example.hotdealnoti.auth.dto.AccountDto;
import com.example.hotdealnoti.auth.dto.AuthDto;
import com.example.hotdealnoti.auth.security.CustomSecurityContextHolder;
import com.example.hotdealnoti.auth.service.KakaoLoginService;
import com.example.hotdealnoti.auth.service.UserEmailService;
import com.example.hotdealnoti.repository.jpa.JpaAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserEmailController {


    private final UserEmailService userEmailService;
    private final JpaAccountRepository jpaAccountRepository;

    @PostMapping("/accounts/email/{userEmail}/verification")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity sendEmailVerificationCode(@PathVariable String userEmail){
        Account accountFromToken = CustomSecurityContextHolder.getAccountFromToken();
        userEmailService.sendEmailVerificationCode(accountFromToken, userEmail);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PostMapping("/accounts/email/verification-code/{verificationCode}/check")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity checkEmailVerificationCode(@PathVariable String verificationCode){
        Account accountFromToken = CustomSecurityContextHolder.getAccountFromToken();
        userEmailService.checkEmailVerificationCode(accountFromToken, verificationCode);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping("/accounts/email")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AccountDto.GetUserEmailResponse> getUserEmail(){
        Account accountFromToken = CustomSecurityContextHolder.getAccountFromToken();
        Account account = jpaAccountRepository.findById(accountFromToken.getAccountId()).get();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(AccountDto.GetUserEmailResponse.builder().userEmail(account.getEmail()).build());
    }
}
