package com.mit.pyramid.common.util;

public class ReplaceUtil {
    public static String replace(String str, int num) {
       return str.replace("{0}",String.valueOf(num));
    }

    public static String replace(String str, String str1) {
        return str.replace("{0}",str1);
    }
}
