package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisInspectionCombinationMapper;
import com.ahsj.hiscore.entity.HisInspecProject;
import com.ahsj.hiscore.entity.HisInspectionCombination;
import com.ahsj.hiscore.entity.HisProject;
import com.ahsj.hiscore.services.HisInspecProjectService;
import com.ahsj.hiscore.services.HisInspectionCombinationService;
import com.ahsj.hiscore.services.HisProjectService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisInspectionCombinationServiceImpl
 * <p>
 * Date:     2019/7/11 16:32
 *
 * @author XJP
 * @create 2019/7/11
 * @since 1.0.0
 */


@Service
public class HisInspectionCombinationServiceImpl implements HisInspectionCombinationService {

    private Logger log = LoggerFactory.getLogger(HisInspectionCombinationServiceImpl.class.getSimpleName());


    private static final Integer TDISABLE_NUM_ONE = 1;

    private static final Integer TDISABLE_NUM_TWO = 2;


    @Autowired
    HisInspectionCombinationMapper hisInspectionCombinationMapper;

    @Autowired
    HisProjectService hisProjectService;

    @Autowired
    HisInspecProjectService hisInspecProjectService;

    /**
     * @return void
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:26
     **/
    @Override
    @Transactional(readOnly = false)
    public void deleteByPrimaryKey(Long id) throws Exception {
        hisInspectionCombinationMapper.deleteByPrimaryKey(id);
    }

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [hisInspectionCombination]
     * @Author XJP
     * @Date 2019/7/31
     * @Time 19:41
     **/
    @Override
    @Transactional(readOnly = false)
    public Message insertSelective(HisInspectionCombination hisInspectionCombination) throws Exception {
        hisInspectionCombinationMapper.insertSelective(hisInspectionCombination);
        return MessageUtil.createMessage(true, " 添加项目组信息成功 !!!");
    }

    /**
     * @return com.ahsj.hiscore.entity.HisInspectionCombination
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/7/31
     * @Time 19:39
     **/
    @Override
    @Transactional(readOnly = true)
    public HisInspectionCombination selectByPrimaryKey(Long id) {
        if (EmptyUtil.Companion.isNullOrEmpty(id)) {
            log.info("查询失败 !!!! 查询参数不能为空  id 值 : {}", id);
            return new HisInspectionCombination();
        } else {
            HisInspectionCombination combination = hisInspectionCombinationMapper.selectByPrimaryKey(id);
            if (EmptyUtil.Companion.isNullOrEmpty(combination)) {
                log.info("查询失败 !!!! 无对应数据    combination : {}", combination.toString());
                return new HisInspectionCombination();
            } else {
                return combination;
            }
        }
    }

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [hisInspectionCombination]
     * @Author XJP
     * @Date 2019/7/31
     * @Time 19:39
     **/
    @Override
    @Transactional(readOnly = false)
    public Message updateByPrimaryKeySelective(HisInspectionCombination hisInspectionCombination) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisInspectionCombination)) {
            log.info("参数不能为空 hisInspectionCombination  : {} ", hisInspectionCombination.toString());
            return MessageUtil.createMessage(false, " 修改项目组信息失败 !!!");
        } else {
            HisInspectionCombination select = hisInspectionCombinationMapper.selectByPrimaryKey(hisInspectionCombination.getId());
            if (EmptyUtil.Companion.isNullOrEmpty(select.getName()) || EmptyUtil.Companion.isNullOrEmpty(select.getNumber())) {
                return MessageUtil.createMessage(false, " 修改项目组信息失败 !!!");
            } else {
                HisInspectionCombination combination = hisInspectionCombinationMapper.selectByhisInspectionCombinationName(hisInspectionCombination.getName());
                if (EmptyUtil.Companion.isNullOrEmpty(combination)) {
                    hisInspectionCombinationMapper.updateByPrimaryKeySelective(hisInspectionCombination);
                    return MessageUtil.createMessage(true, " 修改项目组信息成功 !!!");
                }
                if (StringUtils.equals(combination.getName(), hisInspectionCombination.getName())) {
                    if (combination.getId() == hisInspectionCombination.getId()) {
                        hisInspectionCombinationMapper.updateByPrimaryKeySelective(hisInspectionCombination);
                        return MessageUtil.createMessage(true, " 修改项目组信息成功 !!!");
                    } else {
                        return MessageUtil.createMessage(false, " 修改项目组信息失败 !!!");
                    }
                }
                return MessageUtil.createMessage(false, " 修改项目组信息失败 !!!");
            }
        }

    }

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisInspectionCombination>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/7/31
     * @Time 19:28
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisInspectionCombination> queryList(PageBean<HisInspectionCombination> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisInspectionCombinationMapper.queryList(pageBean)));
        return pageBean;
    }

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [ids, nums, combinationName, combinationNumber, hisProjectOrderNums, combinationId]
     * @Author XJP
     * @Date 2019/7/31
     * @Time 19:27
     **/
    @Override
    @Transactional(readOnly = false)
    public Message addUpdateSelectiveList(Long[] ids, Integer[] nums, String combinationName,
                                          String combinationNumber, Integer[] hisProjectOrderNums, Long combinationId) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(ids) || EmptyUtil.Companion.isNullOrEmpty(hisProjectOrderNums) || EmptyUtil.Companion.isNullOrEmpty(nums)) {
            return MessageUtil.createMessage(false, " 批量添加收费项目到项目组失败");
        } else if (StringUtils.isEmpty(combinationName) || StringUtils.isEmpty(combinationNumber)) {
            return MessageUtil.createMessage(false, " 批量添加收费项目失败,组合名或组合简码不能为空");
        } else {
            for (Integer num : nums) {
                if (EmptyUtil.Companion.isNullOrEmpty(num)) {
                    return MessageUtil.createMessage(false, " 批量添加收费项目到项目组失败!!! 项目数量不能为空");
                }
            }
            for (Integer orderNum : hisProjectOrderNums) {
                if (EmptyUtil.Companion.isNullOrEmpty(orderNum)) {
                    return MessageUtil.createMessage(false, " 批量添加收费项目到项目组失败!!! 项目序号不能为空");
                }
            }
            List<Integer> list1 = new ArrayList<>();
            ArrayList<Integer> list2 = new ArrayList<>();
            for (int i = 0; i < hisProjectOrderNums.length; i++) {
                list1.add(hisProjectOrderNums[i]);
                list2.add((i + 1));
            }
            boolean diffrent = checkDiffrent(list2, list1);
            if (!diffrent) {
                return MessageUtil.createMessage(false, " 批量添加收费项目失败, 项目序号有误");
            } else {
                hisInspecProjectService.deleteByInspectionId(combinationId);
                HisInspectionCombination inspectionCombination = hisInspectionCombinationMapper.selectByPrimaryKey(combinationId);
                BigDecimal money = new BigDecimal("0");
                inspectionCombination.setMoney(money);
                for (int i = 0; i < ids.length; i++) {
                    HisInspecProject hisInspecProject = new HisInspecProject();
                    hisInspecProject.setProjectId(ids[i]);
                    HisProject hisProject = hisProjectService.selectByPrimaryKey(ids[i]);
                    hisInspecProject.setNum(nums[i]);
                    hisInspecProject.setOrderNum(hisProjectOrderNums[i]);
                    BigDecimal price = hisProject.getPrice();
                    BigDecimal num = new BigDecimal(nums[i] + "");
                    BigDecimal multiply = price.multiply(num);
                    money = multiply.add(money);
                    hisInspecProject.setInspectionId(combinationId);
                    hisInspecProjectService.insert(hisInspecProject);
                }
                inspectionCombination.setMoney(money);
                hisInspectionCombinationMapper.updateByPrimaryKeySelective(inspectionCombination);
                return MessageUtil.createMessage(true, "批量添加收费项目明细成功");
            }

        }

    }


    /**
     * @return core.message.Message
     * @功能说明
     * @Params [ids, nums, combinationName, combinationNumber, hisProjectOrderNums]
     * @Author XJP
     * @Date 2019/7/31
     * @Time 19:27
     **/
    @Override
    @Transactional(readOnly = false)
    public Message updateSelectiveList(Long[] ids, Integer[] nums, String combinationName,
                                       String combinationNumber, Integer[] hisProjectOrderNums) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(ids) || EmptyUtil.Companion.isNullOrEmpty(hisProjectOrderNums) || EmptyUtil.Companion.isNullOrEmpty(nums)) {
            return MessageUtil.createMessage(false, " 批量添加收费项目到项目组失败");
        } else if (StringUtils.isEmpty(combinationName) || StringUtils.isEmpty(combinationNumber)) {
            return MessageUtil.createMessage(false, " 批量添加收费项目失败,组合名或组合简码不能为空");
        } else {
            for (Integer num : nums) {
                if (EmptyUtil.Companion.isNullOrEmpty(num)) {
                    return MessageUtil.createMessage(false, " 批量添加收费项目到项目组失败!!! 项目数量不能为空");
                }
            }
            for (Integer orderNum : hisProjectOrderNums) {
                if (EmptyUtil.Companion.isNullOrEmpty(orderNum)) {
                    return MessageUtil.createMessage(false, " 批量添加收费项目到项目组失败!!! 项目序号不能为空");
                }
            }
            List<Integer> list1 = new ArrayList<>();
            ArrayList<Integer> list2 = new ArrayList<>();
            for (int i = 0; i < hisProjectOrderNums.length; i++) {
                list1.add(hisProjectOrderNums[i]);
                list2.add((i + 1));
            }
            boolean diffrent = checkDiffrent(list2, list1);
            if (!diffrent) {
                return MessageUtil.createMessage(false, " 批量添加收费项目失败, 项目序号有误");
            } else {
                HisInspectionCombination hisInspectionCombination = new HisInspectionCombination();
                hisInspectionCombination.setName(combinationName);
                hisInspectionCombination.setNumber(combinationNumber);
                HisInspectionCombination select = hisInspectionCombinationMapper.selectByNumber(combinationNumber);
                if (select != null) {
                    return MessageUtil.createMessage(false, " 批量添加收费项目失败,组合名或组合简码不能重复");
                } else {
                    hisInspectionCombinationMapper.insert(hisInspectionCombination);
                    BigDecimal combination_sum = new BigDecimal("0");

                    for (int i = 0; i < ids.length; i++) {
                        HisInspecProject hisInspecProject = new HisInspecProject();
                        hisInspecProject.setProjectId(ids[i]);
                        HisProject hisProject = hisProjectService.selectByPrimaryKey(ids[i]);
                        hisInspecProject.setNum(nums[i]);
                        hisInspecProject.setOrderNum(hisProjectOrderNums[i]);
                        BigDecimal price = hisProject.getPrice();
                        BigDecimal num = new BigDecimal(nums[i] + "");
                        BigDecimal multiply = price.multiply(num);
                        combination_sum = multiply.add(combination_sum);
                        hisInspecProject.setInspectionId(hisInspectionCombination.getId());
                        hisInspecProjectService.insert(hisInspecProject);
                    }
                    hisInspectionCombination.setMoney(combination_sum);
                    Integer orderNum = Integer.valueOf(hisInspectionCombination.getId() + "");
                    hisInspectionCombination.setOrdernum(1 + orderNum);
                    hisInspectionCombination.setIsEnable(1);
                    hisInspectionCombinationMapper.updateByPrimaryKeySelective(hisInspectionCombination);

                    return MessageUtil.createMessage(true, " 批量添加收费项目到项目组成功");
                }
            }
        }
    }

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [ids]
     * @Author XJP
     * @Date 2019/7/31
     * @Time 19:38
     **/
    @Override
    @Transactional(readOnly = false)
    public Message updateSetDisable(Long[] ids) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(ids)) {
            return MessageUtil.createMessage(false, "修改收组合项目费停用状态失败。");
        } else {
            for (Long id : ids) {
                HisInspectionCombination hisInspectionCombination = hisInspectionCombinationMapper.selectByPrimaryKey(id);
                if (hisInspectionCombination.getIsEnable() == 1) {
                    hisInspectionCombination.setIsEnable(TDISABLE_NUM_TWO);
                    hisInspectionCombinationMapper.updateByPrimaryKeySelective(hisInspectionCombination);
                } else {
                    hisInspectionCombination.setIsEnable(TDISABLE_NUM_ONE);
                    hisInspectionCombinationMapper.updateByPrimaryKeySelective(hisInspectionCombination);
                }
            }
            return MessageUtil.createMessage(true, "修改收组合项目费停用状态成功。");
        }
    }

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [ids]
     * @Author XJP
     * @Date 2019/7/31
     * @Time 19:39
     **/
    @Override
    @Transactional(readOnly = false)
    public Message deleteHisCombinationByIds(Long[] ids) throws Exception {
        if (!EmptyUtil.Companion.isNullOrEmpty(ids)) {
            for (Long id : ids) {
                hisInspectionCombinationMapper.deleteByPrimaryKey(id);
                hisInspecProjectService.deleteByInspectionId(id);
            }
            return MessageUtil.createMessage(true, "删除成功。");
        } else {
            return MessageUtil.createMessage(false, "删除失败。");
        }
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisProject>
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:27
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisProject> selectInspecProjectList(Long id) throws Exception {
        List<HisProject> list = new ArrayList<>();
        List<HisProject> hisProjects = hisProjectService.queryCombinationId(id);
        for (HisProject project : hisProjects) {
            HisProject hisProject = new HisProject();
            hisProject.setId(project.getId());
            hisProject.setHisProjectNum(project.getHisProjectNum());
            hisProject.setName(project.getName());
            hisProject.setNumber(project.getNumber());
            hisProject.setPrice(project.getPrice());
            hisProject.setUnit(project.getUnit());
            hisProject.setHisProjectOrderNum(project.getHisProjectOrderNum());
            hisProject.setHisProjectNum(project.getHisProjectNum());
            list.add(hisProject);
        }
        return list;
    }

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [hisProject]
     * @Author XJP
     * @Date 2019/7/31
     * @Time 19:39
     **/
    @Override
    @Transactional(readOnly = false)
    public Message updateHisProject(HisProject hisProject) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisProject)) {
            return MessageUtil.createMessage(false, "更新失败。");
        } else {
            HisInspecProject hisInspecProject = hisInspecProjectService.selectByPrimaryKey(hisProject.getInspectionId());
            hisInspecProject.setNum(hisProject.getHisProjectNum());
            hisInspecProjectService.updateByPrimaryKey(hisInspecProject);
            List<HisInspecProject> hisInspecProjectList = hisInspecProjectService.selectByHisInspecProjectInspectionId(hisInspecProject.getInspectionId());
            BigDecimal combination_sum = new BigDecimal("0");
            for (HisInspecProject inspecProject : hisInspecProjectList) {
                BigDecimal priceNum = inspecProject.getPriceNum();
                Integer num = inspecProject.getNum();
                BigDecimal nums = new BigDecimal(num + "");
                BigDecimal multiply = priceNum.multiply(nums);
                combination_sum = multiply.add(combination_sum);
            }
            HisInspectionCombination hisInspectionCombination = hisInspectionCombinationMapper.selectByPrimaryKey(hisInspecProject.getInspectionId());
            hisInspectionCombination.setMoney(combination_sum);
            hisInspectionCombinationMapper.updateByPrimaryKeySelective(hisInspectionCombination);
            return MessageUtil.createMessage(true, " 收费项目明细数量修改成功");
        }
    }

    /**
     * @return boolean
     * @功能说明
     * @Params [list, list1]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:27
     **/
    public static boolean checkDiffrent(List<Integer> list, List<Integer> list1) {
        if (list.size() != list1.size()) {
            return false;
        }
        for (Integer str : list) {
            if (!list1.contains(str)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisInspectionCombination>
     * @Description 查询所有项目组合
     * @Params []
     * @Author zhushixiang
     * @Date 2019-09-14
     * @Time 16:44
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisInspectionCombination> selectCombineProject() throws Exception {
        return hisInspectionCombinationMapper.selectCombineProject();
    }

    /**
     * @return
     * @Description 查看门诊病历模板中的组合项目明细
     * @Params
     * @Author zhushixiang
     * @Date 2019-09-19
     * @Time 21:23
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisInspectionCombination> viewDetailInMedicalTemplate(PageBean<HisInspectionCombination> pageEntity) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisInspectionCombinationMapper.viewDetailInMedicalTemplate(pageEntity)))
            return new PageBean<HisInspectionCombination>();
        pageEntity.setData(CodeHelper.getInstance().setCodeValue(hisInspectionCombinationMapper.viewDetailInMedicalTemplate(pageEntity)));
        return pageEntity;
    }

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [ids, nums, combinationName, combinationNumber, hisProjectOrderNums, combinationId]
     * @Author XJP
     * @Date 2019/7/31
     * @Time 19:27
     **/
    @Override
    @Transactional(readOnly = false)
    public Long addUpdateSelectiveReturnId(Long[] ids, Integer[] nums, String combinationName,
                                           String combinationNumber, Integer[] hisProjectOrderNums, Long combinationId) throws Exception {
        HisInspectionCombination hisInspectionCombination = new HisInspectionCombination();
        hisInspectionCombination.setName(combinationName);
        hisInspectionCombination.setNumber(combinationNumber);
        hisInspectionCombination.setIsEnable(1);//1代表可用
        hisInspectionCombinationMapper.insert(hisInspectionCombination);
        BigDecimal money = new BigDecimal("0");
        for (int i = 0; i < ids.length; i++) {
            HisInspecProject hisInspecProject = new HisInspecProject();
            hisInspecProject.setProjectId(ids[i]);
            HisProject hisProject = hisProjectService.selectByPrimaryKey(ids[i]);
            hisInspecProject.setNum(nums[i]);
            hisInspecProject.setOrderNum(hisProjectOrderNums[i]);
            BigDecimal price = hisProject.getPrice();
            BigDecimal num = new BigDecimal(nums[i] + "");
            BigDecimal multiply = price.multiply(num);
            money = multiply.add(money);
            hisInspecProject.setInspectionId(hisInspectionCombination.getId());
            hisInspecProjectService.insert(hisInspecProject);
        }
        hisInspectionCombination.setMoney(money);
        hisInspectionCombinationMapper.updateByPrimaryKeySelective(hisInspectionCombination);
        return hisInspectionCombination.getId();
}
}
