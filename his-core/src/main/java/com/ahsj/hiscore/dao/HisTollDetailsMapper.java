package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisHospitalManage;
import com.ahsj.hiscore.entity.HisTollDetails;
import com.ahsj.hiscore.entity.HisTollRecord;
import com.ahsj.hiscore.entity.dto.HisFinanceCondition;
import org.apache.ibatis.annotations.Mapper;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HisTollDetailsMapper extends BaseMapper<HisTollDetails> {
    int deleteByPrimaryKey(Integer id);

    int insert(HisTollDetails record);

    int insertSelective(HisTollDetails record);

    HisTollDetails selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HisTollDetails record);

    int updateByPrimaryKey(HisTollDetails record);

    List<HisTollDetails> listByMecordIdForHospital(PageBean<HisTollDetails> pageBean);//

    List<HisTollDetails> nurselistByMecordIdForHospital(PageBean<HisTollDetails> pageBean);//

    List<? extends HisTollDetails> listForOutpatientCharges(PageBean<HisTollDetails> pageBean);

    List<HisTollDetails> selectForOutpatientChargesByMedicalRecordId(String medicalRecordId);

    void saveForHospi(List<HisTollDetails> hisTollDetails);

    List<HisTollDetails> listByMecordIdForSave(PageBean<HisTollDetails> pageBean);

    List<? extends HisTollDetails> listForOutpatientByMecordId(PageBean<HisTollDetails> hisTollDetailsPageBean);

    List<? extends HisTollDetails> listByMecordIdForOutpatientSave(PageBean<HisTollDetails> pageBean);

    List<HisTollDetails> listHisTollDetails(PageBean<HisTollDetails> pageBean);

    List<HisTollDetails> listByNumber(String number);


    /**
     * @return void
     * @Description 批量插入
     * @Params [hisTollDetailsList]
     * @Author zhushixiang
     * @Date 2019-09-11
     * @Time 11:31
     **/
    void insertBatch(List<HisTollDetails> hisTollDetailsList);

    /**
     * @return com.ahsj.hiscore.entity.HisTollDetails
     * @Description 根据交易流水号查询当次所付住院费用的明细
     * @Params [tollRecordNumber]
     * @Author zhushixiang
     * @Date 2019-09-12
     * @Time 17:00
     **/
    HisTollDetails selectByTollNumberForBedAmount(String tollRecordNumber);

    /**
     * @return java.util.List<? extends com.ahsj.hiscore.entity.HisTollDetails>
     * @Description 根据公共编号 搜索出对应的消费明细
     * @Params [hisTollDetailsPageBean]
     * @Author zhushixiang
     * @Date 2019-09-13
     * @Time 16:35
     **/
    List<? extends HisTollDetails> listForcommonSwipeByCommonNumber(PageBean<HisTollDetails> hisTollDetailsPageBean);//

    /**
     * @Description
     * @Params: [number]
     * @Author: dingli
     * @Return: java.util.List<com.ahsj.hiscore.entity.HisTollDetails>
     * @Date 2019/9/26
     * @Time 18:51
     **/
    List<HisTollDetails> listByNumberLeave(String number);

    /**
     * @Description
     * @Params: [number]
     * @Author: dingli
     * @Return: java.util.List<com.ahsj.hiscore.entity.HisTollDetails>
     * @Date 2019/9/26
     * @Time 20:22
     **/
    HisTollDetails listByNumberFor(@Param("number") String number);

    HisTollDetails listByNumberFors(@Param("number") String number);

    /**
     * @Description
     * @Params: [number]  根据交易号判断是否住院
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisHospitalManage
     * @Date 2019/9/27
     * @Time 13:55
     **/
    HisTollDetails selectNumber(String number);

    /**
     * @Description 根据交易流水号查询价格信息
     * @Params: [number]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisTollRecord
     * @Date 2019/10/7
     * @Time 14:10
     **/
    HisTollDetails getPriceByNumber(String number) throws Exception;

    List<HisTollDetails> printShowThere(@Param("number") String number);

    /**
     *@Description 计算出院总费用
     *@Params [medicalRecordId]
     *@return java.util.List<com.ahsj.hiscore.entity.HisTollDetails>
     *@Author zhushixiang
     *@Date 2020-04-21
     *@Time 19:15
    **/
    List<HisTollDetails> listByNumberForLeave(String medicalRecordId);

    List<HisTollDetails> queryByRecordConsumablesLists(PageBean<HisTollDetails> pageBean);

    /**
     *@Description 查询所有收入的费用（除挂号费）
     *@Params [hisFinanceCondition]
     *@return java.util.List<com.ahsj.hiscore.entity.HisTollDetails>
     *@Author zhushixiang
     *@Date 2020-06-02
     *@Time 10:05
    **/
    List<HisTollDetails> selectByTimeCondition(HisFinanceCondition hisFinanceCondition);

    /**
     *@Description 根据条件按天统计费用
     *@Params [hisFinanceCondition]
     *@return java.util.List<com.ahsj.hiscore.entity.HisTollDetails>
     *@Author zhushixiang
     *@Date 2020-06-02
     *@Time 17:05
    **/
    List<HisTollDetails> getTotalByDay(HisFinanceCondition hisFinanceCondition);

    /**
     *@Description 获取指定时间段内医院住院收入总价
     *@Params [hisFinanceCondition]
     *@return java.util.List<com.ahsj.hiscore.entity.HisTollDetails>
     *@Author zhushixiang
     *@Date 2020-06-03
     *@Time 9:52
    **/
    List<HisTollDetails> getInhospitalTotal(HisFinanceCondition hisFinanceCondition);

    /**
     *@Description 获取指定时间段内医院门诊收入总价
     *@Params [hisFinanceCondition]
     *@return java.util.List<com.ahsj.hiscore.entity.HisTollDetails>
     *@Author zhushixiang
     *@Date 2020-06-03
     *@Time 11:23
    **/
    List<HisTollDetails> getNotInhospitalTotal(HisFinanceCondition hisFinanceCondition);

    /**
     *@Description 根据条件按天统计住院费用
     *@Params [hisFinanceCondition]
     *@return java.util.List<com.ahsj.hiscore.entity.HisTollDetails>
     *@Author zhushixiang
     *@Date 2020-06-03
     *@Time 11:29
    **/
    List<HisTollDetails> getInHospitalTotalByDay(HisFinanceCondition hisFinanceCondition);

    /**
     *@Description 根据条件按天统计住门诊费用
     *@Params [hisFinanceCondition]
     *@return java.util.List<com.ahsj.hiscore.entity.HisTollDetails>
     *@Author zhushixiang
     *@Date 2020-06-03
     *@Time 14:44
    **/
    List<HisTollDetails> getNotInHospitalTotalByDay(HisFinanceCondition hisFinanceCondition);
}
