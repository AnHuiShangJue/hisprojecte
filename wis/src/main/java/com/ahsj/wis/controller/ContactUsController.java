package com.ahsj.wis.controller;


import com.ahsj.wis.entity.ContactUs;
import com.ahsj.wis.service.ContactUsUpdateService;
import core.message.Message;
import core.message.MessageUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

import java.util.Date;

@Api(value = "/api/contactus", tags = "智慧湾联系我们")
@RestController
@RequestMapping("/api/contactus")
public class ContactUsController {

    @Autowired
    ContactUsUpdateService contactUsUpdateService;

    @RequestMapping("/contactUsUpdate/index.ahsj")
    ModelAndView aboutWisUpdate() throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/wisdom/contact_us_update");
        modelAndView.addObject("title", "更新页面");
        ContactUs contactUs = contactUsUpdateService.select();
        modelAndView.addObject("contactUs", contactUs);
        return modelAndView;
    }

    /**
     *@Description 新增
     *@Params
     *@return
     *@Author jin
     *@Date 2019/11/16
     *@Time 17:19
    */
    @RequestMapping("/insert.ahsj")
    public Message insert(ContactUs record) throws Exception {
        record.setCreateDate(new Date());
        int a = contactUsUpdateService.insert(record);
        if (EmptyUtil.Companion.isNullOrEmpty(a)){
            return MessageUtil.createMessage(false,"修改失败");
        }else {
            return MessageUtil.createMessage(true,"修改成功");
        }
    }

}
