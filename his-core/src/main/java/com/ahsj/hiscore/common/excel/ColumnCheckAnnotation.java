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
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ColumnCheckAnnotation {
    int index() default -1;// -1表示该字段段不用从excel里面读取，0，1,2,3表示该字段属于excel哪个列

    abstract DataType dataType() default DataType.String;// 数据类型
    
    String codeKey() default "";

    int length() default 32;// 长度

    boolean nullable() default false;// 是否为空

    String dateFormat() default "";// 时间格式

    String checksql() default "";// 表示该字段需要把excel单元格数据区数据库查询相应的值
    
    boolean isRepeat() default true;//是否允许重复
    
    String[] enums() default {}; // 枚举 值
    
    String[] enumCNs() default {}; // 枚举 名
    
    String rePepeatSql() default "";
    
}
