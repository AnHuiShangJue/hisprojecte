package com.ahsj.hiscore.services.impl;


import com.ahsj.hiscore.common.dto.HisTypographyUserInfoRep;
import com.ahsj.hiscore.dao.HisTypographyUserInfoMapper;
import com.ahsj.hiscore.entity.HisTypographyUserInfo;
import com.ahsj.hiscore.entity.UserInfo;
import com.ahsj.hiscore.entity.sys.Organization;
import com.ahsj.hiscore.fallback.ITranslateServiceFallnack;
import com.ahsj.hiscore.feign.IOrganizationService;
import com.ahsj.hiscore.feign.IUserService;
import com.ahsj.hiscore.services.HisNurseService;
import com.ahsj.hiscore.services.HisTypographyUserInfoService;
import core.message.Message;
import core.message.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈HisTypographyUserInfoServiceImpl〉
 *
 * @author anhuishangjue
 * @create 2019/7/4
 * @since 1.0.0
 */
@Service
public class HisTypographyUserInfoServiceImpl implements HisTypographyUserInfoService {

    Logger log = LoggerFactory.getLogger(HisTypographyUserInfoServiceImpl.class.getSimpleName());


    @Autowired
    HisTypographyUserInfoMapper hisTypographyUserInfoMapper;

    @Autowired
    IOrganizationService iOrganizationService;

    @Autowired
    IUserService iUserService;

    @Autowired
    HisNurseService hisNurseService;

    @Autowired
    AmqpTemplate amqpTemplat;

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [pId]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:43
     **/
    @Override
    @Transactional(readOnly = false)
    public Message deleteByPrimaryKey(Integer pId) throws Exception {
        hisTypographyUserInfoMapper.deleteByPrimaryKey(pId);
        return MessageUtil.createMessage(false, "删除成功!");
    }

    /**
     * @return com.ahsj.hiscore.entity.HisTypographyUserInfo
     * @功能说明
     * @Params [pId]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:43
     **/
    @Override
    @Transactional(readOnly = true)
    public HisTypographyUserInfo selectByPrimaryKey(Integer pId) throws Exception {
        HisTypographyUserInfo hisTypographyUserInfo = hisTypographyUserInfoMapper.selectByPrimaryKey(pId);
        return hisTypographyUserInfo;
    }

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [hisTypographyUserInfo]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:43
     **/
    @Override
    @Transactional(readOnly = false)
    public Message updateByPrimaryKeySelective(HisTypographyUserInfo hisTypographyUserInfo) throws Exception {
        hisTypographyUserInfoMapper.updateByPrimaryKeySelective(hisTypographyUserInfo);
        return MessageUtil.createMessage(false, "修改成功!");
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisTypographyUserInfo>
     * @功能说明
     * @Params [hisTypographyUserInfo]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:43
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisTypographyUserInfo> getHisTypographyUserInfoByStartDateAndEndDate(HisTypographyUserInfo hisTypographyUserInfo) throws Exception {
        List<HisTypographyUserInfo> infoList = hisTypographyUserInfoMapper.getHisTypographyUserInfoByStartDateAndEndDate(hisTypographyUserInfo);
        if (!CollectionUtils.isEmpty(infoList)) {
            return infoList;
        } else {
            return null;
        }
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisTypographyUserInfo>
     * @功能说明
     * @Params [startDate]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:43
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisTypographyUserInfo> getHisTypographyUserInfoByNowDate(String startDate) throws Exception {
        List<HisTypographyUserInfo> userInfoList = hisTypographyUserInfoMapper.getHisTypographyUserInfoByNowDate(startDate);
        if (!CollectionUtils.isEmpty(userInfoList)) {
            return userInfoList;
        } else {
            return null;
        }
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisTypographyUserInfo>
     * @功能说明
     * @Params [endDate]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:43
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisTypographyUserInfo> getHisTypographyUserInfoByHistoryDate(String endDate) throws Exception {
        List<HisTypographyUserInfo> userInfoList = hisTypographyUserInfoMapper.getHisTypographyUserInfoByHistoryDate(endDate);
        if (!CollectionUtils.isEmpty(userInfoList)) {
            return userInfoList;
        } else {
            return null;
        }
    }

    /**
     * @return java.lang.Integer
     * @功能说明
     * @Params [ids]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:43
     **/
    @Override
    @Transactional(readOnly = false)
    public Integer deletebatchHisTypographyUserInfo(Integer[] ids) throws Exception {
        Integer integer = hisTypographyUserInfoMapper.deletebatchHisTypographyUserInfo(ids);
        if (integer != 0) {
            return integer;
        }
        return null;
    }


    /**
     * @return core.message.Message
     * @功能说明 批量新增值班人信息
     * @Params [hisTypographyUserInfoRep, deptId]
     * @Author XJP
     * @Date 2019/7/25
     * @Time 9:54
     **/
    @Override
    @Transactional(readOnly = false)
    public Message saveHisTypographyUserInfo(List<HisTypographyUserInfoRep> hisTypographyUserInfoRep, Long deptId) throws Exception {
        if (CollectionUtils.isEmpty(hisTypographyUserInfoRep) || EmptyUtil.Companion.isNullOrEmpty(deptId)) {
            return MessageUtil.createMessage(false, "参数传出失败 ！ 请查看对应参数 ！！！");
        } else {
            Organization organization = iOrganizationService.getOrganizationByIds(deptId);
            List<HisTypographyUserInfo> list = new ArrayList<>();
            for (HisTypographyUserInfoRep rep : hisTypographyUserInfoRep) {

                HisTypographyUserInfo hisTypographyUserInfo = new HisTypographyUserInfo();
                Boolean aBoolean = getDate(rep.getEndDate(), rep.getStartDate());
                if (!aBoolean) {
                    return MessageUtil.createMessage(false, "值班时间不能大于12小时 ！ 请重新设置  ！！！");
                } else {
                    UserInfo userInfo = iUserService.getUserLoginId(rep.getUserId());
                    hisTypographyUserInfo.setStartDate(rep.getStartDate());
                    hisTypographyUserInfo.setEndDate(rep.getEndDate());
                    hisTypographyUserInfo.setPhone(userInfo.getTelPhone());
                    hisTypographyUserInfo.setDepartmentName(organization.getName());
                    String userId = userInfo.getUserId();
                    hisTypographyUserInfo.setUserLogin(userId);
                    String sex = userInfo.getSex();
                    hisTypographyUserInfo.setSexName(getSex(sex) ? "男" : "女");
                    hisTypographyUserInfo.setTypographyUserName(rep.getTypographyUserName());
                    list.add(hisTypographyUserInfo);
                }
            }
            hisTypographyUserInfoMapper.insertList(list);
            return MessageUtil.createMessage(true, " 批量新增值班人员成功 ！！！");
        }
    }


    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisTypographyUserInfo>
     * @功能说明 查看某部门 规定某一天的值班人员信息
     * @Params [hisTypographyUserInfo]
     * @Author XJP
     * @Date 2019/7/25
     * @Time 9:54
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisTypographyUserInfo> selectHisTypographyUserInfoDateAll(HisTypographyUserInfo hisTypographyUserInfo) throws Exception {
        Date startDate = hisTypographyUserInfo.getStartDate();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = format.format(startDate);
        Date parse = format.parse(s);
        hisTypographyUserInfo.setStartDate(parse);
        hisTypographyUserInfo.setEndDate(parse);
        List<HisTypographyUserInfo> list = hisTypographyUserInfoMapper.queryHisTypographyUserInfoList(hisTypographyUserInfo);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        } else {
            return list;
        }
    }


    /**
     * @return core.message.Message
     * @Description * 导入 excel
     * @Params [typographyUserInfo]
     * @Author XJP
     * @Date 2019/7/22
     * @Time 19:20
     **/
    @Override
    @Transactional(readOnly = false)
    public Message saveHisTypographyUserInfos(List<HisTypographyUserInfo> typographyUserInfo) throws Exception {
        if (CollectionUtils.isEmpty(typographyUserInfo)) {
            return MessageUtil.createMessage(false, " 批量导入值班人员失败 ， 数据不能为空  ！！！");
        } else {
            for (HisTypographyUserInfo rep : typographyUserInfo) {
                rep.setId(null);
                Boolean aBoolean = getDate(rep.getEndDate(), rep.getStartDate());
                if (!aBoolean) {
                    return MessageUtil.createMessage(false, "值班时间不能大于12小时 ！ 请重新设置  ！！！");
                }
            }
            hisTypographyUserInfoMapper.insertList(typographyUserInfo);
            return MessageUtil.createMessage(true, " 导入成功 ！！！");
        }
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisTypographyUserInfo>
     * @功能说明 查看某部门 时间范围的值班数据 ——————》 废弃
     * @Params [typographyUserInfoList, deptId]
     * @Author XJP
     * @Date 2019/7/25
     * @Time 9:52
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisTypographyUserInfo> queryHisTypographyUserInfoList(List<HisTypographyUserInfo> typographyUserInfoList, Long deptId) throws Exception {
        if (CollectionUtils.isEmpty(typographyUserInfoList) || EmptyUtil.Companion.isNullOrEmpty(deptId)) {
            return null;
        } else {
            List<HisTypographyUserInfo> list = new ArrayList<>();
            Organization organization = iOrganizationService.getOrganizationByIds(deptId);
            for (HisTypographyUserInfo info : typographyUserInfoList) {
                HisTypographyUserInfo typographyUserInfo = new HisTypographyUserInfo();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date startDate = info.getStartDate();
                String s = format.format(startDate);
                Date parse = format.parse(s);
                typographyUserInfo.setStartDate(parse);
                typographyUserInfo.setEndDate(parse);
                typographyUserInfo.setDepartmentName(organization.getName());
                List<HisTypographyUserInfo> queryHisTypographyUserInfoList = hisTypographyUserInfoMapper.queryHisTypographyUserInfoList(typographyUserInfo);
                if (CollectionUtils.isEmpty(queryHisTypographyUserInfoList)) {
                    continue;
                } else {
                    for (HisTypographyUserInfo hisTypographyUserInfo : queryHisTypographyUserInfoList) {
                        list.add(hisTypographyUserInfo);
                    }
                }
            }
            return list;
        }

    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisTypographyUserInfo>
     * @功能说明 查看某部门 时间范围的值班数据
     * @Params [typographyUserInfoListdate, deptId]
     * @Author XJP
     * @Date 2019/8/1
     * @Time 15:40
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisTypographyUserInfo> getTypographyUserInfoListdate(String[] typographyUserInfoListdate, Long deptId) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(typographyUserInfoListdate) || EmptyUtil.Companion.isNullOrEmpty(deptId)) {
            return null;
        } else {
            List<HisTypographyUserInfo> list = new ArrayList<>();
            Organization organization = iOrganizationService.getOrganizationByIds(deptId);
            for (String info : typographyUserInfoListdate) {
                HisTypographyUserInfo typographyUserInfo = new HisTypographyUserInfo();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date parse = format.parse(info);
                typographyUserInfo.setStartDate(parse);
                typographyUserInfo.setEndDate(parse);
                typographyUserInfo.setDepartmentName(organization.getName());
                List<HisTypographyUserInfo> queryHisTypographyUserInfoList = hisTypographyUserInfoMapper.queryHisTypographyUserInfoList(typographyUserInfo);
                if (CollectionUtils.isEmpty(queryHisTypographyUserInfoList)) {
                    continue;
                } else {
                    for (HisTypographyUserInfo hisTypographyUserInfo : queryHisTypographyUserInfoList) {
                        list.add(hisTypographyUserInfo);
                    }
                }
            }
            return list;
        }
    }

    /**
     * @return java.lang.Boolean
     * @功能说明
     * @Params [endDate, startDate]
     * @Author XJP
     * @Date 2019/8/1
     * @Time 15:40
     **/
    public Boolean getDate(Date endDate, Date startDate) {
        long dff = endDate.getTime() - startDate.getTime();
        long day = dff / (1000 * 60 * 60 * 24);
        long hour = (dff - day * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (dff - day * (1000 * 60 * 60 * 24) - hour * (1000 * 60 * 60)) / (1000 * 60);
        if (hour > 12) {
            return false;
        } else if (hour == 12 && minutes > 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 封装发送到消息队列的方法
     *
     * @param id
     * @param type
     */
    private void sendMessage(Long id, String type) {
        try {
            amqpTemplat.convertAndSend("com.ahsj." + type, id);
        } catch (Exception e) {
            log.error("{}消息发送异常，{}", type, id, e);
        }
    }

    /**
     * @return java.lang.Boolean
     * @功能说明
     * @Params [sex]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:43
     **/
    public Boolean getSex(String sex) {
        if (StringUtils.equals("1", sex)) {
            return true;
        } else {
            return false;
        }
    }

}