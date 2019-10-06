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

    int updateRemarks(HisInfusion hisInfusion);

    List<? extends HisInfusion> list(PageBean<HisInfusion> pageBean);

    List<? extends HisInfusion> listByHM(PageBean<HisInfusion> pageBean);

    List<? extends HisInfusion> listAllByNumber(PageBean<HisInfusion> pageBean);

    List<HisInfusion> listByHMForPrint(String Hm);
    List<HisInfusion> listByRemarkForPrint(String remark);



    List<HisInfusion> listByRecordForPrint(String recordId);

    void insertBatch(@NotNull List<HisInfusion> hisInfusionList);

    List<? extends HisInfusion> selectByRecordId(PageBean<HisInfusion> pageBean);

    List<? extends HisInfusion> infusionDrugDetailsList(PageBean<HisInfusion> pageBean);

    /**
     *@Description 根据输液单编号查找
     *@Params [infusionNumber]
     *@return java.util.List<com.ahsj.hiscore.entity.HisInfusion>
     *@Author zhushixiang
     *@Date 2019-09-25
     *@Time 16:29
    **/
    List<HisInfusion> selectByNumber(String infusionNumber);

    /**
     *@Description 根据输液单编号查找(仅为住院输液单服务)
     *@Params [number]
     *@return java.util.List<? extends com.ahsj.hiscore.entity.HisInfusion>
     *@Author zhushixiang
     *@Date 2019-09-26
     *@Time 0:08
    **/
    List< HisInfusion> listByHMForHospitalPrint(String number);

    /**
     *@Description 根据分组编号查询  listByRemark
     *@Params
     *@return
     *@Author jin
     *@Date 2019/10/6
     *@Time 16:59
    */
    List<? extends HisInfusion> listByRemark(PageBean<HisInfusion> pageBean);

}