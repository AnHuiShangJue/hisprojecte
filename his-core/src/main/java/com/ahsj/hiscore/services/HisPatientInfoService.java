package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisPatientInfo;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisPatientInfoService
 * <p>
 * Date:     2019/8/19 16:49
 *
 * @author XJP
 * @create 2019/8/19
 * @since 1.0.0
 */

public interface HisPatientInfoService {
    /**
     * @return com.ahsj.hiscore.entity.HisPatientInfo
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/8/19
     * @Time 16:51
     **/
    HisPatientInfo selectByPrimaryKey(Long id);
}
