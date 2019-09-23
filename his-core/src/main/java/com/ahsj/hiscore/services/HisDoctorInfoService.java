package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisDoctorInfo;
import com.ahsj.hiscore.entity.HisPatientInfo;
import core.entity.PageBean;
import core.message.Message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface HisDoctorInfoService {
    /**
     * @Description 新增或更新
     * @Author   muxu
     * @Date 2019/6/20
     * @Time 19:02
     * @Return core.message.Message
     * @Params [hisDoctorInfo, request]
    **/
    Message saveOrUpdate(HisDoctorInfo hisDoctorInfo) throws Exception;

    /**
     * @Description 删除
     * @Author   muxu
     * @Date 2019/6/20
     * @Time 19:02
     * @Return core.message.Message
     * @Params [ids]
    **/
    Message delete(Long[] ids) throws Exception;




    /**
     * @Description lsit
     * @Author   muxu
     * @Date 2019/6/20
     * @Time 19:06
     * @Return core.entity.PageBean<com.ahsj.hiscore.entity.HisDoctorInfo>
     * @Params [pageBean]
    **/
    PageBean<HisDoctorInfo> list(PageBean<HisDoctorInfo> pageBean)throws Exception;

    /**
     * @Description 初始化
     * @Author   muxu
     * @Date 2019/6/20
     * @Time 18:05
     * @Return
     * @Params
    **/
    HisDoctorInfo updateInit(Long id)throws Exception;

    HisDoctorInfo selectById(Long id);
    /**
     *
     * @Author   dingli
     * @Date 2019/7/9
     * @Time 17:08
     * @Return   List<HisDoctorInfo>
     * @Params departmentId
     **/
    List<HisDoctorInfo> getHisDoctorInfoBydepartmentId(Long departmentId);

    /**
     * @Description
     * @Author  muxu
     * @Date  2019/8/27
     * @Time 10:06
     * @Return
     * @Params
    **/
    Message saveHisDoctorInfo(List<HisDoctorInfo> hisDoctorInfoList) throws Exception;

    /**
     * @Description excel导出
     * @Author  muxu
     * @Date  2019/8/28
     * @Time 15:16
     * @Return
     * @Params
    **/

    void exportExcels(Long[] ids, HttpServletRequest request, HttpServletResponse response,String param, HttpSession session,HisDoctorInfo hisd) throws Exception;

    /**
     * @Description excel导入
     * @Author  muxu
     * @Date  2019/9/2
     * @Time 18:03
     * @Return java.util.Map
     * @Params [excelData]
    **/
    Map importExcel(List<HisDoctorInfo> excelData) throws Exception;

    /**
     * @Description
     * @Author  muxu
     * @Date  2019/9/3
     * @Time 13:53
     * @Return
     * @Params
    **/
    void importDoctorInfo(List<HisDoctorInfo> doctorInsertList) throws Exception;

    /**
     *@Description 获取医生信息
     *@Params
     *@return
     *@Author jin
     *@Date 2019/9/14
     *@Time 16:31
    */
    List<HisDoctorInfo> getAllDoctor() throws  Exception;


}
