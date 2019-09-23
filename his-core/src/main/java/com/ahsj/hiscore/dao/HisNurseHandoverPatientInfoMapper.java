package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisNurseHandoverPatientInfo;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface HisNurseHandoverPatientInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HisNurseHandoverPatientInfo record);

    int insertSelective(HisNurseHandoverPatientInfo record);

    HisNurseHandoverPatientInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisNurseHandoverPatientInfo record);

    int updateByPrimaryKey(HisNurseHandoverPatientInfo record);

    /**
     *@Description 批量插入
     *@Params
     *@return
     *@Author jin
     *@Date 2019/9/15
     *@Time 11:29
    */
    void insertBatch(@NotNull List<HisNurseHandoverPatientInfo> hisNurseHandoverPatientInfoList);




}