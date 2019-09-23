package com.ahsj.translate.feign;



import com.ahsj.translate.entity.SysCodeDetail;
import com.ahsj.translate.fallback.factory.ICodeServiceFallbackFactory;
import com.ahsj.translate.feign.config.FeignConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "eureka-userinfo",fallbackFactory = ICodeServiceFallbackFactory.class,configuration = FeignConfiguration.class)
public interface ICodeService {




    /**
     *@Description lstSysCodeDetail
     *@Params [id]
     *@return List<SysCodeDetail>
     *@Author chenzhicai
     *@Date 2019-06-13
     *@Time 18:32
     **/
    @PostMapping("/api/syscode/inner/lstSysCodeDetail.ahsj")
    @LoadBalanced
    public List<SysCodeDetail> lstSysCodeDetail() throws Exception;


    /**
     *@功能说明
     *@Params [code]
     *@return com.ahsj.hiscore.entity.sys.SysCodeDetail
     *@Author XJP
     *@Date 2019/7/29
     *@Time 17:29
    **/
    @PostMapping("/api/syscode/inner/lstSysCodeDetail/type.ahsj")
    @LoadBalanced
    SysCodeDetail SysCodeDetail(@RequestBody SysCodeDetail sysCodeDetail) throws Exception;
}
