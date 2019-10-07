package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.common.Constants;
import com.ahsj.hiscore.common.utils.JsonUtils;
import com.ahsj.hiscore.controller.BaseLoginUser;
import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisMedicalOrderDetailMapper;
import com.ahsj.hiscore.entity.*;
import com.ahsj.hiscore.entity.TranslateModel.HisMedicalOrderDetailTranslate;
import com.ahsj.hiscore.entity.TranslateModel.HisProjectTranslate;
import com.ahsj.hiscore.entity.TranslateModel.TranslateModels;
import com.ahsj.hiscore.feign.ITranslateService;
import com.ahsj.hiscore.services.*;
import com.sun.org.apache.bcel.internal.generic.NEW;
import core.entity.PageBean;
import core.message.BoolMessage;
import core.message.Message;
import core.message.MessageUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class HisMedicalOrderDetailServicelmpl implements HisMedicalOrderDetailService {

    private Logger log = LoggerFactory.getLogger(HisMedicalOrderDetailServicelmpl.class.getSimpleName());

    @Autowired
    HisMedicalOrderDetailMapper hisMedicalOrderDetailMapper;

    @Autowired
    HisMedicalOrderTemplateDetailService hisMedicalOrderTemplateDetailService;

    @Autowired
    HisPharmacyDetailService hisPharmacyDetailService;

    @Autowired
    HisMedicalOrderService hisMedicalOrderService;

    @Autowired
    HisMedicationDetailsService hisMedicationDetailsService;

    @Autowired
    HisMedicalRecordService hisMedicalRecordService;

    @Autowired
    HisMedicineInfoService hisMedicineInfoService;

    @Autowired
    HisProjectService hisProjectService;

    @Autowired
    HisRecordProjectService hisRecordProjectService;

    @Autowired
    HisInfusionService hisInfusionService;

    @Autowired
    HisHospitalManageService hisHospitalManageService;

    @Autowired
    ITranslateService iTranslateService;

    @Autowired
    AmqpTemplate amqpTemplat;



    @Override
    @Transactional(readOnly = true)
    public PageBean<HisMedicalOrderDetail> listDetailByNumber(PageBean<HisMedicalOrderDetail> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisMedicalOrderDetailMapper.listDetailByNumber(pageBean)));
        return pageBean;
    }

    /**
     * @return core.message.Message
     * @Description 删除
     * @Params [ids]
     * @Author zhushixiang
     * @Date 2019-07-17
     * @Time 17:31
     **/
    @Override
    @Transactional(readOnly = false)
    public Message delete(Long[] ids) throws Exception {
        HisMedicalOrderDetail hisMedicalOrderDetail = hisMedicalOrderDetailMapper.selectByPrimaryKey(ids[0]);
        List<HisMedicalOrderDetail> hisMedicalOrderDetailList = hisMedicalOrderDetailMapper.selectByNumber(hisMedicalOrderDetail.getNumber());
        if (hisMedicalOrderDetailList.size() == 1) {
            hisMedicalOrderDetailMapper.deleteByPrimaryKey(ids[0]);
            return MessageUtil.createMessage(true, "删除成功!");
        }
        for (Long id : ids) {
            HisMedicalOrderDetail forRemove = hisMedicalOrderDetailMapper.selectByPrimaryKey(id);
            for (int i = 0; i < hisMedicalOrderDetailList.size(); i++) {
                if (forRemove.getId() == hisMedicalOrderDetailList.get(i).getId()) {
                    hisMedicalOrderDetailList.remove(i);
                    break;
                }
            }
        }
        if (hisMedicalOrderDetailList.size() == 0)
            hisMedicalOrderDetailMapper.deleteByNumber(hisMedicalOrderDetail.getNumber());
        else
            arrayToLinked(hisMedicalOrderDetailList);
        return MessageUtil.createMessage(true, "删除成功!");
    }

    /**
     * @return com.ahsj.hiscore.entity.HisMedicalOrderDetail
     * @Description
     * @Params [id]
     * @Author zhushixiang
     * @Date 2019-07-17
     * @Time 17:36
     **/
    @Override
    @Transactional(readOnly = true)
    public HisMedicalOrderDetail selectById(Long id) throws Exception {
        return CodeHelper.getInstance().setCodeValue(hisMedicalOrderDetailMapper.selectByPrimaryKey(id));
    }

    /**
     * @return core.message.Message
     * @Description 新增或更新
     * @Params [hisMedicalOrderDetail]
     * @Author zhushixiang
     * @Date 2019-07-17
     * @Time 20:58
     **/
    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdate(HisMedicalOrderDetail hisMedicalOrderDetail) throws Exception {
        HisMedicalOrderDetail checkId = hisMedicalOrderDetailMapper.selectByPrimaryKey(hisMedicalOrderDetail.getId());
        List<HisMedicalOrderDetail> hisMedicalOrderDetailList = hisMedicalOrderDetailMapper.selectByNumber(hisMedicalOrderDetail.getNumber());
        //新增医嘱（要有序号）
        if (EmptyUtil.Companion.isNullOrEmpty(checkId)) {
            hisMedicalOrderDetail.setIsApproved(2);
            hisMedicalOrderDetail.setIsProofreading(2);
            hisMedicalOrderDetail.setIsStop(2);
            if (EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getOrderNum())) {
                if (EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetailList))
                    hisMedicalOrderDetail.setOrderNum(1);
                else
                    hisMedicalOrderDetail.setOrderNum(hisMedicalOrderDetailList.get(0).getOrderNum() + 1);
                //设置1即为医嘱可编辑
                hisMedicalOrderDetail.setIsFirstEdit(1);
                if(EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getTotalAmount()))
                    hisMedicalOrderDetail.setTotalAmount(new BigDecimal("1"));
                hisMedicalOrderDetailMapper.insert(hisMedicalOrderDetail);


                log.info("--------------------医嘱新增翻译发送开始---------------------------");
                BaseLoginUser loginUser = new BaseLoginUser();
                TranslateModels translateModels = new TranslateModels();
                HisMedicalOrderDetailTranslate orderDetailTranslate = new HisMedicalOrderDetailTranslate();
                BeanUtils.copyProperties(hisMedicalOrderDetail, orderDetailTranslate);
                translateModels.setUserId(loginUser.getId());
                translateModels.setHisMedicalOrderDetailTranslate(orderDetailTranslate);
                amqpTemplat.convertAndSend("com.ahsj.addHisMedicalOrderDetail", JsonUtils.serialize(translateModels));
                log.info(JsonUtils.serialize(translateModels));
                log.info("--------------------医嘱新增翻译发送结束---------------------------");



                return MessageUtil.createMessage(true, "新增成功");
            } else {
                if (hisMedicalOrderDetail.getOrderNum() > hisMedicalOrderDetailList.size())
                    return MessageUtil.createMessage(false, "输入序号大于当前医嘱总数，请重新输入");
                else {
                    hisMedicalOrderDetailList.add(hisMedicalOrderDetailList.size() - (hisMedicalOrderDetail.getOrderNum() - 1), hisMedicalOrderDetail);
                    arrayToLinked(hisMedicalOrderDetailList);

                    //如果为用药医嘱 此步骤要保存用药明细
                    //用药医嘱



                    return MessageUtil.createMessage(true, "新增成功");
                }
            }

            //更新医嘱（）
        } else {
            if (!EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getMedicalOrderType())){
                if (hisMedicalOrderDetail.getMedicalOrderType() == 2) {
                    HisPharmacyDetail hisPharmacyDetail = hisPharmacyDetailService.selectById(hisMedicalOrderDetail.getTargetId());
                    //保存开药明细
                    HisMedicalOrder hisMedicalOrder = hisMedicalOrderService.selectByNumber(hisMedicalOrderDetail.getNumber());
                    HisMedicationDetails hisMedicationDetails = new HisMedicationDetails();
                    if (!EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getCorrespondId())) {
                        hisMedicationDetails = hisMedicationDetailsService.selectById(hisMedicalOrderDetail.getCorrespondId());
                    }
                    hisMedicationDetails.setMedicationId(hisMedicalOrderDetail.getTargetId());
                    if (EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getTotalAmount())) {
                        hisMedicationDetails.setCount(1);
                        hisMedicalOrderDetail.setTotalAmount(new BigDecimal("1"));
                    } else
                        hisMedicationDetails.setCount(hisMedicalOrderDetail.getTotalAmount().intValue());
                    hisMedicationDetails.setDrugsNumb(hisPharmacyDetail.getDrugsNumb());
                    hisMedicationDetails.setDrugsName(hisPharmacyDetail.getDrugsName());
                    hisMedicationDetails.setDrugsAlias(hisPharmacyDetail.getDrugsAlias());
                    hisMedicationDetails.setDrugsSpec(hisPharmacyDetail.getDrugsSpec());
                    hisMedicationDetails.setTotalPrice(hisPharmacyDetail.getSalePrice().multiply(BigDecimal.valueOf(hisMedicationDetails.getCount())));
                    HisMedicalRecord hisMedicalRecord = hisMedicalRecordService.selectByMedicalRecordId(hisMedicalOrder.getRecordId());
                    hisMedicationDetails.setMedicalRecordId(hisMedicalRecord.getId());
                    hisMedicationDetails.setIsOut(2);
                    hisMedicationDetails.setIsPay(2);
                    hisMedicationDetails.setIsBack(2);
                    hisMedicationDetails.setIsDel(2);
                    hisMedicationDetails.setDescription(hisMedicalOrderDetail.getUsages());
                    if (EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getCorrespondId())) {
                        hisMedicationDetailsService.insert(hisMedicationDetails);
                        hisMedicalOrderDetail.setCorrespondId(hisMedicationDetails.getId());
                    } else {
                        hisMedicationDetailsService.update(hisMedicationDetails);
                    }
                } else if (hisMedicalOrderDetail.getMedicalOrderType() == 3) {
                    HisProject hisProject = hisProjectService.selectByPrimaryKey(hisMedicalOrderDetail.getTargetId());
                    //保存开项目明细
                    HisMedicalOrder hisMedicalOrder = hisMedicalOrderService.selectByNumber(hisMedicalOrderDetail.getNumber());
                    HisRecordProject hisRecordProject = new HisRecordProject();
                    if (!EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getCorrespondId())) {
                        hisRecordProject = hisRecordProjectService.selectByPrimaryKey(hisMedicalOrderDetail.getCorrespondId());
                    }
                    if (EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getTotalAmount())) {
                        hisMedicalOrderDetail.setTotalAmount(new BigDecimal("1"));
                        hisRecordProject.setNum(1);
                    } else
                        hisRecordProject.setNum(hisMedicalOrderDetail.getTotalAmount().intValue());
                    hisRecordProject.setName(hisProject.getName());
                    hisRecordProject.setDescription(hisProject.getDescription());
                    hisRecordProject.setType(hisProject.getType());
                    hisRecordProject.setPrice(hisProject.getPrice());
                    hisRecordProject.setNumber(hisProject.getNumber());
                    hisRecordProject.setPinyinCode(hisProject.getPinyinCode());
                    hisRecordProject.setUnit(hisProject.getUnit());
                    hisRecordProject.setProjectId(hisProject.getId());
                    HisMedicalRecord hisMedicalRecord = hisMedicalRecordService.selectByMedicalRecordId(hisMedicalOrder.getRecordId());
                    hisRecordProject.setRecordId(hisMedicalRecord.getId());
                    hisRecordProject.setIsChecked((short) 2);
                    hisRecordProject.setIsPayed((short) 2);
                    hisRecordProject.setIsBack(2);
                    if (EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getCorrespondId())) {
                        hisRecordProjectService.insert(hisRecordProject);
                        hisMedicalOrderDetail.setCorrespondId(hisRecordProject.getId());
                    } else {
                        hisRecordProjectService.update(hisRecordProject);
                    }
                }
        }
            hisMedicalOrderDetail.setIsFirstEdit(2);
            if(EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getTotalAmount()))
                hisMedicalOrderDetail.setTotalAmount(new BigDecimal("1"));
            hisMedicalOrderDetailMapper.updateByPrimaryKeySelective(hisMedicalOrderDetail);

            log.info("--------------------医嘱修改翻译发送开始---------------------------");
            BaseLoginUser loginUser = new BaseLoginUser();
            TranslateModels translateModels = new TranslateModels();
            HisMedicalOrderDetailTranslate orderDetailTranslate = new HisMedicalOrderDetailTranslate();
            BeanUtils.copyProperties(hisMedicalOrderDetail, orderDetailTranslate);
            translateModels.setUserId(loginUser.getId());
            translateModels.setHisMedicalOrderDetailTranslate(orderDetailTranslate);
            amqpTemplat.convertAndSend("com.ahsj.updateHisMedicalOrderDetail", JsonUtils.serialize(translateModels));
            log.info(JsonUtils.serialize(translateModels));
            log.info("--------------------医嘱修改翻译发送结束---------------------------");


            return MessageUtil.createMessage(true, "更新成功(update completed)");

        }
    }

    @Override
    public Message proofreading(Long[] ids, String recordId) throws Exception {
        List<HisMedicalOrderDetail> hisMedicalOrderDetailList = hisMedicalOrderDetailMapper.selectByNumber(recordId);
        for (int i = 0; i < hisMedicalOrderDetailList.size(); ++i) {
            hisMedicalOrderDetailList.get(i).setIsProofreading(1);
            hisMedicalOrderDetailMapper.updateByPrimaryKey(hisMedicalOrderDetailList.get(i));
        }
        return MessageUtil.createMessage(true, "校对成功!");
    }

    @Override
    public Message isApproved(Long[] ids, String recordId) throws Exception {
        List<HisMedicalOrderDetail> hisMedicalOrderDetailList = hisMedicalOrderDetailMapper.selectByNumber(recordId);
        for (int i = 0; i < hisMedicalOrderDetailList.size(); ++i) {
            hisMedicalOrderDetailList.get(i).setIsApproved(1);
            hisMedicalOrderDetailMapper.updateByPrimaryKey(hisMedicalOrderDetailList.get(i));
        }
        return MessageUtil.createMessage(true, "核准成功!");

    }


    /**
     * @return void
     * @Description 为操作序号排序
     * @Params [hisMedicalOrderDetailList]
     * @Author zhushixiang
     * @Date 2019-07-28
     * @Time 19:23
     **/
    public void arrayToLinked(List<HisMedicalOrderDetail> hisMedicalOrderDetailList) throws Exception {
        LinkedList<HisMedicalOrderDetail> hisMedicalOrderDetailLinkedList = new LinkedList<HisMedicalOrderDetail>();
        for (int i = hisMedicalOrderDetailList.size() - 1; i >= 0; i--) {
            hisMedicalOrderDetailList.get(i).setOrderNum(hisMedicalOrderDetailList.size() - i);
            hisMedicalOrderDetailLinkedList.add(hisMedicalOrderDetailList.get(i));
        }
        if (hisMedicalOrderDetailList.size() != 0)
            hisMedicalOrderDetailMapper.deleteByNumber(hisMedicalOrderDetailList.get(0).getNumber());
        if (hisMedicalOrderDetailLinkedList.size() != 0)
            hisMedicalOrderDetailMapper.insertBatch(hisMedicalOrderDetailLinkedList);

        log.info("--------------------医嘱新增翻译发送开始---------------------------");
        TranslateModels translateModels = new TranslateModels();
        BaseLoginUser loginUser = new BaseLoginUser();
        List<HisMedicalOrderDetailTranslate> infoTranslates = new ArrayList<>();
        for (HisMedicalOrderDetail medicalOrderDetail : hisMedicalOrderDetailLinkedList) {
            HisMedicalOrderDetailTranslate translate = new HisMedicalOrderDetailTranslate();
            BeanUtils.copyProperties(medicalOrderDetail, translate);
            infoTranslates.add(translate);
        }
        translateModels.setUserId(loginUser.getId());
        translateModels.setHisMedicalOrderDetailTranslates(infoTranslates);
        amqpTemplat.convertAndSend("com.ahsj.addHisMedicalOrderDetailList", JsonUtils.serialize(translateModels));
        log.info(JsonUtils.serialize(translateModels));
        log.info("--------------------医嘱新增翻译发送结束---------------------------");
    }

    /**
     * @return core.message.Message
     * @Description 为医嘱明细增加模板
     * @Params [templateNumber, number]
     * @Author zhushixiang
     * @Date 2019-07-29
     * @Time 9:35
     **/
    @Override
    public Message addTemplate(String templateNumber, String number) throws Exception {
        List<HisMedicalOrderDetail> hisMedicalOrderDetailList = hisMedicalOrderDetailMapper.selectByNumber(number);
        List<HisMedicalOrderTemplateDetail> hisMedicalOrderTemplateDetailList = hisMedicalOrderTemplateDetailService.selectByTemplateNumber(templateNumber);
        for (int i = hisMedicalOrderTemplateDetailList.size() - 1; i >= 0; i--) {
            HisMedicalOrderDetail hisMedicalOrderDetail = new HisMedicalOrderDetail();
            hisMedicalOrderDetail.setNumber(number);
            if (!EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderTemplateDetailList.get(i).getName()))
                hisMedicalOrderDetail.setName(hisMedicalOrderTemplateDetailList.get(i).getName());
            hisMedicalOrderDetail.setOrderNum(hisMedicalOrderTemplateDetailList.get(i).getOrderNum());
            hisMedicalOrderDetail.setIsApproved(2);
            hisMedicalOrderDetail.setIsProofreading(2);
            hisMedicalOrderDetail.setIsStop(2);
            if (!EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderTemplateDetailList.get(i).getTotalAmount()))
                hisMedicalOrderDetail.setTotalAmount(BigDecimal.valueOf(hisMedicalOrderTemplateDetailList.get(i).getTotalAmount()));
            if (!EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderTemplateDetailList.get(i).getIntervals()))
                hisMedicalOrderDetail.setIntervals(hisMedicalOrderTemplateDetailList.get(i).getIntervals());
            if (!EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderTemplateDetailList.get(i).getDosage()))
                hisMedicalOrderDetail.setDosage(hisMedicalOrderTemplateDetailList.get(i).getDosage());
            if (!EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderTemplateDetailList.get(i).getIsSkinTest()))
                hisMedicalOrderDetail.setIsSkinTest(hisMedicalOrderTemplateDetailList.get(i).getIsSkinTest());
            if (!EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderTemplateDetailList.get(i).getUnit()))
                hisMedicalOrderDetail.setUnit(hisMedicalOrderTemplateDetailList.get(i).getUnit());
            if (!EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderTemplateDetailList.get(i).getUsages()))
                hisMedicalOrderDetail.setUsages(hisMedicalOrderTemplateDetailList.get(i).getUsages());
            if (!EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderTemplateDetailList.get(i).getSpecification()))
                hisMedicalOrderDetail.setSpecification(hisMedicalOrderTemplateDetailList.get(i).getSpecification());
            hisMedicalOrderDetailList.add(0, hisMedicalOrderDetail);
        }
        if (hisMedicalOrderDetailList.size() == 0)
            return MessageUtil.createMessage(false, "无可添加医嘱");
        arrayToLinked(hisMedicalOrderDetailList);
        return MessageUtil.createMessage(true, "添加成功");
    }

    /**
     * @return core.message.Message
     * @Description 通过医嘱开药
     * @Params [drugsNumb, medicalOrderNumber]
     * @Author zhushixiang
     * @Date 2019-09-17
     * @Time 18:16
     **/
    @Override
    @Transactional(readOnly = false)
    public Message addMedicine(Long[] ids, String medicalOrderNumber) throws Exception {
        for (int i = 0; i <ids.length ; i++) {
            //保存医嘱内容
            HisPharmacyDetail hisPharmacyDetail = hisPharmacyDetailService.selectById(ids[i]);
            HisMedicalOrderDetail hisMedicalOrderDetail = new HisMedicalOrderDetail();
            hisMedicalOrderDetail.setNumber(medicalOrderNumber);
            hisMedicalOrderDetail.setName(hisPharmacyDetail.getDrugsName() + hisPharmacyDetail.getDrugsSpec());
            hisMedicalOrderDetail.setSpecification(hisPharmacyDetail.getDrugsSpec());
            hisMedicalOrderDetail.setIsSkinTest(2);
            hisMedicalOrderDetail.setMedicalOrderType(2);
            hisMedicalOrderDetail.setTargetId(hisPharmacyDetail.getId());
            BoolMessage message = (BoolMessage) saveOrUpdate(hisMedicalOrderDetail);
            if (!message.isSuccess())
                return MessageUtil.createMessage(false, "保存医嘱明细失败(Saving medical order failed)");
        }
        return MessageUtil.createMessage(true, "保存用药医嘱成功（Save the medication order success）");
    }

    /**
     * @return core.message.Message
     * @Description 通过医嘱开项目
     * @Params [drugsNumb, medicalOrderNumber]
     * @Author zhushixiang
     * @Date 2019-09-17
     * @Time 18:16
     **/
    @Override
    @Transactional(readOnly = false)
    public Message addProject(Long[] ids, String medicalOrderNumber) throws Exception {
        for (int i = 0; i <ids.length ; i++) {
            //保存医嘱内容
            HisProject hisProject = hisProjectService.selectByPrimaryKey(ids[i]);
            HisMedicalOrderDetail hisMedicalOrderDetail = new HisMedicalOrderDetail();
            hisMedicalOrderDetail.setNumber(medicalOrderNumber);
            hisMedicalOrderDetail.setName(hisProject.getName());
            hisMedicalOrderDetail.setIsSkinTest(2);
            //3代表医嘱为项目医嘱
            hisMedicalOrderDetail.setMedicalOrderType(3);
            hisMedicalOrderDetail.setTargetId(ids[i]);
            BoolMessage message = (BoolMessage) saveOrUpdate(hisMedicalOrderDetail);
            if (!message.isSuccess())
                return MessageUtil.createMessage(false, "保存医嘱明细失败(Saving medical order failed)");
        }
        return MessageUtil.createMessage(true, "保存项目医嘱成功（Save the project successfully）");
    }

    /**
     *@Description 停嘱
     *@Params [id]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-09-18
     *@Time 13:56
    **/
    @Override
    @Transactional(readOnly = false)
    public Message stopOrder(Long id,Long loginUser,Date stopDate) throws Exception {
        StringBuffer returnMessage = new StringBuffer();//记录返回信息
        HisMedicalOrderDetail hisMedicalOrderDetail = hisMedicalOrderDetailMapper.selectByPrimaryKey(id);
        if(hisMedicalOrderDetail.getIsStop()==1){
            return MessageUtil.createMessage(true,"此医嘱已停嘱，请勿多次停嘱(This medical plan has been suspended. Please do not stop it several times.)");
        }
        if(EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getIsInfusionList())){
            hisMedicalOrderDetail.setIsStop(1);
            hisMedicalOrderDetail.setStopDate(stopDate);
            //已经停嘱设置不可编辑
//        hisMedicalOrderDetail.setIsFirstEdit(2);
            hisMedicalOrderDetail.setStopUserId(loginUser);
            hisMedicalOrderDetailMapper.updateByPrimaryKeySelective(hisMedicalOrderDetail);
            return MessageUtil.createMessage(true, "停嘱成功(Stop success)    " + returnMessage.toString());
        }
        if(hisMedicalOrderDetail.getIsInfusionList() == 1){
            List<HisMedicalOrderDetail> hisMedicalOrderDetailList = hisMedicalOrderDetailMapper.selectByInfusionNumber(hisMedicalOrderDetail.getInfusionNumber());
            for (HisMedicalOrderDetail medicalOrderDetail : hisMedicalOrderDetailList) {
                medicalOrderDetail.setIsStop(1);
                medicalOrderDetail.setStopDate(stopDate);
                //已经停嘱设置不可编辑
//        hisMedicalOrderDetail.setIsFirstEdit(2);
                medicalOrderDetail.setStopUserId(loginUser);
                HisMedicationDetails hisMedicationDetails = hisMedicationDetailsService.selectById(medicalOrderDetail.getCorrespondId());
                if (!EmptyUtil.Companion.isNullOrEmpty(hisMedicationDetails)) {
                    //2代表未付钱
                    if (hisMedicationDetails.getIsPay() == 2) {
                        hisMedicationDetailsService.deleteById(hisMedicationDetails.getId());
                    } else if (hisMedicationDetails.getIsPay() == 1) {
                        returnMessage.append(hisMedicationDetails.getDrugsNumb()+",");
                    }
                }
                hisMedicalOrderDetailMapper.updateByPrimaryKeySelective(medicalOrderDetail);
            }
            if(!EmptyUtil.Companion.isNullOrEmpty(returnMessage)){
                returnMessage.append("药品已付费，请走出库-退药流程(The existence of drugs has been paid, please go out of the library - withdrawal process)");
            }
            return MessageUtil.createMessage(true, "停嘱成功(Stop success)    " + returnMessage.toString());
        }else {
            hisMedicalOrderDetail.setIsStop(1);
            hisMedicalOrderDetail.setStopDate(stopDate);
            //已经停嘱设置不可编辑
//        hisMedicalOrderDetail.setIsFirstEdit(2);
            hisMedicalOrderDetail.setStopUserId(loginUser);
            if (hisMedicalOrderDetail.getMedicalOrderType() == 2) {
                //停嘱时若药品未付钱直接删除此项明细,若已付请出库后再退回
                //搜索出对应的用药明细
                HisMedicationDetails hisMedicationDetails = hisMedicationDetailsService.selectById(hisMedicalOrderDetail.getCorrespondId());
                if (!EmptyUtil.Companion.isNullOrEmpty(hisMedicationDetails)) {
                    //2代表未付钱
                    if (hisMedicationDetails.getIsPay() == 2) {
                        hisMedicationDetailsService.deleteById(hisMedicationDetails.getId());
                    } else if (hisMedicationDetails.getIsPay() == 1) {
                        returnMessage.append("药品已付费，请走出库-退药流程(The existence of drugs has been paid, please go out of the library - withdrawal process)");
                    }
                }
            } else if (hisMedicalOrderDetail.getMedicalOrderType() == 3) {
                HisRecordProject hisRecordProject = hisRecordProjectService.selectByPrimaryKey(hisMedicalOrderDetail.getCorrespondId());
                if (!EmptyUtil.Companion.isNullOrEmpty(hisRecordProject)) {
                    if (hisRecordProject.getIsPayed() == 2) {
                        hisRecordProjectService.deleteById(hisRecordProject.getId());
                    } else if (hisRecordProject.getIsPayed() == 1) {
                        returnMessage.append("项目已付费，请走退项目流程（The project has been paid, please go back to the project process）");
                    }
                }
            }

            hisMedicalOrderDetailMapper.updateByPrimaryKeySelective(hisMedicalOrderDetail);
            return MessageUtil.createMessage(true, "停嘱成功(Stop success)    " + returnMessage.toString());
        }
    }

    /**
     *@Description 根据医嘱编号查看明细
     *@Params [number]
     *@return java.util.List<com.ahsj.hiscore.entity.HisMedicalOrderDetail>
     *@Author zhushixiang
     *@Date 2019-09-21
     *@Time 17:54
    **/
    @Override
    @Transactional(readOnly = true)
    public List<HisMedicalOrderDetail> selectByNumberAscAndNotStop(String number) throws Exception {
        List<HisMedicalOrderDetail> hisMedicalOrderDetails = hisMedicalOrderDetailMapper.selectByNumberAscAndNotStop(number);
        for (HisMedicalOrderDetail hisMedicalOrderDetail : hisMedicalOrderDetails) {
            Translate translate = new Translate();
            translate.setTranslateId(hisMedicalOrderDetail.getId());
            translate.setTranslateType(Constants.TRANSLATE_HIS_MEDICALORDERDETAIL);
            List<Translate> translates = iTranslateService.queryTranslate(translate);
            if (EmptyUtil.Companion.isNullOrEmpty(translates)){
                continue;
            }else {
                String kname = "";
                for (Translate translate1 : translates) {
                    if (StringUtils.equals(hisMedicalOrderDetail.getName(),translate1.getTranslateChina())){
                        kname = translate1.getTranslateKhmer();
                    }
                    if (StringUtils.equals(hisMedicalOrderDetail.getUsages(),translate1.getTranslateChina())){
                        kname = kname+"  "+translate1.getTranslateKhmer();
                        hisMedicalOrderDetail.setTtanslateName(kname);
                        hisMedicalOrderDetail.setName(hisMedicalOrderDetail.getName()+"   " +hisMedicalOrderDetail.getUsages()+"("+hisMedicalOrderDetail.getTtanslateName()+")");
                    }
                }
            }
       /*     if (hisMedicalOrderDetail.getMedicalOrderType() == 2){
                HisMedicineInfo hisMedicineInfo = hisMedicineInfoService.selectById(hisMedicalOrderDetail.getTargetId());
                Translate translate = new Translate();
                translate.setTranslateId(hisMedicalOrderDetail.getTargetId());
                translate.setTranslateType(Constants.TRANSLATE_HIS_MEDICINEINFO);
                List<Translate> translates = iTranslateService.queryTranslate(translate);
                String kname = "";
                for (Translate translate1 : translates) {
                    if (StringUtils.equals(hisMedicineInfo.getDrugsName(),translate1.getTranslateChina())){
                        kname = translate1.getTranslateKhmer();
                    }
                    if (StringUtils.equals(hisMedicineInfo.getDrugsSpec(),translate1.getTranslateChina())){
                        kname = kname+"  "+translate1.getTranslateKhmer();
                        hisMedicalOrderDetail.setTtanslateName(kname);
                        hisMedicalOrderDetail.setName(hisMedicalOrderDetail.getName() +"("+hisMedicalOrderDetail.getTtanslateName()+")");
                    }
                }
            }
            if (hisMedicalOrderDetail.getMedicalOrderType() == 3){
                HisProject hisProject = hisProjectService.selectByPrimaryKey(hisMedicalOrderDetail.getTargetId());
                Translate translate = new Translate();
                translate.setTranslateId(hisMedicalOrderDetail.getTargetId());
                translate.setTranslateType(Constants.TRANSLATE_HIS_PROJECT);
                List<Translate> translates = iTranslateService.queryTranslate(translate);
                for (Translate translate1 : translates) {
                    String kname = "";
                    if (StringUtils.equals(hisProject.getName(),translate1.getTranslateChina())){
                        hisMedicalOrderDetail.setTtanslateName(translate1.getTranslateKhmer());
                        hisMedicalOrderDetail.setName(hisMedicalOrderDetail.getName() +"("+hisMedicalOrderDetail.getTtanslateName()+")");
                    }
                }
            }*/
        }

        return hisMedicalOrderDetails;
    }

    /**
     *@Description 医嘱添加药品组合
     *@Params [ids]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-09-24
     *@Time 18:20
    **/
    @Override
    @Transactional(readOnly = false)
    public Message addCombinationMedicine(Long[] ids) throws Exception {
        //查询已选医嘱明细
        List<HisMedicalOrderDetail> alreadySelect = hisMedicalOrderDetailMapper.selectByIds(ids);
        //设置不能组合非用药医嘱
        Integer size = 0;
        Integer integer;//记录序号  判定是否正确
        integer = alreadySelect.get(0).getOrderNum();
        for (HisMedicalOrderDetail hisMedicalOrderDetail : alreadySelect) {

            //getIsFirstEdit = 1 说明用药医嘱开过后没有设置医嘱的用法用量以及开始时间
            if(EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getMedicalOrderType()))
                return MessageUtil.createMessage(false,"不能组合非用药医嘱（Cannot combine non-medical doctors）");
            if(hisMedicalOrderDetail.getMedicalOrderType()!=2)
                return MessageUtil.createMessage(false,"不能组合非用药医嘱（Cannot combine non-medical doctors）");
            if(hisMedicalOrderDetail.getIsStop() == 1  ||EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getCorrespondId())){
                return MessageUtil.createMessage(false,"不能把已停嘱药品/未设置用法用量的药品作为输液单药品(Cannot use drugs that have been stopped or have no usage and dosage as infusion drugs)");
            }
            if(hisMedicalOrderDetail.getOrderNum() == integer){
                integer++;
            }else {
                return MessageUtil.createMessage(false,"所选输液单药品不连续（Selected infusion single medicine is not continuous）");
            }
            if(!EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getIsInfusionList())) {
                if (hisMedicalOrderDetail.getIsInfusionList() == 1)
                    size++;
            }
        }
        if(size == alreadySelect.size())
            return MessageUtil.createMessage(false,"已经添加为输液单，请勿重复添加（Already added as an infusion order, do not add it repeatedly）");
        //确认所选无误后  将其添加为输液单
        String name="SYD";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String createdate = sdf.format(date);
        for (HisMedicalOrderDetail hisMedicalOrderDetail : alreadySelect) {
            //设置其类型为输液单
            hisMedicalOrderDetail.setIsInfusionList(1);
            //根据医嘱编号查询出对应医嘱
            HisMedicalOrder hisMedicalOrder = hisMedicalOrderService.selectByNumber(hisMedicalOrderDetail.getNumber());
            //根据用药医嘱绑定的药库ID targetId 查询对应药品信息
            HisPharmacyDetail hisPharmacyDetail = hisPharmacyDetailService.selectById(hisMedicalOrderDetail.getTargetId());
            //查询出目标药品
            HisInfusion hisInfusion = new HisInfusion();
            //设置输液单表数据

            //此步设置的是就诊记录编号 "HM" 命名不规范注意
            hisInfusion.setHosptalregistNumber(hisMedicalOrder.getRecordId());
            //输液单起始日期 是医嘱的开始时间还是当前时间
            hisInfusion.setStartTime(hisMedicalOrderDetail.getStartTime());
            hisInfusion.setPatientId(hisMedicalOrder.getPatientId());
            hisInfusion.setUsages(hisMedicalOrderDetail.getUsages());
            hisInfusion.setIntervals(hisMedicalOrderDetail.getIntervals());
            hisInfusion.setDrugsNumb(hisPharmacyDetail.getDrugsNumb());
            hisInfusion.setDrugname(hisPharmacyDetail.getDrugsName());
//            hisInfusion.setDosage(hisMedicalOrderDetail.getTotalAmount().toString());
//            hisInfusion.setPrice(hisMedicalOrderDetail.getTotalAmount().multiply(hisPharmacyDetail.getSalePrice()));
            hisInfusion.setNumber(name + createdate);
            //设置医嘱单编号
            //type=2 表示住院
            hisInfusion.setType(2);
            hisInfusionService.saveOrUpdate(hisInfusion);
            hisMedicalOrderDetail.setInfusionNumber(hisInfusion.getNumber());
            hisMedicalOrderDetailMapper.updateByPrimaryKeySelective(hisMedicalOrderDetail);


        }
        return MessageUtil.createMessage(true,"添加输液单成功（Adding an infusion order successfully）");
    }

    /**
     *@Description 取消临时医嘱
     *@Params [id]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-09-25
     *@Time 11:10
    **/
    @Override
    @Transactional(readOnly = false)
    public Message cancleOrder(Long id,Long loginUser) throws Exception {
        StringBuffer returnMessage = new StringBuffer();//记录返回信息
        HisMedicalOrderDetail hisMedicalOrderDetail = hisMedicalOrderDetailMapper.selectByPrimaryKey(id);
        if(hisMedicalOrderDetail.getIsStop()==1){
            return MessageUtil.createMessage(true,"此临时医嘱已取消，请勿多次取消(This temporary medical order has been cancelled, please do not cancel multiple times)");
        }
        if(EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getIsInfusionList())){
            hisMedicalOrderDetail.setIsStop(1);
            hisMedicalOrderDetail.setStopDate(new Date());
            //已经停嘱设置不可编辑
//        hisMedicalOrderDetail.setIsFirstEdit(2);
            hisMedicalOrderDetail.setStopUserId(loginUser);
            hisMedicalOrderDetailMapper.updateByPrimaryKeySelective(hisMedicalOrderDetail);
            return MessageUtil.createMessage(true, "取消成功(Cancle success)    " + returnMessage.toString());
        }
        if(hisMedicalOrderDetail.getIsInfusionList() == 1) {
            List<HisMedicalOrderDetail> hisMedicalOrderDetailList = hisMedicalOrderDetailMapper.selectByInfusionNumber(hisMedicalOrderDetail.getInfusionNumber());
            for (HisMedicalOrderDetail medicalOrderDetail : hisMedicalOrderDetailList) {
                medicalOrderDetail.setIsStop(1);
                medicalOrderDetail.setStopDate(new Date());
                //已经停嘱设置不可编辑
//        hisMedicalOrderDetail.setIsFirstEdit(2);
                medicalOrderDetail.setStopUserId(loginUser);
                HisMedicationDetails hisMedicationDetails = hisMedicationDetailsService.selectById(medicalOrderDetail.getCorrespondId());
                if (!EmptyUtil.Companion.isNullOrEmpty(hisMedicationDetails)) {
                    //2代表未付钱
                    if (hisMedicationDetails.getIsPay() == 2) {
                        hisMedicationDetailsService.deleteById(hisMedicationDetails.getId());
                    } else if (hisMedicationDetails.getIsPay() == 1) {
                        returnMessage.append(hisMedicationDetails.getDrugsNumb() + ",");
                    }
                }
                hisMedicalOrderDetailMapper.updateByPrimaryKeySelective(medicalOrderDetail);
            }
            if (!EmptyUtil.Companion.isNullOrEmpty(returnMessage)) {
                returnMessage.append("药品已付费，请走出库-退药流程(The existence of drugs has been paid, please go out of the library - withdrawal process)");
            }
            return MessageUtil.createMessage(true, "取消成功(Cancle success)    " + returnMessage.toString());
        }
        else {
            hisMedicalOrderDetail.setIsStop(1);
            hisMedicalOrderDetail.setStopDate(new Date());
            //已经停嘱设置不可编辑
//        hisMedicalOrderDetail.setIsFirstEdit(2);
//        hisMedicalOrderDetail.setStopUserId(loginUser);
            if (hisMedicalOrderDetail.getMedicalOrderType() == 2) {
                //取消临时医嘱时若药品未付钱直接删除此项明细,若已付请出库后再退回
                //搜索出对应的用药明细
                HisMedicationDetails hisMedicationDetails = hisMedicationDetailsService.selectById(hisMedicalOrderDetail.getCorrespondId());
                if (!EmptyUtil.Companion.isNullOrEmpty(hisMedicationDetails)) {
                    //2代表未付钱
                    if (hisMedicationDetails.getIsPay() == 2) {
                        hisMedicationDetailsService.deleteById(hisMedicationDetails.getId());
                    } else if (hisMedicationDetails.getIsPay() == 1 && hisMedicationDetails.getIsOut() == 2) {
                        returnMessage.append("药品已付费且未出库，请走出库-退药流程(The existence of drugs has been paid and not out, please go out of the library - withdrawal process)");
                    }
                }
            } else if (hisMedicalOrderDetail.getMedicalOrderType() == 3) {
                HisRecordProject hisRecordProject = hisRecordProjectService.selectByPrimaryKey(hisMedicalOrderDetail.getCorrespondId());
                if (!EmptyUtil.Companion.isNullOrEmpty(hisRecordProject)) {
                    if (hisRecordProject.getIsPayed() == 2) {
                        hisRecordProjectService.deleteById(hisRecordProject.getId());
                    } else if (hisRecordProject.getIsPayed() == 1 && hisRecordProject.getIsChecked() == 2) {
                        returnMessage.append("项目已付费且未检查，请走退项目流程（The project has been paid not check, please go back to the project process）");
                    }
                }
            }
            hisMedicalOrderDetailMapper.updateByPrimaryKeySelective(hisMedicalOrderDetail);
            return MessageUtil.createMessage(true, "取消成功(Cancle success)    " + returnMessage.toString());
        }
    }

    /**
     *@Description
     *@Params []
     *@return void
     *@Author zhushixiang
     *@Date 2019-09-25
     *@Time 21:37
    **/
    @Override
    @Transactional(readOnly = false)
    public void createInfusionByMedicalOrder() throws Exception{
        //扫描所有输液单医嘱  并按照输液单编号分组
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String name = "SYD";
        String createdate = "";
        List<String> infusionNumberList = hisMedicalOrderDetailMapper.groupByinfusionNumber();
        if(!EmptyUtil.Companion.isNullOrEmpty(infusionNumberList)&& infusionNumberList.size() !=0){
            for (String s : infusionNumberList) {
                List<HisInfusion> hisInfusionList = new ArrayList<>();
                boolean flag = true;
                while (flag) {
                    Date date = new Date();
                    createdate = sdf.format(date);
                    hisInfusionList =hisInfusionService.selectByNumber(name+createdate);
                    if(EmptyUtil.Companion.isNullOrEmpty(hisInfusionList)||hisInfusionList.size() == 0){
                        flag = false;
                    }
                }
                List<HisMedicalOrderDetail> hisMedicalOrderDetailList = hisMedicalOrderDetailMapper.selectByInfusionNumber(s);
                for (HisMedicalOrderDetail hisMedicalOrderDetail : hisMedicalOrderDetailList) {
                    //根据医嘱编号查询出对应医嘱
                    HisMedicalOrder hisMedicalOrder = hisMedicalOrderService.selectByNumber(hisMedicalOrderDetail.getNumber());
                    //根据用药医嘱绑定的药库ID targetId 查询对应药品信息
                    HisPharmacyDetail hisPharmacyDetail = hisPharmacyDetailService.selectById(hisMedicalOrderDetail.getTargetId());
                    //查询出目标药品
                    HisInfusion hisInfusion = new HisInfusion();
                    //设置输液单表数据

                    //此步设置的是就诊记录编号 "HM" 命名不规范注意
                    hisInfusion.setHosptalregistNumber(hisMedicalOrder.getRecordId());
                    //输液单起始日期 是医嘱的开始时间还是当前时间
                    hisInfusion.setStartTime(new Date());
                    hisInfusion.setPatientId(hisMedicalOrder.getPatientId());
                    hisInfusion.setUsages(hisMedicalOrderDetail.getUsages());
                    hisInfusion.setIntervals(hisMedicalOrderDetail.getIntervals());
                    hisInfusion.setDrugsNumb(hisPharmacyDetail.getDrugsNumb());
                    hisInfusion.setDrugname(hisPharmacyDetail.getDrugsName());
                    hisInfusion.setCreateDate(Calendar.getInstance().getTime());
                    hisInfusion.setUpdateDate(Calendar.getInstance().getTime());
                    if(!EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getCreateUserId()))
                        hisInfusion.setCreateUserId(hisMedicalOrderDetail.getCreateUserId());
                    if(!EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getUpdateUserId()))
                        hisInfusion.setUpdateUserId(hisMedicalOrderDetail.getUpdateUserId());
//            hisInfusion.setDosage(hisMedicalOrderDetail.getTotalAmount().toString());
//            hisInfusion.setPrice(hisMedicalOrderDetail.getTotalAmount().multiply(hisPharmacyDetail.getSalePrice()));
                    hisInfusion.setNumber(name + createdate);
                    //设置医嘱单编号
                    //type=2 表示住院
                    hisInfusion.setType(2);
                    hisInfusionService.saveOrUpdate(hisInfusion);
                }
            }

        }
    }

    /**
     *@Description 为所有正在住院的病人根据他们的长期医嘱每天定时生成对应的药品与项目收费明细（长期医嘱医生只需开一天的量即可）
     *@Params []
     *@return void
     *@Author zhushixiang
     *@Date 2019-10-04
     *@Time 23:45
    **/
    @Override
    @Transactional(readOnly = false)
    public void createChargeDetailsForMedicalOrder() throws Exception {
        //查询所有在住院病人且具有长期医嘱且未停嘱的用药与项目医嘱相关信息
        List<HisHospitalManage> hisHospitalManageList = hisHospitalManageService.selectInpatientAndHaveLongTermMedicalAdvice();
        //根据查询出来的生成对应的明细,非空才能执行
        if(!EmptyUtil.Companion.isNullOrEmpty(hisHospitalManageList) && hisHospitalManageList.size() != 0) {
            for (HisHospitalManage hisHospitalManage : hisHospitalManageList) {
                //生成新的消费明细时 医嘱明细表中的 correspond_id需要修改 他代表的是当前医嘱明细对应的用药明细与用项目明细表的ID

                //生成对应的用药明细
                if(hisHospitalManage.getMedicalOrderType() == 2) {
                    HisMedicationDetails hisMedicationDetails = new HisMedicationDetails();
                    hisMedicationDetails.setMedicationId(hisHospitalManage.getTargetId());
                    hisMedicationDetails.setCount(hisHospitalManage.getTotalAmount().intValue());
                    HisPharmacyDetail hisPharmacyDetail = hisPharmacyDetailService.selectById(hisHospitalManage.getTargetId());
                    hisMedicationDetails.setDrugsNumb(hisPharmacyDetail.getDrugsNumb());
                    hisMedicationDetails.setDrugsName(hisPharmacyDetail.getDrugsName());
                    hisMedicationDetails.setDrugsAlias(hisPharmacyDetail.getDrugsAlias());
                    hisMedicationDetails.setDrugsSpec(hisPharmacyDetail.getDrugsSpec());
                    hisMedicationDetails.setTotalPrice(hisPharmacyDetail.getSalePrice().multiply(BigDecimal.valueOf(hisMedicationDetails.getCount())));
                    HisMedicalRecord hisMedicalRecord = hisMedicalRecordService.selectByMedicalRecordId(hisHospitalManage.getMedicalNumber());
                    hisMedicationDetails.setMedicalRecordId(hisMedicalRecord.getId());
                    hisMedicationDetails.setIsOut(2);
                    hisMedicationDetails.setIsPay(2);
                    hisMedicationDetails.setIsBack(2);
                    hisMedicationDetails.setIsDel(2);
                    HisMedicalOrderDetail hisMedicalOrderDetail = hisMedicalOrderDetailMapper.selectByPrimaryKey(hisHospitalManage.getMedicalOrderDetailId());
                    hisMedicationDetails.setCreateDate(Calendar.getInstance().getTime());
                    hisMedicationDetails.setUpdateDate(Calendar.getInstance().getTime());
                    hisMedicationDetails.setCreateUserId(hisMedicalOrderDetail.getCreateUserId());
                    hisMedicationDetails.setUpdateUserId(hisMedicalOrderDetail.getUpdateUserId());
                    hisMedicationDetails.setDescription(hisHospitalManage.getUsages());
                    hisMedicationDetailsService.insert(hisMedicationDetails);
                    //修改医嘱明细对应的 correspond_id

                    hisMedicalOrderDetail.setCorrespondId(hisMedicationDetails.getId());
                    hisMedicalOrderDetailMapper.updateByPrimaryKeySelective(hisMedicalOrderDetail);
                }
                //生成对应的项目明细
                else if(hisHospitalManage.getMedicalOrderType() == 3){
                    HisProject hisProject = hisProjectService.selectByPrimaryKey(hisHospitalManage.getTargetId());
                    //保存开项目明细
                    HisRecordProject hisRecordProject = new HisRecordProject();
                    hisRecordProject.setNum(hisHospitalManage.getTotalAmount().intValue());
                    hisRecordProject.setName(hisProject.getName());
                    hisRecordProject.setDescription(hisProject.getDescription());
                    hisRecordProject.setType(hisProject.getType());
                    hisRecordProject.setPrice(hisProject.getPrice());
                    hisRecordProject.setNumber(hisProject.getNumber());
                    hisRecordProject.setPinyinCode(hisProject.getPinyinCode());
                    hisRecordProject.setUnit(hisProject.getUnit());
                    hisRecordProject.setProjectId(hisProject.getId());
                    HisMedicalRecord hisMedicalRecord = hisMedicalRecordService.selectByMedicalRecordId(hisHospitalManage.getMedicalNumber());
                    hisRecordProject.setRecordId(hisMedicalRecord.getId());
                    hisRecordProject.setIsChecked((short) 2);
                    hisRecordProject.setIsPayed((short) 2);
                    hisRecordProject.setIsBack(2);
                    HisMedicalOrderDetail hisMedicalOrderDetail = hisMedicalOrderDetailMapper.selectByPrimaryKey(hisHospitalManage.getMedicalOrderDetailId());
                    hisRecordProject.setCreateDate(Calendar.getInstance().getTime());
                    hisRecordProject.setUpdateDate(Calendar.getInstance().getTime());
                    hisRecordProject.setCreateUserId(hisMedicalOrderDetail.getCreateUserId());
                    hisRecordProject.setUpdateUserId(hisMedicalOrderDetail.getUpdateUserId());
                    hisRecordProjectService.insert(hisRecordProject);
                    //修改医嘱明细对应的 correspond_id

                    hisMedicalOrderDetail.setCorrespondId(hisRecordProject.getId());
                    hisMedicalOrderDetailMapper.updateByPrimaryKeySelective(hisMedicalOrderDetail);
                }
            }
        }


    }
}
