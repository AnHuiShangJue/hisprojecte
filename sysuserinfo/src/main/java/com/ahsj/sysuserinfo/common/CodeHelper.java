package com.ahsj.sysuserinfo.common;

import com.ahsj.sysuserinfo.entity.SysCodeDetail;
import core.code.CodeValueColumn;
import core.code.Constants;
import utils.ReflectUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * <ul>
 * <li>Title: 匠桥基础开发框架-CodeHelper.java</li>
 * <li>Description: TODO </li>
 * <li>Copyright: Copyright (c) 2018</li>
 * <li>Company: http://www.ahjianqiao.com/</li>
 * </ul>
 *
 * @author shenggs
 * @version 匠桥基础开发框架1.0
 * @date 2018年3月25日 下午10:37:08
 */
public class CodeHelper {
    
    private CodeHelper() {
        
    }
    public static CodeHelper getInstance() {
         return new CodeHelper();
    }

    public <T> List<T> setCodeValue(List<T> lst) {
        if (lst == null || lst.size() == 0) {
            return lst;
        }
        try {
            List<Field> annotation = new ArrayList<Field>();
            Field[] field = null;
            for (Class<?> clazz = lst.get(0).getClass(); clazz != Object.class; clazz = clazz
                    .getSuperclass()) {
                try {
                    field = clazz.getDeclaredFields();
                    for (int i = 0; i < field.length; i++) {
                        if (field[i].isAnnotationPresent(CodeValueColumn.class)) {
                            annotation.add(field[i]);
                        }
                    }
                } catch (Exception e) {
                }
            }
            T baseEntity = null;
            LinkedHashMap<String, Object> enumData = null;
            List<SysCodeDetail> codeData = null;
            ConcurrentHashMap<String, LinkedHashMap<String, Object>> enumDatas = (ConcurrentHashMap<String, LinkedHashMap<String, Object>>)WebContextUtil.getServletContext().getAttribute(Constants.GLOBAL_DATA_ENUM);
            ConcurrentHashMap<String, List<SysCodeDetail>> codeDatas = (ConcurrentHashMap<String, List<SysCodeDetail>>)WebContextUtil.getServletContext().getAttribute(Constants.GLOBAL_DATA_TABLE);
            for (int j = 0; j < lst.size(); j++) {
                baseEntity = lst.get(j);
                for (Field each : annotation) {
                    CodeValueColumn codeValueColumn = each.getAnnotation(CodeValueColumn.class);
                    if (Constants.TYPE_ENUM.equals(codeValueColumn.type())) {
                        Object object = ReflectUtils.getFieldValue(baseEntity, each.getName());
                        enumData = enumDatas.get(codeValueColumn.typeKey());
                        for (Entry<String, Object> entry : enumData.entrySet()) {
                            if (object != null && entry.getKey().equals(object.toString())) {
                                ReflectUtils.setFieldValue(baseEntity, codeValueColumn.typeName(), entry.getValue());
                            }
                        }
                    }
                    if (Constants.TYPE_CODE.equals(codeValueColumn.type())) {
                        Object object = ReflectUtils.getFieldValue(baseEntity, each.getName());
                        codeData = codeDatas.get(codeValueColumn.typeKey());
                        if (codeData != null) {
                            for (SysCodeDetail sysCodeDetail: codeData) {
                                if (object != null && sysCodeDetail.getCode().equals(object.toString())) {
                                    ReflectUtils.setFieldValue(baseEntity, codeValueColumn.typeName(), sysCodeDetail.getValue());
                                }
                            }
                        }
                    }
                }
            }
            return lst;
        } catch (Exception e) {
            return lst;
        }
    }

    public <T> T setCodeValue(T t) {
        try {
            List<Field> annotation = new ArrayList<Field>();
            Field[] field = null;
            for (Class<?> clazz = t.getClass(); clazz != Object.class; clazz = clazz
                    .getSuperclass()) {
                try {
                    field = clazz.getDeclaredFields();
                    for (int i = 0; i < field.length; i++) {
                        if (field[i].isAnnotationPresent(CodeValueColumn.class)) {
                            annotation.add(field[i]);
                        }
                    }
                } catch (Exception e) {
                }
            }

            List<SysCodeDetail> codeData = null;
            ConcurrentHashMap<String, LinkedHashMap<String, Object>> enumDatas = (ConcurrentHashMap<String, LinkedHashMap<String, Object>>)WebContextUtil.getServletContext().getAttribute(Constants.GLOBAL_DATA_ENUM);
            ConcurrentHashMap<String, List<SysCodeDetail>> codeDatas = (ConcurrentHashMap<String, List<SysCodeDetail>>)WebContextUtil.getServletContext().getAttribute(Constants.GLOBAL_DATA_TABLE);
               for (Field each : annotation) {
                    CodeValueColumn codeValueColumn = each.getAnnotation(CodeValueColumn.class);
                    if (Constants.TYPE_CODE.equals(codeValueColumn.type())) {
                        codeData = codeDatas.get(codeValueColumn.typeKey());

                        Object object = ReflectUtils.getFieldValue(t, each.getName());
                        if (object != null) {
                            for (SysCodeDetail codeDataDetail : codeData) {
                                if (codeDataDetail.getCode().equals(object.toString())) {
                                    ReflectUtils.setFieldValue(t, codeValueColumn.typeName(), codeDataDetail.getValue());
                                }
                            }
                        }
                    }
                }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return t;
    }
}