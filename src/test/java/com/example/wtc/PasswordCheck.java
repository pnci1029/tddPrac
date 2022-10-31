package com.example.wtc;

public class PasswordCheck {

    public PasswordString input(String data) {
//        널값 or 빈값 예외처리
        if (data == null || data.equals("")) {
            throw new IllegalArgumentException();
        }
//        입력값이 8자 미만일때
        if (data.length() < 8) {
            return PasswordString.WEEK;
        }
//        숫자만 사용한 경우
        countLength(data);

        return PasswordString.STRONG;
    }

    public static void countLength(String data) {
        int numbCount = 0;
        char[] dataBox = data.toCharArray();
        for (char box : dataBox) {
            if (box >= 48 && box <= 57) {
                numbCount++;
            }
        }
        if (numbCount == dataBox.length) {
            throw new IllegalArgumentException();
        }
    }
}
