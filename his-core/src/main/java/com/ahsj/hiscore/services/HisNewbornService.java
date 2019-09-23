package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisNewborn;
import core.entity.PageBean;
import core.message.Message;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisNewbornService
 * <p>
 * Date:     2019/9/10 13:13
 *
 * @author XJP
 * @create 2019/9/10
 * @since 1.0.0
 */

public interface HisNewbornService {

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisNewborn>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/9/10
     * @Time 13:31
     **/
    PageBean<HisNewborn> queryList(PageBean<HisNewborn> pageBean) throws Exception;

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [hisNewborn]
     * @Author XJP
     * @Date 2019/9/10
     * @Time 13:41
     **/
    Message insertSelective(HisNewborn hisNewborn) throws Exception;

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [hisNewborn]
     * @Author XJP
     * @Date 2019/9/10
     * @Time 13:46
     **/
    Message updateByPrimaryKeySelective(HisNewborn hisNewborn) throws Exception;

    /**
     * @return core.message.Message
     * @功能说明 通过病床编号修改婴儿的信息停用状态
     * @Params [hisNewborn]
     * @Author XJP
     * @Date 2019/9/10
     * @Time 15:17
     **/
    Message updateIsEnable(HisNewborn hisNewborn) throws Exception;

    /**
     * @return com.ahsj.hiscore.entity.HisNewborn
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/9/11
     * @Time 10:12
     **/
    HisNewborn selectByPrimaryKey(Long id) throws Exception;
}
