package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisMedicalTemplate;
import com.ahsj.hiscore.entity.TreeEntity;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HisMedicalTemplateMapper extends BaseMapper<HisMedicalTemplate>{
    int deleteByPrimaryKey(Long id);

    int insert(HisMedicalTemplate record);

    int insertSelective(HisMedicalTemplate record);

    HisMedicalTemplate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisMedicalTemplate record);

    int updateByPrimaryKey(HisMedicalTemplate record);

    /**
     *@Description 根据TreeId搜索
     *@Params [id]
     *@return com.ahsj.hiscore.entity.HisMedicalTemplate
     *@Author zhushixiang
     *@Date 2019-09-14
     *@Time 9:35
    **/
    HisMedicalTemplate selectByTreeId(String id);

    /**
     *@Description 根据模板名称查找
     *@Params [hisMedicalTemplate]
     *@return com.ahsj.hiscore.entity.HisMedicalTemplate
     *@Author zhushixiang
     *@Date 2019-09-14
     *@Time 9:51
    **/
    HisMedicalTemplate selectByName(HisMedicalTemplate hisMedicalTemplate);

    /**
     *@Description 查询出此ParentId下最大的treeId
     *@Params [parentId]
     *@return com.ahsj.hiscore.entity.HisMedicalTemplate
     *@Author zhushixiang
     *@Date 2019-09-14
     *@Time 9:52
    **/
    HisMedicalTemplate selectMaxTreeId(String parentId);

    /**
     *@Description
     *@Params []
     *@return java.util.List<com.ahsj.hiscore.entity.TreeEntity>
     *@Author zhushixiang
     *@Date 2019-09-14
     *@Time 10:18
    **/
    List<TreeEntity> selectTreeCode();

    /**
     *@Description 根据parentId查询
     *@Params [parentId]
     *@return com.ahsj.hiscore.entity.HisMedicalTemplate
     *@Author zhushixiang
     *@Date 2019-09-14
     *@Time 10:25
    **/
    List<HisMedicalTemplate> selectByParentId(String parentId);

    /**
     *@Description 根据登录人的ID 获取他可以使用的药方
     *@Params [pageBean]
     *@return java.util.List<? extends com.ahsj.hiscore.entity.HisMedicalTemplate>
     *@Author zhushixiang
     *@Date 2019-09-18
     *@Time 17:10
    **/
    List<?extends HisMedicalTemplate> listForMedical(PageBean<HisMedicalTemplate> pageBean);

    /**
     *@Description 删除当前节点
     *@Params [id]
     *@return void
     *@Author zhushixiang
     *@Date 2019-09-20
     *@Time 10:27
    **/
    void deleteByTreeId(String id);

    /**
     *@Description 
     *@Params [id]
     *@return void
     *@Author zhushixiang
     *@Date 2019-09-20
     *@Time 10:50
    **/
    void deleteByParentId(String id);
}