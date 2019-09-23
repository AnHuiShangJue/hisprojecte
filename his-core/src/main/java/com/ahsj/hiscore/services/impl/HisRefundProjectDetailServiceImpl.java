package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.common.Constants;
import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisRefundProjectDetailMapper;
import com.ahsj.hiscore.entity.HisProject;
import com.ahsj.hiscore.entity.HisRefundProjectDetail;
import com.ahsj.hiscore.entity.Translate;
import com.ahsj.hiscore.feign.ITranslateService;
import com.ahsj.hiscore.services.HisProjectService;
import com.ahsj.hiscore.services.HisRefundProjectDetailService;
import core.entity.PageBean;
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
 * FileName: HisRefundProjectDetailServiceImpl
 * <p>
 * Date:     2019/8/26 15:31
 *
 * @author XJP
 * @create 2019/8/26
 * @since 1.0.0
 */

@Service
public class HisRefundProjectDetailServiceImpl implements HisRefundProjectDetailService {

    @Autowired
    HisRefundProjectDetailMapper hisRefundProjectDetailMapper;

    @Autowired
    HisProjectService hisProjectService;

    @Autowired
    ITranslateService iTranslateService;

    /**
     * @return int
     * @功能说明
     * @Params [hisRefundProjectDetails]
     * @Author XJP
     * @Date 2019/8/26
     * @Time 15:31
     **/
    @Override
    @Transactional(readOnly = false)
    public Message insertHisRefundProjectDetailList(List<HisRefundProjectDetail> hisRefundProjectDetails) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisRefundProjectDetails)) {
            return MessageUtil.createMessage(false, "添加失败");
        } else {
            hisRefundProjectDetailMapper.insertHisRefundProjectDetailList(hisRefundProjectDetails);
            return MessageUtil.createMessage(true, "添加成功");
        }

    }

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisRefundProjectDetail>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/8/26
     * @Time 17:19
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisRefundProjectDetail> projectInfoDetail(PageBean<HisRefundProjectDetail> pageBean) {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisRefundProjectDetailMapper.projectInfoDetail(pageBean)));
        return pageBean;
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRefundProjectDetail>
     * @功能说明
     * @Params [hisRefundProjectDetail]
     * @Author XJP
     * @Date 2019/8/26
     * @Time 18:00
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisRefundProjectDetail> HisRecordProjectLists(HisRefundProjectDetail hisRefundProjectDetail) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisRefundProjectDetail)){
            return new ArrayList<>();
        }else {
            List<HisRefundProjectDetail> printlylist = hisRefundProjectDetailMapper.HisRecordProjectLists(hisRefundProjectDetail);
            if (EmptyUtil.Companion.isNullOrEmpty(printlylist)){
                return new ArrayList<>();
            }else {
                for (HisRefundProjectDetail detail : printlylist) {
                    String number = detail.getNumber();
                    HisProject hisProject = new HisProject();
                    hisProject.setNumber(number);
                     hisProject = hisProjectService.getHisProject(hisProject);
                    Translate translate = new Translate();
                    translate.setTranslateId(hisProject.getId());
                    translate.setTranslateType(Constants.TRANSLATE_HIS_PROJECT);
                    List<Translate> translates = iTranslateService.queryTranslate(translate);
                    for (Translate trans : translates) {
                        if (StringUtils.equals(trans.getTranslateChina(),detail.getName())){
                            String khmer = trans.getTranslateKhmer();
                            detail.setTranName(khmer);
                        }
                    }
                }
                return printlylist;
            }
        }
    }
}
