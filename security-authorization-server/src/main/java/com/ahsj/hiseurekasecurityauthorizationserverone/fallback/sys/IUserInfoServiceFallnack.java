package com.ahsj.hiseurekasecurityauthorizationserverone.fallback.sys;


import com.ahsj.hiseurekasecurityauthorizationserverone.entity.UserInfo;
import com.ahsj.hiseurekasecurityauthorizationserverone.feign.sys.ISysMenuService;
import com.ahsj.hiseurekasecurityauthorizationserverone.feign.sys.ISysUserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
public class IUserInfoServiceFallnack implements ISysUserInfoService {

    Logger logger = LoggerFactory.getLogger(IUserInfoServiceFallnack.class.getSimpleName());

    @Override
    public UserInfo selectByName(String username){
        logger.error("服务获取异常,不能够访问组织服务");
        return  new UserInfo();
    }

}
