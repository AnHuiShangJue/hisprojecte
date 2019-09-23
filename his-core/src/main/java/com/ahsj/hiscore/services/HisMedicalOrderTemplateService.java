package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisMedicalOrderTemplate;
import core.entity.PageBean;
import core.message.Message;

import java.util.List;

public interface HisMedicalOrderTemplateService {
    /**
     *@Description 医嘱模板分页查询
     *@Params [pageBean]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisMedicalOrderTemplate>
     *@Author zhushixiang
     *@Date 2019-07-28
     *@Time 16:59
    **/
    PageBean<HisMedicalOrderTemplate> list(PageBean<HisMedicalOrderTemplate> pageBean)throws Exception;

    /**
     *@Description
     *@Params [id]
     *@return com.ahsj.hiscore.entity.HisMedicalOrderTemplate
     *@Author zhushixiang
     *@Date 2019-07-28
     *@Time 17:27
    **/
    HisMedicalOrderTemplate selectById(Long id)throws Exception;

    /**
     *@Description 新增或更新
     *@Params [hisMedicalOrderTemplate]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-07-28
     *@Time 17:28
    **/
    Message saveOrUpdate(HisMedicalOrderTemplate hisMedicalOrderTemplate)throws Exception;

    /**
     *@Description 删除
     *@Params [ids]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-07-28
     *@Time 18:12
    **/
    Message delete(Long[] ids)throws Exception;

    /**
     *@Description 获取医嘱模板名到select中
     *@Params []
     *@return java.util.List<com.ahsj.hiscore.entity.HisMedicalOrderTemplate>
     *@Author zhushixiang
     *@Date 2019-07-29
     *@Time 9:02
    **/
    List<HisMedicalOrderTemplate> selectTemplate()throws Exception;

    /**
     *@Description
     *@Params [templateNumber]
     *@return com.ahsj.hiscore.entity.HisMedicalOrderTemplate
     *@Author zhushixiang
     *@Date 2019-07-29
     *@Time 9:55
    **/
    HisMedicalOrderTemplate selectByTemplateNumber(String templateNumber)throws Exception;
}
