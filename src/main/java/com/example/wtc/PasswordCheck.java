package com.example.wtc;

public class PasswordCheck {

    public PasswordString input(String data) {
//        널값 or 빈값 예외처리
        if (data == null || data.equals("")) {
            throw new IllegalArgumentException();
        }

        boolean lengthRule = data.length() >= 8;

//        문자만 사용한 경우
        if (!numberRule(data) && uppercaseRule(data)) {
            return PasswordString.MIDDLE;
        }
//        숫자만 사용한 경우
        if (!numberRule(data)) {
            return PasswordString.WEEK;
        }
//        입력값이 8자 미만일때
        if (!lengthRule) {
            return PasswordString.WEEK;
        }
//        대문자가 없는 경우
        if (!uppercaseRule(data)) {
            return PasswordString.WEEK;
        }

        String[] box = data.split("");

        return PasswordString.STRONG;
    }

    public boolean uppercaseRule(String data) {
        char[] chars = data.toCharArray();
        for (char target : chars) {
            if (target >= 'A' && target <= 'Z') {
                return true;
            }
        }
        return false;
    }

    public boolean numberRule(String data) {
        char[] chars = data.toCharArray();
        for (char target : chars) {
            if (target >= '0' && target <= '9') {
                return true;
            }
        }
        return false;
    }
}
