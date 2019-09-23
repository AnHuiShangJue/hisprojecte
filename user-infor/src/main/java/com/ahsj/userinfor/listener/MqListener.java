package com.ahsj.userinfor.listener;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import utils.EmptyUtil;

import java.util.List;

/**
 * @author XJP
 * @date 2019/07/25
 */
@Slf4j
@Component
public class MqListener {



    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "com.mq.verify.queue2"),
            exchange = @Exchange(name = "com.ahsj.exchange", type = ExchangeTypes.TOPIC),
            key = "com.ahsj.msgTwo"
    ))
    public void listenMq(Integer num) {
        if (EmptyUtil.Companion.isNullOrEmpty(num)){
            return;
        }
        System.out.println("-------------习近平 测试   MQ----------->"+num);
        /*String phone = (String) msg.remove("phone");
        if (StringUtils.isBlank(phone)) {
            return;
        }*/
        //smsUtil.sendSms(props.getSignName(), props.getVerifyCodeTemplate(), phone, JsonUtils.serialize(msg));


    }


}
