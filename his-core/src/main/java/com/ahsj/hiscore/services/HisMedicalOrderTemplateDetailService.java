package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisMedicalOrderTemplate;
import com.ahsj.hiscore.entity.HisMedicalOrderTemplateDetail;
import core.entity.PageBean;
import core.message.Message;

import java.util.List;

public interface HisMedicalOrderTemplateDetailService {
    /**
     *@Description 医嘱模板明细分页查询
     *@Params [pageBean]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisMedicalOrderTemplateDetail>
     *@Author zhushixiang
     *@Date 2019-07-28
     *@Time 18:24
    **/
    PageBean<HisMedicalOrderTemplateDetail> list(PageBean<HisMedicalOrderTemplateDetail> pageBean)throws Exception;

    /**
     *@Description
     *@Params [id]
     *@return com.ahsj.hiscore.entity.HisMedicalOrderTemplateDetail
     *@Author zhushixiang
     *@Date 2019-07-28
     *@Time 19:06
    **/
    HisMedicalOrderTemplateDetail selectById(Long id);

    /**
     *@Description 新增或更新
     *@Params [hisMedicalOrderTemplateDetail]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-07-28
     *@Time 19:13
    **/
    Message saveOrUpdate(HisMedicalOrderTemplateDetail hisMedicalOrderTemplateDetail)throws Exception;

    /**
     *@Description 删除
     *@Params [ids]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-07-28
     *@Time 20:11
    **/
    Message delete(Long[] ids)throws Exception;

    /**
     *@Description 根据模板编号搜索对应模板明细
     *@Params [templateNumber]
     *@return java.util.List<com.ahsj.hiscore.entity.HisMedicalOrderTemplateDetail>
     *@Author zhushixiang
     *@Date 2019-07-29
     *@Time 9:38
    **/
    List<HisMedicalOrderTemplateDetail> selectByTemplateNumber(String templateNumber);
}
