package com.ahsj.hiscore.services;


import com.ahsj.hiscore.entity.HisPatientInfo;
import core.entity.PageBean;
import core.message.Message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


public interface HisPatientService {
    /**
     * @return core.message.Message
     * @Description 新增或更新
     * @Params [hisNurse]
     * @Author zhushixiang
     * @Date 2019-06-18
     * @Time 21:43
     **/
    Message saveOrUpdate(HisPatientInfo patientInfo) throws Exception;

    /**
     * @return core.message.Message
     * @Description 删除
     * @Params [ids]
     * @Author zhushixiang
     * @Date 2019-06-18
     * @Time 21:43
     **/
    Message delete(Long[] ids) throws Exception;

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisNurse>
     * @Description list查询
     * @Params [pageBean]
     * @Author zhushixiang
     * @Date 2019-06-18
     * @Time 21:43
     **/
    PageBean<HisPatientInfo> list(PageBean<HisPatientInfo> pageBean) throws Exception;

    /**
     * @return com.ahsj.hiscore.entity.HisNurse
     * @Description 初始化
     * @Params [id]
     * @Author zhushixiang
     * @Date 2019-06-18
     * @Time 21:43
     **/
    HisPatientInfo updateInit(Long id) throws Exception;

    HisPatientInfo selectById(Long id);


    /**
     * @param medicalRecordId
     * @return
     * @Description 根据就诊编号查看病人信息
     * @Author: dingli
     * @Date 2019/7/20
     * @Time 12:24
     **/
    HisPatientInfo selectByMedicalRecordId(String medicalRecordId) throws Exception;

    /**
     *@Description 功能同上 recordId
     *@Params
     *@return
     *@Author jin
     *@Date 2019/9/17
     *@Time 18:22
    */
    HisPatientInfo selectByRecordId(String recordId) throws Exception;

    /**
     * @return
     * @Description selectByAnkleDetailNumber
     * @Params
     * @Author jin
     * @Date 2019/8/1
     * @Time 16:26
     */
    HisPatientInfo selectByAnkleDetailNumber(String number) throws Exception;

    /**
     * @param hisPatientInfo
     * @param request
     * @param response
     * @param session
     * @Description 病人信息导出
     * @Author: dingli
     * @return: void
     * @Date 2019/8/29
     * @Time 16:00
     **/
    void exportExcels(Long[] ids, String param, HisPatientInfo hisPatientInfo, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception;

    /**
     * @param list
     * @Description 病人信息导入
     * @Author: dingli
     * @return: java.util.Map
     * @Date 2019/8/30
     * @Time 16:40
     **/
    Map importExcel(List<HisPatientInfo> list,String filename) throws Exception;

    /**
     *@Description
     *@Params
     *@return
     *@Author jin
     *@Date 2019/9/2
     *@Time 15:27
    */
    HisPatientInfo selectByPrimaryKey(Long id)throws Exception;

}
