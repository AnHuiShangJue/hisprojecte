package com.ahsj.hiscore.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.google.gson.internal.bind.util.ISO8601Utils.format;

/**
 * @ClassName : DateNumber
 * @Description :
 * @Author : XJP
 * @Date: 2020-04-23 16:26
 */
public class DateNumber {

    public static String getNumbenr(String str){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String number = format.format(new Date());
        number = str + number;
        return number;
    }

    public static void main(String[] args) throws ParseException {
        String hc = getNumbenr("HC");
        System.out.println(hc);
    }
}
