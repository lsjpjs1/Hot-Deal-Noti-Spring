package com.example.hotdealnoti.repository.jpa;

import com.example.hotdealnoti.auth.domain.Account;
import com.example.hotdealnoti.auth.domain.EmailVerification;
import com.example.hotdealnoti.enums.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaEmailVerificationRepository extends JpaRepository<EmailVerification,Long> {
    Optional<EmailVerification> findTopByAccountIdOrderByEmailVerificationIdDesc(Long accountId);

    Optional<EmailVerification> findTopByAccountId(Long accountId);
}
