package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisRegisteredpayMapper;
import com.ahsj.hiscore.dao.HisTollDetailsMapper;
import com.ahsj.hiscore.entity.HisRegisteredpay;
import com.ahsj.hiscore.entity.HisTollDetails;
import com.ahsj.hiscore.entity.dto.HisFinanceCondition;
import com.ahsj.hiscore.services.HisFinanceService;
import com.ahsj.hiscore.services.HisTollDetailsService;
import core.entity.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description
 * @Author dingli
 * @Date 2019/7/20
 * @Time 9:53
 **/
@Service
public class HisFinanceServiceImpl implements HisFinanceService {
    @Autowired
    private HisTollDetailsService hisTollDetailsService;
    @Autowired
    private HisTollDetailsMapper hisTollDetailsMapper;

    @Autowired
    HisRegisteredpayMapper hisRegisteredpayMapper;

    @Override
    public PageBean<HisRegisteredpay> getAllHisFinance(PageBean<HisRegisteredpay> pageBean) throws Exception {
        List<HisRegisteredpay> list = hisRegisteredpayMapper.getAllHisRegisteredpay(pageBean); //条件查询所取得的所有结果
        pageBean.setData(CodeHelper.getInstance().setCodeValue(list));
        return pageBean;
    }

    /**
     * className HisFinanceServiceImpl
     *
     * @author dingli
     * @date 2019/7/20 9:53
     */
    public HisRegisteredpay getHisRegisteredpay(String number) {
        return CodeHelper.getInstance().setCodeValue(hisRegisteredpayMapper.getHisRegisteredpay(number));

    }

    /**
     *@Description 获取指定时间段内医院收入总价
     *@Params [hisFinanceCondition]
     *@return java.math.BigDecimal
     *@Author zhushixiang
     *@Date 2020-06-02
     *@Time 9:54
    **/
    @Override
    @Transactional(readOnly = true)
    public  List<HisTollDetails> getTotal(HisFinanceCondition hisFinanceCondition) throws Exception {
        // 查询所有收入的费用（除挂号费）
        List<HisTollDetails> hisTollDetailsList = hisTollDetailsService.selectByTimeCondition(hisFinanceCondition);
//        BigDecimal total = new BigDecimal("0");
//        if(!EmptyUtil.Companion.isNullOrEmpty(hisTollDetailsList)){
//            for (HisTollDetails hisTollDetails : hisTollDetailsList) {
//                if(hisTollDetails.getType() == 1 || hisTollDetails.getType() == 2 || hisTollDetails.getType() == 3 ){
//                    total = total.add(hisTollDetails.getMoney());
//                }else if(hisTollDetails.getType() == 4 || hisTollDetails.getType() == 5) {
//                    total = total.subtract(hisTollDetails.getMoney());
//                }
//            }
//        }
        return hisTollDetailsList;
    }

    /**
     *@Description 根据条件按天统计费用
     *@Params [hisFinanceCondition]
     *@return java.util.List<com.ahsj.hiscore.entity.HisTollDetails>
     *@Author zhushixiang
     *@Date 2020-06-02
     *@Time 17:04
    **/
    @Override
    @Transactional(readOnly = true)
    public List<HisTollDetails> getTotalByDay(HisFinanceCondition hisFinanceCondition) throws Exception {
        List<HisTollDetails> hisTollDetailsList = hisTollDetailsMapper.getTotalByDay(hisFinanceCondition);
        return hisTollDetailsList;
    }

    /**
     *@Description 获取指定时间段内医院住院收入总价
     *@Params [hisFinanceCondition]
     *@return java.util.List<com.ahsj.hiscore.entity.HisTollDetails>
     *@Author zhushixiang
     *@Date 2020-06-03
     *@Time 9:52
    **/
    @Override
    @Transactional(readOnly = true)
    public List<HisTollDetails> getInhospitalTotal(HisFinanceCondition hisFinanceCondition) throws Exception {
        List<HisTollDetails> hisTollDetailsList = hisTollDetailsMapper.getInhospitalTotal(hisFinanceCondition);
        return hisTollDetailsList;
    }

    /**
     *@Description 获取指定时间段内医院门诊收入总价
     *@Params [hisFinanceCondition]
     *@return java.util.List<com.ahsj.hiscore.entity.HisTollDetails>
     *@Author zhushixiang
     *@Date 2020-06-03
     *@Time 11:22
    **/
    @Override
    @Transactional(readOnly = true)
    public List<HisTollDetails> getNotInhospitalTotal(HisFinanceCondition hisFinanceCondition) throws Exception {
        List<HisTollDetails> hisTollDetailsList = hisTollDetailsMapper.getNotInhospitalTotal(hisFinanceCondition);
        return hisTollDetailsList;
    }

    /**
     *@Description 根据条件按天统计住院费用
     *@Params [hisFinanceCondition]
     *@return java.util.List<com.ahsj.hiscore.entity.HisTollDetails>
     *@Author zhushixiang
     *@Date 2020-06-03
     *@Time 11:24
    **/
    @Override
    @Transactional(readOnly = true)
    public List<HisTollDetails> getInHospitalTotalByDay(HisFinanceCondition hisFinanceCondition) {
        List<HisTollDetails> hisTollDetailsList = hisTollDetailsMapper.getInHospitalTotalByDay(hisFinanceCondition);
        return hisTollDetailsList;
    }

    /**
     *@Description 根据条件按天统计住门诊费用
     *@Params [hisFinanceCondition]
     *@return java.util.List<com.ahsj.hiscore.entity.HisTollDetails>
     *@Author zhushixiang
     *@Date 2020-06-03
     *@Time 11:29
    **/
    @Override
    @Transactional(readOnly = true)
    public List<HisTollDetails> getNotInHospitalTotalByDay(HisFinanceCondition hisFinanceCondition) {
        List<HisTollDetails> hisTollDetailsList = hisTollDetailsMapper.getNotInHospitalTotalByDay(hisFinanceCondition);
        return hisTollDetailsList;
    }
}
