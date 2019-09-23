package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisMedicalOrder;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;

@Mapper
public interface HisMedicalOrderMapper extends BaseMapper<HisMedicalOrder>{
    int deleteByPrimaryKey(Long id);

    int insert(HisMedicalOrder record);

    int insertSelective(HisMedicalOrder record);

    HisMedicalOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisMedicalOrder record);

    int updateByPrimaryKey(HisMedicalOrder record);

    /**
     *@Description 根据就诊记录编号查找医嘱及其详细数据
     *@Params [recordId]
     *@return java.util.List<com.ahsj.hiscore.entity.HisMedicalOrder>
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:31
    **/
    List<HisMedicalOrder> selectByRecordId(String recordId);

    /**
     *@Description 根据就诊记录编号查找医嘱及其详细数据 根据序号排序
     *@Params [recordId]
     *@return java.util.List<com.ahsj.hiscore.entity.HisMedicalOrder>
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:31
    **/
    List<HisMedicalOrder> selectByRecordIdOrderByOrderNum(String recordId);

    /**
     *@Description 根据就诊记录编号查找医嘱及其详细数据
     *@Params [pageBean]
     *@return java.util.List<? extends com.ahsj.hiscore.entity.HisMedicalOrder>
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:31
    **/
    List<? extends HisMedicalOrder> listByRecordId(PageBean<HisMedicalOrder> pageBean);

    /**
     *@Description 删除
     *@Params [recordId]
     *@return void
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:32
    **/
    void deleteByRecordId(String recordId);

    /**
     *@Description 批量插入
     *@Params [hisMedicalOrderLinkedList]
     *@return void
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:32
    **/
    void insertBatch(@NotNull LinkedList<HisMedicalOrder> hisMedicalOrderLinkedList);

    /**
     *@Description 查询今日新增数目
     *@Params [createdate]
     *@return int
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:32
    **/
    int selectNumbCount(String createdate);

    /**
     *@Description 根据医嘱编号查询
     *@Params [medicalOrderNumber]
     *@return com.ahsj.hiscore.entity.HisMedicalOrder
     *@Author zhushixiang
     *@Date 2019-09-17
     *@Time 18:32
    **/
    HisMedicalOrder selectByNumber(String medicalOrderNumber);
}