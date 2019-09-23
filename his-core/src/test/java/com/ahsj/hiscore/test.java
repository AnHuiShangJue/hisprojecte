package com.ahsj.hiscore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @Description
 * @Author dingli
 * @Date 2019/8/13
 * @Time 13:58
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HisCoreApplication.class)
public class test {
/** className test
 *@author dingli
 *@date 2019/8/13 13:58
 */
private static final  int NUM = 1000;
@Test
public void test()throws Exception{
   Date date = new Date();
    System.out.println(date.getTime());


}

    public static void main(String[] args) {


    }


}
