package com.ahsj.smartparkcore.services.impl;

import com.ahsj.smartparkcore.dao.RegionMapper;
import com.ahsj.smartparkcore.services.RegionService;
import com.google.cloud.GcpLaunchStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: RegionServiceImpl
 * <p>
 * Date:     2019/10/16 18:05
 *
 * @author XJP
 * @create 2019/10/16
 * @since 1.0.0
 */

@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    RegionMapper regionMapper;


}
