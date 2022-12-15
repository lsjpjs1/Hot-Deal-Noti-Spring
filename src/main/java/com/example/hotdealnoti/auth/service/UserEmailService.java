package com.example.hotdealnoti.auth.service;

import com.example.hotdealnoti.auth.domain.Account;
import com.example.hotdealnoti.auth.domain.EmailVerification;
import com.example.hotdealnoti.common.util.MailDTO;
import com.example.hotdealnoti.common.util.MailUtil;
import com.example.hotdealnoti.common.util.RandomNumber;
import com.example.hotdealnoti.exception.CustomException;
import com.example.hotdealnoti.exception.ErrorCode;
import com.example.hotdealnoti.repository.jpa.JpaAccountRepository;
import com.example.hotdealnoti.repository.jpa.JpaEmailVerificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserEmailService {

   private final MailUtil mailUtil;
   private final JpaEmailVerificationRepository jpaEmailVerificationRepository;
   private final JpaAccountRepository jpaAccountRepository;

    @Transactional
    public void sendEmailVerificationCode(Account account,String userEmail) {

        String emailVerificationCode = RandomNumber.generateRandomCode();

        try {
            mailUtil.sendMail(
                    MailDTO.MailRequest.builder()
                            .receiverEmail(userEmail)
                            .subject("[특가어디가] 이메일 인증번호")
                            .body("인증번호: "+emailVerificationCode)
                            .build()
            );

            jpaEmailVerificationRepository.findTopByAccountId(account.getAccountId())
                    .ifPresentOrElse(
                            (emailVerification -> {
                                emailVerification.setEmail(userEmail);
                                emailVerification.setEmailVerificationCode(emailVerificationCode);
                                jpaEmailVerificationRepository.save(emailVerification);
                            }),
                            ()->{
                                EmailVerification emailVerification = EmailVerification.builder()
                                        .accountId(account.getAccountId())
                                        .emailVerificationCode(emailVerificationCode)
                                        .email(userEmail)
                                        .build();
                                jpaEmailVerificationRepository.save(emailVerification);
                            }
                    );
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new CustomException(ErrorCode.SEND_EMAIL_FAIL);
        }


    }

    @Transactional
    public void checkEmailVerificationCode(Account account,String verificationCode) {

       //인증 엔티티 가져오기
        EmailVerification emailVerification = jpaEmailVerificationRepository.findTopByAccountIdOrderByEmailVerificationIdDesc(account.getAccountId())
                .orElseThrow(() -> {
                    throw new CustomException(ErrorCode.VERIFICATION_CODE_NOT_EXIST);
                });


        //인증번호 검사 하기,  틀리면 에러 던지기
        if (!emailVerification.getEmailVerificationCode().equals(verificationCode)){
            throw new CustomException(ErrorCode.VERIFICATION_CODE_WRONG);
        }

        //계정에 email 업데이트
        Account persistAccount = jpaAccountRepository.findById(account.getAccountId())
                .orElseThrow(() -> {
                    throw new CustomException(ErrorCode.ACCOUNT_NOT_FOUND);
                });
        persistAccount.setEmail(emailVerification.getEmail());

    }

}
