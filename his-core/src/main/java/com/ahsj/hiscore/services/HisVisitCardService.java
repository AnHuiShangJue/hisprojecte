package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisPatientInfo;
import com.ahsj.hiscore.entity.HisVisitCard;
import core.message.Message;

public interface HisVisitCardService {
    /**
     * @return core.message.Message
     * @Description 新增就诊卡/办卡
     * @Params [hisVisitCard]
     * @Author zhushixiang
     * @Date 2019-09-10
     * @Time 13:59
     **/
    Message save(HisVisitCard hisVisitCard) throws Exception;

    /**
     * @Description 根据就诊卡号查询信息
     * @Params: [number]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisVisitCard
     * @Date 2019/9/10
     * @Time 15:13
     **/
    HisVisitCard selectByNumbers(String number) throws Exception;

    /**
     * @Description 充值就诊卡
     * @Params: [balance, id]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/9/10
     * @Time 15:39
     **/
    Message voucherCenter(HisVisitCard hisVisitCard) throws Exception;

    /**
     * @return com.ahsj.hiscore.entity.HisPatientInfo
     * @Description 根据就诊卡编号拉取病人信息
     * @Params [number]
     * @Author zhushixiang
     * @Date 2019-09-10
     * @Time 15:40
     **/
    HisPatientInfo getPatientInfoByNumber(String number) throws Exception;

    /**
     * @return com.ahsj.hiscore.entity.HisVisitCard
     * @Description 根据就诊卡编号查询就诊卡信息
     * @Params [visitCardNumber]
     * @Author zhushixiang
     * @Date 2019-09-10
     * @Time 17:03
     **/
    HisVisitCard selectByNumber(String visitCardNumber) throws Exception;

    /**
     * @Description 退卡
     * @Params: [hisVisitCard]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/9/10
     * @Time 17:51
     **/
    Message returnIdCard(HisVisitCard hisVisitCard) throws Exception;

    /**
     *@Description 就诊卡信息修改  多用于金额的修改
     *@Params [hisVisitCard]
     *@return void
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 17:49
    **/
    Message update(HisVisitCard hisVisitCard)throws Exception;

    /**
     *@Description 刷卡（目前仅针对门诊）  （未对接实际机器，仅有对数据库的操作）
     *@Params [visitCardNumber]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 17:53
    **/
    Message swipe(String visitCardNumber,String commonNumber)throws Exception;
}
