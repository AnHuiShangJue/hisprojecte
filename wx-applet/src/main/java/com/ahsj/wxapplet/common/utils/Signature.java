package com.ahsj.wxapplet.common.utils;

/**
 * @Description 签名类
 * @Author  muxu
 * @Date  2019/10/19
 * @Time 15:41
 * @Return 
 * @Params 
**/
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class Signature {
    public static String getSign(Object o) throws IllegalAccessException {
        ArrayList<String> list = new ArrayList<String>();
        Class cls = o.getClass();//返回引用o运行时真正所指的对象(因为:子对象的引用可能会赋给父对象的引用变量中)所属的类的Class的对象
        Field[] fields = cls.getDeclaredFields();//getDeclaredFields()获得某个类的所有申明的字段，即包括public、private和proteced，但是不包括父类的申明字段。
        for (Field f : fields) {
            f.setAccessible(true);//类中的成员变量为private,故必须进行此操作
            if (f.get(o) != null && f.get(o) != "") {
                String name = f.getName();
                XStreamAlias anno = f.getAnnotation(XStreamAlias.class);
                if(anno != null)
                    name = anno.value();
                list.add(name + "=" + f.get(o) + "&");
            }
        }
        int size = list.size();
        String [] arrayToSort = list.toArray(new String[size]);//使用toArray重载方法将List转为数组（将list转化为String类型的数组）
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);//对key进行排序（在compare中先按照字符串长度取短的那个字符串的长度作为条件，然后循环判断两个字符串的第一个字符的ASCII码大小，做出递增排序，如果两个字符串第一个字符的ASCII码一致，则判断第二个字符，以此类推，通过这种方式将字符串通过首字母的ASCII码进行排序）
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i ++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        result += "key=" + Configure.getKey();
        System.out.println("签名数据："+result);
        result = MD5.MD5Encode(result).toUpperCase();
        return result;
    }

    public static String getSign(Map<String,Object> map){
        ArrayList<String> list = new ArrayList<String>();
        for(Map.Entry<String,Object> entry:map.entrySet()){
            if(entry.getValue()!=""){
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String [] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i ++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        result += "key=" + Configure.getKey();
        //Util.log("Sign Before MD5:" + result);
        result = MD5.MD5Encode(result).toUpperCase();
        //Util.log("Sign Result:" + result);
        return result;
    }
}
