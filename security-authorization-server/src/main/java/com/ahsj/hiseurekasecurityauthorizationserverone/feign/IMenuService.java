package com.ahsj.hiseurekasecurityauthorizationserverone.feign;

import com.ahsj.hiseurekasecurityauthorizationserverone.entity.Menu;
import com.ahsj.hiseurekasecurityauthorizationserverone.entity.MenuOperate;
import com.ahsj.hiseurekasecurityauthorizationserverone.fallback.IMenuServiceFallback;
import com.ahsj.hiseurekasecurityauthorizationserverone.fallback.IUserServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @program: his
 * @description:菜单服务
 * @author: chenzhicai
 * @create: 2019-06-30-21-35
 **/
@FeignClient(name = "eureka-userinfo", fallback = IMenuServiceFallback.class, path = "/api/menu/")
public interface IMenuService {

    /**
     * @return java.util.List<com.ahsj.hiseurekasecurityauthorizationserverone.entity.Menu>
     * @Description
     * @Params [id]
     * @Author chenzhicai
     * @Date 2019/6/30
     * @Time 9:41 PM
     **/
    @PostMapping(value = "info/inner/listSysMenu.ahsj")
     List<Menu> listSysMenu(@RequestParam("id")Long id);


    /**
     *@Description
     *@Params [id]
     *@return java.util.List<com.ahsj.hiseurekasecurityauthorizationserverone.entity.MenuOperate>
     *@Author chenzhicai
     *@Date 2019/7/1
     *@Time 3:38 PM
    **/
    @RequestMapping("info/inner/listSysMenuOperate.ahsj")
    List<MenuOperate> listSysMenuOperate(@RequestParam("id") Long id);
}

    