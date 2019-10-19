package com.ahsj.smartparkcore;

import com.ahsj.smartparkcore.common.utils.LocalUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmartparkCoreApplicationTests {

    @Test
    public void test1(){
        LocalUtil lu =  LocalUtil.getInstance();
        List<String> list = 	lu.getCities("中国", "北京");
        for(int i=0; i<list.size(); i++){
            System.out.print(list.get(i) + " ");
        }
    }
    @Test
    public void test2(){
        LocalUtil lu =  LocalUtil.getInstance();
        List<String> list = 	lu.getProvinces("中国");
        int j=0;
        for(int i=0; i<list.size(); i++){
            j++;
            System.out.print(list.get(i) + " "+"\n");
        }
        System.out.println(j);
    }
    @Test
    public  void test3(){
        LocalUtil lu = LocalUtil.getInstance();
        List<String> str = new ArrayList<>();
        List<String> list = lu.getcounty("中国", "浙江", "杭州");
        for (int i = 0; i < list.size(); i++) {
            str.add(list.get(i) + " ");
        }
        System.out.println(str);
    }

}
