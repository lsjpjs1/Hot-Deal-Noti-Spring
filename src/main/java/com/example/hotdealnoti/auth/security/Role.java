package com.example.hotdealnoti.auth.security;

import com.example.hotdealnoti.enums.EnumEntityConvertable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role implements EnumEntityConvertable {

    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private String role;

    @Override
    public String getEntityValue() {
        return role;
    }

    @Override
    public Boolean isTargetEnum(String s) {
        return role.equals(s);
    }
}
