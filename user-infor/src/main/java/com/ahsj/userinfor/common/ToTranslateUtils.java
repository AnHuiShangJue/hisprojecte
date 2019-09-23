package com.ahsj.userinfor.common;

import com.ahsj.userinfor.controller.BaseLoginUser;
import com.ahsj.userinfor.entity.Translate;
import com.ahsj.userinfor.entity.model.TranslateModel;
import com.ahsj.userinfor.feign.ITranslateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: ToTranslateUtils
 * <p>
 * Date:     2019/7/26 19:50
 *
 * @author XJP
 * @create 2019/7/26
 * @since 1.0.0
 */

@Component
public class ToTranslateUtils {

    @Autowired
    ITranslateService iTranslateService;


    public  void toTranslate(Object obj, Class<?> clazz, Long id, String constants) {
        if (obj == null) {
            return ;
        }
        List<Translate> list = new ArrayList<>();
        TranslateModel model = new TranslateModel();
        BaseLoginUser loginUser =  new BaseLoginUser();
        model.setUserId(loginUser.getId());
        Field[] fields = clazz.getDeclaredFields();// 根据Class对象获得属性 私有的也可以获得
        String s = "";
        try {
            for (Field f : fields) {
                Translate translate = new Translate();
                translate.setTranslateId(id);
                translate.setTranslateType(constants);
                translate.setTranslateColumn(f.getName());// 得到此属性的名称
                f.setAccessible(true); // 设置些属性是可以访问的
                Object china = f.get(obj);// 得到此属性的值
                if (china==null){
                    continue;
                }
                translate.setTranslateChina(china.toString());
                list.add(translate);
            }
            model.setTranslateList(list);
            iTranslateService.addTranslate(model);
        } catch (Exception e) {
        }
        //return s;
    }

}
