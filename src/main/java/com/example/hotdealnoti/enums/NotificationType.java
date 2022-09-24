package com.example.hotdealnoti.enums;

public enum NotificationType implements EnumEntityConvertable {
    KEYWORD("KEYWORD");

    private String notificationType;

    NotificationType(String accountType) {this.notificationType = accountType;}

    @Override
    public String getEntityValue() {
        return notificationType;
    }

    @Override
    public Boolean isTargetEnum(String s) {
        return notificationType.equals(s);
    }

}
