package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisRefundProjectInfo;
import core.entity.PageBean;

import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisRefundProjectInfoService
 * <p>
 * Date:     2019/8/19 16:08
 *
 * @author XJP
 * @create 2019/8/19
 * @since 1.0.0
 */

public interface HisRefundProjectInfoService {
    /**
     * @return com.ahsj.hiscore.entity.HisRefundProjectInfo
     * @功能说明
     * @Params [hisRefundProjectInfo]
     * @Author XJP
     * @Date 2019/8/19
     * @Time 16:10
     **/
    HisRefundProjectInfo queryHisRefundProjectInfo(HisRefundProjectInfo hisRefundProjectInfo) throws Exception;

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisRefundProjectInfo>
     * @功能说明 查看退项目明细
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/8/20
     * @Time 10:40
     **/
    PageBean<HisRefundProjectInfo> list(PageBean<HisRefundProjectInfo> pageBean);

    /**
     *@功能说明
     *@Params
     *@return
     *@Author XJP
     *@Date 2019/8/26
     *@Time 17:40
     **/
    List<HisRefundProjectInfo> printlylist(HisRefundProjectInfo hisRefundProjectInfo);
}
