package com.ahsj.syssecurity.feign.sys;

import com.ahsj.syssecurity.entity.UserInfo;
import com.ahsj.syssecurity.fallback.sys.factory.IUserInfoServiceFallnackFactotory;
import com.ahsj.syssecurity.feign.sys.config.FeignConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: IMenuService
 * <p>
 * Date:     2019/9/6 14:53
 *
 * @author XJP
 * @create 2019/9/6
 * @since 1.0.0
 */
@FeignClient(name = "eureka-sysuserinfo", fallbackFactory = IUserInfoServiceFallnackFactotory.class, configuration = FeignConfiguration.class)
public interface ISysUserInfoService {

    @RequestMapping("/api/sysuserinfo/info/inner/selectByName.ahsj")
    @LoadBalanced
    UserInfo selectByName(@RequestParam("username") String username);



}
