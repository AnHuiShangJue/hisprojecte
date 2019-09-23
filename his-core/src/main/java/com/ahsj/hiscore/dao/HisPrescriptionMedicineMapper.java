package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisPrescriptionMedicine;
import com.ahsj.hiscore.entity.TreeEntity;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Mapper
public interface HisPrescriptionMedicineMapper extends BaseMapper<HisPrescriptionMedicine>{
    int deleteByPrimaryKey(Long id);

    int insert(HisPrescriptionMedicine record);

    int insertSelective(HisPrescriptionMedicine record);

    HisPrescriptionMedicine selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisPrescriptionMedicine record);

    int updateByPrimaryKey(HisPrescriptionMedicine record);

    /**
     *@Description
     *@Params []
     *@return java.util.List<com.ahsj.hiscore.entity.TreeEntity>
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:42
    **/
    List<TreeEntity> selectTreeCode();

    /**
     *@Description 分页查询
     *@Params [pageEntity]
     *@return java.util.List<com.ahsj.hiscore.entity.HisPrescriptionMedicine>
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:42
    **/
    List<HisPrescriptionMedicine> list(PageBean<HisPrescriptionMedicine> pageEntity);

    /**
     *@Description 根据编号查找
     *@Params [hisPrescriptionMedicine]
     *@return com.ahsj.hiscore.entity.HisPrescriptionMedicine
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:42
    **/
    HisPrescriptionMedicine selectByNumb(HisPrescriptionMedicine hisPrescriptionMedicine);

    /**
     *@Description 根据药方名称查找
     *@Params [hisPrescriptionMedicine]
     *@return com.ahsj.hiscore.entity.HisPrescriptionMedicine
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:42
    **/
    HisPrescriptionMedicine selectByName(HisPrescriptionMedicine hisPrescriptionMedicine);

    /**
     *@Description
     *@Params [parentId]
     *@return com.ahsj.hiscore.entity.HisPrescriptionMedicine
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:42
    **/
    HisPrescriptionMedicine selectMaxTreeId(String parentId);

    //根据处方ID搜索对应药品信息，但是要将pharmacy_id 取别名为id，为适应自定义表格selfTable
    List<HisPrescriptionMedicine> selectByPrescriptionIdForTable(String prescriptionId);

    /**
     *@Description
     *@Params [id]
     *@return java.util.List<com.ahsj.hiscore.entity.HisPrescriptionMedicine>
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:42
    **/
    List<HisPrescriptionMedicine> selectByPrescriptionIdForTables(Long id);

    /**
     *@Description 批量插入
     *@Params [hisPrescriptionMedicineList]
     *@return void
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:42
    **/
    void insertBatch(@NotNull List<HisPrescriptionMedicine> hisPrescriptionMedicineList);

    /**
     *@Description 查询药方id对应的所有药品信息
     *@Params
     *@return
     *@Author zhushixiang
     *@Date 2019-07-13
     *@Time 17:11
    **/
    List<HisPrescriptionMedicine> listForDetails(String prescriptionId);

    /**
     *@Description 根据PrescriptionId删除所有
     *@Params [prescriptionId]
     *@return void
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:43
    **/
    void deleteByPrescriptionId(String prescriptionId);

    /**
     *@Description 根据PrescriptionId查询
     *@Params [prescriptionId]
     *@return java.util.List<com.ahsj.hiscore.entity.HisPrescriptionMedicine>
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:43
    **/
    List<HisPrescriptionMedicine> selectByPrescriptionId(String prescriptionId);

    /**
     *@Description
     *@Params [pageEntity]
     *@return java.util.List<com.ahsj.hiscore.entity.HisPrescriptionMedicine>
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:43
    **/
    List<HisPrescriptionMedicine> listDetail(PageBean<HisPrescriptionMedicine> pageEntity);

    /**
     *@Description 导出信息
     *@Params [hisPrescriptionMedicine]
     *@return java.util.List<com.ahsj.hiscore.entity.HisPrescriptionMedicine>
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:44
    **/
    List<HisPrescriptionMedicine> exportHisPrescriptionMedicine(HisPrescriptionMedicine hisPrescriptionMedicine);

    /**
     *@Description 查看门诊病历模板中的药方明细
     *@Params [pageEntity]
     *@return java.util.List<? extends com.ahsj.hiscore.entity.HisPrescriptionMedicine>
     *@Author zhushixiang
     *@Date 2019-09-19
     *@Time 17:44
    **/
    List<?extends HisPrescriptionMedicine> viewDetailInMedicalTemplate(PageBean<HisPrescriptionMedicine> pageEntity);
}