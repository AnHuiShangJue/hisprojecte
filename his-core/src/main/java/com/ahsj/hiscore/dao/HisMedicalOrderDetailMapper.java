package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisMedicalOrderDetail;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;
@Mapper
public interface HisMedicalOrderDetailMapper extends BaseMapper<HisMedicalOrderDetail>{
    int deleteByPrimaryKey(Long id);

    int insert(HisMedicalOrderDetail record);

    int insertSelective(HisMedicalOrderDetail record);

    HisMedicalOrderDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisMedicalOrderDetail record);

    int updateByPrimaryKey(HisMedicalOrderDetail record);

    /**
     *@Description 根据医嘱编号查询 返回分页对象
     *@Params [pageBean]
     *@return java.util.List<com.ahsj.hiscore.entity.HisMedicalOrderDetail>
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:30
    **/
    List<HisMedicalOrderDetail> listDetailByNumber(PageBean<HisMedicalOrderDetail> pageBean);

    /**
     *@Description 根据医嘱编号查询 返回List
     *@Params [number]
     *@return java.util.List<com.ahsj.hiscore.entity.HisMedicalOrderDetail>
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:30
    **/
    List<HisMedicalOrderDetail> selectByNumber(String number);

    /**
     *@Description 根据医嘱编号删除
     *@Params [number]
     *@return void
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:30
    **/
    void deleteByNumber(String number);

    /**
     *@Description 批量插入
     *@Params [hisMedicalOrderDetailLinkedList]
     *@return void
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:32
    **/
    void insertBatch(@NotNull LinkedList<HisMedicalOrderDetail> hisMedicalOrderDetailLinkedList);

    /**
     *@Description 根据医嘱编号查看医嘱明细
     *@Params [number]
     *@return java.util.List<com.ahsj.hiscore.entity.HisMedicalOrderDetail>
     *@Author zhushixiang
     *@Date 2019-09-21
     *@Time 18:02
    **/
    List<HisMedicalOrderDetail> selectByNumberAscAndNotStop(String number);
}