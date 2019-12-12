package com.sergeb;

public class StringHelper {

    public static boolean isNumeric(String str) {
        return str.matches("[0-9]+");
    }

    public static boolean isValidYesNo(String str) {
        return str.matches("[Y|N]$");
    }
}
