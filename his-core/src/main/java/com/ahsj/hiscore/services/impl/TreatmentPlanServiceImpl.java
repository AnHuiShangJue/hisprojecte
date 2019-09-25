package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.dao.HisRecordProjectMapper;
import com.ahsj.hiscore.entity.*;
import com.ahsj.hiscore.services.*;
import core.message.BoolMessage;
import core.message.Message;
import core.message.MessageUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.util.List;

/**
 * @program: his
 * @description:TreatmentPlanService
 * @author: chenzhicai
 * @create: 2019-07-11-17-18
 **/
@Service
public class TreatmentPlanServiceImpl implements TreatmentPlanService {


    @Autowired
    HisMedicationDetailsService hisMedicationDetailsService;

    @Autowired
    HisMedicalService hisMedicalService;

    @Autowired
    HisPharmacyDetailService hisPharmacyDetailService;

    @Autowired
    HisMedicalRecordService hisMedicalRecordService;

    @Autowired
    HisMedicalProjectService hisMedicalProjectService;

    @Autowired
    HisRecordProjectMapper hisRecordProjectMapper;

    @Autowired
    HisRecordProjectService hisRecordProjectService;


    /**
     * @return core.message.Message
     * @Description 新增或更新
     * @Params [hisMedical, detailsList, projects, recordId]
     * @Author chenzhicai
     * @Date 2019-07-11
     * @Time 17:49
     **/
    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdate(HisMedical hisMedical, List<HisMedicationDetails> detailsList, List<HisRecordProject> projects, Long recordId) throws Exception {
        Message message =null;
        if (!EmptyUtil.Companion.isNullOrEmpty(recordId)) {
            //处理门诊病历
            //判断门诊病历是否已经存在
            HisMedicalRecord hisMedicalRecord = hisMedicalRecordService.selectById(recordId);
            if (!EmptyUtil.Companion.isNullOrEmpty(hisMedicalRecord)) {
                fullHisMedicalRecord(hisMedicalRecord, hisMedical);
              message =  hisMedicalService.saveOrUpdate(hisMedical);
                if (!((BoolMessage)message).isSuccess())
                    return MessageUtil.createMessage(false, "就诊记录保存失败！请联系管理员！");
            }
            //处理用药明细
            //先根据药品id取到对应的药库药品信息
            List<HisPharmacyDetail> hisPharmacyDetailList = hisPharmacyDetailService.selectForTreatPlan(detailsList);
            //根据就诊记录id查找属于该次就诊记录id的用药明细（未付费且未出药）
            List<HisMedicationDetails> hisMedicationDetails = hisMedicationDetailsService.selectByRecordIdNotIsOutOrIsPay(recordId);

             //用药明细是非空
//            if(!EmptyUtil.Companion.isNullOrEmpty(hisMedicationDetails) && 0 != hisMedicationDetails.size()){
                //传入的用药明细是非空
                if(detailsList.size() > 0){
                    if (!EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetailList) &&0 != hisPharmacyDetailList.size()) {
                        //先进行数据填充
                        fullHisMediDetais(hisPharmacyDetailList, detailsList, recordId);
                        //在进行新增更新处理
                        startHandleMDdatas(hisMedicationDetails,detailsList);
                        if(!EmptyUtil.Companion.isNullOrEmpty(hisMedicationDetails)){
                            startHandleMDdatas(hisMedicationDetails,detailsList);
                        }
                        //保存数据
                        if (!((BoolMessage) hisMedicationDetailsService.saveOrUpdate(detailsList)).isSuccess())
                            return MessageUtil.createMessage(false, "用药明细保存失败！请联系管理员！");
                    }
                }
                //传入的用药明细是空的，表明是要进行删除
                else if(detailsList.size() == 0){
                    Long[] tmps = new Long[hisMedicationDetails.size()];
                    int i =0;
                    for (HisMedicationDetails h: hisMedicationDetails
                    ) {
                        //如果已经付款了，则不能移除
                        if(2 == h.getIsPay()){
                            tmps[i] = h.getId();
                           i++;
                        }
                    }
                    Long[] deletedIds =new Long[i+1];
                    for (int j=0;j<i;j++) {
                        deletedIds[j] = tmps[j];
                    }
                    hisMedicationDetailsService.delete(deletedIds);
                }
//            }


            //处理诊疗项目
            List<HisProject> hisProjectList = hisMedicalProjectService.selectForTreatPlan(projects);
                //查询未付费且未检查的诊疗项目
            List<HisRecordProject> hisRecordProjectList = hisRecordProjectMapper.selectByRecordIdNotIsCheckdOrIsPayed(recordId);
            if(projects.size() > 0) {
                if (!EmptyUtil.Companion.isNullOrEmpty(hisProjectList)) {
                    fullHisProjects(projects, hisProjectList, recordId);
                    //在进行新增更新处理
                    if (!EmptyUtil.Companion.isNullOrEmpty(hisMedicationDetails)) {
                        startHandleRPdatas(hisRecordProjectList, projects);
                    }
                    //批量删除未付费且未检查的诊疗项目
                    hisRecordProjectMapper.deleteByRecordIdNotIsCheckdOrIsPayed(recordId);
                    hisRecordProjectMapper.insertBatch(projects);
                }
            }else if(projects.size() == 0){
                Long[] tmps = new Long[hisRecordProjectList.size()];
                int i =0;
                for (HisRecordProject h: hisRecordProjectList
                ) {
                    //如果已经付款了，则不能移除
                    if(2 == h.getIsPayed()){
                        tmps[i] = h.getId();
                        i++;
                    }
                }
                Long[] deletedIds =new Long[i+1];
                for (int j=0;j<i;j++) {
                    deletedIds[j] = tmps[j];
                }
                hisRecordProjectService.delete(deletedIds);
            }
           String mes =  ((BoolMessage)message).getMessage();
            return MessageUtil.createMessage(true, "就诊记录"+mes);
        } else return MessageUtil.createMessage(false, "保存就诊记录失败！该就诊记录无任何关联或已被删除！请联系管理人员！");

    }

    /**
     *@Description 开始处理诊疗项目数据
     *@Params [hisRecordProjectList, hisProjectList]
     *@return void
     *@Author chenzhicai
     *@Date 2019-08-03
     *@Time 09:23
    **/
    private void startHandleRPdatas(List<HisRecordProject> hisRecordProjectList, List<HisRecordProject> hisProjectList) {
        //等于0说明是新增，则不做任何处理，如果为0，则进行合并数据处理
        if(hisRecordProjectList.size() != 0){
            for (HisRecordProject h1:
                    hisProjectList) {
                for (HisRecordProject h2:hisRecordProjectList
                ) {
                    if(h1.getName().equals(h2.getName()) && h1.getType() .equals(h2.getType()) && h1.getPinyinCode().equals(h2.getPinyinCode())){
                        if(h1.getIsPayed().equals(1)){
                            h1 = h2;
                        }
                    }
                }
            }
        }
    }

    /**
     *@Description 开始处理就诊药品数据
     *@Params [hisMedicationDetails, detailsList]
     *@return void
     *@Author chenzhicai
     *@Date 2019-08-03
     *@Time 09:23
    **/
    private void startHandleMDdatas(List<HisMedicationDetails> hisMedicationDetails, List<HisMedicationDetails> detailsList) {
        if(!EmptyUtil.Companion.isNullOrEmpty(hisMedicationDetails)) {
            //等于0说明是新增，则不做任何处理，如果不为0，则进行合并数据处理
            if (hisMedicationDetails.size() != 0) {
                for (HisMedicationDetails h1 :
                        detailsList) {
                    for (HisMedicationDetails h2 : hisMedicationDetails
                    ) {
                        //如果药品编号相同，则说明库中存在该条记录，应当为新增
                        if (h1.getDrugsNumb().equals(h2.getDrugsNumb())) {
                            //如果库中该明细已经被支付了，则不允许修改
                            //如果已经出库了，则不允许修改
                            if(h2.getIsPay().equals(1) || h2.getIsOut().equals(1)){
                                h1 = h2;
                            }
                        }
                    }
                }
            }
        }
    }


    /**
     * @return void
     * @Description 填充医疗记录信息
     * @Params [hisMedicalRecord, hisMedical]
     * @Author chenzhicai
     * @Date 2019-07-13
     * @Time 15:02
     **/
    private void fullHisMedicalRecord(HisMedicalRecord hisMedicalRecord, HisMedical hisMedical) {
        hisMedical.setPatientName(hisMedicalRecord.getPatientName());
        hisMedical.setPatientIdcard(hisMedicalRecord.getIdCard());
    }

    /**
     * @return void
     * @Description 填充药品详细信息
     * @Params [hisPharmacyDetailList, detailsList, recordId]
     * @Author chenzhicai
     * @Date 2019-07-13
     * @Time 15:03
     **/
    private void fullHisMediDetais(List<HisPharmacyDetail> hisPharmacyDetailList, @NotNull List<HisMedicationDetails> detailsList, Long recordId) {
        for (HisMedicationDetails hisMedicationDetails : detailsList) {
            for (HisPharmacyDetail hisPharmacyDetail : hisPharmacyDetailList) {
                if (hisMedicationDetails.getId().equals(hisPharmacyDetail.getId())) {
                    //药品名称
                    hisMedicationDetails.setDrugsName(hisPharmacyDetail.getDrugsName());
                    hisMedicationDetails.setDrugsNumb(hisPharmacyDetail.getDrugsNumb());
                    hisMedicationDetails.setDrugsSpec(hisPharmacyDetail.getDrugsSpec());
                    hisMedicationDetails.setSmallUnit(hisPharmacyDetail.getSmallUnit());
                    hisMedicationDetails.setDrugsAlias(hisPharmacyDetail.getDrugsAlias());
                    hisMedicationDetails.setConversionRate(hisPharmacyDetail.getConversionRate());
                    hisMedicationDetails.setLargeUnit(hisPharmacyDetail.getLargeUnit());
                    hisMedicationDetails.setLevel(hisPharmacyDetail.getLevel());
                    //这是药库药品id
                    hisMedicationDetails.setMedicationId(hisPharmacyDetail.getId());
                    //这是医疗记录id
                    hisMedicationDetails.setMedicalRecordId(recordId);
                    //设置对应药品状态
                    hisMedicationDetails.setIsBack(2);
                    hisMedicationDetails.setIsDel(2);
                    hisMedicationDetails.setIsOut(2);
                    hisMedicationDetails.setIsPay(2);
                }
            }
        }
        for (HisMedicationDetails h : detailsList
        ) {
            h.setId(null);
        }
    }

    /**
     * @return void
     * @Description 填充项目详细信息
     * @Params [hisPharmacyDetailList, detailsList, recordId]
     * @Author chenzhicai
     * @Date 2019-07-13
     * @Time 15:03
     **/
    private void fullHisProjects(List<HisRecordProject> hisRecordProjects, List<HisProject> projects, Long recordId) {
        for (HisRecordProject hisRecordProject :
                hisRecordProjects) {
            for (HisProject h :
                    projects) {
                if (h.getId().equals(hisRecordProject.getId())) {
                    hisRecordProject.setName(h.getName());
                    hisRecordProject.setDescription(h.getDescription());
                    hisRecordProject.setNumber(h.getNumber());
                    hisRecordProject.setPinyinCode(h.getPinyinCode());
                    hisRecordProject.setType(h.getType());
                    hisRecordProject.setRecordId(recordId);
                    hisRecordProject.setProjectId(h.getId());
                    hisRecordProject.setPrice(h.getPrice());
                    hisRecordProject.setUnit(h.getUnit());
                    hisRecordProject.setIsChecked(new Short("2"));
                    hisRecordProject.setIsPayed(new Short("2"));
                }
            }
        }
        for (HisRecordProject hisRecordProject :
                hisRecordProjects) {
            hisRecordProject.setId(null);
        }
    }

}

    