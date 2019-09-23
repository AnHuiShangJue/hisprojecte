package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.MedicinePurchasingPlan;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MedicinePurchasingPlanMapper extends BaseMapper<MedicinePurchasingPlan> {
    int deleteByPrimaryKey(Long id);

    int insert(MedicinePurchasingPlan record);

    int insertSelective(MedicinePurchasingPlan record);

    MedicinePurchasingPlan selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MedicinePurchasingPlan record);

    int updateByPrimaryKey(MedicinePurchasingPlan record);

    /**
     *@Description 查询采购计划明细
     *@Params [pageBean]
     *@return java.util.List<com.ahsj.hiscore.entity.MedicinePurchasingPlan>
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:43
    **/
    List<MedicinePurchasingPlan> details(PageBean<MedicinePurchasingPlan> pageBean);

    /**
     *@Description 根据计划编号查询
     *@Params [planNumber]
     *@return java.util.List<com.ahsj.hiscore.entity.MedicinePurchasingPlan>
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:43
    **/
    List<MedicinePurchasingPlan> selectByPlanNumber(String planNumber);

    /**
     *@Description 根据计划编号查询
     *@Params [planNumber]
     *@return java.util.List<com.ahsj.hiscore.entity.MedicinePurchasingPlan>
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:44
    **/
    List<MedicinePurchasingPlan> selectByPlanNumberforList(String planNumber);

    /**
     *@Description 根据计划编号删除
     *@Params [planNumber]
     *@return int
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:44
    **/
    int deleteByPlanNumber(String planNumber);

    /**
     *@Description 查询今日已新增数目
     *@Params [createdate]
     *@return int
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:44
    **/
    int selectNumbCount(String createdate);
}