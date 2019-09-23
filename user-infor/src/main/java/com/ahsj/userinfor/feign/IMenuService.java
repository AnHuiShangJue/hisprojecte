package com.ahsj.userinfor.feign;

import com.ahsj.userinfor.entity.Menu;
import com.ahsj.userinfor.entity.Role;
import com.ahsj.userinfor.entity.TreeEntity;
import com.ahsj.userinfor.entity.dto.ParamVo;
import com.ahsj.userinfor.fallback.factory.IMenuServiceFallnackFactotory;
import com.ahsj.userinfor.feign.config.FeignConfiguration;
import core.entity.PageBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

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
public interface IMenuService {
    /**
     * @return java.util.Map<java.lang.String, java.lang.String>
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/9/7
     * @Time 14:52
     **/
    @PostMapping("/api/sysuserinfo/menu/layout/lstMenu.ahsj")
    @LoadBalanced
    Map<String, String> listMenu(@RequestParam("id") Long id) throws Exception;

    /**
     * @return core.entity.PageBean<com.ahsj.userinfor.entity.Menu>
     * @功能说明
     * @Params [sysMenu]
     * @Author XJP
     * @Date 2019/9/7
     * @Time 14:52
     **/
    @PostMapping("/api/sysuserinfo/menu/list.ahsj")
    @LoadBalanced
    PageBean<Menu> list(Menu sysMenu) throws Exception;

    /**
     * @return java.util.List<com.ahsj.userinfor.entity.TreeEntity>
     * @功能说明
     * @Params [lv, id]
     * @Author XJP
     * @Date 2019/9/7
     * @Time 14:52
     **/
    @PostMapping("/api/sysuserinfo/menu/treeCode.ahsj")
    @ResponseBody
     List<TreeEntity> treeCode(@RequestBody ParamVo paramVo) throws Exception;









    //---------------------------------------------------------------------------------------------------


}
