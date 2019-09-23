package com.ahsj.hiscore.quartz;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @program: his
 * @description:ApplicationStartQuartzJobListener
 * @author: chenzhicai
 * @create: 2019-07-20-16-01
 **/
public class ApplicationStartQuartzJobListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

    }
}

    