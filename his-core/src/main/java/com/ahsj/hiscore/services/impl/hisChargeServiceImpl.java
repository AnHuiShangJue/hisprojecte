package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisChargeMapper;
import com.ahsj.hiscore.entity.HisCharge;
import com.ahsj.hiscore.services.HisChargeService;
import com.ahsj.hiscore.services.HisMedicalRecordService;
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
 * @Date 2019/7/15
 * @Time 10:56
 **/
@Service
public class hisChargeServiceImpl implements HisChargeService {
    @Autowired
    private HisChargeMapper hisChargeMapper;
    @Transactional(readOnly = true)
    @Override
    /**
     *@Description
     * @param pageBean
     *@Author: dingli
     *@return
     *@Date 2019/7/16
     *@Time 9:38
     **/
    public PageBean<HisCharge> getAllHisCharge(PageBean<HisCharge> pageBean) throws Exception {
        List<HisCharge> list = hisChargeMapper.getAllHisCharge(pageBean);
        if (!EmptyUtil.Companion.isNullOrEmpty(list)) {//就诊号存在，计算总价
            BigDecimal amountPrice = new BigDecimal(0);
            for (HisCharge h : list) {
                BigDecimal count = new BigDecimal(h.getCount());
                BigDecimal price = h.getPrice();
                h.setTotalPrice(price.multiply(count)); //赋单项总价
                if (h.getIsPay() == 2) {//未付费
                    BigDecimal totalPrice = h.getTotalPrice();
                    amountPrice = amountPrice.add(totalPrice);//总应付金额
                }
            }
            list.get(0).setAmountPrice(amountPrice);
        }
        pageBean.setData(CodeHelper.getInstance().setCodeValue(list));
        return pageBean;
    }
/** className hisChargeServiceImpl
 *@author dingli
 *@date 2019/7/15 10:56
 */
}
