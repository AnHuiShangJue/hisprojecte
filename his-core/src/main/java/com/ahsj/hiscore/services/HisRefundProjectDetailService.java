package com.ahsj.hiscore.services;


import com.ahsj.hiscore.entity.HisRefundProjectDetail;
import core.entity.PageBean;
import core.message.Message;

import java.security.cert.X509Certificate;
import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisRefundProjectDetailService
 * <p>
 * Date:     2019/8/26 15:30
 *
 * @author XJP
 * @create 2019/8/26
 * @since 1.0.0
 */

public interface HisRefundProjectDetailService {

    /**
     * @return int
     * @功能说明
     * @Params [hisRefundProjectDetails]
     * @Author XJP
     * @Date 2019/8/26
     * @Time 15:29
     **/
    Message insertHisRefundProjectDetailList(List<HisRefundProjectDetail> hisRefundProjectDetails) throws Exception;

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisRefundProjectDetail>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/8/26
     * @Time 17:19
     **/
    PageBean<HisRefundProjectDetail> projectInfoDetail(PageBean<HisRefundProjectDetail> pageBean);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRefundProjectDetail>
     * @功能说明
     * @Params [hisRefundProjectDetail]
     * @Author XJP
     * @Date 2019/8/26
     * @Time 18:00
     **/
    List<HisRefundProjectDetail> HisRecordProjectLists(HisRefundProjectDetail hisRefundProjectDetail) throws Exception;
}
