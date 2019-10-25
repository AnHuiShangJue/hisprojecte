package com.ahsj.payalipay.alipay.api;


import com.alipay.api.AlipayRequest;
import com.alipay.api.AlipayResponse;

/**
 * Created by bruce on 2017/12/25.
 */
public interface AlipayClient {

    public <T extends AlipayResponse> void execute(AlipayRequest<T> request, AlipayCallBack callBack);

}
