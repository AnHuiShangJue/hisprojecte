package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisStageSummary;
import com.ahsj.hiscore.entity.MedicalCrisisNotice;
import core.message.Message;

/**
 * @program: hisprojecte
 * @description:
 * @author: chenzhicai
 * @create: 2019-09-17-10-04
 **/
public interface MedicalCrisisNoticeService {
    /**
     * @return core.message.Message
     * @Description 保存
     * @Params [hisHandover]
     * @Author chenzhicai
     * @Date 2019-09-15
     * @Time 13:21
     **/
    Message saveOrUpdate(MedicalCrisisNotice medicalCrisisNotice) throws Exception;



    /**
     * @return com.ahsj.hiscore.entity.HisRegistered
     * @Description 根据id查询
     * @Params [id]
     * @Author chenzhicai
     * @Date 2019/6/21
     * @Time 11:49 PM
     **/
    MedicalCrisisNotice selectById(Long id);


    /**
     *@Description 根据住院编号查找
     *@Params [number]
     *@return com.ahsj.hiscore.entity.HisSuccession
     *@Author chenzhicai
     *@Date 2019-09-16
     *@Time 12:35
     **/
    MedicalCrisisNotice selectByHosNumber(String number);
}
