package com.example.hotdealnoti.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AccountType implements EnumEntityConvertable {
    KAKAO("KAKAO");

    private String accountType;

    AccountType(String accountType) {this.accountType = accountType;}

    @Override
    public String getEntityValue() {
        return accountType;
    }

    @Override
    public Boolean isTargetEnum(String s) {
        return accountType.equals(s);
    }

}
