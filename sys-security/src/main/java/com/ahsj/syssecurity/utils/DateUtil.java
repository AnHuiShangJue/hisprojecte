package com.ahsj.syssecurity.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @program: squarenavigationsys
 * @description:时间工具类
 * @author: chenzhicai
 * @create: 2018-11-14-16-17
 **/
public class DateUtil {
    public static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String FORMAT_YYYYMMDD = "yyyyMMdd";
    public static final String FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";


    private static final SimpleDateFormat ISO8601_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZZ");

    public DateUtil() {
    }

    public static final Date getSysDate() {
        return Calendar.getInstance().getTime();
    }

    public static final String dateToStr(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String formatISO(Date date) {
        return ISO8601_DATE_FORMAT.format(date);
    }

    public static String formatISO(long timestamp) {
        return formatISO(new Date(timestamp));
    }

    public static String format(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static String format(long timestamp, String pattern) {
        return format(new Date(timestamp), pattern);
    }
}

    