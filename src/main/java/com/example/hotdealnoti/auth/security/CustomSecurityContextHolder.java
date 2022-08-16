package com.example.hotdealnoti.auth.security;

import com.example.hotdealnoti.auth.domain.Account;
import org.springframework.security.core.context.SecurityContextHolder;

public class CustomSecurityContextHolder extends SecurityContextHolder {
    public static Account getAccountFromToken() {
        return (Account) getContext().getAuthentication().getPrincipal();
    }
}
