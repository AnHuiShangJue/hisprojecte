package com.ahsj.smartparkcore.services.impl;

import com.ahsj.smartparkcore.core.CodeHelper;
import com.ahsj.smartparkcore.dao.LeaseMapper;
import com.ahsj.smartparkcore.entity.vo.LeaseVO;
import com.ahsj.smartparkcore.services.LeaseService;
import core.entity.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
                    leaseVO.setBookTypeName("会议室");
                }
                if (leaseVO.getBookType() == 2) {
                    leaseVO.setBookTypeName("场地");
                }
                if (leaseVO.getBookType() == 3) {
                    leaseVO.setBookTypeName("工位");
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
                    leaseVO.setBookTypeName("会议室");
                }
                if (leaseVO.getBookType() == 2) {
                    leaseVO.setBookTypeName("场地");
                }
                if (leaseVO.getBookType() == 3) {
                    leaseVO.setBookTypeName("工位");
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
                    leaseVO.setBookTypeName("会议室");
                }
                if (leaseVO.getBookType() == 2) {
                    leaseVO.setBookTypeName("场地");
                }
                if (leaseVO.getBookType() == 3) {
                    leaseVO.setBookTypeName("工位");
                }
            }
            return list;
        }
    }
}
