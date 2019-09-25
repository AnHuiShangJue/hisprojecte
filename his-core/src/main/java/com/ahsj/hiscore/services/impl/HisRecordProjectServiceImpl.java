package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.common.Constants;
import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisRecordProjectMapper;
import com.ahsj.hiscore.entity.HisRecordProject;
import com.ahsj.hiscore.entity.HisRefundProject;
import com.ahsj.hiscore.entity.Translate;
import com.ahsj.hiscore.feign.ITranslateService;
import com.ahsj.hiscore.services.HisRecordProjectService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: his
 * @description:HisRecordProjectService
 * @author: chenzhicai
 * @create: 2019-07-19-10-12
 **/
@Service
public class HisRecordProjectServiceImpl implements HisRecordProjectService {


    @Autowired
    HisRecordProjectMapper hisRecordProjectMapper;

    @Autowired
    ITranslateService iTranslateService;

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRecordProject>
     * @Description 根据医疗记录查询
     * @Params [id]
     * @Author chenzhicai
     * @Date 2019-07-19
     * @Time 10:15
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisRecordProject> selectByMedicalRecordId(Long id) {
        return CodeHelper.getInstance().setCodeValue(hisRecordProjectMapper.selectByMedicalRecordId(id));
    }

    /**
     * @param record
     * @return int
     * @Description 修改付费状态
     * @Author: dingli
     * @Date 2019/7/19
     * @Time 11:03
     **/
    @Override
    @Transactional(readOnly = false)
    public int updateHisRecordProject(HisRecordProject record) throws Exception {
        return hisRecordProjectMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * @return void
     * @Description 删除
     * @Params [deletedIds]
     * @Author zhushixiang
     * @Date 2019-08-13
     * @Time 14:01
     **/
    @Override
    @Transactional(readOnly = false)
    public Message delete(Long[] deletedIds) throws Exception {
        for (int i = 0; i < deletedIds.length; i++) {
            hisRecordProjectMapper.deleteByPrimaryKey(deletedIds[i]);
        }
        return MessageUtil.createMessage(true, "删除成功");
    }

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisRecordProject>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 17:38
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisRecordProject> queryAddList(PageBean<HisRecordProject> pageBean) {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisRecordProjectMapper.queryList(pageBean)));
        return pageBean;
    }

    /**
     * @return com.ahsj.hiscore.entity.HisRecordProject
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/8/17
     * @Time 10:25
     **/
    @Override
    @Transactional(readOnly = true)
    public HisRecordProject selectByPrimaryKey(Long id) {
        HisRecordProject hisRecordProject = hisRecordProjectMapper.selectByPrimaryKey(id);
        if (EmptyUtil.Companion.isNullOrEmpty(hisRecordProject)) {
            return new HisRecordProject();
        } else {
            return hisRecordProject;
        }
    }

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisRecordProject>
     * @功能说明 查询申请退项目的列表记录明细
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/8/17
     * @Time 10:44
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisRecordProject> projectInfo(PageBean<HisRecordProject> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisRecordProjectMapper.projectInfo(pageBean)));
        return pageBean;
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRecordProject>
     * @功能说明
     * @Params [hisRecordProject]
     * @Author XJP
     * @Date 2019/8/24
     * @Time 17:45
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisRecordProject> projectInfoList(HisRecordProject hisRecordProject) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisRecordProject)) {
            return new ArrayList<>();
        } else {
            List<HisRecordProject> recordProjectList = hisRecordProjectMapper.projectInfoList(hisRecordProject);
            if (EmptyUtil.Companion.isNullOrEmpty(recordProjectList)) {
                return new ArrayList<>();
            } else {
                return recordProjectList;
            }
        }
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRecordProject>
     * @功能说明
     * @Params [hisRecordProject]
     * @Author XJP
     * @Date 2019/8/26
     * @Time 15:08
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisRecordProject> pricelistsBytollRecordNumber(HisRecordProject hisRecordProject) {
        if (EmptyUtil.Companion.isNullOrEmpty(hisRecordProject)) {
            return new ArrayList<>();
        } else {
            List<HisRecordProject> recordProjectList = hisRecordProjectMapper.pricelistsBytollRecordNumber(hisRecordProject);
            if (EmptyUtil.Companion.isNullOrEmpty(recordProjectList)) {
                return new ArrayList<>();
            } else {
                return recordProjectList;
            }
        }
    }


    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRecordProject>
     * @Description 根据就诊记录ID查询出未检查且未付费的药品
     * @Params [id]
     * @Author zhushixiang
     * @Date 2019-08-16
     * @Time 10:00
     **/
    @Override
    public List<HisRecordProject> selectByMedicalRecordIdNotIsCheckedOrIspayed(Long id) {
        return CodeHelper.getInstance().setCodeValue(hisRecordProjectMapper.selectByRecordIdNotIsCheckdOrIsPayed(id));
    }

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisRefundProject>
     * @功能说明 查询退款明细
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/8/17
     * @Time 20:05
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisRecordProject> pageBeanList(PageBean<HisRecordProject> pageBean) {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisRecordProjectMapper.pageBeanList(pageBean)));
        return pageBean;
    }


    /**
     * @Description 项目病人清单
     * @Author muxu
     * @Date 2019/8/16
     * @Time 17:11
     * @Return
     * @Params
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisRecordProject> ItemPatientList(PageBean<HisRecordProject> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisRecordProjectMapper.ItemPatientList(pageBean)));
        return pageBean;
    }


    /**
     * @Description 病人诊疗项目审核
     * @Author muxu
     * @Date 2019/8/17
     * @Time 10:29
     * @Return core.entity.PageBean<com.ahsj.hiscore.entity.HisRecordProject>
     * @Params [id]
     **/

    @Override
    @Transactional(readOnly = true)
    public PageBean<HisRecordProject> selectByMedicalRecordIdList(PageBean<HisRecordProject> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisRecordProjectMapper.selectByMedicalRecordIdList(pageBean)));
        return pageBean;
    }

    @Override
    @Transactional(readOnly = false)
    public Message UpdateReviewStatus(Long id) throws Exception {
        HisRecordProject hisRecordProject = hisRecordProjectMapper.selectByPrimaryKey(id);
        hisRecordProject.setIsChecked(Short.valueOf("1"));
        hisRecordProjectMapper.updateByPrimaryKey(hisRecordProject);
        return MessageUtil.createMessage(true, "项目审核成功，已检查！");
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRecordProject>
     * @功能说明
     * @Params [hisRecordProject]
     * @Author XJP
     * @Date 2019/8/19
     * @Time 15:09
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisRecordProject> HisRecordProjectLists(HisRecordProject hisRecordProject) throws Exception {
        List<HisRecordProject> hisRecordProjects = hisRecordProjectMapper.HisRecordProjectLists(hisRecordProject);
        if (EmptyUtil.Companion.isNullOrEmpty(hisRecordProjects)) {
            return new ArrayList<>();
        }
        for (HisRecordProject recordProject : hisRecordProjects) {
            Long id = recordProject.getProjectId();
            Translate translate = new Translate();
            translate.setTranslateId(id);
            translate.setTranslateType(Constants.TRANSLATE_HIS_PROJECT);
            List<Translate> translates = iTranslateService.queryTranslate(translate);
            System.out.println(translates.size());
            Translate translate1 = translates.get(0);
            recordProject.setTranName(translate1.getTranslateKhmer());
        }
        return hisRecordProjects;
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRecordProject>
     * @功能说明
     * @Params [project]
     * @Author XJP
     * @Date 2019/8/20
     * @Time 9:51
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisRecordProject> queryLists(HisRecordProject project) {
        if (EmptyUtil.Companion.isNullOrEmpty(project)) {
            return new ArrayList<>();
        } else {
            List<HisRecordProject> hisRecordProjects = hisRecordProjectMapper.queryLists(project);
            if (EmptyUtil.Companion.isNullOrEmpty(hisRecordProjects)) {
                return new ArrayList<>();
            } else {
                return hisRecordProjects;
            }
        }
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRefundProject>
     * @功能说明
     * @Params [hisRefundProject]
     * @Author XJP
     * @Date 2019/8/24
     * @Time 17:53
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisRefundProject> queryHisRecordProject(HisRefundProject hisRefundProject) {
        if (EmptyUtil.Companion.isNullOrEmpty(hisRefundProject)) {
            return new ArrayList<>();
        } else {
            List<HisRefundProject> refundProjectList = hisRecordProjectMapper.queryHisRecordProject(hisRefundProject);
            if (EmptyUtil.Companion.isNullOrEmpty(refundProjectList)) {
                return new ArrayList<>();
            } else {
                return refundProjectList;
            }
        }
    }

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [hisRecordProject]
     * @Author XJP
     * @Date 2019/8/24
     * @Time 20:34
     **/
    @Override
    @Transactional(readOnly = false)
    public Message updateByHisRefundProjectListBack(List<HisRecordProject> hisRecordProjectList) {
        if (EmptyUtil.Companion.isNullOrEmpty(hisRecordProjectList)) {
            return MessageUtil.createMessage(false, "修改状态失败");
        } else {
            hisRecordProjectMapper.updateByHisRefundProjectListBack(hisRecordProjectList);
            return MessageUtil.createMessage(true, "修改状态成功");
        }
    }

    /**
     * @Description
     * @Params: [number]
     * @Author: dingli
     * @Return: java.util.List<com.ahsj.hiscore.entity.HisRecordProject>
     * @Date 2019/9/7
     * @Time 12:05
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisRecordProject> selectPrint(String number) throws Exception {
        List<HisRecordProject> hisRecordProjects = hisRecordProjectMapper.selectPrint(number);
        for (HisRecordProject hisRecordProject : hisRecordProjects) {
            Translate translate = new Translate();//翻译
            translate.setTranslateId(hisRecordProject.getId());
            translate.setTranslateType(Constants.TRANSLATE_HIS_PROJECT);
            List<Translate> translates = iTranslateService.queryTranslate(translate);
            if (!EmptyUtil.Companion.isNullOrEmpty(translates)) {
                translates.stream().filter(f -> f.getTranslateChina().equals(hisRecordProject.getName())).forEach(f -> hisRecordProject.setTname(f.getTranslateKhmer()));
            }
        }
        return hisRecordProjects;
    }


    /**
     * @Description 获取项目打印信息
     * @Author muxu
     * @Date 2019/8/19
     * @Time 10:43
     * @Return com.ahsj.hiscore.entity.HisRecordProject
     * @Params [id]
     **/
    @Override
    @Transactional(readOnly = false)
    public HisRecordProject getProject(Long id) throws Exception {
        HisRecordProject hisRecordProject = hisRecordProjectMapper.getProjectById(id);
        Translate translate = new Translate();
        translate.setTranslateId(hisRecordProject.getProjectId());
        translate.setTranslateType(Constants.TRANSLATE_HIS_PROJECT);
        List<Translate> translates = iTranslateService.queryTranslate(translate);
        if (!EmptyUtil.Companion.isNullOrEmpty(translates)) {
            for (Translate t : translates) {
                if (t.getTranslateChina().equals(hisRecordProject.getName())) {
                    hisRecordProject.setTname(t.getTranslateKhmer()); //赋翻译字段                        }
                }
                if (t.getTranslateChina().equals(hisRecordProject.getUnit())) {
                    hisRecordProject.setTunit(t.getTranslateKhmer());
                }
            }
        }
        return hisRecordProject;
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRecordProject>
     * @Description 查询出当前就诊编号未付费的项目列表
     * @Params [medicalRecordId]
     * @Author zhushixiang
     * @Date 2019-09-10
     * @Time 17:29
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisRecordProject> selectByMedicalRecordIdNotIspayed(Long medicalRecordId) throws Exception {
        return hisRecordProjectMapper.selectByMedicalRecordIdNotIspayed(medicalRecordId);
    }

    /**
     * @return int
     * @Description 批量更新
     * @Params [hisRecordProjectList]
     * @Author zhushixiang
     * @Date 2019-09-11
     * @Time 10:09
     **/
    @Override
    @Transactional(readOnly = false)
    public void updateBatchForIsPay(List<HisRecordProject> hisRecordProjectList) {
        if (!EmptyUtil.Companion.isNullOrEmpty(hisRecordProjectList) && hisRecordProjectList.size() != 0) {
            hisRecordProjectMapper.setPayBatch(hisRecordProjectList);
        }
    }

    /**
     * @return core.message.Message
     * @Description 新增
     * @Params [hisRecordProject]
     * @Author zhushixiang
     * @Date 2019-09-18
     * @Time 9:40
     **/
    @Override
    @Transactional(readOnly = true)
    public Message insert(HisRecordProject hisRecordProject) throws Exception {
        hisRecordProjectMapper.insert(hisRecordProject);
        return MessageUtil.createMessage(true, "新增医嘱项目成功（New medical order success）");
    }

    /**
     * @Description 根据就诊编号查询用药明细
     * @Params: [pageBean]
     * @Author: dingli
     * @Return: core.entity.PageBean<com.ahsj.hiscore.entity.HisRecordProject>
     * @Date 2019/9/24   bignum1.multiply(bignum2)
     * @Time 14:27
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisRecordProject> selectByMedicalNumber(PageBean<HisRecordProject> pageBean) throws Exception {
        List<HisRecordProject> hisRecordProjects = hisRecordProjectMapper.selectByMedicalNumber(pageBean);
        hisRecordProjects.stream().forEach(e -> e.setTotalPrice(e.getPrice().multiply(new BigDecimal(e.getNum()))));
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisRecordProjects));
        return pageBean;
    }

    @Override
    @Transactional(readOnly = false)
    public void update(HisRecordProject hisRecordProject) {
        hisRecordProjectMapper.updateByPrimaryKeySelective(hisRecordProject);
    }
}

    