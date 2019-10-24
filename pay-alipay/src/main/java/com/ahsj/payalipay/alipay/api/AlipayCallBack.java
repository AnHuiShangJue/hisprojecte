package com.ahsj.payalipay.alipay.api;


import com.alipay.api.AlipayResponse;

/**
 * Created by bruce on 2017/12/25.
 */

public interface AlipayCallBack {
    <T extends AlipayResponse> T onResponse(T response);
}
