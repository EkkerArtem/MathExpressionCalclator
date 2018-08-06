package com.EkkerArtem.stream.calculator;

public class IsValid {

    /**
     * @param exp input string.
     * @return if it is open parenthesis.
     */
    public static boolean isOpenParentheses(String exp) {
        char[] chars = exp.toCharArray();
        if (chars.length > 1) {
            throw new IllegalArgumentException();
        }
        return chars[0] == 40;
    }

    /**
     * @param exp input string.
     * @return if it is open parenthesis.
     */
    public static boolean isCloseParentheses(String exp) {
        char[] chars = exp.toCharArray();
        if (chars.length > 1) {
            throw new IllegalArgumentException();
        }
        return chars[0] == 41;
    }
}
