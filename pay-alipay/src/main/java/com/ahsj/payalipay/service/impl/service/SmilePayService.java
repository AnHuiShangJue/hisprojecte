package com.ahsj.payalipay.service.impl.service;

import java.util.Map;

/**
 * @Description
 * @Author dingli
 * @Date 2019/10/17
 * @Time 17:12
 **/
public interface SmilePayService {

    /**
     * @Description 调用接口zolozGetMetaInfo采集刷脸所需的设备信息并完成刷脸的准备工作。
     * 如果zolozGetMetaInfo返回成功结果，则请求商户服务端调用支付宝开放平台的人脸初始化接口。
     * @Params: [smileToPayResponse]
     * @Author: dingli
     * @Return: void
     * @Date 2019/10/17
     * @Time 17:13
     **/
    void response(Map smileToPayResponse);
}
