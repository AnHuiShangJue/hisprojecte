package com.ahsj.userinfor.fallback;


import com.ahsj.userinfor.entity.Menu;
import com.ahsj.userinfor.entity.Role;
import com.ahsj.userinfor.entity.TreeEntity;
import com.ahsj.userinfor.entity.dto.ParamVo;
import com.ahsj.userinfor.feign.IMenuService;
import com.ahsj.userinfor.feign.ISysRoleService;
import core.entity.PageBean;
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
public class ISysRoleServiceFallnack implements ISysRoleService {

    Logger logger = LoggerFactory.getLogger(ISysRoleServiceFallnack.class.getSimpleName());


    @Override
    public PageBean<Role> list(Role role) throws Exception {
        logger.error("服务获取异常,不能够访问组织服务");
        return  new PageBean<>();
    }
}
