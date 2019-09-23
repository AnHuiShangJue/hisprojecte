package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisRecordProject;
import com.ahsj.hiscore.entity.HisRefundProject;
import core.entity.PageBean;
import core.message.Message;

import java.util.List;

/**
 * @program: his
 * @description:HisRecordProjectService
 * @author: chenzhicai
 * @create: 2019-07-19-10-12
 **/
public interface HisRecordProjectService {


    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRecordProject>
     * @Description 根据医疗记录查询
     * @Params [id]
     * @Author chenzhicai
     * @Date 2019-07-19
     * @Time 10:16
     **/
    List<HisRecordProject> selectByMedicalRecordId(Long id);

    /**
     * @param record
     * @return int
     * @Description 修改付费状态
     * @Author: dingli
     * @Date 2019/7/19
     * @Time 11:03
     **/
    int updateHisRecordProject(HisRecordProject record) throws Exception;

    /**
     * @return void
     * @Description 删除
     * @Params [deletedIds]
     * @Author zhushixiang
     * @Date 2019-08-13
     * @Time 14:01
     **/

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisRecordProject>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 17:38
     **/
    PageBean<HisRecordProject> queryAddList(PageBean<HisRecordProject> pageBean);

    /**
     * @return com.ahsj.hiscore.entity.HisRecordProject
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/8/17
     * @Time 10:24
     **/
    HisRecordProject selectByPrimaryKey(Long id);

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisRecordProject>
     * @功能说明 查询申请退项目的列表记录明细
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/8/17
     * @Time 10:43
     **/
    PageBean<HisRecordProject> projectInfo(PageBean<HisRecordProject> pageBean) throws Exception;

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisRecordProject>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/8/24
     * @Time 17:44
     **/
    List<HisRecordProject> projectInfoList(HisRecordProject hisRecordProject) throws Exception;

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRecordProject>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/8/21
     * @Time 18:50
     **/
    List<HisRecordProject> pricelistsBytollRecordNumber(HisRecordProject hisRecordProject);

    Message delete(Long[] deletedIds) throws Exception;

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRecordProject>
     * @Description 根据就诊记录ID查询出未检查且未付费的药品
     * @Params [id]
     * @Author zhushixiang
     * @Date 2019-08-16
     * @Time 10:00
     **/
    List<HisRecordProject> selectByMedicalRecordIdNotIsCheckedOrIspayed(Long id);

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisRefundProject>
     * @功能说明 查询退款明细
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/8/17
     * @Time 20:04
     **/
    PageBean<HisRecordProject> pageBeanList(PageBean<HisRecordProject> pageBean);

    /**
     * @Description 项目病人清单
     * @Author muxu
     * @Date 2019/8/16
     * @Time 17:10
     * @Return
     * @Params
     **/

    PageBean<HisRecordProject> ItemPatientList(PageBean<HisRecordProject> pageBean) throws Exception;

    /**
     * @Description 病人项目审核
     * @Author muxu
     * @Date 2019/8/17
     * @Time 10:26
     * @Return
     * @Params
     **/

    PageBean<HisRecordProject> selectByMedicalRecordIdList(PageBean<HisRecordProject> pageBean) throws Exception;

    /**
     * @Description 修改审核状态（是否已检查）
     * @Author muxu
     * @Date 2019/8/17
     * @Time 18:00
     * @Return
     * @Params
     **/

    Message UpdateReviewStatus(Long id) throws Exception;

    /**
     * @Description 获取项目打印信息
     * @Author muxu
     * @Date 2019/8/19
     * @Time 10:40
     * @Return
     * @Params
     **/

    HisRecordProject getProject(Long id) throws Exception;

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRecordProject>
     * @功能说明
     * @Params [hisRecordProject]
     * @Author XJP
     * @Date 2019/8/19
     * @Time 15:08
     **/
    List<HisRecordProject> HisRecordProjectLists(HisRecordProject hisRecordProject) throws Exception;

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRecordProject>
     * @功能说明
     * @Params [project]
     * @Author XJP
     * @Date 2019/8/20
     * @Time 9:47
     **/
    List<HisRecordProject> queryLists(HisRecordProject project);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRefundProject>
     * @功能说明
     * @Params [hisRefundProject]
     * @Author XJP
     * @Date 2019/8/24
     * @Time 17:42
     **/
    List<HisRefundProject> queryHisRecordProject(HisRefundProject hisRefundProject);

    /**
     * @return void
     * @功能说明
     * @Params [hisRecordProject]
     * @Author XJP
     * @Date 2019/8/24
     * @Time 20:33
     **/
    Message updateByHisRefundProjectListBack(List<HisRecordProject> hisRecordProjectList);

    /**
     * @Description
     * @Params: [number]
     * @Author: dingli
     * @Return: java.util.List<com.ahsj.hiscore.entity.HisRecordProject>
     * @Date 2019/9/7
     * @Time 12:00
     **/
    List<HisRecordProject> selectPrint(String number) throws Exception;

    /**
     *@Description 查询出当前就诊编号未付费的项目列表
     *@Params [id]
     *@return java.util.List<com.ahsj.hiscore.entity.HisRecordProject>
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 17:28
    **/
    List<HisRecordProject> selectByMedicalRecordIdNotIspayed(Long medicalRecordId)throws Exception;

    /**
     *@Description 批量更新
     *@Params [hisRecordProjectList]
     *@return void
     *@Author zhushixiang
     *@Date 2019-09-11
     *@Time 10:08
    **/
    void updateBatchForIsPay(List<HisRecordProject> hisRecordProjectList);

    /**
     *@Description 新增
     *@Params [hisRecordProject]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-09-18
     *@Time 9:40
    **/
    Message insert(HisRecordProject hisRecordProject)throws Exception;
}
