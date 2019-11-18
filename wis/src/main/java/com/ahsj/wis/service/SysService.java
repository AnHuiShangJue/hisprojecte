package com.ahsj.wis.service;

import com.ahsj.wis.entity.Sys;
import core.message.Message;

import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: SysService
 * <p>
 * Date:     2019/11/15 17:32
 *
 * @author XJP
 * @create 2019/11/15
 * @since 1.0.0
 */

public interface SysService {

    /**
     * @return java.util.List<com.ahsj.wis.entity.Sys>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/11/16
     * @Time 9:38
     **/
    List<Sys> getSysAll() throws Exception;

    /**
     * @return sun.plugin2.message.Message
     * @功能说明
     * @Params [sys]
     * @Author XJP
     * @Date 2019/11/16
     * @Time 9:38
     **/
    Message addSys(Sys sys) throws Exception;

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [sys]
     * @Author XJP
     * @Date 2019/11/16
     * @Time 15:23
     **/
    Message delete(Sys sys) throws Exception;
}
