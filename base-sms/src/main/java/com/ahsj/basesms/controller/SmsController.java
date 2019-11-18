package com.ahsj.basesms.controller;

import core.controller.BaseController;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sms/")
public class SmsController extends BaseController {


     @RequestMapping("getSms/index.ahsj")
     public void getSms(String phonenumber,String deviceId) throws Exception{

         OkHttpClient client = new OkHttpClient();
             RequestBody body = RequestBody.create(JSON, json);
             Request request = new Request.Builder()
                     .url("https://api.netease.im/sms/sendcode.action")
                     .post(body)
                     .build();
             Response response = client.newCall(request).execute();

}
