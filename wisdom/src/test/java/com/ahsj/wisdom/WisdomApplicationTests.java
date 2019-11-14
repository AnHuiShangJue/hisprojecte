package com.ahsj.wisdom;


import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class WisdomApplicationTests {


    public static void main(String[] args) {
        String a = System.getProperty("user.dir") + "/wisdom/src/main/resources/static/assets/app-assets/images/wisdom/two.png";
        System.out.println(a);
        File f = new File(a);
        boolean delete = f.delete();
        System.out.println(delete);
    }
}
