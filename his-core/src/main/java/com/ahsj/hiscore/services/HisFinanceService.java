package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisRegisteredpay;
import com.ahsj.hiscore.entity.HisTollDetails;
import com.ahsj.hiscore.entity.dto.HisFinanceCondition;
import core.entity.PageBean;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description
 * @Author dingli
 * @Date 2019/7/20
 * @Time 9:50
 **/
public interface HisFinanceService {
    /**
     * @param pageBean
     * @return
     * @Description 条件查询挂号收费统计
     * @Author: dingli
     * @Date 2019/7/20
     * @Time 9:51
     **/
    PageBean<HisRegisteredpay> getAllHisFinance(PageBean<HisRegisteredpay> pageBean) throws Exception;

    /**
     * @param number
     * @return
     * @Description
     * @Author: dingli
     * @Date 2019/7/20
     * @Time 19:04
     **/
    HisRegisteredpay getHisRegisteredpay(String number);

    /**
     *@Description 获取指定时间段内医院收入总价
     *@Params [hisFinanceCondition]
     *@return java.math.BigDecimal
     *@Author zhushixiang
     *@Date 2020-06-02
     *@Time 9:54
    **/
    List<HisTollDetails> getTotal(HisFinanceCondition hisFinanceCondition)throws Exception;

    /**
     *@Description 根据条件按天统计费用
     *@Params [hisFinanceCondition]
     *@return java.util.List<com.ahsj.hiscore.entity.HisTollDetails>
     *@Author zhushixiang
     *@Date 2020-06-02
     *@Time 17:10
    **/
    List<HisTollDetails> getTotalByDay(HisFinanceCondition hisFinanceCondition)throws Exception;

    /**
     *@Description 获取指定时间段内医院住院收入总价
     *@Params [hisFinanceCondition]
     *@return java.util.List<com.ahsj.hiscore.entity.HisTollDetails>
     *@Author zhushixiang
     *@Date 2020-06-03
     *@Time 10:47
    **/
    List<HisTollDetails> getInhospitalTotal(HisFinanceCondition hisFinanceCondition)throws Exception;

    /**
     *@Description 获取指定时间段内医院门诊收入总价
     *@Params [hisFinanceCondition]
     *@return java.util.List<com.ahsj.hiscore.entity.HisTollDetails>
     *@Author zhushixiang
     *@Date 2020-06-03
     *@Time 11:21
    **/
    List<HisTollDetails> getNotInhospitalTotal(HisFinanceCondition hisFinanceCondition)throws Exception;

    /**
     *@Description 根据条件按天统计住院费用
     *@Params [hisFinanceCondition]
     *@return java.util.List<com.ahsj.hiscore.entity.HisTollDetails>
     *@Author zhushixiang
     *@Date 2020-06-03
     *@Time 11:23
    **/
    List<HisTollDetails> getInHospitalTotalByDay(HisFinanceCondition hisFinanceCondition);

    /**
     *@Description 根据条件按天统计住门诊费用
     *@Params [hisFinanceCondition]
     *@return java.util.List<com.ahsj.hiscore.entity.HisTollDetails>
     *@Author zhushixiang
     *@Date 2020-06-03
     *@Time 11:29
    **/
    List<HisTollDetails> getNotInHospitalTotalByDay(HisFinanceCondition hisFinanceCondition);
}
