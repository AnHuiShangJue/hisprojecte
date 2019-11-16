package com.ahsj.wis.service;

import com.ahsj.wis.entity.Sys;

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

    List<Sys> getSysAll() throws Exception;
}
