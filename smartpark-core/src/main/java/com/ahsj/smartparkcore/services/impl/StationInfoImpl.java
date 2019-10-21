package com.ahsj.smartparkcore.services.impl;

import com.ahsj.smartparkcore.core.CodeHelper;
import com.ahsj.smartparkcore.core.ResultModel;
import com.ahsj.smartparkcore.core.ResultStatus;
import com.ahsj.smartparkcore.dao.StationInfoMapper;
import com.ahsj.smartparkcore.entity.dto.StationInfoDTO;
import com.ahsj.smartparkcore.entity.po.EnterpriseInfo;
import com.ahsj.smartparkcore.entity.po.Region;
import com.ahsj.smartparkcore.entity.po.Site;
import com.ahsj.smartparkcore.entity.po.StationInfo;
import com.ahsj.smartparkcore.entity.vo.SiteVo;
import com.ahsj.smartparkcore.entity.vo.StationInfoVO;
import com.ahsj.smartparkcore.services.RegionService;
import com.ahsj.smartparkcore.services.StationInfoService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.events.Event;
import utils.EmptyUtil;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: StationInfoImpl
 * <p>
 * Date:     2019/10/19 17:57
 *
 * @author XJP
 * @create 2019/10/19
 * @since 1.0.0
 */

@Service
public class StationInfoImpl implements StationInfoService {

    @Autowired
    StationInfoMapper stationInfoMapper;

    @Autowired
    RegionService regionService;

    /**
     * @return core.entity.PageBean<com.ahsj.smartparkcore.entity.po.StationInfo>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/10/19
     * @Time 18:15
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<StationInfo> queryList(PageBean<StationInfo> pageBean) {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(stationInfoMapper.queryList(pageBean)));
        return pageBean;
    }

    @Override
    @Transactional(readOnly = true)
    public StationInfoVO selectById(Long id) throws Exception {
        StationInfo stationInfo = stationInfoMapper.selectByPrimaryKey(id);
        StationInfoVO stationInfoVO = new StationInfoVO();
        BeanUtils.copyProperties(stationInfo, stationInfoVO);
        String substring = StringUtils.substring(stationInfo.getLocation(), 0, 3);
        String substring1 = StringUtils.substring(stationInfo.getLocation(), 3, 6);
        String substring2 = StringUtils.substring(stationInfo.getLocation(), 6, 9);
        String addressName = StringUtils.substring(stationInfo.getLocation(), 9, stationInfo.getLocation().length());
        Region region = regionService.queryRegionName(substring);
        Region region1 = regionService.queryRegionName(substring1);
        Region region2 = regionService.queryRegionName(substring2);
        stationInfoVO.setProvinceId(region.getId());
        stationInfoVO.setCityId(region1.getId());
        stationInfoVO.setAreaId(region2.getId());
        stationInfoVO.setLocation(addressName);
        return stationInfoVO;
    }

    @Override
    @Transactional(readOnly = false)
    public Message addStationinfo(StationInfoDTO infoDTO, MultipartFile[] file, String relateKet, String relatePage) throws Exception {
        StationInfo stationInfo = new StationInfo();
        BeanUtils.copyProperties(infoDTO, stationInfo);
        Region region1 = regionService.selectById(infoDTO.getProvinceId());
        Region region2 = regionService.selectById(infoDTO.getCityId());
        Region region3 = regionService.selectById(infoDTO.getAreaId());
        stationInfo.setLocation(region1.getName() + region2.getName() + region3.getName() + stationInfo.getLocation());
        stationInfo.setBookType(3);
        stationInfo.setIsEnable((short) 2);
        stationInfo.setIsLease(2);
        stationInfo.setIsVerify(2);
        StationInfo stationInfo1 = stationInfoMapper.selectByTitle(stationInfo.getTitle());
        if (!EmptyUtil.Companion.isNullOrEmpty(stationInfo1)) {
            return MessageUtil.createMessage(false, "工位信息新增失败 ！ 该工位已存在 ！！");
        } else {
            stationInfoMapper.insert(stationInfo);
            return MessageUtil.createMessage(true, "工位信息新增成功 ！！！");
        }

    }

    @Override
    @Transactional(readOnly = false)
    public Message updateStationinfo(StationInfoDTO stationInfoDTO, MultipartFile[] file, String relateKet, String relatePage) throws Exception {
        StationInfo stationInfo = new StationInfo();
        BeanUtils.copyProperties(stationInfoDTO, stationInfo);
        Region region1 = regionService.selectById(stationInfoDTO.getProvinceId());
        Region region2 = regionService.selectById(stationInfoDTO.getCityId());
        Region region3 = regionService.selectById(stationInfoDTO.getAreaId());
        stationInfo.setLocation(region1.getName() + region2.getName() + region3.getName() + stationInfo.getLocation());
        StationInfo stationInfo1 = stationInfoMapper.selectByTitle(stationInfo.getTitle());
        if (EmptyUtil.Companion.isNullOrEmpty(stationInfo1)) {
            return MessageUtil.createMessage(false, "工位信息修改失败 ！ 该工位已存在 ！！");
        } else {
            if (stationInfo1.getId().longValue() == stationInfo.getId().longValue()){
             stationInfoMapper.updateByPrimaryKeySelective(stationInfo);
                return MessageUtil.createMessage(true, "工位信息修改成功 ！！！");
            }else {
                return MessageUtil.createMessage(false, "工位信息修改失败 ！ 该工位已存在 ！！");
            }

        }

    }
}
