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

    public static final String ConferenceRoomInfo= "会议室";

    public static final String site = "场地";

    public static final String StationInfo= "工位";

    public static final String IS_LEASE= "以租赁";

    public static final String NO_LEASE= "未租赁";

    public static final String SMARTPARKCORE= "smartparkcore";

    public static final String  STATIC= "static";

    public static final String  LIST= "list";

    public static final String  STATIONINFO= "stationinfo";

    public static final String  SITE= "site";

    public static final String  CONFERENCEROOMINFO= "conferenceRoomInfo";

    public static final String  LOCALHOST= "http://localhost:8022";

    public static final String HIS_SYS_EXCEL_PROJECT_CH_FILE_URL = resourseUrl.getJarResourcesPaths() + "/config/LocList.xml";

    public static final String FILE_PATHS = resourseUrl.getJarResourcesPaths() + "/static/assets/upload/";
    public static final String FILE_PATHS_WX = resourseUrl.getJarResourcesPaths() + "/static/assets/upload/wximg";

    public static final String FILE_PATHS_LOCAL = resourseUrl.getJarResourcesPaths();

    public Constants() {
    }
}
