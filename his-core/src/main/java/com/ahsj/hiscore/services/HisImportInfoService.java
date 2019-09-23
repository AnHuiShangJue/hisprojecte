package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisImportInfo;

import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisImportInfoService
 * <p>
 * Date:     2019/8/30 9:29
 *
 * @author XJP
 * @create 2019/8/30
 * @since 1.0.0
 */

public interface HisImportInfoService {
    /**
     * @return void
     * @功能说明
     * @Params [importInfoList]
     * @Author XJP
     * @Date 2019/8/30
     * @Time 13:42
     **/
    void inserHisImportInfo(List<HisImportInfo> importInfoList);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisImportInfo>
     * @功能说明
     * @Params [hisImportInfo]
     * @Author XJP
     * @Date 2019/9/3
     * @Time 15:23
     **/
    List<HisImportInfo> queryHisImportInfo(HisImportInfo hisImportInfo);
}
