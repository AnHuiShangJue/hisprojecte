package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisMedicinePurchasingPlanRecord;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HisMedicinePurchasingPlanRecordMapper extends BaseMapper<HisMedicinePurchasingPlanRecord> {
    int deleteByPrimaryKey(Long id);

    int insert(HisMedicinePurchasingPlanRecord record);

    int insertSelective(HisMedicinePurchasingPlanRecord record);

    HisMedicinePurchasingPlanRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisMedicinePurchasingPlanRecord record);

    int updateByPrimaryKey(HisMedicinePurchasingPlanRecord record);

    /**
     *@Description 分页查询
     *@Params [pageBean]
     *@return java.util.List<com.ahsj.hiscore.entity.HisMedicinePurchasingPlanRecord>
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:35
    **/
    List<HisMedicinePurchasingPlanRecord> list(PageBean<HisMedicinePurchasingPlanRecord> pageBean);

    /**
     *@Description 根据计划编号查找
     *@Params [planNumber]
     *@return com.ahsj.hiscore.entity.HisMedicinePurchasingPlanRecord
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:37
    **/
    HisMedicinePurchasingPlanRecord selectByPlanNumber(String planNumber);

}