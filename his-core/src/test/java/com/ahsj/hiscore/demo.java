package com.ahsj.hiscore;

import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author dingli
 * @Date 2019/8/15
 * @Time 11:10
 **/
public class demo {
/** className demo
 *@author dingli
 *@date 2019/8/15 11:10
 */
@Test
public void init(){
    ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    System.out.println("开始");
    scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
        @Override
        public void run() {
            System.out.println("-----------测试------------------");
        }
    },0,10000, TimeUnit.MILLISECONDS);

}



}
