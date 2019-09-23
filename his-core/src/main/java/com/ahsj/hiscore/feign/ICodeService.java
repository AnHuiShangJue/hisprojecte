package com.ahsj.hiscore.feign;


import com.ahsj.hiscore.entity.sys.SysCode;
import com.ahsj.hiscore.entity.sys.SysCodeDetail;
import com.ahsj.hiscore.fallback.factory.ICodeServiceFallbackFactory;
import com.ahsj.hiscore.feign.config.FeignConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "eureka-userinfo", fallbackFactory = ICodeServiceFallbackFactory.class, configuration = FeignConfiguration.class)
public interface ICodeService {


    /**
     * @return List<SysCodeDetail>
     * @Description lstSysCodeDetail
     * @Params [id]
     * @Author chenzhicai
     * @Date 2019-06-13
     * @Time 18:32
     **/
    @PostMapping("/api/syscode/inner/lstSysCodeDetail.ahsj")
    @LoadBalanced
    public List<SysCodeDetail> lstSysCodeDetail() throws Exception;


    /**
     * @return com.ahsj.hiscore.entity.sys.SysCodeDetail
     * @功能说明
     * @Params [code]
     * @Author XJP
     * @Date 2019/7/29
     * @Time 17:29
     **/
    @PostMapping("/api/syscode/inner/lstSysCodeDetail/type.ahsj")
    @LoadBalanced
    SysCodeDetail SysCodeDetail(@RequestBody SysCodeDetail sysCodeDetail) throws Exception;


    /**
     * @return java.util.List<com.ahsj.hiscore.entity.sys.SysCodeDetail>
     * @功能说明
     * @Params [sysCodeDetail]
     * @Author XJP
     * @Date 2019/8/9
     * @Time 12:56
     **/
    @RequestMapping(value = "/api/syscode/queryTranslateInfoByDate/sysCodeDetail.ahsj", method = {RequestMethod.POST})
    @LoadBalanced
    List<SysCodeDetail> queryTranslateInfoByDate(@RequestBody SysCodeDetail sysCodeDetail) throws Exception;

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.sys.SysCode>
     * @功能说明
     * @Params [sysCode]
     * @Author XJP
     * @Date 2019/8/9
     * @Time 12:56
     **/
    @RequestMapping(value = "/api/syscode/queryTranslateInfoByDate/sysCode.ahsj", method = {RequestMethod.POST})
    @LoadBalanced
    List<SysCode> queryTranslateInfoByDate(@RequestBody SysCode sysCode) throws Exception;

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.sys.SysCode>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/11
     * @Time 14:54
     **/
    @RequestMapping(value = "/api/syscode/queryTranslateInfoByDate/getSysCodeAll.ahsj", method = {RequestMethod.POST})
    @LoadBalanced
    List<SysCode> getSysCodeAll() throws Exception;

    /**
     * @return com.ahsj.hiscore.entity.sys.SysCodeDetail
     * @功能说明
     * @Params [sysCodeDetail]
     * @Author XJP
     * @Date 2019/8/27
     * @Time 15:06
     **/
    @RequestMapping(value = "/api/syscode/queryTranslateInfoByDate/getSysCodeName.ahsj", method = {RequestMethod.POST})
    @LoadBalanced
    SysCodeDetail getSysCodeName(@RequestBody SysCodeDetail sysCodeDetail) throws Exception;

    /**
     * @param sysCodeDetail
     * @Description 根据字段，值查询是否存在
     * @Author: dingli
     * @return: com.ahsj.hiscore.entity.sys.SysCodeDetail
     * @Date 2019/8/30
     * @Time 16:17
     **/
    @RequestMapping(value = "/api/syscode/queryTranslateInfoByDate/selectSysCode.ahsj", method = {RequestMethod.POST})
    @LoadBalanced
    SysCodeDetail selectSysCode(@RequestBody SysCodeDetail sysCodeDetail) throws Exception;
}
