package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.*;
import core.entity.PageBean;
import core.message.Message;

import java.util.Date;
import java.util.List;

public interface HisHospitalManageService {


    /**
     * @return
     * @Description list
     * @Params
     * @Author chenzhicai
     * @Date 2019-06-13
     * @Time 0:23
     **/
    PageBean<HisHospitalManage> list(PageBean<HisHospitalManage> pageBean) throws Exception;


    /**
     * @Description saveOrUpdate
     * @Author muxu
     * @Date 2019/6/27
     * @Time 15:09
     * @Return core.message.Message
     * @Params [hisMedicalRecord, request]
     **/

    Message saveOrUpdate(HisHospitalManage hisHospitalManage) throws Exception;

    /**
     * @Description 住院状态调整
     * @Author muxu
     * @Date 2019/7/1
     * @Time 23:06
     * @Return core.message.Message
     * @Params [hisMedicalRecord]
     **/
    Message hosEnter(Long id) throws Exception;

    /**
     * @Description
     * @Author muxu
     * @Date 2019/7/17
     * @Time 13:59
     * @Return core.message.Message
     * @Params [id]
     **/
    Message fail(Long id) throws Exception;


    /**
     * @Description 根据id查询
     * @Author muxu
     * @Date 2019/7/1
     * @Time 23:09
     * @Return com.ahsj.hiscore.entity.HisMedicalRecord
     * @Params [is_Re]
     **/

    HisHospitalManage selectById(Long id);


    /**
     * @Description 删除
     * @Author muxu
     * @Date 2019/7/8
     * @Time 10:55
     * @Return core.message.Message
     * @Params [ids]
     **/
    Message delete(Long[] ids) throws Exception;


    HisHospitalManage selectByHosptalRegistId(Long id) throws Exception;

    HisHospitalManage selectByPatientId(Long id) throws Exception;

    HisHospitalManage selectIsDischarged(Long patientId) throws Exception;

    List<HisHospitalManage> selectByHosOrder(Long patientId) throws Exception;

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisHospitalManage>
     * @Description 住院信息表的基本信息查询，因list名被占用,故起为其他名（只查询未出院的）
     * @Params [pageBean]
     * @Author zhushixiang
     * @Date 2019-07-15
     * @Time 11:51
     **/
    PageBean<HisHospitalManage> listForDetails(PageBean<HisHospitalManage> pageBean) throws Exception;

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisHospitalManage>
     * @Description 根据就诊编号查看住院信息
     * @Params [pageBean]
     * @Author zhushixiang
     * @Date 2019-07-16
     * @Time 9:54
     **/
    HisHospitalManage selectByNumber(String number) throws Exception;


    /**
     * @Description 住院天数定时任务
     * @Author muxu
     * @Date 2019/9/24
     * @Time 18:24
     * @Return void
     * @Params []
     **/
    void startAddDate();

    /**
     * @Description
     * @Author muxu
     * @Date 2019/9/24
     * @Time 18:24
     * @Return void
     * @Params []
     **/
    void startAddcareLevel();

    /**
     * @Description 时间段内住院人数统计
     * @Author muxu
     * @Date 2019/7/28
     * @Time 21:59
     * @Return com.ahsj.hiscore.entity.HisRegistered
     * @Params [hisRegistered]
     **/
    Integer[] selectHospitalManageDataCount(Date createDate) throws Exception;

    /**
     * @return com.ahsj.hiscore.entity.HisHospitalManage
     * @Description 根据住院编号查询
     * @Params [medicalRecordId]
     * @Author chenzhicai
     * @Date 2019-07-30
     * @Time 14:18
     **/
    HisHospitalManage selectByHospNumber(String medicalRecordId);

    /**
     * @param
     * @Description 未出院病床数
     * @Author: dingli
     * @return: int
     * @Date 2019/8/13
     * @Time 17:29
     **/
    int countUseBed() throws Exception;

    /**
     * @param
     * @Description 获取所有未出院的信息
     * @Author: dingli
     * @return: java.util.List<com.ahsj.hiscore.entity.HisHospitalManage>
     * @Date 2019/8/13
     * @Time 17:52
     **/
    List<HisHospitalManage> getHisHospitalManageAll(HisHospitalManage hisHospitalManage) throws Exception;


    /**
     * @param number
     * @Description 根据病床号获取信息
     * @Author: dingli
     * @return: com.ahsj.hiscore.entity.HisHospitalManage
     * @Date 2019/8/14
     * @Time 9:34
     **/
    HisHospitalManage getHisHospitalManageByNumber(String number) throws Exception;

    /**
     * @return com.ahsj.hiscore.entity.HisHospitalManage
     * @功能说明 查询新生儿的病床号
     * @Params [number]
     * @Author XJP
     * @Date 2019/9/11
     * @Time 13:17
     **/
    List<HisHospitalManage> getHisHospitalManageBed() throws Exception;

    /**
     * @return com.ahsj.hiscore.entity.HisHospitalManage
     * @Description 根据patientId查看病人当前是否正在住院
     * @Params [patientId]
     * @Author zhushixiang
     * @Date 2019-09-12
     * @Time 15:16
     **/
    HisHospitalManage selectByPatientIdAndInpatient(Long patientId) throws Exception;

    /**
     * @return void
     * @Description 修改
     * @Params [hisHospitalManage]
     * @Author zhushixiang
     * @Date 2019-09-12
     * @Time 15:31
     **/
    void update(HisHospitalManage hisHospitalManage) throws Exception;

    /**
     * @Description 根据住院病院编号获取病人信息
     * @Params: [number]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisHospitalManage
     * @Date 2019/9/14
     * @Time 15:39
     **/
    HisHospitalManage selectNumber(String number);

    /**
     * @return com.ahsj.hiscore.entity.HisHospitalManage
     * @Description 根据交易流水号核对是否为住院
     * @Params [tollNumber]
     * @Author zhushixiang
     * @Date 2019-09-26
     * @Time 22:07
     **/
    HisHospitalManage checkIsInpatient(String tollNumber) throws Exception;

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisHospitalManage>
     * @Description 查询所有在住院病人且具有长期医嘱且未停嘱的用药与项目医嘱相关信息
     * @Params []
     * @Author zhushixiang
     * @Date 2019-10-04
     * @Time 23:58
     **/
    List<HisHospitalManage> selectInpatientAndHaveLongTermMedicalAdvice() throws Exception;

    /**
     * @Description 设置病床
     * @Params: [id, hospitalNumber]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/11/16
     * @Time 16:21
     **/
    Message sedBed(HisHospitalManage hisHospitalManage) throws Exception;
}
