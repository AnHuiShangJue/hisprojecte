package com.ahsj.wisdom.controller;

import com.ahsj.wisdom.entity.WisdomIndex;
import com.ahsj.wisdom.service.WisdomIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: InitController
 * <p>
 * Date:     2019/11/6 11:31
 *
 * @author XJP
 * @create 2019/11/6
 * @since 1.0.0
 */

@RestController
public class InitController {

    @Autowired
    WisdomIndexService wisdomIndexService;

    @RequestMapping("/")
    ModelAndView lanhuIndex() throws Exception {
        List<WisdomIndex> wisdomIndices = wisdomIndexService.selectAll();
        WisdomIndex wisdomIndex = wisdomIndices.get(0);
        ModelAndView modelAndView = new ModelAndView("backend/wisdom/index");
        modelAndView.addObject("title", "欢迎页");
        modelAndView.addObject("wisdomIndex", wisdomIndex);
        return modelAndView;
    }
}