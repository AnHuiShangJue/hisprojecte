package com.ahsj.smartparkcore.services;

import com.ahsj.smartparkcore.entity.po.EnterpriseInfo;
import com.ahsj.smartparkcore.entity.po.StationInfo;
import core.entity.PageBean;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: StationInfoService
 * <p>
 * Date:     2019/10/19 17:57
 *
 * @author XJP
 * @create 2019/10/19
 * @since 1.0.0
 */

public interface StationInfoService {

    PageBean<StationInfo>  queryList(PageBean<StationInfo> pageBean);
}
