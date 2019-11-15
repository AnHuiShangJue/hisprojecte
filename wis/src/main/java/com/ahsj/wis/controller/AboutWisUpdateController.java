package com.ahsj.wis.controller;

import com.ahsj.wis.entity.AboutWisdom;
import com.ahsj.wis.service.AboutWisdomService;
import core.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

@RestController
@RequestMapping("/api/aboutwisdom")
public class AboutWisUpdateController {

    @Autowired
    AboutWisdomService aboutWisdomService;

    @RequestMapping("/aboutWisUpdate/index.ahsj")
    ModelAndView aboutWisUpdate() {
        ModelAndView modelAndView = new ModelAndView("backend/wisdom/about_wisdom_update");
        modelAndView.addObject("title", "更新页面");
        AboutWisdom aboutWisdom = new AboutWisdom();
        aboutWisdom.setIntroduce("苏州智慧湾科技发展有限公司是基于智慧城市、大数据及研发、产品管理（IPD）为核心的系统咨询公司，研究人与城市的生态关系。通过智慧城市建设、运行、分析，使用大数据和人工智能技术，建立一套评价与优化的城市智慧化建设运行体系。");
        modelAndView.addObject("aboutWisdom", aboutWisdom);
        return modelAndView;
    }


    /**
     *@Description update
     *@Params
     *@return
     *@Author jin
     *@Date 2019/11/15
     *@Time 17:31
    */
    @RequestMapping("/insert.ahsj")
    public int insert(AboutWisdom record) throws Exception {
        return aboutWisdomService.insert(record);
    }

}
