package com.ahsj.smartparkcore.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: his
 * @description:SpringMvcConfig
 * @author: chenzhicai
 * @create: 2019-06-21-18-32
 **/
//@Configuration
public class SpringMvcConfig {
    /**
     * 注册一个类型解析器
     * @param binder
     */
    @InitBinder
    public void InitBinder(WebDataBinder binder){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @Autowired
    RequestMappingHandlerAdapter handlerAdapter;
    @PostConstruct
    public void  initEditableValidate(){
        ConfigurableWebBindingInitializer initilaize = (ConfigurableWebBindingInitializer) handlerAdapter.getWebBindingInitializer();
        if(initilaize.getConversionService() != null){
            GenericConversionService genericConver = (GenericConversionService) initilaize.getConversionService();
            genericConver.addConverter(new StringToDateConverter());
        }
    }
}

    