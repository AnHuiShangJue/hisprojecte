package com.ahsj.hiscore.services;

import com.ahsj.hiscore.common.utils.ZipUtils;
import com.ahsj.hiscore.entity.HisMedicationDetails;
import core.entity.PageBean;
import core.message.Message;

import java.util.Date;
import java.util.List;

public interface HisMedicationDetailsService {
    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisMedicationDetails>
     * @Description list
     * @Params [pageBean]
     * @Author zhushixiang
     * @Date 2019-07-11
     * @Time 13:36
     **/
    PageBean<HisMedicationDetails> list(PageBean<HisMedicationDetails> pageBean) throws Exception;

    /**
     * @return core.message.Message
     * @Description 删除
     * @Params [ids]
     * @Author zhushixiang
     * @Date 2019-07-11
     * @Time 13:37
     **/
    Message delete(Long[] ids) throws Exception;

    /**
     * @return core.message.Message
     * @Description 新增或更新
     * @Params [hisMedicineInfo]
     * @Author zhushixiang
     * @Date 2019-07-11
     * @Time 13:37
     **/
    Message saveOrUpdate(List<HisMedicationDetails> hisMedicationDetailsList) throws Exception;


    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisMedicationDetails>
     * @Description 根据就诊编号查询
     * @Params [id]
     * @Author chenzhicai
     * @Date 2019-07-12
     * @Time 11:11
     **/
    List<HisMedicationDetails> selectByRecordId(Long id) throws Exception;

    /**
     * @param record
     * @return
     * @Description 根据id修改是否付费
     * @Author: dingli
     * @Date 2019/7/17
     * @Time 19:31
     **/
    int updateHisMedicationDetails(HisMedicationDetails record) throws Exception;

    /**
     * @return void
     * @Description 批量更新药是否已出状态
     * @Params [hisMedicationDetails]
     * @Author zhushixiang
     * @Date 2019-07-23
     * @Time 20:38
     **/
    void updateBatch(List<HisMedicationDetails> hisMedicationDetails);

    /**
     * @Description 统计各个时间段内售药数目
     * @Author muxu
     * @Date 2019/7/28
     * @Time 22:44
     * @Return
     * @Params
     **/
    Integer[] selectMedicationDetailsDataCount(Date createDate) throws Exception;

    /**
     * @Description 根据就诊编号查出用药信息
     * @Param pageBean:
     * @Author: dingli
     * @return: core.entity.PageBean<com.ahsj.hiscore.entity.HisMedicationDetails>
     * @Date 2019/8/1
     * @Time 14:56
     **/
    PageBean<HisMedicationDetails> useDrug(PageBean<HisMedicationDetails> pageBean) throws Exception;

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisMedicationDetails>
     * @功能说明
     * @Params [hisMedicationDetails]
     * @Author XJP
     * @Date 2019/8/8
     * @Time 17:47
     **/
    List<HisMedicationDetails> queryTranslateInfoByDate(HisMedicationDetails hisMedicationDetails) throws Exception;

    /**
     * @param
     * @Description 查询HisMedicationDetails的所有信息
     * @Author: dingli
     * @return: java.util.List<com.ahsj.hiscore.entity.HisMedicationDetails>
     * @Date 2019/8/11
     * @Time 15:33
     **/
    List<HisMedicationDetails> queryAll() throws Exception;


    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisMedicationDetails>
     * @Description 根据就诊记录ID查询未付费且未出药的用药明细
     * @Params [id]
     * @Author zhushixiang
     * @Date 2019-08-15
     * @Time 17:44
     **/
    List<HisMedicationDetails> selectByRecordIdNotIsOutOrIsPay(Long id);

    /**
     * @param medicalNumber
     * @Description 打印用药明细
     * @Author: dingli
     * @return: java.util.List<com.ahsj.hiscore.entity.HisMedicationDetails>
     * @Date 2019/8/19
     * @Time 13:54
     **/
    List<HisMedicationDetails> selectByMedicalNumber(String medicalNumber) throws Exception;

    /**
     * @Description 查询输液单可选药品信息
     * @Author muxu
     * @Date 2019/8/29
     * @Time 15:07
     * @Return
     * @Params
     **/

    PageBean<HisMedicationDetails> selectOutpatientInfusionByMedicalRecordId(PageBean<HisMedicationDetails> pageBean) throws Exception;

    /**
     * @Description
     * @Params: [number]
     * @Author: dingli
     * @Return: java.util.List<com.ahsj.hiscore.entity.HisMedicalRecord>
     * @Date 2019/9/7
     * @Time 11:17
     **/
    List<HisMedicationDetails> selectPrint(String number) throws Exception;

    /**
     *@Description 查询出当前就诊编号未付费的药品
     *@Params [id]
     *@return java.util.List<com.ahsj.hiscore.entity.HisMedicationDetails>
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 17:33
    **/
    List<HisMedicationDetails> selectByRecordIdNotIsPay(Long medicalRecordId)throws Exception;

    /**
     *@Description 新增
     *@Params [hisMedicationDetails]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-09-17
     *@Time 19:01
    **/
    Message insert(HisMedicationDetails hisMedicationDetails)throws Exception;
}
