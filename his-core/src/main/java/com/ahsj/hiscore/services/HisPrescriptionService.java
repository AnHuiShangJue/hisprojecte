package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisPrescription;
import com.ahsj.hiscore.entity.HisPrescriptionMedicine;
import com.ahsj.hiscore.entity.TreeEntity;
import core.entity.PageBean;
import core.message.Message;

import java.util.List;

public interface HisPrescriptionService {
    Message saveOrUpdate(HisPrescription hisPrescription);

    List<HisPrescription> selectAll();

    HisPrescription selectByPrescriptionId(Long id);

    Message delete(Long[] id);

    Message addMedical(Long prescriptionId, HisPrescriptionMedicine[] hisPrescriptionMedicines);

    Message deleteMedicine(Long[] medicineId);

    List<TreeEntity> selectTrreCode();

    PageBean<HisPrescription> list(PageBean<HisPrescription> pageEntity);


    HisPrescription updateInit(String id);

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisPrescription>
     * @Description 查询药方
     * @Params [prescriptionPageBean]
     * @Author chenzhicai
     * @Date 2019-07-17
     * @Time 02:47
     **/
    PageBean<HisPrescription> listForMedicalRecord(PageBean<HisPrescription> prescriptionPageBean);

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisPrescription>
     * @Description 查询可用药方（药方中的药品医院有且数量充足，药方处于启用状态）
     * @Params [prescriptionPageBean]
     * @Author zhushixiang
     * @Date 2019-08-27
     * @Time 10:54
     **/
    PageBean<HisPrescription> listByTreatmentPlan(PageBean<HisPrescription> prescriptionPageBean);


    /**
     * @param prescriptionPageBean
     * @Description 查询可用药方（药方中的药品医院有且数量充足，药方处于启用状态）
     * @Author: dingli
     * @return: core.entity.PageBean<com.ahsj.hiscore.entity.HisPrescription>
     * @Date 2019/8/28
     * @Time 9:31
     **/
    PageBean<HisPrescription> listByTreatmentPlans(PageBean<HisPrescription> prescriptionPageBean);


    /**
     *@Description 批量新增药方
     *@Params [insertParentMenu]
     *@return void
     *@Author zhushixiang
     *@Date 2019-09-03
     *@Time 11:40
    **/
    void saveForMultiple(List<HisPrescription> insertParentMenu);

    /**
     *@Description 批量更新药方
     *@Params [updatePrescriptionList]
     *@return void
     *@Author zhushixiang
     *@Date 2019-09-03
     *@Time 11:55
    **/
    void updateForMultiple(List<HisPrescription> updatePrescriptionList);

    /**
     *@Description 查询所有药方
     *@Params []
     *@return java.util.List<com.ahsj.hiscore.entity.HisPrescription>
     *@Author zhushixiang
     *@Date 2019-09-14
     *@Time 14:13
    **/
    List<HisPrescription> selectPrescription();

    /**
     *@Description 设置药方可用状态
     *@Params []
     *@return void
     *@Author zhushixiang
     *@Date 2019-09-14
     *@Time 15:31
    **/
    void searchIsAvailable()throws Exception;

    /**
     *@Description 根据名称查找药方
     *@Params [name]
     *@return com.ahsj.hiscore.entity.HisPrescription
     *@Author zhushixiang
     *@Date 2019-09-20
     *@Time 13:18
    **/
    HisPrescription selectByName(HisPrescription hisPrescription)throws Exception;
}
