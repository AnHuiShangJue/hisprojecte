package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisRescue;
import core.entity.PageBean;
import core.message.Message;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisRescueService
 * <p>
 * Date:     2019/9/15 10:51
 *
 * @author XJP
 * @create 2019/9/15
 * @since 1.0.0
 */

public interface HisRescueService {


    /**
     * @return com.ahsj.hiscore.entity.HisRescue
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/9/15
     * @Time 17:17
     **/
    HisRescue selectByPrimaryKey(Long id) throws Exception;

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisRescue>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/9/15
     * @Time 10:59
     **/
    PageBean<HisRescue> queryList(PageBean<HisRescue> pageBean) throws Exception;

    /**
     * @return core.message.Message
     * @功能说明 增添抢救人员信息
     * @Params [hisRescue]
     * @Author XJP
     * @Date 2019/9/15
     * @Time 11:52
     **/
    Message addOrUpdateHisRescue(HisRescue hisRescue) throws Exception;

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [hisRescue]
     * @Author XJP
     * @Date 2019/9/15
     * @Time 14:25
     **/
    Message updateHisRescue(HisRescue hisRescue) throws Exception;

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [hisRescue]
     * @Author XJP
     * @Date 2019/9/16
     * @Time 9:34
     **/
    Message signature(HisRescue hisRescue,Long userId) throws Exception;

    /**
     *@Description
     *@Params [targetId]
     *@return com.ahsj.hiscore.entity.HisRescue
     *@Author zhushixiang
     *@Date 2019-09-16
     *@Time 11:08
    **/
    HisRescue selectById(Long targetId)throws Exception;
}
