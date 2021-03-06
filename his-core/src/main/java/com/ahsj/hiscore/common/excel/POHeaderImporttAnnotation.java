/******************************************************************************
 * 
 * 版权所有：安徽驭鹏轮胎有限公司
 * 
 ******************************************************************************
 * 注意：本内容仅限于安徽驭鹏轮胎有限公司内部使用，禁止转发
 *****************************************************************************/
package com.ahsj.hiscore.common.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisRefundProjectInfoController
 * <p>
 * Date:     2019/8/17 13:17
 *
 * @author XJP
 * @create 2019/8/17
 * @since 1.0.0
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface POHeaderImporttAnnotation {

    int rowIndex() default -1;// 头所在行

    int colIndex() default -1;// 头所在列

    String renderAttribute() default "";// 头部映射的字段名称

    Class renderAttributeType() default String.class;// 头部映射的字段数据类型

    String sqlCheck() default "";// sql查询该字段

    String processName() default "";// 对头部单元格解析类全名称

}