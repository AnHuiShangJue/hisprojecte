package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.dao.HisRegisteredpayMapper;
import com.ahsj.hiscore.entity.HisRegisteredpay;
import com.ahsj.hiscore.services.HisRegisteredService;
import com.ahsj.hiscore.services.HisRegisteredpayService;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.EmptyUtil;

/**
 * @program: his
 * @description:挂号支付服务实现类
 * @author: chenzhicai
 * @create: 2019-06-28-13-57
 **/
@Service
public class HisRegisteredpaySerivceImpl implements HisRegisteredpayService {

    @Autowired
    HisRegisteredService hisRegisteredService;

    @Autowired
    HisRegisteredpayMapper hisRegisteredpayMapper;

    @Override
    public Message saveOrUpdate(HisRegisteredpay hisRegisteredpay) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisRegisteredpay.getId())) {
            if (EmptyUtil.Companion.isNullOrEmpty(hisRegisteredpay.getRegisteredId())) {
                return MessageUtil.createMessage(false, "付费失败，并未绑定指定的挂号！");
            } else {
                HisRegisteredpay tmp = hisRegisteredpayMapper.selectByRegisteredId(hisRegisteredpay.getRegisteredId());
                if (!EmptyUtil.Companion.isNullOrEmpty(tmp) ) {
                    return MessageUtil.createMessage(false, "付费失败，该挂号单已经付清完毕！");
                }
                hisRegisteredpayMapper.insert(hisRegisteredpay);
                //付费设置成功后修改付费清单的信息
                return MessageUtil.createMessage(true, "付费成功!");
            }
        } else {
            HisRegisteredpay tmp = hisRegisteredpayMapper.selectByPrimaryKey(hisRegisteredpay.getId());
            if (!EmptyUtil.Companion.isNullOrEmpty(tmp) )
                return MessageUtil.createMessage(false, "付费失败，该挂号单不存在！");
            hisRegisteredpayMapper.updateByPrimaryKey(hisRegisteredpay);
            return MessageUtil.createMessage(true, "付费修改成功！");
        }
    }

    @Override
    public HisRegisteredpay getPrice(HisRegisteredpay hisRegisteredpay) throws Exception {
        return hisRegisteredpayMapper.getPrice(hisRegisteredpay);
    }
}

    