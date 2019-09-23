package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.dao.HisPatientInfoMapper;
import com.ahsj.hiscore.entity.HisPatientInfo;
import com.ahsj.hiscore.services.HisPatientInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisPatientInfoServiceImpl
 * <p>
 * Date:     2019/8/19 16:50
 *
 * @author XJP
 * @create 2019/8/19
 * @since 1.0.0
 */

@Service
public class HisPatientInfoServiceImpl implements HisPatientInfoService {

    @Autowired
    HisPatientInfoMapper hisPatientInfoMapper;

    /**
     * @return com.ahsj.hiscore.entity.HisPatientInfo
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/8/19
     * @Time 16:51
     **/
    @Override
    @Transactional(readOnly = true)
    public HisPatientInfo selectByPrimaryKey(Long id) {
        return hisPatientInfoMapper.selectByPrimaryKey(id);
    }
}
