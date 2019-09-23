package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisRefundProjectInfoMapper;
import com.ahsj.hiscore.entity.HisRefundProjectInfo;
import com.ahsj.hiscore.services.HisRefundProjectInfoService;
import core.entity.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisRefundProjectInfoServiceImpl
 * <p>
 * Date:     2019/8/19 16:08
 *
 * @author XJP
 * @create 2019/8/19
 * @since 1.0.0
 */

@Service
public class HisRefundProjectInfoServiceImpl implements HisRefundProjectInfoService {

    @Autowired
    HisRefundProjectInfoMapper hisRefundProjectInfoMapper;

    /**
     * @return com.ahsj.hiscore.entity.HisRefundProjectInfo
     * @功能说明
     * @Params [hisRefundProjectInfo]
     * @Author XJP
     * @Date 2019/8/20
     * @Time 10:41
     **/
    @Override
    @Transactional(readOnly = true)
    public HisRefundProjectInfo queryHisRefundProjectInfo(HisRefundProjectInfo hisRefundProjectInfo) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisRefundProjectInfo)) {
            return new HisRefundProjectInfo();
        } else {
            HisRefundProjectInfo info = hisRefundProjectInfoMapper.queryHisRefundProjectInfo(hisRefundProjectInfo);
            return info;
        }
    }

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisRefundProjectInfo>
     * @功能说明 查看退项目明细
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/8/20
     * @Time 10:41
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisRefundProjectInfo> list(PageBean<HisRefundProjectInfo> pageBean) {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisRefundProjectInfoMapper.list(pageBean)));
        return pageBean;
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRefundProjectInfo>
     * @功能说明
     * @Params [hisRefundProjectInfo]
     * @Author XJP
     * @Date 2019/8/26
     * @Time 17:41
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisRefundProjectInfo> printlylist(HisRefundProjectInfo hisRefundProjectInfo) {
        if (EmptyUtil.Companion.isNullOrEmpty(hisRefundProjectInfo)){
            return new ArrayList<>();
        }else {
            List<HisRefundProjectInfo> printlylist = hisRefundProjectInfoMapper.printlylist(hisRefundProjectInfo);
            if (EmptyUtil.Companion.isNullOrEmpty(printlylist)){
                return new ArrayList<>();
            }else {
                return printlylist;
            }
        }
    }
}
