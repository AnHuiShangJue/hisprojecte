package com.ahsj.wis.service.impl;

import com.ahsj.wis.dao.SysMapper;
import com.ahsj.wis.entity.Sys;
import com.ahsj.wis.service.SysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: SysServiceImpl
 * <p>
 * Date:     2019/11/15 17:32
 *
 * @author XJP
 * @create 2019/11/15
 * @since 1.0.0
 */

@Service
public class SysServiceImpl implements SysService {

    @Autowired
    SysMapper sysMapper;

    /**
     *@功能说明
     *@Params []
     *@return java.util.List<com.ahsj.wis.entity.Sys>
     *@Author XJP
     *@Date 2019/11/15
     *@Time 18:12
    **/
    @Override
    @Transactional(readOnly = true)
    public List<Sys> getSysAll() throws Exception {
        List<Sys> sysAll = sysMapper.getSysAll();
        if (EmptyUtil.Companion.isNullOrEmpty(sysAll)){
            return new ArrayList<>();
        }else {
            return sysAll;
        }
    }
}
