package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.HisCoreApplication;
import com.ahsj.hiscore.services.SystemShutdownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class SystemShutdownServicelmpl implements SystemShutdownService {
    @Autowired
    private ConfigurableApplicationContext context;
    /**
     *@Description 定时关闭
     *@Params []
     *@return void
     *@Author zhushixiang
     *@Date 2019-10-10
     *@Time 9:38
    **/
    @Override
    public void TimedOff() throws Exception {
        context.close();
    }
}
