package com.ahsj.smartparkcore.services;

import com.ahsj.smartparkcore.entity.po.Region;
import core.message.Message;

import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: RegionService
 * <p>
 * Date:     2019/10/16 18:05
 *
 * @author XJP
 * @create 2019/10/16
 * @since 1.0.0
 */

public interface RegionService {
    /**
     * @return core.message.Message
     * @功能说明
     * @Params [region]
     * @Author XJP
     * @Date 2019/10/17
     * @Time 9:25
     **/
    Message addRegion(Region region) throws Exception;

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [regionList]
     * @Author XJP
     * @Date 2019/10/17
     * @Time 9:41
     **/
    Message addRegionList(List<Region> regionList) throws Exception;

    /**
     * @return java.util.List<com.ahsj.smartparkcore.entity.po.Region>
     * @功能说明
     * @Params [region]
     * @Author XJP
     * @Date 2019/10/17
     * @Time 10:37
     **/
    List<Region> queryRegion(Region region) throws Exception;

    Region queryRegionName(Long provinceId) throws Exception;
}
