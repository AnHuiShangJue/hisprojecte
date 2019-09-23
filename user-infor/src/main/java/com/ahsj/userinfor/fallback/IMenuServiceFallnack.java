package com.ahsj.userinfor.fallback;


import com.ahsj.userinfor.entity.Menu;
import com.ahsj.userinfor.entity.Role;
import com.ahsj.userinfor.entity.Translate;
import com.ahsj.userinfor.entity.TreeEntity;
import com.ahsj.userinfor.entity.dto.ParamVo;
import com.ahsj.userinfor.entity.model.TranslateModel;
import com.ahsj.userinfor.feign.IMenuService;
import core.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
public class IMenuServiceFallnack implements IMenuService {

    Logger logger = LoggerFactory.getLogger(IMenuServiceFallnack.class.getSimpleName());

    @Override
    public Map<String, String> listMenu( Long id) throws Exception {
        logger.error("服务获取异常,不能够访问组织服务");
        return  new HashMap<>();
    }

    @Override
    public PageBean<Menu> list(Menu sysMenu) throws Exception {
        logger.error("服务获取异常,不能够访问组织服务");
        return  new PageBean<>();
    }

    @Override
    public List<TreeEntity> treeCode(ParamVo paramVo) throws Exception {
        logger.error("服务获取异常,不能够访问组织服务");
        return  new ArrayList<>();
    }


}
