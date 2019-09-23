package com.ahsj.hiscore.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: CheckRepeatUtils
 * <p>
 * Date:     2019/8/30 11:26
 *
 * @author XJP
 * @create 2019/8/30
 * @since 1.0.0
 */

public class CheckRepeatUtils {

    /**
     * @return java.lang.Boolean
     * @功能说明 判断集合中是否存在重复元素
     * @Params [stringList]
     * @Author XJP
     * @Date 2019/8/30
     * @Time 11:28
     **/
    private static Boolean existRepeatedElements(List<String> stringList) {
        for (int i = 0; i < stringList.size() - 1; i++) {
            for (int j = i + 1; j < stringList.size(); j++) {
                if ((stringList.get(i) == stringList.get(j)) && (i != j)) {

                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @return java.util.Map<java.lang.String, java.util.List < java.lang.Integer>>
     * @功能说明 判断集合中是否存在重复元素 并记录
     * @Params [stringList]
     * @Author XJP
     * @Date 2019/8/30
     * @Time 11:27
     **/
    public static Map<String, List<Integer>> findAllRepeatedElementsString(List<String> stringList) {

        Map<String, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < stringList.size() ; i++) {
            // 重复元素的下标列表
            List<Integer> list = new ArrayList<>();

            // 防止重复元素再次出现覆盖第一次出现时获得的结果
            if (map.get(stringList.get(i)) != null) {
                break;
            }

            list.add(i);
            for (int j = i + 1; j < stringList.size(); j++) {
                if ((StringUtils.equals(stringList.get(i),stringList.get(j))) && (i != j)) {
                    list.add(j);
                    map.put(stringList.get(i), list);
                }
            }
        }
        return map;
    }

    /**
     * @return java.util.Map<java.lang.Integer, java.util.List < java.lang.Integer>>
     * @功能说明  判断集合中是否存在重复元素 并记录
     * @Params [arr]
     * @Author XJP
     * @Date 2019/8/30
     * @Time 11:28
     **/
    public static Map<Integer, List<Integer>> findAllRepeatedElementsInteger(int[] arr) {

        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < arr.length - 1; i++) {
            // 重复元素的下标列表
            List<Integer> list = new ArrayList<>();

            // 防止重复元素再次出现覆盖第一次出现时获得的结果
            if (map.get(arr[i]) != null) {
                break;
            }

            list.add(i);
            for (int j = i + 1; j < arr.length; j++) {
                if ((arr[i] == arr[j]) && (i != j)) {
                    list.add(j);
                    map.put(arr[i], list);
                }
            }
        }
        return map;
    }


}
