package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.common.utils.JsonUtils;
import com.ahsj.hiscore.controller.BaseLoginUser;
import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisMedicalTemplateMapper;
import com.ahsj.hiscore.entity.*;
import com.ahsj.hiscore.entity.HisMedicalTemplate;
import com.ahsj.hiscore.entity.TranslateModel.HisPrescriptionMedicineTranslate;
import com.ahsj.hiscore.entity.TranslateModel.TranslateModels;
import com.ahsj.hiscore.services.HisInspectionCombinationService;
import com.ahsj.hiscore.services.HisMedicalTemplateService;
import com.ahsj.hiscore.services.HisPrescriptionMedicineService;
import com.ahsj.hiscore.services.HisPrescriptionService;
import core.entity.PageBean;
import core.message.BoolMessage;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.util.ArrayList;
import java.util.List;

@Service
public class HisMedicalTemplateServicelmpl implements HisMedicalTemplateService {
    @Autowired
    HisMedicalTemplateMapper hisMedicalTemplateMapper;

    @Autowired
    HisPrescriptionService hisPrescriptionService;

    @Autowired
    HisInspectionCombinationService hisInspectionCombinationService;

    @Autowired
    HisPrescriptionMedicineService hisPrescriptionMedicineService;

    /**
     *@Description
     *@Params
     *@return
     *@Author zhushixiang
     *@Date 2019-09-14
     *@Time 9:34
    **/
    @Override
    @Transactional(readOnly = true)
    public HisMedicalTemplate selectById(Long id) throws Exception {
        return hisMedicalTemplateMapper.selectByPrimaryKey(id);
    }
    
    /**
     *@Description 添加/编辑新的病历模板
     *@Params 
     *@return 
     *@Author zhushixiang
     *@Date 2019-09-14
     *@Time 9:47
    **/
    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdateTemplate(HisMedicalTemplate hisMedicalTemplate) throws Exception {
        //如果id为空则是新增不然就是更新
        if (EmptyUtil.Companion.isNullOrEmpty(hisMedicalTemplate.getId())) {
            HisMedicalTemplate checkName = hisMedicalTemplateMapper.selectByName(hisMedicalTemplate);
            if (!EmptyUtil.Companion.isNullOrEmpty(checkName) && !EmptyUtil.Companion.isNullOrEmpty(checkName.getId())) {
                return MessageUtil.createMessage(false, "此模板名称已存在！");
            }
            //查询出此ParentId下最大的treeId
            HisMedicalTemplate maxTreeId = hisMedicalTemplateMapper.selectMaxTreeId(hisMedicalTemplate.getParentId());

            String treeId = null;
            if (maxTreeId != null && maxTreeId.getTreeId() != null) {
                treeId = maxTreeId.getTreeId();
            }
            /*此步是封装好的操作
            如果上一步if判断中 treeId获得了值那么就使其最后一位+1  如1001001变为1001002
            如果if判断中 treeId没有获得值 那么就令其ParentId后加001 如1001变为1001001
            通俗来说，此步的作用也就是给当前对象的treeId赋值
             */
            treeId = TreeHelper.getInstance().getTreeId(treeId, hisMedicalTemplate.getParentId());
            hisMedicalTemplate.setTreeId(treeId);
            List<HisMedicalTemplate> checkIsLastCode = hisMedicalTemplateMapper.selectByParentId(hisMedicalTemplate.getTreeId());
            if(EmptyUtil.Companion.isNullOrEmpty(checkIsLastCode)||checkIsLastCode.size() == 0)
                hisMedicalTemplate.setIsLastCode(1);
            else
                hisMedicalTemplate.setIsLastCode(2);

            hisMedicalTemplateMapper.insert(hisMedicalTemplate);
           /*
            log.info("--------------------药方基本新增翻译发送开始---------------------------");
            BaseLoginUser loginUser = new BaseLoginUser();
            TranslateModels translateModels = new TranslateModels();
            HisMedicalTemplateTranslate hisMedicalTemplateTranslate = new HisMedicalTemplateTranslate();
            BeanUtils.copyProperties(hisMedicalTemplate, hisMedicalTemplateTranslate);
            translateModels.setUserId(loginUser.getId());
            translateModels.setHisMedicalTemplateTranslate(hisMedicalTemplateTranslate);
            System.out.println(hisMedicalTemplateTranslate.toString()+"<--------------------");
            amqpTemplat.convertAndSend("com.ahsj.add.hisMedicalTemplate", JsonUtils.serialize(translateModels));
            log.info(JsonUtils.serialize(translateModels));
            log.info("--------------------药方基本新增翻译发送结束---------------------------");*/

            return MessageUtil.createMessage(true, "保存成功。");
        } else {
            HisMedicalTemplate checkName = hisMedicalTemplateMapper.selectByName(hisMedicalTemplate);
            if (!EmptyUtil.Companion.isNullOrEmpty(checkName) && !EmptyUtil.Companion.isNullOrEmpty(checkName.getId())) {
                return MessageUtil.createMessage(false, "此模板名称已存在！");
            }
            List<HisMedicalTemplate> checkIsLastCode = hisMedicalTemplateMapper.selectByParentId(hisMedicalTemplate.getTreeId());
            if(EmptyUtil.Companion.isNullOrEmpty(checkIsLastCode)|| checkIsLastCode.size() == 0)
                hisMedicalTemplate.setIsLastCode(1);
            else
                hisMedicalTemplate.setIsLastCode(2);

            int flag = hisMedicalTemplateMapper.updateByPrimaryKeySelective(hisMedicalTemplate);

            /*log.info("--------------------药方基本修改翻译发送开始---------------------------");
            BaseLoginUser loginUser = new BaseLoginUser();
            TranslateModels translateModels = new TranslateModels();
            HisMedicalTemplateTranslate hisMedicalTemplateTranslate = new HisMedicalTemplateTranslate();
            BeanUtils.copyProperties(hisMedicalTemplate, hisMedicalTemplateTranslate);
            translateModels.setUserId(loginUser.getId());
            translateModels.setHisMedicalTemplateTranslate(hisMedicalTemplateTranslate);
            amqpTemplat.convertAndSend("com.ahsj.update.hisMedicalTemplate", JsonUtils.serialize(translateModels));
            log.info(JsonUtils.serialize(translateModels));
            log.info("--------------------药方基本修改翻译发送结束---------------------------");*/
            return MessageUtil.createMessage(true, "更新成功。");

        }
    }

    /**
     *@Description 根据treeId搜索
     *@Params [treeId]
     *@return com.ahsj.hiscore.entity.HisMedicalTemplate
     *@Author zhushixiang
     *@Date 2019-09-14
     *@Time 10:07
    **/
    @Override
    @Transactional(readOnly = true)
    public HisMedicalTemplate selectByTreeId(String treeId) throws Exception {
        return hisMedicalTemplateMapper.selectByTreeId(treeId);
    }

    /**
     *@Description
     *@Params
     *@return
     *@Author zhushixiang
     *@Date 2019-09-14
     *@Time 10:17
    **/
    @Override
    @Transactional(readOnly = true)
    public List<TreeEntity> selectTrreCode() throws Exception {
        return hisMedicalTemplateMapper.selectTreeCode();
    }
    /**
     *@Description 根据parentId查询
     *@Params
     *@return
     *@Author zhushixiang
     *@Date 2019-09-14
     *@Time 10:23
    **/
    @Override
    @Transactional(readOnly = true)
    public List<HisMedicalTemplate> selectByParentId(String parentId) throws Exception {
        return hisMedicalTemplateMapper.selectByParentId(parentId);
    }
    
    /**
     *@Description 保存/修改病历模板明细
     *@Params 
     *@return 
     *@Author zhushixiang
     *@Date 2019-09-14
     *@Time 11:27
    **/
    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdateDetails(HisMedicalTemplate hisMedicalTemplate) throws Exception {
        //如果id为空则是新增不然就是更新
        if (EmptyUtil.Companion.isNullOrEmpty(hisMedicalTemplate.getId())) {
            HisMedicalTemplate checkName = hisMedicalTemplateMapper.selectByName(hisMedicalTemplate);
            if (!EmptyUtil.Companion.isNullOrEmpty(checkName) && !EmptyUtil.Companion.isNullOrEmpty(checkName.getId())) {
                return MessageUtil.createMessage(false, "此模板名称已存在！");
            }
            HisMedicalTemplate maxTreeId = hisMedicalTemplateMapper.selectMaxTreeId(hisMedicalTemplate.getParentId());
            String treeId = null;
            if (maxTreeId != null && maxTreeId.getTreeId() != null) {
                treeId = maxTreeId.getTreeId();
            }
            treeId = TreeHelper.getInstance().getTreeId(treeId, hisMedicalTemplate.getParentId());
            hisMedicalTemplate.setTreeId(treeId);
            if(EmptyUtil.Companion.isNullOrEmpty(hisMedicalTemplate.getPrescriptionId()))
                hisMedicalTemplate.setPrescriptionId(hisMedicalTemplate.getParentId());
            List<HisMedicalTemplate> checkIsLastCode = hisMedicalTemplateMapper.selectByParentId(hisMedicalTemplate.getTreeId());
            if(EmptyUtil.Companion.isNullOrEmpty(checkIsLastCode)||checkIsLastCode.size() == 0)
                hisMedicalTemplate.setIsLastCode(1);
            else
                hisMedicalTemplate.setIsLastCode(2);

            int flag = hisMedicalTemplateMapper.insertSelective(hisMedicalTemplate);
            return MessageUtil.createMessage(true, "保存成功。");
        } else {
            List<HisMedicalTemplate> checkIsLastCode = hisMedicalTemplateMapper.selectByParentId(hisMedicalTemplate.getTreeId());
            if(EmptyUtil.Companion.isNullOrEmpty(checkIsLastCode) ||checkIsLastCode.size() == 0)
                hisMedicalTemplate.setIsLastCode(1);
            else
                hisMedicalTemplate.setIsLastCode(2);
            int flag = hisMedicalTemplateMapper.updateByPrimaryKeySelective(hisMedicalTemplate);
            return MessageUtil.createMessage(true, "更新成功。");

        }
    }

    /**
     *@Description 门诊使用病历模板list界面
     *@Params
     *@return
     *@Author zhushixiang
     *@Date 2019-09-18
     *@Time 16:42
    **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisMedicalTemplate> listForMedical(PageBean<HisMedicalTemplate> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisMedicalTemplateMapper.listForMedical(pageBean)));
        return pageBean;
    }


    /**
     * @return core.message.Message
     * @Description 删除当前节点(级联删除其子节点以及子节点以下的数据)
     * @Params [id]
     * @Author zhushixiang
     * @Date 2019-07-30
     * @Time 14:58
     **/
    @Override
    @Transactional(readOnly = false)
    public Message delete(Long[] id) {
        for (int i = 0; i < id.length; i++) {
            recursionDelete(id[i].toString());
        }
        return MessageUtil.createMessage(true, "删除成功(successfully deleted)。");
    }

    /**
     * @return void
     * @Description 递归删除父节点下的所有子节点
     * @Params [treeId]
     * @Author zhushixiang
     * @Date 2019-07-30
     * @Time 15:28
     **/
    @Transactional(readOnly = false)
    public void recursionDelete(String id) {
        while (true) {
            List<HisMedicalTemplate> hisMedicalTemplateList = hisMedicalTemplateMapper.selectByParentId(id);
            hisMedicalTemplateMapper.deleteByTreeId(id);//删除当前节点
            hisMedicalTemplateMapper.deleteByParentId(id);//首先删除当前节点下的所有子节点
            if (hisMedicalTemplateList.size() == 0) {
                break;
            } else {
                for (int i = 0; i < hisMedicalTemplateList.size(); i++) {
                    recursionDelete(hisMedicalTemplateList.get(i).getTreeId());
                }
            }
        }
    }

    /**
     *@Description 保存当前信息为个人模板
     *@Params
     *@return
     *@Author zhushixiang
     *@Date 2019-09-20
     *@Time 12:57
    **/
    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdatePersonalTemplate(HisMedical hisMedical, List<HisMedicationDetails> detailsList, List<HisRecordProject> projects, Long recordId, Long loginUserId,String loginUserName) throws Exception {
        HisMedicalTemplate hisMedicalTemplate = new HisMedicalTemplate();
        //保存病历（主诉等）至病历模板
        hisMedicalTemplate.setNowCondition(hisMedical.getNowCondition());
        hisMedicalTemplate.setHiscondition(hisMedical.getHiscondition());
        hisMedicalTemplate.setChiefcomplaint(hisMedical.getChiefcomplaint());
        hisMedicalTemplate.setPersonalHistory(hisMedical.getPersonalHistory());
        hisMedicalTemplate.setAllergies(hisMedical.getAllergies());
        hisMedicalTemplate.setFamilyHistory(hisMedical.getFamilyHistory());
        hisMedicalTemplate.setOther(hisMedical.getOther());
        //保存当前用药明细为药方
            //所有药方放在"模板药方文件夹下"
        if(!EmptyUtil.Companion.isNullOrEmpty(detailsList)&&detailsList.size()!=0) {
            HisPrescription checkName = new HisPrescription();
            checkName.setName("模板药方");
            HisPrescription hisPrescription = hisPrescriptionService.selectByName(checkName);
            //如果没有创建新药方名称为模板药方
            if (EmptyUtil.Companion.isNullOrEmpty(hisPrescription)) {
                hisPrescription = new HisPrescription();
                hisPrescription.setName("模板药方");
                hisPrescription.setDescrption("模板药方");
                hisPrescription.setParentId("1");
                hisPrescription.setIsAvailable(2);
                BoolMessage message = (BoolMessage) hisPrescriptionService.saveOrUpdate(hisPrescription);
                if(!message.isSuccess()){
                    return MessageUtil.createMessage(false,"此病历模板名称已存在");
                }
            }
            //保存药方明细
            HisPrescription personalSave = new HisPrescription();
            personalSave.setIsAvailable(1);
            personalSave.setParentId(hisPrescription.getTreeId());
            personalSave.setName(loginUserName + "专用药方:"+hisMedical.getTemplateName());
            personalSave.setDescrption(loginUserName + "专用药方:"+hisMedical.getTemplateName());
            BoolMessage message = (BoolMessage) hisPrescriptionService.saveOrUpdate(personalSave);
            if(!message.isSuccess()){
                return MessageUtil.createMessage(false,"此模板名称已存在");
            }
            Long[] ids = new Long[detailsList.size()];
            String prescriptionId = personalSave.getTreeId();
            Integer[] nums = new Integer[detailsList.size()];
            String[] descriptions = new String[detailsList.size()];
            for (int i = 0; i <detailsList.size() ; i++) {
                ids[i]=detailsList.get(i).getId();
                nums[i]=detailsList.get(i).getCount();
                descriptions[i]=detailsList.get(i).getDescription();
            }
            BoolMessage message1 = (BoolMessage) hisPrescriptionMedicineService.saveOrUpdateForDetailsForTemplate(ids,prescriptionId,nums,descriptions);
            if(!message1.isSuccess()){
                return MessageUtil.createMessage(false,"保存药方明细失败");
            }

            //保存病历模板中药方ID 为新建药方ID
            hisMedicalTemplate.setPrescriptionId(personalSave.getId().toString());
        }else {
            hisMedicalTemplate.setPrescriptionId("0");
        }
        //保存当前项目集合为项目组合
            //保存项目组合
        if(!EmptyUtil.Companion.isNullOrEmpty(projects)&&projects.size()!=0) {
            Long[] ids = new Long[projects.size()];
            Integer[] nums = new Integer[projects.size()];
            String combinationName = loginUserName+"专用组合项目:"+hisMedical.getTemplateName();
            String combinationNumber = loginUserName+"zyzhxm";
            Integer[] hisProjectOrderNums = new Integer[projects.size()];
            Long combinationId = -1L;
            for (int i = 0; i <projects.size() ; i++) {
                ids[i]=projects.get(i).getId();
                nums[i]=projects.get(i).getNum();
                hisProjectOrderNums[i]=i+1;
            }
            Long combineId = hisInspectionCombinationService.addUpdateSelectiveReturnId(ids,nums,combinationName,combinationNumber,hisProjectOrderNums,combinationId);
            //保存病历模板中项目组合 为新建项目组合ID
            hisMedicalTemplate.setCombineId(combineId.intValue());
            //保存病历模板,设置一些必要信息

        }else {
            hisMedicalTemplate.setCombineId(0);
        }
        HisMedicalTemplate checkTemplate = new HisMedicalTemplate();
        checkTemplate.setName("病历模板");
        HisMedicalTemplate hisMedicalTemplate1 = hisMedicalTemplateMapper.selectByName(checkTemplate);
        //如果没有创建新药方名称为模板药方
        if (EmptyUtil.Companion.isNullOrEmpty(hisMedicalTemplate1)) {
            hisMedicalTemplate1 = new HisMedicalTemplate();
            hisMedicalTemplate1.setName("病历模板");
            hisMedicalTemplate1.setDescrption("病历模板");
            hisMedicalTemplate1.setParentId("1");
            hisMedicalTemplate1.setIsLastCode(2);
            BoolMessage message = (BoolMessage)saveOrUpdateTemplate(hisMedicalTemplate1);
            if(!message.isSuccess()){
                return MessageUtil.createMessage(false,"保存病历模板失败");
            }
        }


        hisMedicalTemplate.setIsLastCode(1);
        hisMedicalTemplate.setParentId(hisMedicalTemplate1.getTreeId());
        hisMedicalTemplate.setName(loginUserName + "专用模板:"+hisMedical.getTemplateName());
        hisMedicalTemplate.setDescrption(loginUserName + "专用模板:"+hisMedical.getTemplateName());
        hisMedicalTemplate.setPermissionType(1);
        hisMedicalTemplate.setTargetId(loginUserId);
        BoolMessage message = (BoolMessage) saveOrUpdateDetails(hisMedicalTemplate);
        if(!message.isSuccess()){
            return MessageUtil.createMessage(false,"保存病历模板失败");
        }
        // 提示用户已保存的位置
        return MessageUtil.createMessage(true,"保存成功");
    }
}
