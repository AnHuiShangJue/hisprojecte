package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisMedicalOrderTemplateDetail;
import core.entity.PageBean;

import java.util.LinkedList;
import java.util.List;

public interface HisMedicalOrderTemplateDetailMapper extends BaseMapper<HisMedicalOrderTemplateDetail>{
    int deleteByPrimaryKey(Long id);

    int insert(HisMedicalOrderTemplateDetail record);

    int insertSelective(HisMedicalOrderTemplateDetail record);

    HisMedicalOrderTemplateDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisMedicalOrderTemplateDetail record);

    int updateByPrimaryKey(HisMedicalOrderTemplateDetail record);

    /**
     *@Description 分页查询
     *@Params [pageBean]
     *@return java.util.List<? extends com.ahsj.hiscore.entity.HisMedicalOrderTemplateDetail>
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:32
    **/
    List<?extends  HisMedicalOrderTemplateDetail> list(PageBean<HisMedicalOrderTemplateDetail> pageBean);

    /**
     *@Description 根据模板编号  且根据序号排序
     *@Params [templateNumber]
     *@return java.util.List<com.ahsj.hiscore.entity.HisMedicalOrderTemplateDetail>
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:32
    **/
    List<HisMedicalOrderTemplateDetail> selectByTemplateNumberOrderByOrderNum(String templateNumber);

    /**
     *@Description 根据医嘱模板编号删除所有
     *@Params [number]
     *@return void
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:33
    **/
    void deleteByTemplateNumber(String number);

    /**
     *@Description 批量插入
     *@Params [hisMedicalOrderDetailLinkedList]
     *@return void
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:37
    **/
    void insertBatch(LinkedList<HisMedicalOrderTemplateDetail> hisMedicalOrderDetailLinkedList);
}