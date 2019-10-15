package com.ahsj.payalipay.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: Test
 * <p>
 * Date:     2019/10/15 19:55
 *
 * @author XJP
 * @create 2019/10/15
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/test")
public class Test {


    @PostMapping("/hi")
    public String getHi(){
        return "订立";
    }
}
