package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisRecordProjectMapper;
import com.ahsj.hiscore.dao.HisRefundProjectInfoMapper;
import com.ahsj.hiscore.dao.HisRefundProjectMapper;
import com.ahsj.hiscore.dao.HisTollDetailsMapper;
import com.ahsj.hiscore.entity.*;
import com.ahsj.hiscore.services.HisProjectService;
import com.ahsj.hiscore.services.HisRecordProjectService;
import com.ahsj.hiscore.services.HisRefundProjectDetailService;
import com.ahsj.hiscore.services.HisRefundProjectService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisRefundProjectServiceImpl
 * <p>
 * Date:     2019/8/16 18:41
 *
 * @author XJP
 * @create 2019/8/16
 * @since 1.0.0
 */

@Service
public class HisRefundProjectServiceImpl implements HisRefundProjectService {

    private Logger log = LoggerFactory.getLogger(HisRefundProjectServiceImpl.class.getSimpleName());


    @Autowired
    HisRefundProjectMapper hisRefundProjectMapper;

    @Autowired
    HisRecordProjectService hisRecordProjectService;

    @Autowired
    HisRefundProjectInfoMapper hisRefundProjectInfoMapper;

    @Autowired
    HisTollDetailsMapper hisTollDetailsMapper;

    @Autowired
    HisRecordProjectMapper hisRecordProjectMapper;

    @Autowired
    HisProjectService hisProjectService;

    @Autowired
    HisRefundProjectDetailService hisRefundProjectDetailService;

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [hisRecordProject, nums, ids]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 18:58
     **/
    @Override
    @Transactional(readOnly = false)
    public Message saveForAppEdit(HisRefundProject hisRefundProject, Integer[] nums, Long[] ids, String userName) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisRefundProject.getPatientName()) || EmptyUtil.Companion.isNullOrEmpty(nums) || EmptyUtil.Companion.isNullOrEmpty(ids) || EmptyUtil.Companion.isNullOrEmpty(hisRefundProject.getRemarks())) {
            log.info("提交申请失败! 申请人不能为空 或 申请退项目数量不能为空 或  原因不能为空");
            return MessageUtil.createMessage(false, "提交申请失败! 申请人不能为空 或 申请退项目数量不能为空 或  原因不能为空");
        } else if (EmptyUtil.Companion.isNullOrEmpty(hisRefundProject.getRecordNumber()) && EmptyUtil.Companion.isNullOrEmpty(hisRefundProject.getTollRecordNumber())) {
            log.info("提交申请失败! 就诊记录编号 或交易流水号 不能为空");
            return MessageUtil.createMessage(false, "提交申请失败!  就诊记录编号 或交易流水号 不能为空");
        } else if (!StringUtils.equals(userName, hisRefundProject.getPatientName())) {
            log.info("提交申请失败! 申请人和当前用户名不符合");
            return MessageUtil.createMessage(false, "提交申请失败! 申请人和当前用户名不符合");
        } else {
         /*   List<HisRefundProject> list = hisRefundProjectMapper.queryRecordNumber(hisRefundProject.getRecordNumber());
            if (list.size() > 0) {
                return MessageUtil.createMessage(false, "提交申请失败! 就诊记录编号 : " + hisRefundProject.getRecordNumber() + " 已申请过 无法再次申请 ");
            }*/
            for (int i = 0; i < ids.length; i++) {
                if (nums[i] == null || nums[i] <= 0) {
                    return MessageUtil.createMessage(false, "提交申请失败! 申请退项目数量填写有误");
                } else {
                    HisRecordProject hisRecordProject = hisRecordProjectService.selectByPrimaryKey(ids[i]);
                    if (nums[i] > hisRecordProject.getNum()) {
                        log.info("提交申请失败! 申请退项目数量不能大于已预约项目的数量");
                        return MessageUtil.createMessage(false, "提交申请失败! " + hisRecordProject.getName() + "   申请退项目数量不能大于已预约项目的数量");
                    }
                }
            }
            //处理已经退的项目不能退
            hisRefundProject.setIsBack(1);
            List<HisRefundProject> refundProjectList = hisRefundProjectMapper.queryHisRecordProject(hisRefundProject);
            if (!EmptyUtil.Companion.isNullOrEmpty(refundProjectList)) {
                for (HisRefundProject refundProject : refundProjectList) {
                    HisRecordProject hisRecordProject = new HisRecordProject();
                    hisRecordProject.setVoucher(refundProject.getVoucher());
                    List<HisRecordProject> hisRecordProjects = hisRecordProjectService.projectInfoList(hisRecordProject);
                    List<Long> listId = hisRecordProjects.stream().map(HisRecordProject::getId).collect(Collectors.toList());
                    List<Long> list = Arrays.asList(ids);
                    List<Long> longList = getLongList(list, listId);
                    if (!EmptyUtil.Companion.isNullOrEmpty(longList)) {
                        for (Long aLong : longList) {
                            HisProject hisProject = hisProjectService.selectByPrimaryKey(aLong);
                            return MessageUtil.createMessage(false, "提交申请失败!  项目已经退款，无法再次申请退款");
                        }
                    }
                }
            }

            HisRecordProject project = new HisRecordProject();
            project.setTollRecordNumber(hisRefundProject.getTollRecordNumber());
            project.setMedicalRecordId(hisRefundProject.getRecordNumber());
            List<HisRecordProject> hisRecordProjects = hisRecordProjectService.queryLists(project);
            HisRecordProject hisRecordProject = hisRecordProjects.get(0);

            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            SimpleDateFormat fat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = fat.parse(fat.format(new Date()));
            String fmt = "RH" + format.format(new Date());
            List<HisRefundProject> hisRefundProjectList = new ArrayList<>();
            for (int i = 0; i < ids.length; i++) {
                HisRefundProject hrp = new HisRefundProject();
                hrp.setPatientName(hisRefundProject.getPatientName());
                hrp.setRecordProjectId(ids[i]);
                if (StringUtils.isEmpty(hisRefundProject.getTollRecordNumber())) {
                    hrp.setRecordNumber(hisRefundProject.getRecordNumber());
                }
                hrp.setVoucher(fmt);
                if (StringUtils.isEmpty(hisRefundProject.getRecordNumber())) {
                    hrp.setTollRecordNumber(hisRefundProject.getTollRecordNumber());
                }
                hrp.setRecordNumber(hisRecordProject.getMedicalRecordId());
                hrp.setRemarks(hisRefundProject.getRemarks());
                hrp.setRefundNum(nums[i]);
                hrp.setApplicationTime(date);
                hrp.setIsBack(2);
                hrp.setIsReview(3);
                hisRefundProjectList.add(hrp);
            }
            hisRefundProjectMapper.saveForAppEdit(hisRefundProjectList);
            return MessageUtil.createMessage(true, "保存成功。");
        }
    }

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisRefundProject>
     * @功能说明 查询申请退项目的列表记录
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/8/17
     * @Time 9:26
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisRefundProject> list(PageBean<HisRefundProject> pageBean) {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisRefundProjectMapper.list(pageBean)));
        return pageBean;
    }

    /**
     * @return core.message.Message
     * @功能说明 批量删除申请退款项目
     * @Params [recordNumbers]
     * @Author XJP
     * @Date 2019/8/20
     * @Time 13:11
     **/
    @Override
    @Transactional(readOnly = false)
    public Message delete(String[] vouchers) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(vouchers)) {
            return MessageUtil.createMessage(false, "删除申请失败!");
        } else {
            hisRefundProjectMapper.delete(vouchers);
            return MessageUtil.createMessage(true, "删除申请成功!");
        }
    }

    /**
     * @return core.message.Message
     * @功能说明 退项目申请审核通过
     * @Params [hisRefundProject]
     * @Author XJP
     * @Date 2019/8/17
     * @Time 16:05
     **/
    @Override
    @Transactional(readOnly = false)
    public Message reviewSuccess(HisRefundProject hisRefundProject) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisRefundProject)) {
            return MessageUtil.createMessage(false, "审核失败!");
        } else {
            List<HisRefundProject> refundProjectList = hisRefundProjectMapper.queryHisRefundProjectVoucher(hisRefundProject);
            for (HisRefundProject refundProject : refundProjectList) {
                refundProject.setIsReview(1);
            }
            hisRefundProjectMapper.updateByHisRefundProjectList(refundProjectList);
            return MessageUtil.createMessage(true, "审核成功! 审核通过");
        }
    }

    /**
     * @return core.message.Message
     * @功能说明 项目申请审核失败
     * @Params [hisRefundProject]
     * @Author XJP
     * @Date 2019/8/17
     * @Time 16:22
     **/
    @Override
    @Transactional(readOnly = false)
    public Message reviewFail(HisRefundProject hisRefundProject) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisRefundProject)) {
            return MessageUtil.createMessage(false, "审核失败!");
        } else {
            List<HisRefundProject> refundProjectList = hisRefundProjectMapper.queryHisRefundProject(hisRefundProject);
            for (HisRefundProject refundProject : refundProjectList) {
                refundProject.setIsReview(2);
            }
            hisRefundProjectMapper.updateByHisRefundProjectList(refundProjectList);
            return MessageUtil.createMessage(true, "审核失败! 审核不通过");
        }
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRefundProject>
     * @功能说明
     * @Params [hisRefundProject]
     * @Author XJP
     * @Date 2019/8/17
     * @Time 16:49
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisRefundProject> queryHisRefundProject(HisRefundProject hisRefundProject) {
        if (EmptyUtil.Companion.isNullOrEmpty(hisRefundProject)) {
            return new ArrayList<>();
        } else {
            List<HisRefundProject> refundProjectList = hisRefundProjectMapper.queryHisRefundProject(hisRefundProject);
            if (EmptyUtil.Companion.isNullOrEmpty(refundProjectList)) {
                return new ArrayList<>();
            } else {
                return refundProjectList;
            }
        }
    }

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisRefundProject>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/8/17
     * @Time 16:49
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisRefundProject> lists(PageBean<HisRefundProject> pageBean) {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisRefundProjectMapper.lists(pageBean)));
        return pageBean;
    }

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisRefundProject>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/8/17
     * @Time 19:00
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisRefundProject> pricelists(PageBean<HisRefundProject> pageBean) {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisRefundProjectMapper.pricelists(pageBean)));
        return pageBean;
    }

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [hisRefundProjectInfo]
     * @Author XJP
     * @Date 2019/8/19
     * @Time 14:04
     **/
    @Override
    @Transactional(readOnly = false)
    public Message saveHisRefundProjectInfo(HisRefundProjectInfo hisRefundProjectInfo) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisRefundProjectInfo.getRefundSumProce()) || EmptyUtil.Companion.isNullOrEmpty(hisRefundProjectInfo.getVoucher()) ||
                EmptyUtil.Companion.isNullOrEmpty(hisRefundProjectInfo.getTollRecordNumber())) {
            return MessageUtil.createMessage(false, "退款失败!");
        } else {
            HisRefundProjectInfo projectInfo = hisRefundProjectInfoMapper.queryHisRefundProjectInfo(hisRefundProjectInfo);
            if (!EmptyUtil.Companion.isNullOrEmpty(projectInfo)) {
                return MessageUtil.createMessage(false, "退款失败! 该项目已经退款成功 ");
            }
            HisRecordProject hisRecordProject = new HisRecordProject();
            hisRecordProject.setTollRecordNumber(hisRefundProjectInfo.getTollRecordNumber());
            List<HisRecordProject> hisRecordProjectList = hisRecordProjectMapper.pricelistsBytollRecordNumber(hisRecordProject);
            BigDecimal sumPrice = new BigDecimal("0");
            for (int i = 0; i < hisRecordProjectList.size(); i++) {
                sumPrice = sumPrice.add(hisRecordProjectList.get(i).getProjectSumPrice());
            }
            if (sumPrice.compareTo(hisRefundProjectInfo.getRefundSumProce()) != 0) {
                return MessageUtil.createMessage(false, "退款金额不一致");
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String fmt = "RHM" + format.format(new Date());
            //保存明细

            List<HisRecordProject> pricelistsBytollRecordNumber = hisRecordProjectService.pricelistsBytollRecordNumber(hisRecordProject);
            List<HisRefundProjectDetail> list = new ArrayList<>();
            for (HisRecordProject recordProject : pricelistsBytollRecordNumber) {
                HisRefundProjectDetail refundProjectDetail = new HisRefundProjectDetail();

                BeanUtils.copyProperties(recordProject, refundProjectDetail);
                refundProjectDetail.setId(null);
                refundProjectDetail.setVouchers(fmt);
                list.add(refundProjectDetail);
            }
            //
            hisRefundProjectDetailService.insertHisRefundProjectDetailList(list);

            HisRefundProject hisRefundProject = new HisRefundProject();
            hisRefundProject.setTollRecordNumber(hisRefundProjectInfo.getTollRecordNumber());
            List<HisRefundProject> refundProjectList = hisRefundProjectMapper.queryHisRefundProject(hisRefundProject);
            for (HisRefundProject refundProject : refundProjectList) {
                refundProject.setIsBack(1);
            }
            hisRefundProjectMapper.updateByHisRefundProjectListBack(refundProjectList);

            SimpleDateFormat fat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = fat.parse(fat.format(new Date()));
            hisRefundProjectInfo.setApplicationTime(date);
            hisRefundProjectInfo.setVoucher(fmt);
            hisRefundProjectInfoMapper.insert(hisRefundProjectInfo);
            HisRecordProject project = new HisRecordProject();
            project.setTollRecordNumber(hisRefundProjectInfo.getTollRecordNumber());
            List<HisRecordProject> hisRecordProjects = hisRecordProjectService.HisRecordProjectLists(project);
            System.out.println("----------------->"+hisRecordProjects.size());
            for (HisRecordProject recordProject : hisRecordProjects) {
                recordProject.setIsBack(1);
            }
            hisRecordProjectService.updateByHisRefundProjectListBack(hisRecordProjects);

            return MessageUtil.createMessage(true, "退款成功!");
        }
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRefundProject>
     * @功能说明
     * @Params [hisRefundProject]
     * @Author XJP
     * @Date 2019/8/26
     * @Time 16:03
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisRefundProject> queryHisRefundProjectVoucher(HisRefundProject hisRefundProject) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisRefundProject)) {
            return new ArrayList<>();
        } else {
            List<HisRefundProject> refundProjectList = hisRefundProjectMapper.queryHisRefundProjectVoucher(hisRefundProject);
            if (EmptyUtil.Companion.isNullOrEmpty(refundProjectList)) {
                return new ArrayList<>();
            } else {
                return refundProjectList;
            }
        }
    }

    /**
     * @return java.util.List<java.lang.Long>
     * @功能说明
     * @Params [listA, listB]
     * @Author XJP
     * @Date 2019/8/24
     * @Time 18:23
     **/
    public static List<Long> getLongList(List<Long> listA, List<Long> listB) {
        // 差集（扣除）
        Collection disjunction = CollectionUtils.intersection(listA, listB);
        List<Long> collect = (List<Long>) disjunction.stream().collect(Collectors.toList());
        return collect;
    }
}



