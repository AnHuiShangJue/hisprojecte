package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.*;
import core.entity.PageBean;
import core.message.Message;

import javax.servlet.http.HttpServletRequest;

public interface HisMedicalRecordService {


    /**
     * @return
     * @Description list
     * @Params
     * @Author chenzhicai
     * @Date 2019-06-13
     * @Time 0:23
     **/
    PageBean<HisMedicalRecord> list(PageBean<HisMedicalRecord> pageBean) throws Exception;


    /**
     * @Description saveOrUpdate
     * @Author muxu
     * @Date 2019/6/27
     * @Time 15:09
     * @Return core.message.Message
     * @Params [hisMedicalRecord, request]
     **/

    Message saveOrUpdate(HisMedicalRecord hisMedicalRecord, HttpServletRequest request) throws Exception;

    /**
     * @Description 病史输入
     * @Author muxu
     * @Date 2019/7/1
     * @Time 23:06
     * @Return core.message.Message
     * @Params [hisMedicalRecord]
     **/
    Message recEnter(Long id, String userId, Integer type, Long patientId) throws Exception;


    /**
     * @Description 根据id查询
     * @Author muxu
     * @Date 2019/7/1
     * @Time 23:09
     * @Return com.ahsj.hiscore.entity.HisMedicalRecord
     * @Params [is_Re]
     **/

    HisMedicalRecord selectById(Long id);


    /**
     * @Description 就诊记录list
     * @Author muxu
     * @Date 2019/7/8
     * @Time 10:59
     * @Return core.entity.PageBean<com.ahsj.hiscore.entity.HisMedicalRecord>
     * @Params [pageBean]
     **/
    PageBean<HisMedicalRecord> medicalrecordlist(PageBean<HisMedicalRecord> pageBean) throws Exception;

    /**
     * @Description 删除
     * @Author muxu
     * @Date 2019/7/8
     * @Time 10:55
     * @Return core.message.Message
     * @Params [ids]
     **/
    Message delete(Long[] ids) throws Exception;

    /**
     * @Description
     * @Author muxu
     * @Date 2019/7/9
     * @Time 9:53
     * @Return
     * @Params
     **/

    /**
     * @Description medicalRecordId
     * @Author dingli
     * @Date 2019/7/11
     * @Time 11:03
     * @Return com.ahsj.hiscore.entity.HisMedicalRecord
     * @Params [medicalRecordId]
     **/
    HisMedicalRecord selectByMedicalRecordId(String medicalRecordId);


    HisMedicalRecord selectByRegisterId(Long id);

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisMedicalRecord>
     * @Description 查看过往病历（由就诊记录表medical_record,住院信息表hospital_manage,护嘱表ankle以及医嘱表medical_order中的信息组合而成）根据PatientId来查找此病人病历
     * @Params [pageBean]
     * @Author zhushixiang
     * @Date 2019-07-15
     * @Time 16:59
     **/
    PageBean<HisMedicalRecord> listForMedicalHistoryByPatientId(PageBean<HisMedicalRecord> pageBean) throws Exception;

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisMedicalRecord>
     * @Description 查看病人所有历史病历不包括正在住院的那一条
     * @Params [pageBean]
     * @Author zhushixiang
     * @Date 2019-08-17
     * @Time 15:06
     **/
    PageBean<HisMedicalRecord> listForAllMedicalHistoryByPatientId(PageBean<HisMedicalRecord> pageBean);

    /**
     * @Description
     * @Params: [number]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisMedicalRecord
     * @Date 2019/9/7
     * @Time 14:01
     **/
    HisMedicalRecord selectPrint(String number)throws Exception;

    /**
     *@Description 根据病人ID查询出最近一条的病人就诊编号
     *@Params [patientId]
     *@return com.ahsj.hiscore.entity.HisMedicalRecord
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 17:16
    **/
    HisMedicalRecord selectTheLastRecordByPatientId(Long patientId)throws Exception;
}
