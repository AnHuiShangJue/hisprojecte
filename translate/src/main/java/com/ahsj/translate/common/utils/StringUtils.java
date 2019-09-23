package com.ahsj.translate.common.utils;

/**
 * @description
 * @author: xjp
 * @create: 2019-07-21 10:27
 **/
public class StringUtils {

    public static boolean isBlank(String string) {
        if (string == null || "".equals(string.trim())) {
            return true;
        }

        return false;
    }

    public static boolean isNotBlank(String string) {
        return !isBlank(string);
    }
}
