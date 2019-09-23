package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisMedicalOrderTemplate;
import core.entity.PageBean;

import java.util.List;

public interface HisMedicalOrderTemplateMapper extends BaseMapper<HisMedicalOrderTemplate> {
    int deleteByPrimaryKey(Long id);

    int insert(HisMedicalOrderTemplate record);

    int insertSelective(HisMedicalOrderTemplate record);

    HisMedicalOrderTemplate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisMedicalOrderTemplate record);

    int updateByPrimaryKey(HisMedicalOrderTemplate record);

    /**
     *@Description 分页查询
     *@Params [pageBean]
     *@return java.util.List<? extends com.ahsj.hiscore.entity.HisMedicalOrderTemplate>
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:33
    **/
    List<? extends HisMedicalOrderTemplate> list(PageBean<HisMedicalOrderTemplate> pageBean);

    /**
     *@Description 查询出今日新增数目
     *@Params [createdate]
     *@return int
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:33
    **/
    int selectNumbCount(String createdate);

    /**
     *@Description 根据模板名称查找
     *@Params [templateName]
     *@return com.ahsj.hiscore.entity.HisMedicalOrderTemplate
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:33
    **/
    HisMedicalOrderTemplate selectByTemplateName(String templateName);

    /**
     *@Description
     *@Params []
     *@return java.util.List<com.ahsj.hiscore.entity.HisMedicalOrderTemplate>
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:33
    **/
    List<HisMedicalOrderTemplate> selectTemplate();

    /**
     *@Description 根据模板编号查找
     *@Params [templateNumber]
     *@return com.ahsj.hiscore.entity.HisMedicalOrderTemplate
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:37
    **/
    HisMedicalOrderTemplate selectByTemplateNumber(String templateNumber);
}