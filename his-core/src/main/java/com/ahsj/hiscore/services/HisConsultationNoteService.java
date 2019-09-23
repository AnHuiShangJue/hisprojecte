package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisConsultationNote;
import core.message.Message;

/**
 * @Description
 * @Author dingli
 * @Date 2019/9/17
 * @Time 15:55
 **/
public interface HisConsultationNoteService {

    /**
     * @Description 会诊记录新增或修改
     * @Params: [hisConsultationNote]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/9/17
     * @Time 15:58
     **/
    Message saveOrUpdate(HisConsultationNote hisConsultationNote) throws Exception;

    /**
     * @Description 查询会诊详细信息
     * @Params: [hisConsultationNote]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisConsultationNote
     * @Date 2019/9/17
     * @Time 16:19
     **/
    HisConsultationNote getHisConsultationNote(HisConsultationNote hisConsultationNote) throws Exception;
}
