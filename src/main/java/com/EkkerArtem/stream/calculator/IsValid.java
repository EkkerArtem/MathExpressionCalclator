package com.EkkerArtem.stream.calculator;

public class IsValid {

    public static boolean isOpenParentheses(String exp) {
        char[] chars = exp.toCharArray();
        if (chars.length > 1) {
            throw new IllegalArgumentException();
        }
        return chars[0] == 40;
    }

    public static boolean isCloseParentheses(String exp) {
        char[] chars = exp.toCharArray();
        if (chars.length > 1) {
            throw new IllegalArgumentException();
        }
        return chars[0] == 41;
    }
}
