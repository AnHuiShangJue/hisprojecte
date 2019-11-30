package com.ahsj.wis.controller;

import com.ahsj.wis.entity.AboutWisdom;
import com.ahsj.wis.service.AboutWisdomService;
import core.message.Message;
import core.message.MessageUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

import java.util.Date;

@Api(value = "/api/aboutwisdom", tags = "智慧湾修改")
@RestController
@RequestMapping("/api/aboutwisdom")
public class AboutWisUpdateController {

    @Autowired
    AboutWisdomService aboutWisdomService;

    @RequestMapping("/aboutWisUpdate/index.ahsj")
    ModelAndView aboutWisUpdate() throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/wisdom/about_wisdom_update");
        modelAndView.addObject("title", "更新页面");
        AboutWisdom aboutWisdom = aboutWisdomService.select();
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
    public Message insert(AboutWisdom record) throws Exception {
        record.setCreateDate(new Date());
        int a = aboutWisdomService.insert(record);
        if (EmptyUtil.Companion.isNullOrEmpty(a)){
            return MessageUtil.createMessage(false,"修改失败");
        }else {
            return MessageUtil.createMessage(true,"修改成功");
        }
    }

}
