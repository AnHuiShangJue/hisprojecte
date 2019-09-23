package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.dao.HisImportInfoMapper;
import com.ahsj.hiscore.entity.HisImportInfo;
import com.ahsj.hiscore.services.HisImportInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisImportInfoServiceImpl
 * <p>
 * Date:     2019/8/30 9:29
 *
 * @author XJP
 * @create 2019/8/30
 * @since 1.0.0
 */

@Service
public class HisImportInfoServiceImpl implements HisImportInfoService {

    @Autowired
    HisImportInfoMapper hisImportInfoMapper;

    /**
     * @return void
     * @功能说明
     * @Params [importInfoList]
     * @Author XJP
     * @Date 2019/8/30
     * @Time 13:42
     **/
    @Override
    @Transactional(readOnly = false)
    public void inserHisImportInfo(List<HisImportInfo> importInfoList) {
        if (!EmptyUtil.Companion.isNullOrEmpty(importInfoList)) {
            hisImportInfoMapper.inserHisImportInfo(importInfoList);
        }
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisImportInfo>
     * @功能说明
     * @Params [hisImportInfo]
     * @Author XJP
     * @Date 2019/9/3
     * @Time 15:24
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisImportInfo> queryHisImportInfo(HisImportInfo hisImportInfo) {
        if (EmptyUtil.Companion.isNullOrEmpty(hisImportInfo)) {
            return new ArrayList<>();
        } else {
            List<HisImportInfo> hisImportInfoList = hisImportInfoMapper.queryHisImportInfo(hisImportInfo);
            if (EmptyUtil.Companion.isNullOrEmpty(hisImportInfoList)){
                return new ArrayList<>();
            }else {
                return hisImportInfoList;
            }
        }

    }
}
