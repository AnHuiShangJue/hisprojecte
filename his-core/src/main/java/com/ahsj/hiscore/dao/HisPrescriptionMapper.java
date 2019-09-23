package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisPrescription;
import com.ahsj.hiscore.entity.TreeEntity;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Mapper
public interface HisPrescriptionMapper extends BaseMapper<HisPrescription> {


    /**
     *@Description 根据药方名称查找
     *@Params [hisPrescription]
     *@return com.ahsj.hiscore.entity.HisPrescription
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:40
    **/
    HisPrescription selectByName(HisPrescription hisPrescription);

    int insert(HisPrescription hisPrescription);

    int deleteByPrimaryKey(Long var1);


    int insertSelective(HisPrescription var1);

    HisPrescription selectByPrimaryKey(Long var1);

    int updateByPrimaryKeySelective(HisPrescription var1);

    int updateByPrimaryKey(HisPrescription var1);

    List<HisPrescription> selectAll();

    List<TreeEntity> selectTreeCode();

    /**
     *@Description 分页查询
     *@Params [pageEntity]
     *@return java.util.List<com.ahsj.hiscore.entity.HisPrescription>
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:40
    **/
    List<HisPrescription> list(PageBean<HisPrescription> pageEntity);

    /**
     *@Description 查找树子节点最大treeID
     *@Params [parentId]
     *@return com.ahsj.hiscore.entity.HisPrescription
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:41
    **/
    HisPrescription selectMaxTreeId(String parentId);

    /**
     *@Description 根据treeId查找
     *@Params [treeId]
     *@return com.ahsj.hiscore.entity.HisPrescription
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:41
    **/
    HisPrescription selectByTreeId(String treeId);

    /**
     *@Description 根据TreeId删除
     *@Params [treeId]
     *@return void
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:41
    **/
    void deleteByTreeId(String treeId);

    /**
     *@Description 查询药方
     *@Params [prescriptionPageBean]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisPrescription>
     *@Author chenzhicai
     *@Date 2019-07-17
     *@Time 02:55
    **/
    List<HisPrescription> listForMedicalRecord(PageBean<HisPrescription> prescriptionPageBean);

    /**
     *@Description 根据parentId查找
     *@Params [parentId]
     *@return java.util.List<com.ahsj.hiscore.entity.HisPrescription>
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:41
    **/
    List<HisPrescription> selectByParentId(String parentId);

    /**
     *@Description 根据parentId删除
     *@Params [parentId]
     *@return void
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:41
    **/
    void deleteByParentId(String parentId);

    /**
     *@Description
     *@Params [prescriptionPageBean]
     *@return java.util.List<? extends com.ahsj.hiscore.entity.HisPrescription>
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:42
    **/
    List<?extends HisPrescription> listByTreatmentPlan(PageBean<HisPrescription> prescriptionPageBean);

    /**
     *@Description
     *@Params [prescriptionPageBean]
     *@return java.util.List<? extends com.ahsj.hiscore.entity.HisPrescription>
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:42
    **/
    List<?extends HisPrescription> listByTreatmentPlans(PageBean<HisPrescription> prescriptionPageBean);

    /**
     *@Description 批量插入
     *@Params [insertParentMenu]
     *@return int
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:42
    **/
    int insertBatch(@NotNull List<HisPrescription> insertParentMenu);

    /**
     *@Description 批量更新
     *@Params [updatePrescriptionList]
     *@return int
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:44
    **/
    int updateBatch(List<HisPrescription> updatePrescriptionList);

    /**
     *@Description 查询所有药方
     *@Params []
     *@return java.util.List<com.ahsj.hiscore.entity.HisPrescription>
     *@Author zhushixiang
     *@Date 2019-09-14
     *@Time 14:13
    **/
    List<HisPrescription> selectPrescription();
}