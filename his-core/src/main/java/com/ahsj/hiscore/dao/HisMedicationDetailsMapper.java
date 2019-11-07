package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisInfusion;
import com.ahsj.hiscore.entity.HisMedicationDetails;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.List;

@Mapper
public interface HisMedicationDetailsMapper extends BaseMapper<HisMedicationDetails> {
    int deleteByPrimaryKey(Long id);

    int insert(HisMedicationDetails record);

    int insertSelective(HisMedicationDetails record);

    HisMedicationDetails selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisMedicationDetails record);

    int updateByPrimaryKey(HisMedicationDetails record);

    List<HisMedicationDetails> list(PageBean<HisMedicationDetails> pageBean);

    void deleteByMedicalRecordId(Long medicalRecordId);

    //    @NotNull  被注释的元素不能为null
    void insertBatch(@NotNull List<HisMedicationDetails> hisMedicationDetailsList);

    List<HisMedicationDetails> selectByRecordId(Long id);

    void setPayBatch(List<HisMedicationDetails> hisMedicationDetails);

    List<HisMedicationDetails> listForMedicationDetailsByNumber(String number);

    void updateBatch(List<HisMedicationDetails> hisMedicationDetails);

    void updateBatchForIsBack(List<HisMedicationDetails> hisMedicationDetailsList);

    /**
     * @Description 统计各个时间段内的售药数目
     * @Author muxu
     * @Date 2019/7/28
     * @Time 22:43
     * @Return
     * @Params
     **/

    List<HisMedicationDetails> selectMedicationDetailsDataCount(@Param(value = "createDate") Date createDate, @Param(value = "endDate") Date endDate) throws Exception;

    List<HisMedicationDetails> useDrug(PageBean<HisMedicationDetails> pageBean);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisMedicationDetails>
     * @功能说明
     * @Params [hisMedicationDetails]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 11:01
     **/
    List<HisMedicationDetails> queryTranslateInfoByDate(HisMedicationDetails hisMedicationDetails);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisMedicationDetails>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/11
     * @Time 16:34
     **/
    List<HisMedicationDetails> queryAll();

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisMedicationDetails>
     * @Description
     * @Params [id]
     * @Author zhushixiang
     * @Date 2019-08-15
     * @Time 17:45
     **/
    List<HisMedicationDetails> selectByRecordIdNotIsOutOrIsPay(Long id);

    /**
     * @return void
     * @Description 根据就诊记录ID删除所有用药明细（未付费且未出药）
     * @Params [medicalRecordId]
     * @Author zhushixiang
     * @Date 2019-08-16
     * @Time 9:44
     **/
    void deleteByMedicalRecordIdNotIsOutOrIspay(Long medicalRecordId);

    /**
     * @param medicalNumber
     * @Description 打印用药明细
     * @Author: dingli
     * @return: java.util.List<com.ahsj.hiscore.entity.HisMedicationDetails>
     * @Date 2019/8/19
     * @Time 13:45
     **/
    List<HisMedicationDetails> selectByMedicalNumber(String medicalNumber);
    /**
     * @Description 查询输液单可选药品信息
     * @Author  muxu
     * @Date  2019/8/29
     * @Time 15:06
     * @Return
     * @Params
    **/
    List<HisMedicationDetails> selectOutpatientInfusionByMedicalRecordId(PageBean<HisMedicationDetails> pageBean);

    List<HisMedicationDetails> selectPrint(String number);

    /**
     *@Description 查询出当前就诊编号未付费的药品
     *@Params [recordId]
     *@return java.util.List<com.ahsj.hiscore.entity.HisMedicationDetails>
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 17:34
    **/
    List<HisMedicationDetails> selectByRecordIdNotIsPay(Long recordId);


    /**
     *@Description 更新remarks 已打印
     *@Params
     *@return
     *@Author jin
     *@Date 2019/9/16
     *@Time 9:50
    */
    void updateAlreadyout(HisMedicationDetails hisMedicationDetails);

    /**
     *@Description 根据就诊记录ID查询未付的药品
     *@Params [pageBean]
     *@return java.util.List<? extends com.ahsj.hiscore.entity.HisMedicationDetails>
     *@Author zhushixiang
     *@Date 2019-10-06
     *@Time 17:59
    **/
    List<? extends HisMedicationDetails> listByRecordIdAndNotPay(PageBean<HisMedicationDetails> pageBean);

    /**
     *@Description 根据ids 批量查找
     *@Params [ids]
     *@return java.util.List<com.ahsj.hiscore.entity.HisMedicationDetails>
     *@Author zhushixiang
     *@Date 2019-10-06
     *@Time 19:25
    **/
    List<HisMedicationDetails> selectByIds(Long[] ids);


}
