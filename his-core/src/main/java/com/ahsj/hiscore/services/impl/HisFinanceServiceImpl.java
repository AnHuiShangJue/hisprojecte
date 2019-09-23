package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisRegisteredpayMapper;
import com.ahsj.hiscore.entity.HisRegisteredpay;
import com.ahsj.hiscore.services.HisFinanceService;
import core.entity.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
