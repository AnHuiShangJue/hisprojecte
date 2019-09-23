/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: CalendarResourse
 * Author:   anhuishangjue
 * Date:     2019/7/5 14:28
 * Description: 日历资源信息数据
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.ahsj.smartparkcore.common.dto;

/**
 * 〈一句话功能简述〉<br>
 * 〈日历资源信息数据〉
 *
 * @author anhuishangjue
 * @create 2019/7/5
 * @since 1.0.0
 */

public class CalendarResourse {
    private String id; //-此资源的唯一字符串标识符
    private String title; //-此资源的字符串标题
    private String extendedProps;//-解析期间指定的非标准道具的哈希
    private String eventConstraint;
    private String eventOverlap;
    private String eventAllow;
    private String eventBackgroundColor;
    private String eventBorderColor;
    private String eventTextColor;
    private String eventClassNames;

    public CalendarResourse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExtendedProps() {
        return extendedProps;
    }

    public void setExtendedProps(String extendedProps) {
        this.extendedProps = extendedProps;
    }

    public String getEventConstraint() {
        return eventConstraint;
    }

    public void setEventConstraint(String eventConstraint) {
        this.eventConstraint = eventConstraint;
    }

    public String getEventOverlap() {
        return eventOverlap;
    }

    public void setEventOverlap(String eventOverlap) {
        this.eventOverlap = eventOverlap;
    }

    public String getEventAllow() {
        return eventAllow;
    }

    public void setEventAllow(String eventAllow) {
        this.eventAllow = eventAllow;
    }

    public String getEventBackgroundColor() {
        return eventBackgroundColor;
    }

    public void setEventBackgroundColor(String eventBackgroundColor) {
        this.eventBackgroundColor = eventBackgroundColor;
    }

    public String getEventBorderColor() {
        return eventBorderColor;
    }

    public void setEventBorderColor(String eventBorderColor) {
        this.eventBorderColor = eventBorderColor;
    }

    public String getEventTextColor() {
        return eventTextColor;
    }

    public void setEventTextColor(String eventTextColor) {
        this.eventTextColor = eventTextColor;
    }

    public String getEventClassNames() {
        return eventClassNames;
    }

    public void setEventClassNames(String eventClassNames) {
        this.eventClassNames = eventClassNames;
    }

    @Override
    public String toString() {
        return "CalendarResourse{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", extendedProps='" + extendedProps + '\'' +
                ", eventConstraint='" + eventConstraint + '\'' +
                ", eventOverlap='" + eventOverlap + '\'' +
                ", eventAllow='" + eventAllow + '\'' +
                ", eventBackgroundColor='" + eventBackgroundColor + '\'' +
                ", eventBorderColor='" + eventBorderColor + '\'' +
                ", eventTextColor='" + eventTextColor + '\'' +
                ", eventClassNames='" + eventClassNames + '\'' +
                '}';
    }
}
