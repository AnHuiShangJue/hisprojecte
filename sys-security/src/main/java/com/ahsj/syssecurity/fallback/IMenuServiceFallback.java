package com.ahsj.syssecurity.fallback;


import com.ahsj.syssecurity.entity.Menu;
import com.ahsj.syssecurity.entity.MenuOperate;
import com.ahsj.syssecurity.feign.IMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: his
 * @description:IMenuServiceFallback
 * @author: chenzhicai
 * @create: 2019-06-30-21-42
 **/
@RestController
public class IMenuServiceFallback implements IMenuService {
     Logger logger = LoggerFactory.getLogger(IMenuServiceFallback.class.getSimpleName());

    @Override
    public List<Menu> listSysMenu(Long id) {
        logger.error("菜单服务无法访问!");
        return new ArrayList<Menu>();
    }

    @Override
    public List<MenuOperate> listSysMenuOperate(Long id) {
        logger.error("菜单服务无法访问!");
        return new ArrayList<MenuOperate>();
    }
}

    