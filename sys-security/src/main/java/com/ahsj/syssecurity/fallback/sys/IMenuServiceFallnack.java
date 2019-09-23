package com.ahsj.syssecurity.fallback.sys;


import com.ahsj.syssecurity.entity.Menu;
import com.ahsj.syssecurity.entity.MenuOperate;
import com.ahsj.syssecurity.feign.sys.ISysMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: ITranslateServiceFallnack
 * <p>
 * Date:     2019/7/24 15:41
 *
 * @author XJP
 * @create 2019/7/24
 * @since 1.0.0
 */

@RestController
public class IMenuServiceFallnack implements ISysMenuService {

    Logger logger = LoggerFactory.getLogger(IMenuServiceFallnack.class.getSimpleName());

    @Override
    public Map<String, String> listMenu(Long id) throws Exception {
        logger.error("服务获取异常,不能够访问组织服务");
        return  new HashMap<>();
    }

    @Override
    public List<Menu> listSysMenu(Long id) {
        logger.error("服务获取异常,不能够访问组织服务");
        return  new ArrayList<>();
    }

    @Override
    public List<MenuOperate> listSysMenuOperate(Long id) {
        logger.error("服务获取异常,不能够访问组织服务");
        return  new ArrayList<>();
    }
}
