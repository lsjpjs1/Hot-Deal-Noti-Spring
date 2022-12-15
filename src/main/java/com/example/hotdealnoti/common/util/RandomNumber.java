package com.example.hotdealnoti.common.util;

import java.util.Random;

public class RandomNumber {
    private static final Random random = new Random();
    private static final int VERIFICATION_CODE_SIZE = 6;
    private static final int PASSWORD_SIZE = 10;

    public static String generateRandomCode() {
        String verificationCode = "";
        for(int i=0; i<VERIFICATION_CODE_SIZE; i++) {
            verificationCode = verificationCode + random.nextInt(9);
        }
        return verificationCode;
    }

    public static String generateRandomCode(int length) {
        String verificationCode = "";
        for(int i=0; i<length; i++) {
            verificationCode = verificationCode + random.nextInt(9);
        }
        return verificationCode;
    }

    public static String generatePassword() {
        String newPassword = "";
        for(int i=0; i<PASSWORD_SIZE; i++) {
            newPassword = newPassword + random.nextInt(9);
        }
        return newPassword;
    }
}
