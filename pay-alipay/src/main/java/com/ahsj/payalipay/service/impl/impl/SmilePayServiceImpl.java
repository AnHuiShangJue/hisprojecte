package com.ahsj.payalipay.service.impl.impl;

import com.ahsj.payalipay.service.impl.service.SmilePayService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author dingli
 * @Date 2019/10/17
 * @Time 17:11
 **/
@Service
public class SmilePayServiceImpl implements SmilePayService {
/** className smilePayServiceImpl
 *@author dingli
 *@date 2019/10/17 17:11
 */
    //调用zolozGetMetaInfo完成设备信息采集

    /**
     * @Description 调用接口zolozGetMetaInfo采集刷脸所需的设备信息并完成刷脸的准备工作。
     * 如果zolozGetMetaInfo返回成功结果，则请求商户服务端调用支付宝开放平台的人脸初始化接口。
     * @Params: [smileToPayResponse]
     * @Author: dingli
     * @Return: void
     * @Date 2019/10/17
     * @Time 17:13
     */

    private Map mockInfo() {
   /*     Map merchantInfo = new HashMap();
        //以下信息请根据真实情况填写
        //商户 Pid
        merchantInfo.put("merchantId", "2088201820182018");
        //ISV PID
        merchantInfo.put("partnerId", partnerId);
        //添加刷脸付功能的appid
        merchantInfo.put("appId", appId);
        //机具编号，便于关联商家管理的机具
        merchantInfo.put("deviceNum", "TEST_ZOLOZ_TEST");
        //商户的门店编号
        merchantInfo.put("storeCode", "TEST");
        //支付宝门店编号
        merchantInfo.put("alipayStoreCode", "TEST");

        return merchantInfo;*/

        return null;
    }

    @Override
    public void response(Map smileToPayResponse) {

    }
}
