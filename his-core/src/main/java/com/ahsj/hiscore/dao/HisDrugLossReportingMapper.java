package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisDrugLossReporting;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HisDrugLossReportingMapper  extends BaseMapper{
    int deleteByPrimaryKey(Long id);

    int insert(HisDrugLossReporting record);

    int insertSelective(HisDrugLossReporting record);

    HisDrugLossReporting selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisDrugLossReporting record);

    int updateByPrimaryKey(HisDrugLossReporting record);

    /**
     *@Description list查询
     *@Params [pageBean]
     *@return java.util.List<? extends com.ahsj.hiscore.entity.HisDrugLossReporting>
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:29
    **/
    List< ? extends HisDrugLossReporting> list(PageBean<HisDrugLossReporting> pageBean);

    /**
     *@Description 批量更新
     *@Params [hisDrugLossReportingList]
     *@return void
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:29
    **/
    void updateBatch(List<HisDrugLossReporting> hisDrugLossReportingList);

    /**
     *@Description 批量插入
     *@Params [hisDrugLossReportingList]
     *@return void
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:30
    **/
    void insertBatch(List<HisDrugLossReporting> hisDrugLossReportingList);
}