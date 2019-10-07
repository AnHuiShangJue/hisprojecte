package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.*;
import core.message.Message;

import java.util.List;

/**
 * @program: his
 * @description:TreatmentPlan
 * @author: chenzhicai
 * @create: 2019-07-11-17-16
 **/
public interface TreatmentPlanService {

    /**
     *@Description 新增或者更新
     *@Params [hisMedical, detailsList, projects]
     *@return core.message.Message
     *@Author chenzhicai
     *@Date 2019-07-11
     *@Time 17:17
    **/

    Message saveOrUpdate(HisMedical hisMedical, List<HisMedicationDetails> detailsList, List<HisRecordProject> projects, Long recordId, List<HisInfusion> hisInfusionList) throws Exception;



}
