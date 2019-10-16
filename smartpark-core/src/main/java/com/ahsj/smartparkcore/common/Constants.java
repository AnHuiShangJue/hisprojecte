package com.ahsj.smartparkcore.common;

import com.ahsj.smartparkcore.controller.BaseOAController;

/**

 *@Author XJP
 *@Date 2019/7/26
 *@Time 14:35
**/
public class Constants {

    private static BaseOAController resourseUrl = new BaseOAController();

    //成功
    public static final String SUCCESSFUL= "SUCCESSFUL";
    //失败
    public static final String ERROR= "ERROR";

    public static final Integer ZERO= 0;

    public static final Integer ONE= 1;

    public static final Integer TWO= 2;

    public static final Integer THREE= 3;

    public static final String HIS_SYS_EXCEL_PROJECT_CH_FILE_URL = resourseUrl.getJarResourcesPaths() + "/config/LocList.xml";

    public Constants() {
    }
}