package com.ahsj.smartparkcore.services.impl;

import com.ahsj.smartparkcore.dao.RegionMapper;
import com.ahsj.smartparkcore.entity.po.Region;
import com.ahsj.smartparkcore.services.RegionService;
import com.google.cloud.GcpLaunchStage;
import core.message.Message;
import core.message.MessageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: RegionServiceImpl
 * <p>
 * Date:     2019/10/16 18:05
 *
 * @author XJP
 * @create 2019/10/16
 * @since 1.0.0
 */

@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    RegionMapper regionMapper;

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [region]
     * @Author XJP
     * @Date 2019/10/17
     * @Time 9:25
     **/
    @Override
    @Transactional(readOnly = false)
    public Message addRegion(Region region) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(region)) {
            return MessageUtil.createMessage(false, "地址添加失败 ！！！");
        } else {
            regionMapper.insert(region);
            return MessageUtil.createMessage(true, "地址添加成功 ！！！");
        }
    }

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [regionList]
     * @Author XJP
     * @Date 2019/10/17
     * @Time 9:42
     **/
    @Override
    @Transactional(readOnly = false)
    public Message addRegionList(List<Region> regionList) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(regionList)) {
            return MessageUtil.createMessage(false, "地址添加失败 ！！！");
        } else {
            regionMapper.addRegionList(regionList);
            return MessageUtil.createMessage(true, "地址添加成功 ！！！");
        }
    }

    /**
     * @return java.util.List<com.ahsj.smartparkcore.entity.po.Region>
     * @功能说明
     * @Params [region]
     * @Author XJP
     * @Date 2019/10/17
     * @Time 10:41
     **/
    @Override
    @Transactional(readOnly = true)
    public List<Region> queryRegion(Region region) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(region)) {
            return new ArrayList<>();
        } else {
            List<Region> regionList = regionMapper.queryRegion(region);
            if (EmptyUtil.Companion.isNullOrEmpty(regionList)) {
                return new ArrayList<>();
            } else {
                return regionList;
            }
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Region selectById(Long id) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(id)) {
            return new Region();
        } else {
            Region region = regionMapper.selectByPrimaryKey(id);
            if (EmptyUtil.Companion.isNullOrEmpty(region)) {
                return new Region();
            } else {
                return region;
            }
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Region queryRegionName(String name) {
        if (EmptyUtil.Companion.isNullOrEmpty(name)) {
            return new Region();
        } else {
            Region region = new Region();
            region.setName(name);
             region = regionMapper.queryRegionName(region);
            if (EmptyUtil.Companion.isNullOrEmpty(region)) {
                return new Region();
            } else {
                return region;
            }
        }
    }
}
