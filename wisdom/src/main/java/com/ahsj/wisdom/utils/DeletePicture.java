package com.ahsj.wisdom.utils;

import com.ahsj.wisdom.Constants;
import utils.EmptyUtil;

import java.io.File;

/**
 * @Description
 * @Author dingli
 * @Date 2019/11/4
 * @Time 10:21
 **/
public class DeletePicture {
    /**
     * className Delete
     *
     * @author dingli
     * @date 2019/11/4 10:21
     */
    /**
     * @Description 根据图片名删除图片
     * @Params: [name]
     * @Author: dingli
     * @Return: java.lang.Boolean
     * @Date 2019/11/4
     * @Time 10:24
     **/
    public static Boolean deletePicture(String name) {
        if (EmptyUtil.Companion.isNullOrEmpty(name)) {
            String a = Constants.ImagePath + name;
            File f = new File(a);
            return f.delete();
        }
        return false;
    }
}
