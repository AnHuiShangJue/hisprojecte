package com.ahsj.wis;


import com.ahsj.wis.dao.AboutWisdomMapper;
import com.ahsj.wis.entity.AboutWisdom;
import com.ahsj.wis.service.AboutWisdomService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Component
public class WisApplicationTests {

    @Autowired
    AboutWisdomService aboutWisdomService;

    @Test
    void contextLoads() throws Exception {

        AboutWisdom select = aboutWisdomService.select();
        System.out.println(select.getHanIntroduce());
    }

}
