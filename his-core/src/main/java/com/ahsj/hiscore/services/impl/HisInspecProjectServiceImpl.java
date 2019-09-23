package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.dao.HisInspecProjectMapper;
import com.ahsj.hiscore.entity.HisInspecProject;
import com.ahsj.hiscore.entity.HisInspectionCombination;
import com.ahsj.hiscore.services.HisInspecProjectService;
import com.ahsj.hiscore.services.HisInspectionCombinationService;
import core.message.Message;
import core.message.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import utils.EmptyUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisInspecProjectServiceImpl
 * <p>
 * Date:     2019/7/12 15:44
 *
 * @author XJP
 * @create 2019/7/12
 * @since 1.0.0
 */

@Service
public class HisInspecProjectServiceImpl implements HisInspecProjectService {

    private Logger log = LoggerFactory.getLogger(HisInspecProjectServiceImpl.class.getSimpleName());

    @Autowired
    HisInspecProjectMapper hisInspecProjectMapper;
    @Autowired
    HisInspectionCombinationService hisInspectionCombinationService;


    @Override
    @Transactional(readOnly = false)
    public void deleteByPrimaryKey(Long id) {
        hisInspecProjectMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void insert(HisInspecProject hisInspecProject) {
        hisInspecProjectMapper.insert(hisInspecProject);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertSelective(HisInspecProject hisInspecProject) {
        hisInspecProjectMapper.insertSelective(hisInspecProject);
    }

    @Override
    @Transactional(readOnly = true)
    public HisInspecProject selectByPrimaryKey(Long id) {
        return hisInspecProjectMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateByPrimaryKeySelective(HisInspecProject hisInspecProject) {
        hisInspecProjectMapper.updateByPrimaryKeySelective(hisInspecProject);
    }

    @Override
    @Transactional(readOnly = false)
    public int updateByPrimaryKey(HisInspecProject hisInspecProject) {
        return hisInspecProjectMapper.updateByPrimaryKey(hisInspecProject);
    }

    @Override
    public void deleteByInspectionId(Long inspectionId) {
        hisInspecProjectMapper.deleteByInspectionId(inspectionId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HisInspecProject> selectInspecProjectList(Long id) {
        return hisInspecProjectMapper.selectInspecProjectByCombinationId(id);
    }

    @Override
    @Transactional(readOnly = false)
    public Message deleteByIds(Long[] ids, Long id) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(ids) || EmptyUtil.Companion.isNullOrEmpty(id)) {
            return MessageUtil.createMessage(false, "删除失败。");
        } else {
            for (Long cid : ids) {
                HisInspecProject hisInspecProject = new HisInspecProject();
                hisInspecProject.setProjectId(cid);
                hisInspecProject.setInspectionId(id);
                hisInspecProjectMapper.deleteByInspectionIds(hisInspecProject);
            }
            List<HisInspecProject> hisInspecProjectList = hisInspecProjectMapper.selectByHisInspecProjectInspectionId(id);
            BigDecimal combination_sum = new BigDecimal("0");
            if (CollectionUtils.isEmpty(hisInspecProjectList)) {
               HisInspectionCombination hisInspectionCombination = hisInspectionCombinationService.selectByPrimaryKey(id);
                hisInspectionCombinationService.deleteByPrimaryKey(id);
             hisInspectionCombinationService.updateByPrimaryKeySelective(hisInspectionCombination);
                return MessageUtil.createMessage(true, "删除成功。");
            } else {
                for (HisInspecProject inspecProject : hisInspecProjectList) {
                    BigDecimal priceNum = inspecProject.getPriceNum();
                    Integer num = inspecProject.getNum();
                    BigDecimal nums = new BigDecimal(num + "");
                    BigDecimal multiply = priceNum.multiply(nums);
                    combination_sum = multiply.add(combination_sum);
                }
                HisInspectionCombination hisInspectionCombination = hisInspectionCombinationService.selectByPrimaryKey(id);
                hisInspectionCombination.setMoney(combination_sum);
                hisInspectionCombinationService.updateByPrimaryKeySelective(hisInspectionCombination);
                return MessageUtil.createMessage(true, "删除成功。");
            }
        }
    }

    @Override
    @Transactional(readOnly = true)
    public HisInspecProject queryCombinationByProjectAndInspectionId(HisInspecProject hisInspecProject) {
        if (EmptyUtil.Companion.isNullOrEmpty(hisInspecProject)) {
            return null;
        } else {
            HisInspecProject combination = hisInspecProjectMapper.queryCombinationByProjectAndInspectionId(hisInspecProject);
            return combination;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<HisInspecProject> selectByHisInspecProjectInspectionId(Long inspectionId) {
        if (EmptyUtil.Companion.isNullOrEmpty(inspectionId)) {
            return null;
        } else {
            List<HisInspecProject> hisInspecProjectList = hisInspecProjectMapper.selectByHisInspecProjectInspectionId(inspectionId);
            if (CollectionUtils.isEmpty(hisInspecProjectList)) {
                return null;
            }
            return hisInspecProjectList;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<HisInspecProject> queryByProjectId(long longValue) {
        if (EmptyUtil.Companion.isNullOrEmpty(longValue)){
            log.info("查询失败 ！！ 请求参数不能为空");
            return new ArrayList<>();
        }else {
            List<HisInspecProject> hisInspecProjectList =  hisInspecProjectMapper.queryByProjectId(longValue);
            if (EmptyUtil.Companion.isNullOrEmpty(hisInspecProjectList)){
                log.info("查询失败 ！！ 无对应数据");
                return new ArrayList<>();
            }else {
                return hisInspecProjectList;
            }
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<HisInspecProject> queryByInspectionId(Long inspectionId) {
        if (EmptyUtil.Companion.isNullOrEmpty(inspectionId)){
            log.info("查询失败 ！！ 请求参数不能为空");
            return new ArrayList<>();
        }else {
            List<HisInspecProject> hisInspecProjectList =  hisInspecProjectMapper.queryByInspectionId(inspectionId);
            if (EmptyUtil.Companion.isNullOrEmpty(hisInspecProjectList)){
                log.info("查询失败 ！！ 无对应数据");
                return new ArrayList<>();
            }else {
                return hisInspecProjectList;
            }
        }
    }


}
