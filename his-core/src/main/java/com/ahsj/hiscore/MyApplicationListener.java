package com.ahsj.hiscore;

import com.ahsj.hiscore.core.WebContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;

@Component
public class MyApplicationListener  implements ApplicationListener<ApplicationReadyEvent> {

    private static Logger logger = LoggerFactory.getLogger(MyApplicationListener.class);

    @Autowired
    private ServletContext servletContext;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        logger.info("初始化全局参数开始----------------------------------------------");
        ApplicationContext applicationContext =   event.getApplicationContext();
        logger.info("ApplicationContext: name is:"+applicationContext.getApplicationName()+"\t id is:"+applicationContext.getId()+"\t DisplayName is:"+applicationContext.getDisplayName());
        logger.info("ServletContext: getContextPath is:"+servletContext.getContextPath()+"\t getServerInfo is:"+servletContext.getServerInfo()+"\t getContextPath is:"+servletContext.getContextPath());
        WebContextUtil.setServletCountext(servletContext);
        try {
            WebContextUtil.initGlobalData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("初始化全局参数结束----------------------------------------------");
    }
}
