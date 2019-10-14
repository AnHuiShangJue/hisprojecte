package com.ahsj.smartparkcore.services.impl;

import com.ahsj.smartparkcore.dao.AccessInfoMapper;
import com.ahsj.smartparkcore.services.AccessInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: AccessInfoServiceImpl
 * <p>
 * Date:     2019/10/14 15:08
 *
 * @author XJP
 * @create 2019/10/14
 * @since 1.0.0
 */

@Service
public class AccessInfoServiceImpl implements AccessInfoService {

    @Autowired
    AccessInfoMapper accessInfoMapper;
}
