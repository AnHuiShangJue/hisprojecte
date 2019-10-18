package com.ahsj.payalipay.service.impl.impl;

import com.ahsj.payalipay.dao.ReturnAlipayMapper;
import com.ahsj.payalipay.entity.ReturnAlipay;
import com.ahsj.payalipay.service.impl.service.ReturnAlipayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description
 * @Author dingli
 * @Date 2019/10/18
 * @Time 14:04
 **/
@Service
public class ReturnAlipayServiceImpl implements ReturnAlipayService {
    /**
     * className ReturnAlipayServiceImpl
     *
     * @author dingli
     * @date 2019/10/18 14:04
     */
    @Autowired
    ReturnAlipayMapper returnAlipayMapper;

    /**
     * @Description 新增退款信息
     * @Params: [record]
     * @Author: dingli
     * @Return: int
     * @Date 2019/10/18
     * @Time 14:46
     **/
    @Override
    @Transactional(readOnly = false)
    public int insert(ReturnAlipay record) throws Exception {
        record.setType(2);//未退款
        return returnAlipayMapper.insert(record);
    }

    /**
     * @Description 修改支付状态
     * @Params: [record]
     * @Author: dingli
     * @Return: int
     * @Date 2019/10/18
     * @Time 14:47
     **/
    @Override
    @Transactional(readOnly = false)
    public int updateByPrimaryKeySelective(ReturnAlipay record) throws Exception {
        return returnAlipayMapper.updateByPrimaryKeySelective(record);
    }
}
