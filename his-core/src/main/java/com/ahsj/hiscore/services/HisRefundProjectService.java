package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisRecordProject;
import com.ahsj.hiscore.entity.HisRefundProject;
import com.ahsj.hiscore.entity.HisRefundProjectInfo;
import core.entity.PageBean;
import core.message.Message;

import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisRefundProjectService
 * <p>
 * Date:     2019/8/16 18:41
 *
 * @author XJP
 * @create 2019/8/16
 * @since 1.0.0
 */

public interface HisRefundProjectService {

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [hisRecordProject, nums, ids]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 18:58
     **/
    Message saveForAppEdit(HisRefundProject hisRefundProject, Integer[] nums, Long[] ids, String userName) throws Exception;

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisRefundProject>
     * @功能说明 查询申请退项目的列表记录
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 20:00
     **/
    PageBean<HisRefundProject> list(PageBean<HisRefundProject> pageBean);

    /**
     * @return core.message.Message
     * @功能说明 批量删除申请退款项目
     * @Params [vouchers]
     * @Author XJP
     * @Date 2019/8/17
     * @Time 12:24
     **/
    Message delete(String[] vouchers) throws Exception;

    /**
     * @return core.message.Message
     * @功能说明 退项目申请审核通过
     * @Params [hisRefundProject]
     * @Author XJP
     * @Date 2019/8/17
     * @Time 15:36
     **/
    Message reviewSuccess(HisRefundProject hisRefundProject) throws Exception;

    /**
     * @return core.message.Message
     * @功能说明 项目申请审核失败
     * @Params [hisRefundProject]
     * @Author XJP
     * @Date 2019/8/17
     * @Time 16:22
     **/
    Message reviewFail(HisRefundProject hisRefundProject) throws Exception;

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRefundProject>
     * @功能说明
     * @Params [hisRefundProject]
     * @Author XJP
     * @Date 2019/8/17
     * @Time 16:30
     **/
    List<HisRefundProject> queryHisRefundProject(HisRefundProject hisRefundProject);

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisRefundProject>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/8/17
     * @Time 16:48
     **/
    PageBean<HisRefundProject> lists(PageBean<HisRefundProject> pageBean);

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisRefundProject>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/8/17
     * @Time 19:00
     **/
    PageBean<HisRefundProject> pricelists(PageBean<HisRefundProject> pageBean);

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [hisRefundProjectInfo]
     * @Author XJP
     * @Date 2019/8/19
     * @Time 14:04
     **/
    Message saveHisRefundProjectInfo(HisRefundProjectInfo hisRefundProjectInfo) throws Exception;

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRefundProject>
     * @功能说明
     * @Params [hisRefundProject]
     * @Author XJP
     * @Date 2019/8/26
     * @Time 16:02
     **/
    List<HisRefundProject> queryHisRefundProjectVoucher(HisRefundProject hisRefundProject) throws Exception;
}
