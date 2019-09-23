package com.ahsj.hiscore.common.utils;

import java.util.Date;

/**

 *  @tags An overview of this file: 相对时间转换
 * 
 */
public class RelativeDateFormat {

    public void getDate(Date startDate , Date endDate){
        long dff = endDate.getTime() - startDate.getTime();
        long day = dff / (1000 * 60 * 60 * 24);
        long hour = (dff - day * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (dff - day * (1000 * 60 * 60 * 24) - hour * (1000 * 60 * 60)) / (1000 * 60);

    }

}