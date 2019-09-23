package com.anhuishangjue.map.controller;

import com.netflix.ribbon.proxy.annotation.Http;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @program: hisprojecte
 * @description:定位服务
 * @author: chenzhicai
 * @create: 2019-09-02-16-22
 **/
public class LocationController {


    String getLocation() throws Exception{
        OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://restapi.amap.com/v3/ip?key=3ed37108785fd17d1e91fc4453796a00")
                    .build();

            try (Response response = client.newCall(request).execute()) {
                System.out.printf("data is"+ response.body().string());
                return response.body().string();
            }
    }
}

