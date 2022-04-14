package ru.netology.rest.data;

import lombok.Value;

public class DataHelper {
    //зашитые данные для верификации

    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya", "123qwerty");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class MyCard{
        String cardNumber;
    }

    public static MyCard getFirstCard(){
        return new MyCard("5559 0000 0000 0001");
    }

    public static MyCard getSecondCard(){
        return new MyCard("5559 0000 0000 0002");
    }

}

