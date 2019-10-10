package com.ahsj.hiscore.feign;

import com.ahsj.hiscore.entity.model.TranslateModel;
import com.ahsj.hiscore.fallback.factory.ISecurityServiceFallbackFactory;
import com.ahsj.hiscore.fallback.factory.ITranslateServiceFallnackFactotory;
import com.ahsj.hiscore.feign.config.FeignConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "security", fallbackFactory = ISecurityServiceFallbackFactory.class, configuration = FeignConfiguration.class)
public interface ISecurityService {

    /**
     *@Description 定时关闭服务
     *@Params []
     *@return void
     *@Author zhushixiang
     *@Date 2019-10-10
     *@Time 13:47
    **/
    @PostMapping("/api/securityShutdown/shutdown.ahsj")
    @LoadBalanced
    void systemShutdownService() throws Exception;
}
