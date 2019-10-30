package com.ahsj.smartparkcore.services.impl;

import com.ahsj.smartparkcore.common.Constants;
import com.ahsj.smartparkcore.core.CodeHelper;
import com.ahsj.smartparkcore.dao.StationInfoMapper;
import com.ahsj.smartparkcore.entity.dto.StationInfoDTO;
import com.ahsj.smartparkcore.entity.po.AccessInfo;
import com.ahsj.smartparkcore.entity.po.ConferenceRoomInfo;
import com.ahsj.smartparkcore.entity.po.Region;
import com.ahsj.smartparkcore.entity.po.StationInfo;
import com.ahsj.smartparkcore.entity.sys.SysAttachmentInfo;
import com.ahsj.smartparkcore.entity.vo.SiteVo;
import com.ahsj.smartparkcore.entity.vo.StationInfoVO;
import com.ahsj.smartparkcore.services.RegionService;
import com.ahsj.smartparkcore.services.StationInfoService;
import com.ahsj.smartparkcore.services.SysAttachmentInfoService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import utils.EmptyUtil;

import java.util.ArrayList;
import java.util.List;

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
public class StationInfoServiceImpl implements StationInfoService {

    private Logger log = LoggerFactory.getLogger(StationInfoServiceImpl.class.getSimpleName());

    @Autowired
    StationInfoMapper stationInfoMapper;

    @Autowired
    RegionService regionService;

    @Autowired
    SysAttachmentInfoService sysAttachmentInfoService;

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
    public PageBean<StationInfoDTO> queryList(PageBean<StationInfoDTO> pageBean) {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(stationInfoMapper.queryList(pageBean)));
        return pageBean;
    }

    /**
     * @return com.ahsj.smartparkcore.entity.vo.StationInfoVO
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/10/21
     * @Time 15:18
     **/
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

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [infoDTO, file, relateKet, relatePage]
     * @Author XJP
     * @Date 2019/10/21
     * @Time 15:18
     **/
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
        stationInfo.setIsEnable((short) 1);
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

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [stationInfoDTO, file, relateKet, relatePage]
     * @Author XJP
     * @Date 2019/10/21
     * @Time 15:17
     **/
    @Override
    @Transactional(readOnly = false)
    public Message updateStationinfo(StationInfoDTO stationInfoDTO, MultipartFile[] file, String relateKet, String relatePage) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(stationInfoDTO.getProvinceId()) || EmptyUtil.Companion.isNullOrEmpty(stationInfoDTO.getCityId())
                || EmptyUtil.Companion.isNullOrEmpty(stationInfoDTO.getAreaId()) || EmptyUtil.Companion.isNullOrEmpty(stationInfoDTO.getLocation())) {
            return MessageUtil.createMessage(false, "工位信息新增失败 ！ 工位地址不能为空！！");
        }
        if (EmptyUtil.Companion.isNullOrEmpty(stationInfoDTO.getTitle())) {
            return MessageUtil.createMessage(false, "工位信息修改失败 ！！！");
        }
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
            if (stationInfo1.getId().longValue() == stationInfo.getId().longValue()) {
                stationInfoMapper.updateByPrimaryKeySelective(stationInfo);
                return MessageUtil.createMessage(true, "工位信息修改成功 ！！！");
            } else {
                return MessageUtil.createMessage(false, "工位信息修改失败 ！ 该工位已存在 ！！");
            }

        }

    }

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [ids]
     * @Author XJP
     * @Date 2019/10/21
     * @Time 15:17
     **/
    @Override
    @Transactional(readOnly = false)
    public Message updateSetDisable(Long[] ids) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(ids)) {
            log.info("修改工位状态失败!!! 参数不能为空 ！！");
            return MessageUtil.createMessage(false, "修改工位状态失败。");
        } else {
            for (Long id : ids) {
                StationInfo stationInfo = stationInfoMapper.selectByPrimaryKey(id);
                if (EmptyUtil.Companion.isNullOrEmpty(stationInfo)) {
                    log.info("查询失败 ！！！  无对应数据");
                    return MessageUtil.createMessage(false, "修改工位状态失败。");
                }
                if (stationInfo.getIsEnable() == 1) {
                    stationInfo.setIsEnable((short) 2);
                    stationInfoMapper.updateByPrimaryKeySelective(stationInfo);
                } else {
                    stationInfo.setIsEnable((short) 1);
                    stationInfoMapper.updateByPrimaryKeySelective(stationInfo);
                }
            }
        }
        return MessageUtil.createMessage(true, "修改工位状态成功。");
    }

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/10/23
     * @Time 10:10
     **/
    @Override
    @Transactional(readOnly = false)
    public Message reviewSuccess(Long id) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(id)) {
            return MessageUtil.createMessage(false, "工位信息审核失败 ！！！");
        }
        StationInfo stationInfo = stationInfoMapper.selectByPrimaryKey(id);
        if (stationInfo.getIsVerify().equals(Constants.ONE)) {
            return MessageUtil.createMessage(false, "该工位信息审核已经成功！ 无法继续审核 ！！！！！");
        } else {
            stationInfo.setIsVerify(Constants.ONE);
            stationInfoMapper.updateByPrimaryKey(stationInfo);
            return MessageUtil.createMessage(true, "工位申请信息审核成功 ！该工位申请信息验证通过 ！！");
        }
    }

    /**
     * @return java.util.List<com.ahsj.smartparkcore.entity.po.StationInfo>
     * @功能说明
     * @Params [ids]
     * @Author XJP
     * @Date 2019/10/29
     * @Time 15:27
     **/
    @Override
    @Transactional(readOnly = true)
    public List<StationInfo> selectByIds(List<Long> ids) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(ids)) {
            return new ArrayList<>();
        } else {
            List<StationInfo> stationInfos = stationInfoMapper.selectByIds(ids);
            if (EmptyUtil.Companion.isNullOrEmpty(stationInfos)) {
                return new ArrayList<>();
            } else {
                return stationInfos;
            }
        }
    }

    /**
     * @return java.util.List<com.ahsj.smartparkcore.entity.po.StationInfo>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/10/30
     * @Time 13:29
     **/
    @Override
    @Transactional(readOnly = true)
    public List<StationInfoVO> listForView() throws Exception {
        List<StationInfoVO> stationInfoVOS = stationInfoMapper.listForView();
        for (StationInfoVO stationInfoVO : stationInfoVOS) {
            SysAttachmentInfo sysAttachmentInfo = new SysAttachmentInfo();
            sysAttachmentInfo.setRelateId(stationInfoVO.getId());
            sysAttachmentInfo.setRelateKey(Constants.STATIONINFO);
            sysAttachmentInfo.setRelatePage(Constants.LIST);
            List<SysAttachmentInfo> sysAttachmentInfos = sysAttachmentInfoService.querySysAttachmentInfo(sysAttachmentInfo);
            if (EmptyUtil.Companion.isNullOrEmpty(sysAttachmentInfos)) {
                continue;
            }
            SysAttachmentInfo sysAttachmentInfo1 = sysAttachmentInfos.get(0);
            String replace = Constants.LOCALHOST + sysAttachmentInfo1.getLocation().replace(Constants.STATIC, Constants.SMARTPARKCORE);
            stationInfoVO.setFilePath(replace);
        }
        return stationInfoVOS;
    }

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/10/23
     * @Time 10:10
     **/
    @Override
    @Transactional(readOnly = false)
    public Message reviewFail(Long id) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(id)) {
            return MessageUtil.createMessage(false, "工位申请信息审核失败 ！！！");
        }
        StationInfo stationInfo = stationInfoMapper.selectByPrimaryKey(id);
        if (stationInfo.getIsVerify().equals(Constants.ONE)) {
            return MessageUtil.createMessage(false, "该工位申请信息审核已经成功！ 无法继续审核 ！！！！！");
        } else {
            stationInfo.setIsVerify(Constants.THREE);
            stationInfoMapper.updateByPrimaryKey(stationInfo);
            return MessageUtil.createMessage(true, "工位申请信息审核成功 ！该工位申请信息验证通过 ！！");
        }
    }
}
