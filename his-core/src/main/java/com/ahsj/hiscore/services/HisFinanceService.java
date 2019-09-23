package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisRegisteredpay;
import core.entity.PageBean;

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

}
