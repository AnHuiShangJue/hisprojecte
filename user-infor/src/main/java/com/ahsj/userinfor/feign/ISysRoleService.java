package com.ahsj.userinfor.feign;

import com.ahsj.userinfor.entity.Role;
import com.ahsj.userinfor.fallback.factory.ISysRoleServiceFallnackFactotory;
import com.ahsj.userinfor.feign.config.FeignConfiguration;
import core.entity.PageBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


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
@FeignClient(name = "eureka-sysuserinfo", fallbackFactory = ISysRoleServiceFallnackFactotory.class, configuration = FeignConfiguration.class)
public interface ISysRoleService {


    @PostMapping("/api/sysuserinfo/role/list.ahsj")
    @LoadBalanced
    PageBean<Role> list(Role role) throws Exception;
}
