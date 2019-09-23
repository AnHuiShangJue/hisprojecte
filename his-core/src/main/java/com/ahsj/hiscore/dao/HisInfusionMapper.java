package com.ahsj.hiscore.dao;

import org.apache.ibatis.annotations.Mapper;
import com.ahsj.hiscore.entity.HisInfusion;
import core.entity.PageBean;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Mapper
public interface HisInfusionMapper extends BaseMapper<HisInfusion>{
    int deleteByPrimaryKey(Long id);

    int deleteByNumber(String number);

    int insert(HisInfusion record);

    int insertSelective(HisInfusion record);

    HisInfusion selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisInfusion record);

    int updateByPrimaryKey(HisInfusion record);

    List<? extends HisInfusion> list(PageBean<HisInfusion> pageBean);

    List<? extends HisInfusion> listByHM(PageBean<HisInfusion> pageBean);

    List<? extends HisInfusion> listAllByHM(PageBean<HisInfusion> pageBean);

    List<HisInfusion> listByHMForPrint(String Hm);


    List<HisInfusion> listByRecordForPrint(String recordId);

    void insertBatch(@NotNull List<HisInfusion> hisInfusionList);

    List<? extends HisInfusion> selectByRecordId(PageBean<HisInfusion> pageBean);

    List<? extends HisInfusion> infusionDrugDetailsList(PageBean<HisInfusion> pageBean);

}