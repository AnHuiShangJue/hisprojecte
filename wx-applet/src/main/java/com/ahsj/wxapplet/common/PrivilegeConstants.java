package com.ahsj.wxapplet.common;

/**
 * @program: his
 * @description:PrivilegeConstants
 * @author: chenzhicai
 * @create: 2019-07-01-10-38
 **/
public class PrivilegeConstants {

    /**
     * 统计首页
     */
    public static final String DATA_COUNT = "sys.data";


    /** 用户信息管理---------------------------------------------------------------------------------------------------BEGIN**/
    /**
     * 用户信息
     **/
    public static final String USER_MENU = "sys.user";

    public static final String USER_ADD = "sys.user.add";

    public static final String USER_DELETE = "sys.user.delete";

    public static final String USER_UPDATE = "sys.user.update";

    /**
     * 用户信息管理---------------------------------------------------------------------------------------------------END
     **/


    public String getUSER_MENU() {
        return USER_MENU;
    }

    public String getUSER_ADD() {
        return USER_ADD;
    }

    public String getUSER_DELETE() {
        return USER_DELETE;
    }

    public String getUSER_UPDATE() {
        return USER_UPDATE;
    }


}

    