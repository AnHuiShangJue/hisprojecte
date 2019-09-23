package com.ahsj.hiscore.controller.hisDailyCourse;

import com.ahsj.hiscore.entity.HisDailyCourse;
import com.ahsj.hiscore.services.HisDailyCourseService;
import core.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description
 * @Author dingli
 * @Date 2019/9/17
 * @Time 13:45
 **/
@Controller
@RequestMapping("/api/hisDailyCourse/")
public class HisDailyCourseController {
    /**
     * className HisDailyCourseController
     *
     * @author dingli
     * @date 2019/9/17 13:45
     */
    @Autowired
    HisDailyCourseService hisDailyCourseService;

    @RequestMapping(value = "saveOrUpdate/index.ahsj")
    @ResponseBody
    public Message saveOrUpdate(HisDailyCourse hisDailyCourse) throws Exception {
        return hisDailyCourseService.saveOrUpdate(hisDailyCourse);
    }

    @RequestMapping(value = "getDailyCourse/index.ahsj")
    @ResponseBody
    public HisDailyCourse  getDailyCourse(Long id) throws Exception {
        return hisDailyCourseService.getDailyCourse(id);
    }

}
