package com.ahsj.payalipay;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PayAlipayApplicationTests {

    @Test
    public void contextLoads() {
        Map<String, String> map = new HashMap<>();
        map.put("1","2");
        for (String key : map.keySet()) {
            String value = map.get(key);
            System.out.println(key+value);
        }
    }

}
