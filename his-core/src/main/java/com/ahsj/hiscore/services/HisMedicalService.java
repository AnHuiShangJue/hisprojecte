package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisMedical;
import core.message.Message;

/**
 * @program: his
 * @description:HisMedicalService
 * @author: chenzhicai
 * @create: 2019-07-11-17-01
 **/
public interface HisMedicalService {

    /**
     * @return void
     * @Description 新增或更新
     * @Params [hisMedical]
     * @Author chenzhicai
     * @Date 2019-07-12
     * @Time 09:08
     **/
    Message saveOrUpdate(HisMedical hisMedical) throws Exception;

    /**
     * @return com.ahsj.hiscore.entity.HisMedical
     * @Description 根绝就诊编号查询门诊流程
     * @Params [id]
     * @Author chenzhicai
     * @Date 2019-07-12
     * @Time 11:03
     **/
    HisMedical selectByRecordId(Long id) throws Exception;

    /**
     * @Description
     * @Params: [number]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisMedical
     * @Date 2019/9/7
     * @Time 12:41
     **/
    HisMedical selectPrint(String number)throws Exception;

    /**
     *@Description 根据就诊记录ID查询当次就诊判断
     *@Params [id]
     *@return com.ahsj.hiscore.entity.HisMedical
     *@Author zhushixiang
     *@Date 2019-09-09
     *@Time 17:36
    **/
    HisMedical selectByMedicalRecordId(Long id)throws Exception;
}
