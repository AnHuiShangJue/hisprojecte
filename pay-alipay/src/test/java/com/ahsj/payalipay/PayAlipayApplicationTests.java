package com.ahsj.payalipay;

import com.ahsj.payalipay.entity.AlipaymentOrder;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PayAlipayApplicationTests {

    @Test
    public void contextLoads() {

        String jsonEntity ="{'alipay_trade_query_response':{'code':'10000','msg':'Success','buyer_logon_id':'nua***@sandbox.com','buyer_pay_amount':'0.00','buyer_user_id':'2088102179888996','buyer_user_type':'PRIVATE','invoice_amount':'0.00','out_trade_no':'201910170122','point_amount':'0.00','receipt_amount':'0.00','send_pay_date':'2019-10-17 15:22:10','total_amount':'0.01','trade_no':'2019101722001488991000042276','trade_status':'TRADE_SUCCESS'},'sign':'K2ZjkfFDpDsN80p6Eq4zfbwTtWB0HeUnjdQo1bW3jQzhM3lMWRE2koGFuVOSosOIpoRu1bmH3d7hu8BURL376plWWuuR0lYoH12+d/VSD8Z/lRYdhFF4vyFkQS9ZDfgDpoH5pdv45/qB0sJtsiQAvnpEc8/YfaNGOFpZnaMuIbysbGOCbFTb8VtvhWz/uoDjQUQ+jfZglgCe9XfKfHbEVwo8gxa08qm+iRNMPilO0i3IUXngH0UtSE8tcob1gUfFFXKDhCewcjS5WTIQdT6XIjWo8hpnrYZDEo5TSErCAjjDxL3xr2DKyr9lmu/9bv8Q+SQIYQE5EAObYQ5p1jZg6w=='}";



        //JSONObject jsonObject =  JSON.parseObject(jsonEntity);
     JSONObject jsonObject =JSON.parseObject( JSON.parseObject(jsonEntity).get("alipay_trade_query_response").toString());
       // Object alipay_trade_refund_response = JSON.parseObject(jsonEntity).get("alipay_trade_refund_response")
        System.out.println(jsonObject.getString("msg"));




    }}
