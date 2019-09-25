package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisMedicalOrderDetailMapper;
import com.ahsj.hiscore.entity.*;
import com.ahsj.hiscore.services.*;
import core.entity.PageBean;
import core.message.BoolMessage;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class HisMedicalOrderDetailServicelmpl implements HisMedicalOrderDetailService {
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
    HisProjectService hisProjectService;

    @Autowired
    HisRecordProjectService hisRecordProjectService;



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
            if (hisMedicalOrderDetail.getMedicalOrderType() == 2) {
                HisPharmacyDetail hisPharmacyDetail = hisPharmacyDetailService.selectById(hisMedicalOrderDetail.getTargetId());
                //保存开药明细
                HisMedicalOrder hisMedicalOrder = hisMedicalOrderService.selectByNumber(hisMedicalOrderDetail.getNumber());
                HisMedicationDetails hisMedicationDetails = new HisMedicationDetails();
                if(!EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getCorrespondId())){
                    hisMedicationDetails = hisMedicationDetailsService.selectById(hisMedicalOrderDetail.getCorrespondId());
                }
                hisMedicationDetails.setMedicationId(hisMedicalOrderDetail.getTargetId());
                if(EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getTotalAmount())) {
                    hisMedicationDetails.setCount(1);
                    hisMedicalOrderDetail.setTotalAmount(new BigDecimal("1"));
                }
                else
                    hisMedicationDetails.setCount(hisMedicalOrderDetail.getTotalAmount().intValue());
                hisMedicationDetails.setDrugsNumb(hisPharmacyDetail.getDrugsNumb());
                hisMedicationDetails.setDrugsName(hisPharmacyDetail.getDrugsName());
                hisMedicationDetails.setDrugsAlias(hisPharmacyDetail.getDrugsAlias());
                hisMedicationDetails.setDrugsSpec(hisPharmacyDetail.getDrugsSpec());
                hisMedicationDetails.setTotalPrice(hisPharmacyDetail.getSalePrice().multiply(BigDecimal.valueOf(hisMedicationDetails.getCount())));
                HisMedicalRecord hisMedicalRecord =hisMedicalRecordService.selectByMedicalRecordId(hisMedicalOrder.getRecordId());
                hisMedicationDetails.setMedicalRecordId(hisMedicalRecord.getId());
                hisMedicationDetails.setIsOut(2);
                hisMedicationDetails.setIsPay(2);
                hisMedicationDetails.setIsBack(2);
                hisMedicationDetails.setIsDel(2);
                hisMedicationDetails.setDescription(hisMedicalOrderDetail.getUsages());
                if(EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getCorrespondId())) {
                    hisMedicationDetailsService.insert(hisMedicationDetails);
                    hisMedicalOrderDetail.setCorrespondId(hisMedicationDetails.getId());
                }else {
                    hisMedicationDetailsService.update(hisMedicationDetails);
                }
            }else if(hisMedicalOrderDetail.getMedicalOrderType() == 3){
                HisProject hisProject = hisProjectService.selectByPrimaryKey(hisMedicalOrderDetail.getTargetId());
                //保存开项目明细
                HisMedicalOrder hisMedicalOrder = hisMedicalOrderService.selectByNumber(hisMedicalOrderDetail.getNumber());
                HisRecordProject hisRecordProject = new HisRecordProject();
                if(!EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getCorrespondId())){
                    hisRecordProject = hisRecordProjectService.selectByPrimaryKey(hisMedicalOrderDetail.getCorrespondId());
                }
                if(EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getTotalAmount())) {
                    hisMedicalOrderDetail.setTotalAmount(new BigDecimal("1"));
                    hisRecordProject.setNum(1);
                }
                else
                    hisRecordProject.setNum(hisMedicalOrderDetail.getTotalAmount().intValue());
                hisRecordProject.setName(hisProject.getName());
                hisRecordProject.setDescription(hisProject.getDescription());
                hisRecordProject.setType(hisProject.getType());
                hisRecordProject.setPrice(hisProject.getPrice());
                hisRecordProject.setNumber(hisProject.getNumber());
                hisRecordProject.setPinyinCode(hisProject.getPinyinCode());
                hisRecordProject.setUnit(hisProject.getUnit());
                hisRecordProject.setProjectId(hisProject.getId());
                HisMedicalRecord hisMedicalRecord =hisMedicalRecordService.selectByMedicalRecordId(hisMedicalOrder.getRecordId());
                hisRecordProject.setRecordId(hisMedicalRecord.getId());
                hisRecordProject.setIsChecked((short)2);
                hisRecordProject.setIsPayed((short)2);
                hisRecordProject.setIsBack(2);
                if(EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getCorrespondId())) {
                    hisRecordProjectService.insert(hisRecordProject);
                    hisMedicalOrderDetail.setCorrespondId(hisRecordProject.getId());
                }else {
                    hisRecordProjectService.update(hisRecordProject);
                }
            }
//            hisMedicalOrderDetail.setIsFirstEdit(2);
            if(EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getTotalAmount()))
                hisMedicalOrderDetail.setTotalAmount(new BigDecimal("1"));
            hisMedicalOrderDetailMapper.updateByPrimaryKeySelective(hisMedicalOrderDetail);
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
    public Message stopOrder(Long id,Long loginUser) throws Exception {
        StringBuffer returnMessage = new StringBuffer();//记录返回信息
        HisMedicalOrderDetail hisMedicalOrderDetail = hisMedicalOrderDetailMapper.selectByPrimaryKey(id);
        if(hisMedicalOrderDetail.getIsStop()==1){
            return MessageUtil.createMessage(true,"此医嘱已停嘱，请勿多次停嘱(This medical plan has been suspended. Please do not stop it several times.)");
        }
        hisMedicalOrderDetail.setIsStop(1);
        hisMedicalOrderDetail.setStopDate(new Date());
        //已经停嘱设置不可编辑
//        hisMedicalOrderDetail.setIsFirstEdit(2);
        hisMedicalOrderDetail.setStopUserId(loginUser);
        if(hisMedicalOrderDetail.getMedicalOrderType() == 2) {
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
        }else if(hisMedicalOrderDetail.getMedicalOrderType() == 3){
            HisRecordProject hisRecordProject = hisRecordProjectService.selectByPrimaryKey(hisMedicalOrderDetail.getCorrespondId());
            if(!EmptyUtil.Companion.isNullOrEmpty(hisRecordProject)){
                if(hisRecordProject.getIsPayed() == 2){
                    hisRecordProjectService.deleteById(hisRecordProject.getId());
                }else if(hisRecordProject.getIsPayed() == 1){
                    returnMessage.append("项目已付费，请走退项目流程（The project has been paid, please go back to the project process）");
                }
            }
        }

        hisMedicalOrderDetailMapper.updateByPrimaryKeySelective(hisMedicalOrderDetail);
        return MessageUtil.createMessage(true,"停嘱成功(Stop success)    "+returnMessage.toString());
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
        return hisMedicalOrderDetailMapper.selectByNumberAscAndNotStop(number);
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
    /*    for (HisMedicalOrderDetail hisMedicalOrderDetail : alreadySelect) {
            if(hisMedicalOrderDetail.getMedicalOrderType()!=2)
                return MessageUtil.createMessage(false,"不能组合非用药医嘱（Cannot combine non-medical doctors）");
        }*/
        //接受排序好后的医嘱明细
        List<HisMedicalOrderDetail> hisMedicalOrderDetailList = new ArrayList<>();
        return null;
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
        hisMedicalOrderDetail.setIsStop(1);
        hisMedicalOrderDetail.setStopDate(new Date());
        //已经停嘱设置不可编辑
//        hisMedicalOrderDetail.setIsFirstEdit(2);
        hisMedicalOrderDetail.setStopUserId(loginUser);
        if(hisMedicalOrderDetail.getMedicalOrderType() == 2) {
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
        }else if(hisMedicalOrderDetail.getMedicalOrderType() == 3){
            HisRecordProject hisRecordProject = hisRecordProjectService.selectByPrimaryKey(hisMedicalOrderDetail.getCorrespondId());
            if(!EmptyUtil.Companion.isNullOrEmpty(hisRecordProject)){
                if(hisRecordProject.getIsPayed() == 2){
                    hisRecordProjectService.deleteById(hisRecordProject.getId());
                }else if(hisRecordProject.getIsPayed() == 1 && hisRecordProject.getIsChecked() == 2){
                    returnMessage.append("项目已付费且未检查，请走退项目流程（The project has been paid not check, please go back to the project process）");
                }
            }
        }

        hisMedicalOrderDetailMapper.updateByPrimaryKeySelective(hisMedicalOrderDetail);
        return MessageUtil.createMessage(true,"取消成功(Cancle success)    "+returnMessage.toString());
    }
}
