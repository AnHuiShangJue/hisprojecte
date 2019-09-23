package com.ahsj.hiscore.controller.hisConsultationNote;

import com.ahsj.hiscore.entity.HisConsultationNote;
import com.ahsj.hiscore.services.HisConsultationNoteService;
import core.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description
 * @Author dingli
 * @Date 2019/9/17
 * @Time 15:56
 **/
@Controller
@RequestMapping("/api/hisConsultationNote/")
public class HisConsultationNoteController {

    @Autowired
    HisConsultationNoteService hisConsultationNoteService;

    /**
     * className HisConsultationNoteController
     *
     * @author dingli
     * @date 2019/9/17 15:56
     */

    /**
     * @Description 会诊记录新增或修改
     * @Params: [hisConsultationNote]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/9/17
     * @Time 16:08
     **/
    @RequestMapping(value = "saveOrUpdate/index.ahsj")
    @ResponseBody
    public Message saveOrUpdate(HisConsultationNote hisConsultationNote) throws Exception {
        return hisConsultationNoteService.saveOrUpdate(hisConsultationNote);
    }

    /**
     * @Description 获取会诊记录
     * @Params: [hisConsultationNote]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisConsultationNote
     * @Date 2019/9/17
     * @Time 16:50
     **/
    @RequestMapping(value = "getHisConsultationNote/index.ahsj")
    @ResponseBody
    public HisConsultationNote getHisConsultationNote(HisConsultationNote hisConsultationNote) throws Exception {
        return hisConsultationNoteService.getHisConsultationNote(hisConsultationNote);
    }
}
