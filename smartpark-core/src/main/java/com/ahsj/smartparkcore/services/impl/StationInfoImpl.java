package com.ahsj.smartparkcore.services.impl;

import com.ahsj.smartparkcore.core.CodeHelper;
import com.ahsj.smartparkcore.dao.StationInfoMapper;
import com.ahsj.smartparkcore.entity.po.StationInfo;
import com.ahsj.smartparkcore.services.StationInfoService;
import core.entity.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: StationInfoImpl
 * <p>
 * Date:     2019/10/19 17:57
 *
 * @author XJP
 * @create 2019/10/19
 * @since 1.0.0
 */

@Service
public class StationInfoImpl implements StationInfoService {

    @Autowired
    StationInfoMapper stationInfoMapper;

    /**
     * @return core.entity.PageBean<com.ahsj.smartparkcore.entity.po.StationInfo>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/10/19
     * @Time 18:15
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<StationInfo> queryList(PageBean<StationInfo> pageBean) {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(stationInfoMapper.queryList(pageBean)));
        return pageBean;
    }
}
