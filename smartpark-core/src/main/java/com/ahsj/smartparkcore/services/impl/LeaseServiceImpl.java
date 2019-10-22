package com.ahsj.smartparkcore.services.impl;

import com.ahsj.smartparkcore.common.Constants;
import com.ahsj.smartparkcore.core.CodeHelper;
import com.ahsj.smartparkcore.dao.ConferenceRoomInfoMapper;
import com.ahsj.smartparkcore.dao.LeaseMapper;
import com.ahsj.smartparkcore.dao.SiteMapper;
import com.ahsj.smartparkcore.dao.StationInfoMapper;
import com.ahsj.smartparkcore.entity.dto.LeaseDTO;
import com.ahsj.smartparkcore.entity.po.ConferenceRoomInfo;
import com.ahsj.smartparkcore.entity.po.Lease;
import com.ahsj.smartparkcore.entity.po.Site;
import com.ahsj.smartparkcore.entity.po.StationInfo;
import com.ahsj.smartparkcore.entity.vo.LeaseVO;
import com.ahsj.smartparkcore.services.LeaseService;
import core.entity.PageBean;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.plugin2.message.Message;
import utils.EmptyUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: LeaseServiceImpl
 * <p>
 * Date:     2019/10/19 15:06
 *
 * @author XJP
 * @create 2019/10/19
 * @since 1.0.0
 */

@Service
public class LeaseServiceImpl implements LeaseService {

    @Autowired
    LeaseMapper leaseMapper;

    @Autowired
    SiteMapper siteMapper;

    @Autowired
    ConferenceRoomInfoMapper conferenceRoomInfoMapper;

    @Autowired
    StationInfoMapper stationInfoMapper;

    @Override
    @Transactional(readOnly = true)
    public PageBean<LeaseVO> queryList(PageBean<LeaseVO> pageBean) {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(leaseMapper.queryList(pageBean)));
        return pageBean;
    }

    /**
     * @return java.util.List<com.ahsj.smartparkcore.entity.vo.LeaseVO>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/10/19
     * @Time 17:39
     **/
    @Override
    @Transactional(readOnly = true)
    public List<LeaseVO> List() throws Exception {
        List<LeaseVO> list = leaseMapper.List();
        if (EmptyUtil.Companion.isNullOrEmpty(list)) {
            return new ArrayList<>();
        } else {
            for (LeaseVO leaseVO : list) {
                if (leaseVO.getBookType() == 1) {
                    leaseVO.setBookTypeName(Constants.ConferenceRoomInfo);
                }
                if (leaseVO.getBookType() == 2) {
                    leaseVO.setBookTypeName(Constants.site);
                }
                if (leaseVO.getBookType() == 3) {
                    leaseVO.setBookTypeName(Constants.StationInfo);
                }
                if (leaseVO.getIsLease() == 1) {
                    leaseVO.setIsLeaseName(Constants.IS_LEASE);
                }
                if (leaseVO.getIsLease() == 2) {
                    leaseVO.setIsLeaseName(Constants.NO_LEASE);
                }
            }
            return list;
        }
    }

    /**
     * @return java.util.List<com.ahsj.smartparkcore.entity.vo.LeaseVO>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/10/19
     * @Time 17:38
     **/
    @Override
    @Transactional(readOnly = true)
    public List<LeaseVO> isLeaseList() throws Exception {
        List<LeaseVO> list = leaseMapper.isLeaseList();
        if (EmptyUtil.Companion.isNullOrEmpty(list)) {
            return new ArrayList<>();
        } else {
            for (LeaseVO leaseVO : list) {
                if (leaseVO.getBookType() == 1) {
                    leaseVO.setBookTypeName(Constants.ConferenceRoomInfo);
                }
                if (leaseVO.getBookType() == 2) {
                    leaseVO.setBookTypeName(Constants.site);
                }
                if (leaseVO.getBookType() == 3) {
                    leaseVO.setBookTypeName(Constants.StationInfo);
                }
                if (leaseVO.getIsLease() == 1) {
                    leaseVO.setIsLeaseName(Constants.IS_LEASE);
                }
                if (leaseVO.getIsLease() == 2) {
                    leaseVO.setIsLeaseName(Constants.NO_LEASE);
                }
            }
            return list;
        }
    }

    /**
     * @return java.util.List<com.ahsj.smartparkcore.entity.vo.LeaseVO>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/10/19
     * @Time 17:38
     **/
    @Override
    @Transactional(readOnly = true)
    public List<LeaseVO> noLeaseList() throws Exception {
        List<LeaseVO> list = leaseMapper.noLeaseList();
        if (EmptyUtil.Companion.isNullOrEmpty(list)) {
            return new ArrayList<>();
        } else {
            for (LeaseVO leaseVO : list) {
                if (leaseVO.getBookType() == 1) {
                    leaseVO.setBookTypeName(Constants.ConferenceRoomInfo);
                }
                if (leaseVO.getBookType() == 2) {
                    leaseVO.setBookTypeName(Constants.site);
                }
                if (leaseVO.getBookType() == 3) {
                    leaseVO.setBookTypeName(Constants.StationInfo);
                }
                if (leaseVO.getIsLease() == 1) {
                    leaseVO.setIsLeaseName(Constants.IS_LEASE);
                }
                if (leaseVO.getIsLease() == 2) {
                    leaseVO.setIsLeaseName(Constants.NO_LEASE);
                }
            }
            return list;
        }
    }

    /**
     * @Description 根据 targetType  targetId 查信息
     * @Params: [lease]
     * @Author: dingli
     * @Return: int
     * @Date 2019/10/21
     * @Time 14:54
     **/
    @Override
    @Transactional(readOnly = false)
    public LeaseDTO selectLease(LeaseVO lease) throws Exception {
        if (lease.getTargetType() == 1) {//会议室
            ConferenceRoomInfo conferenceRoomInfo = conferenceRoomInfoMapper.selectByPrimaryKey(lease.getTargetId());
            if (conferenceRoomInfo.getDescription() == null) {
                conferenceRoomInfo.setDescription("");
            }
            if (conferenceRoomInfo.getPhoneNumber() == null) {
                conferenceRoomInfo.setPhoneNumber("");
            }
            lease.setArea(conferenceRoomInfo.getArea());
            lease.setCapacity(conferenceRoomInfo.getCapacity());
            lease.setDescription(conferenceRoomInfo.getDescription());
            lease.setLocation(conferenceRoomInfo.getLocation());
            lease.setName(conferenceRoomInfo.getConferenceName());
            lease.setPhoneNumber(conferenceRoomInfo.getPhoneNumber());
            lease.setContactName(conferenceRoomInfo.getName());
            lease.setPrice(conferenceRoomInfo.getPrice());
            DozerBeanMapper mapper = new DozerBeanMapper(); //对象转换组件
            LeaseDTO LeaseDTO = mapper.map(lease, LeaseDTO.class);
            return LeaseDTO;
        }
        if (lease.getTargetType() == 2) {//场地
            Site site = siteMapper.selectByPrimaryKey(lease.getTargetId());
            if (site.getDescription() == null) {
                site.setDescription("");
            }
            if (site.getPhoneNumber() == null) {
                site.setPhoneNumber("");
            }
            lease.setArea(site.getArea());
            lease.setCapacity(site.getCapacity());
            lease.setDescription(site.getDescription());
            lease.setLocation(site.getLocation());
            lease.setName(site.getSiteName());
            lease.setPhoneNumber(site.getPhoneNumber());
            lease.setContactName(site.getName());
            lease.setPrice(site.getPrice());
            DozerBeanMapper mapper = new DozerBeanMapper(); //对象转换组件
            LeaseDTO LeaseDTO = mapper.map(lease, LeaseDTO.class);
            return LeaseDTO;
        }
        if (lease.getTargetType() == 3) {//工位
            StationInfo stationInfo = stationInfoMapper.selectByPrimaryKey(lease.getTargetId());
            if (stationInfo.getDescription() == null) {
                stationInfo.setDescription("");
            }
            if (stationInfo.getPhoneNumber() == null) {
                stationInfo.setPhoneNumber("");
            }
            lease.setArea(stationInfo.getArea());
            lease.setCapacity(stationInfo.getCapacity());
            lease.setDescription(stationInfo.getDescription());
            lease.setLocation(stationInfo.getLocation());
            lease.setName(stationInfo.getTitle());
            lease.setPhoneNumber(stationInfo.getPhoneNumber());
            lease.setContactName(stationInfo.getName());
            lease.setPrice(stationInfo.getPrice());
            DozerBeanMapper mapper = new DozerBeanMapper(); //对象转换组件
            LeaseDTO LeaseDTO = mapper.map(lease, LeaseDTO.class);
            return LeaseDTO;
        }
        return new LeaseDTO();
    }
}
