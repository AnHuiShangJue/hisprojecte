package com.ahsj.syssecurity.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Map;

/**
 * @program: squarenavigationsys
 * @description:
 * @author: chenzhicai
 * @create: 2018-11-10-15-24
 **/
public final class CheckUtil {
    public CheckUtil() {
    }

    public static final boolean isDate(String date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            sdf.parse(date);
            return true;
        } catch (ParseException var3) {
            var3.printStackTrace();
            return false;
        }
    }

    public static final boolean valid(String src) {
        return src != null && !"".equals(src.trim());
    }

    public static final boolean valid(String... src) {
        String[] arr$ = src;
        int len$ = src.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            String s = arr$[i$];
            if (!valid(s)) {
                return false;
            }
        }

        return true;
    }

    public static final boolean valid(Object obj) {
        return null != obj;
    }

    public static final boolean valid(Object... objs) {
        return objs != null && objs.length != 0;
    }

    public static final boolean valid(Collection col) {
        return col != null && !col.isEmpty();
    }

    public static final boolean valid(Collection... cols) {
        Collection[] arr$ = cols;
        int len$ = cols.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            Collection c = arr$[i$];
            if (!valid(c)) {
                return false;
            }
        }

        return true;
    }

    public static final boolean valid(Map map) {
        return map != null && !map.isEmpty();
    }

    public static final boolean valid(Map... maps) {
        Map[] arr$ = maps;
        int len$ = maps.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            Map m = arr$[i$];
            if (!valid(m)) {
                return false;
            }
        }

        return true;
    }
}

    