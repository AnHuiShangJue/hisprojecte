package com.ahsj.hiscore.services;

import com.ahsj.hiscore.common.utils.ZipUtils;
import com.ahsj.hiscore.entity.*;
import core.entity.PageBean;
import core.message.Message;

import java.util.List;

public interface HisMedicalTemplateService {
    /**
     *@Description 修改病历模板表信息
     *@Params [toString]
     *@return com.ahsj.hiscore.entity.HisMedicalTemplate
     *@Author zhushixiang
     *@Date 2019-09-14
     *@Time 9:34
    **/
    HisMedicalTemplate selectById(Long id)throws Exception;

    /**
     *@Description 添加/编辑新的病历模板
     *@Params [hisMedicalTemplate]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-09-14
     *@Time 9:46
    **/
    Message saveOrUpdateTemplate(HisMedicalTemplate hisMedicalTemplate)throws Exception;

    /**
     *@Description 根据treeId搜索
     *@Params [treeId]
     *@return com.ahsj.hiscore.entity.HisMedicalTemplate
     *@Author zhushixiang
     *@Date 2019-09-14
     *@Time 10:16
    **/
    HisMedicalTemplate selectByTreeId(String treeId)throws Exception;

    /**
     *@Description
     *@Params []
     *@return java.util.List<com.ahsj.hiscore.entity.TreeEntity>
     *@Author zhushixiang
     *@Date 2019-09-14
     *@Time 10:16
    **/
    List<TreeEntity> selectTrreCode()throws Exception;

    /**
     *@Description 根据parentId查询
     *@Params [treeId]
     *@return com.ahsj.hiscore.entity.HisMedicalTemplate
     *@Author zhushixiang
     *@Date 2019-09-14
     *@Time 10:23
    **/
    List<HisMedicalTemplate> selectByParentId(String parentId)throws Exception;

    /**
     *@Description 保存/修改病历模板明细
     *@Params [hisMedicalTemplate]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-09-14
     *@Time 11:27
    **/
    Message saveOrUpdateDetails(HisMedicalTemplate hisMedicalTemplate)throws Exception;

    /**
     *@Description 门诊使用病历模板list界面
     *@Params [pageBean]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisMedicalTemplate>
     *@Author zhushixiang
     *@Date 2019-09-18
     *@Time 17:37
    **/
    PageBean<HisMedicalTemplate> listForMedical(PageBean<HisMedicalTemplate> pageBean)throws Exception;

    /**
     *@Description 删除模板
     *@Params [id]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-09-20
     *@Time 10:18
    **/
    Message delete(Long[] id)throws Exception;

    /**
     *@Description 保存当前信息为个人模板
     *@Params [hisMedical, detailsList, projects, recordId, loginUserId]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-09-20
     *@Time 12:58
    **/
    Message saveOrUpdatePersonalTemplate(HisMedical hisMedical, List<HisMedicationDetails> detailsList, List<HisRecordProject> projects, Long recordId, Long loginUserId,String loginUserName)throws Exception;
}
