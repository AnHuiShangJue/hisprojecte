package com.ahsj.hiseurekasecurityauthorizationserverone.feign.sys;

import com.ahsj.hiseurekasecurityauthorizationserverone.entity.Menu;
import com.ahsj.hiseurekasecurityauthorizationserverone.entity.MenuOperate;
import com.ahsj.hiseurekasecurityauthorizationserverone.fallback.sys.factory.IMenuServiceFallnackFactotory;
import com.ahsj.hiseurekasecurityauthorizationserverone.feign.sys.config.FeignConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

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
@FeignClient(name = "eureka-sysuserinfo", fallbackFactory = IMenuServiceFallnackFactotory.class, configuration = FeignConfiguration.class)
public interface ISysMenuService {

    @PostMapping("/api/sysuserinfo/menu/layout/lstMenu.ahsj")
    Map<String, String> listMenu(@RequestParam("id") Long id) throws Exception;

    /**
     * @return java.util.List<com.ahsj.hiseurekasecurityauthorizationserverone.entity.Menu>
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/9/7
     * @Time 13:44
     **/
    @PostMapping("/api/sysuserinfo/menu/info/inner/listSysMenu.ahsj")
    List<Menu> listSysMenu(@RequestParam("id") Long id);


    /**
     * @return java.util.List<com.ahsj.hiseurekasecurityauthorizationserverone.entity.MenuOperate>
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/9/7
     * @Time 13:44
     **/
    @RequestMapping("/api/sysuserinfo/menu/info/inner/listSysMenuOperate.ahsj")
    List<MenuOperate> listSysMenuOperate(@RequestParam("id") Long id);
}
