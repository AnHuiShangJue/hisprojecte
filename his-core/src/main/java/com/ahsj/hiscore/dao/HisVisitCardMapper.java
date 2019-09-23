package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisVisitCard;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HisVisitCardMapper extends BaseMapper<HisVisitCard>{
    int deleteByPrimaryKey(Long id);

    int insert(HisVisitCard record);

    int insertSelective(HisVisitCard record);

    HisVisitCard selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisVisitCard record);

    int updateByPrimaryKey(HisVisitCard record);

    HisVisitCard selectByNumber(String number);

    /**
     *@Description 根据就诊卡编号查询就诊卡所有信息
     *@Params [number]
     *@return com.ahsj.hiscore.entity.HisVisitCard
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 15:43
    **/
    HisVisitCard selectByNumbers(String number);
}